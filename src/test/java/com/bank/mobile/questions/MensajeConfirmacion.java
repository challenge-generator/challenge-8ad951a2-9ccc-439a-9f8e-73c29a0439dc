package com.bank.mobile.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MensajeConfirmacion implements Question<String> {

    private static final Pattern PATRON_REFERENCIA = Pattern.compile(
            "Referencia:\\s*([A-Z0-9]{10,20})",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PATRON_FECHA = Pattern.compile(
            "Fecha:\\s*(\\d{2}/\\d{2}/\\d{4})"
    );

    private static final Pattern PATRON_HORA = Pattern.compile(
            "Hora:\\s*(\\d{2}:\\d{2}:\\d{2})"
    );

    private String mensajeEsperado;
    private boolean verificarReferencia;
    private boolean verificarMonto;
    private String montoEsperado;

    public MensajeConfirmacion() {
    }

    public static MensajeConfirmacion elMensaje() {
        return Instrumented.instanceOf(MensajeConfirmacion.class).newInstance();
    }

    public static MensajeConfirmacion es(String mensaje) {
        MensajeConfirmacion question = Instrumented.instanceOf(MensajeConfirmacion.class).newInstance();
        question.mensajeEsperado = mensaje;
        return question;
    }

    public MensajeConfirmacion queContiene(String texto) {
        this.mensajeEsperado = texto;
        return this;
    }

    public MensajeConfirmacion conReferencia() {
        this.verificarReferencia = true;
        return this;
    }

    public MensajeConfirmacion conMonto(String monto) {
        this.verificarMonto = true;
        this.montoEsperado = monto;
        return this;
    }

    @Override
    public String answeredBy(Actor actor) {
        WebElement contenedorMensaje = buscarContenedorConfirmacion(actor);

        if (contenedorMensaje == null) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "No se encontró el contenedor del mensaje de confirmación de transferencia"
            );
        }

        if (!contenedorMensaje.isDisplayed()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación está presente pero no es visible en pantalla"
            );
        }

        String textoMensaje = contenedorMensaje.getText();

        if (textoMensaje == null || textoMensaje.trim().isEmpty()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación está vacío"
            );
        }

        if (!textoMensaje.toLowerCase().contains("transferencia") &&
                !textoMensaje.toLowerCase().contains("envío") &&
                !textoMensaje.toLowerCase().contains("enviado")) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación no contiene texto relacionado con transferencia: " + textoMensaje
            );
        }

        if (!textoMensaje.toLowerCase().contains("éxito") &&
                !textoMensaje.toLowerCase().contains("exitoso") &&
                !textoMensaje.toLowerCase().contains("completado") &&
                !textoMensaje.toLowerCase().contains("realizado")) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje no indica que la transferencia fue exitosa: " + textoMensaje
            );
        }

        if (mensajeEsperado != null && !textoMensaje.toLowerCase().contains(mensajeEsperado.toLowerCase())) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    String.format(
                            "El mensaje de confirmación no contiene el texto esperado. Esperado: '%s', Obtenido: '%s'",
                            mensajeEsperado,
                            textoMensaje
                    )
            );
        }

        if (verificarReferencia && !contieneReferenciaValida(textoMensaje)) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación no contiene una referencia de transferencia válida"
            );
        }

        if (verificarMonto && montoEsperado != null && !textoMensaje.contains(montoEsperado)) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    String.format(
                            "El mensaje de confirmación no contiene el monto esperado. Esperado: '%s', Obtenido: '%s'",
                            montoEsperado,
                            textoMensaje
                    )
            );
        }

        return textoMensaje;
    }

    private WebElement buscarContenedorConfirmacion(Actor actor) {
        List<By> localizadores = List.of(
                By.id("mensajeConfirmacion"),
                By.id("confirmacionTransferencia"),
                By.xpath("//div[contains(@class, 'confirmacion')]"),
                By.xpath("//div[contains(@class, 'mensaje-exito')]"),
                By.xpath("//div[contains(@class, 'resultado-transferencia')]"),
                By.cssSelector("[data-testid='mensaje-confirmacion']"),
                By.xpath("//android.widget.TextView[contains(@text, 'Transferencia')]")
        );

        for (By localizador : localizadores) {
            try {
                List<WebElement> elementos = actor.getDriver().findElements(localizador);
                for (WebElement elemento : elementos) {
                    if (elemento.isDisplayed()) {
                        return elemento;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }

        return null;
    }

    private boolean contieneReferenciaValida(String texto) {
        Matcher matcher = PATRON_REFERENCIA.matcher(texto);
        return matcher.find();
    }

    private boolean contieneFechaValida(String texto) {
        Matcher matcher = PATRON_FECHA.matcher(texto);
        return matcher.find();
    }

    private boolean contieneHoraValida(String texto) {
        Matcher matcher = PATRON_HORA.matcher(texto);
        return matcher.find();
    }
}