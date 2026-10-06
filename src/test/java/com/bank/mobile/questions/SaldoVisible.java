package com.bank.mobile.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.core.steps.UIInteractionBuilder;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SaldoVisible implements Question<BigDecimal> {

    private static final Pattern PATRON_SALDO = Pattern.compile(
            "[$€£]\\s*([\\d,]+\\.\\d{2})"
    );

    private BigDecimal saldoEsperado;

    public SaldoVisible() {
    }

    public static SaldoVisible elSaldo() {
        return Instrumented.instanceOf(SaldoVisible.class).newInstance();
    }

    public static SaldoVisible es(BigDecimal esperado) {
        SaldoVisible question = Instrumented.instanceOf(SaldoVisible.class).newInstance();
        question.saldoEsperado = esperado;
        return question;
    }

    public SaldoVisible queEs(BigDecimal esperado) {
        this.saldoEsperado = esperado;
        return this;
    }

    @Override
    public BigDecimal answeredBy(Actor actor) {
        WebElement elementoSaldo = UIInteractionBuilder.where(
                By.id("lblSaldo")
        ).resolveFor(actor);

        if (elementoSaldo == null) {
            elementoSaldo = UIInteractionBuilder.where(
                    By.xpath("//div[contains(@class, 'saldo-cuenta')]")
            ).resolveFor(actor);
        }

        if (elementoSaldo == null) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "No se encontró el elemento que muestra el saldo en la interfaz"
            );
        }

        String textoSaldo = elementoSaldo.getText();

        if (textoSaldo == null || textoSaldo.trim().isEmpty()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El elemento de saldo está visible pero no contiene texto"
            );
        }

        BigDecimal saldoObtenido = extraerMonto(textoSaldo);

        if (saldoObtenido == null) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "No se pudo extraer el valor numérico del saldo desde el texto: " + textoSaldo
            );
        }

        if (saldoEsperado != null && saldoObtenido.compareTo(saldoEsperado) != 0) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    String.format(
                            "El saldo mostrado (%s) no coincide con el esperado (%s)",
                            saldoObtenido.toString(),
                            saldoEsperado.toString()
                    )
            );
        }

        return saldoObtenido;
    }

    private BigDecimal extraerMonto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }

        String textoLimpio = texto.replaceAll("[^\\d.,]", "");

        if (textoLimpio.contains(",") && textoLimpio.contains(".")) {
            if (textoLimpio.lastIndexOf(",") > textoLimpio.lastIndexOf(".")) {
                textoLimpio = textoLimpio.replace(".", "").replace(",", ".");
            } else {
                textoLimpio = textoLimpio.replace(",", "");
            }
        } else if (textoLimpio.contains(",")) {
            textoLimpio = textoLimpio.replace(",", ".");
        }

        try {
            return new BigDecimal(textoLimpio);
        } catch (NumberFormatException e) {
            Matcher matcher = PATRON_SALDO.matcher(texto);
            if (matcher.find()) {
                String montoStr = matcher.group(1).replace(",", "");
                return new BigDecimal(montoStr);
            }
            return null;
        }
    }
}