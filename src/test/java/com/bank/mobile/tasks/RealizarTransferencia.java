package com.bank.mobile.tasks;

import com.bank.mobile.interactions.IngresarMonto;
import com.bank.mobile.interactions.SeleccionarBeneficiario;
import com.bank.mobile.models.Transferencia;
import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;

public class RealizarTransferencia extends UIInteractions implements Task {
    
    private final Transferencia transferencia;
    private final By botonTransferencia = By.id("com.bank.mobile:id/btn_nueva_transferencia");
    private final By botonSeleccionarBeneficiario = By.id("com.bank.mobile:id/btn_seleccionar_beneficiario");
    private final By campoMonto = By.id("com.bank.mobile:id/input_monto");
    private final By campoConcepto = By.id("com.bank.mobile:id/input_concepto");
    private final By botonConfirmar = By.id("com.bank.mobile:id/btn_confirmar_transferencia");
    private final By botonAceptarConfirmacion = By.id("com.bank.mobile:id/btn_aceptar_confirmacion");
    private final By pantallaConfirmacion = By.id("com.bank.mobile:id/pantalla_confirmacion");
    private final By textoReferencia = By.id("com.bank.mobile:id/texto_referencia");
    private final By mensajeExito = By.id("com.bank.mobile:id/mensaje_exito");
    private final By botonRechazar = By.id("com.bank.mobile:id/btn_rechazar");
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(30);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    
    public RealizarTransferencia(Transferencia transferencia) {
        this.transferencia = transferencia;
    }
    
    public static RealizarTransferencia withData(Transferencia transferencia) {
        return new RealizarTransferencia(transferencia);
    }
    
    public static Transferencia buildTransferencia(String cuentaOrigen, String cuentaDestino, 
                                                     String nombreBeneficiario, String tipoDoc, 
                                                     String numDoc, BigDecimal monto, String moneda, 
                                                     String concepto) {
        return Transferencia.builder()
            .numeroCuentaOrigen(cuentaOrigen)
            .numeroCuentaDestino(cuentaDestino)
            .nombreBeneficiario(nombreBeneficiario)
            .tipoDocumentoBeneficiario(tipoDoc)
            .numeroDocumentoBeneficiario(numDoc)
            .monto(monto)
            .moneda(moneda)
            .concepto(concepto)
            .fechaHora(LocalDateTime.now())
            .referencia(generarReferencia())
            .canal("MOVIL")
            .build();
    }
    
    private static String generarReferencia() {
        return "TRF-" + LocalDateTime.now().format(DATE_FORMATTER) + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(botonTransferencia),
            WaitForElementVisible.by(botonSeleccionarBeneficiario),
            SeleccionarBeneficiario.fromList(transferencia.getNumeroCuentaDestino()),
            IngresarMonto.of(transferencia.getMonto()),
            Enter.theValue(transferencia.getConcepto()).into(campoConcepto),
            Click.on(botonConfirmar),
            WaitForConfirmationScreen(),
            VerifyTransferDetails(actor),
            Click.on(botonAceptarConfirmacion),
            WaitForSuccessMessage()
        );
        
        actor.should(
            seeThat("El mensaje de exito debe aparecer",
                Text.of(mensajeExito).as(actor),
                containsString("Transferencia exitosa")
            )
        );
    }
    
    private VerifyTransferDetails VerifyTransferDetails(Actor actor) {
        return new VerifyTransferDetails(actor, transferencia);
    }
    
    private WaitForConfirmationScreen WaitForConfirmationScreen() {
        return new WaitForConfirmationScreen();
    }
    
    private WaitForSuccessMessage WaitForSuccessMessage() {
        return new WaitForSuccessMessage();
    }
    
    public static class WaitForConfirmationScreen extends UIInteractions {
        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT.toSeconds());
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.bank.mobile:id/pantalla_confirmacion")
            ));
        }
    }
    
    public static class WaitForSuccessMessage extends UIInteractions {
        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT.toSeconds());
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.bank.mobile:id/mensaje_exito")
            ));
        }
    }
    
    public static class VerifyTransferDetails extends UIInteractions {
        private final Actor actor;
        private final Transferencia transferencia;
        
        public VerifyTransferDetails(Actor actor, Transferencia transferencia) {
            this.actor = actor;
            this.transferencia = transferencia;
        }
        
        @Override
        public <T extends Actor> void performAs(T actor) {
            By montoConfirmacion = By.id("com.bank.mobile:id/monto_confirmacion");
            By beneficiarioConfirmacion = By.id("com.bank.mobile:id/beneficiario_confirmacion");
            By cuentaDestinoConfirmacion = By.id("com.bank.mobile:id/cuenta_destino_confirmacion");
            
            WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT.toSeconds());
            wait.until(ExpectedConditions.visibilityOfElementLocated(montoConfirmacion));
            
            String montoMostrado = Text.of(montoConfirmacion).answeredBy(actor).toString();
            String beneficiarioMostrado = Text.of(beneficiarioConfirmacion).answeredBy(actor).toString();
            String cuentaMostrada = Text.of(cuentaDestinoConfirmacion).answeredBy(actor).toString();
            
            System.out.println("Monto mostrado: " + montoMostrado);
            System.out.println("Beneficiario: " + beneficiarioMostrado);
            System.out.println("Cuenta destino: " + cuentaMostrada);
        }
    }
}