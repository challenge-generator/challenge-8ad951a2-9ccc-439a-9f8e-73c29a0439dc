package com.bank.mobile.interactions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class IngresarMonto implements Interaction {

    private static final By CAMPO_MONTO_LOCATOR = By.id("com.bank.mobile:id/campo_monto");
    private static final By TECLADO_NUMERICO_LOCATOR = By.id("com.bank.mobile:id/teclado_numerico");
    private static final Map<String, By> BOTONES_TECLADO = new HashMap<>();

    static {
        BOTONES_TECLADO.put("0", By.id("com.bank.mobile:id/btn_0"));
        BOTONES_TECLADO.put("1", By.id("com.bank.mobile:id/btn_1"));
        BOTONES_TECLADO.put("2", By.id("com.bank.mobile:id/btn_2"));
        BOTONES_TECLADO.put("3", By.id("com.bank.mobile:id/btn_3"));
        BOTONES_TECLADO.put("4", By.id("com.bank.mobile:id/btn_4"));
        BOTONES_TECLADO.put("5", By.id("com.bank.mobile:id/btn_5"));
        BOTONES_TECLADO.put("6", By.id("com.bank.mobile:id/btn_6"));
        BOTONES_TECLADO.put("7", By.id("com.bank.mobile:id/btn_7"));
        BOTONES_TECLADO.put("8", By.id("com.bank.mobile:id/btn_8"));
        BOTONES_TECLADO.put("9", By.id("com.bank.mobile:id/btn_9"));
        BOTONES_TECLADO.put(".", By.id("com.bank.mobile:id/btn_punto"));
        BOTONES_TECLADO.put("BORRAR", By.id("com.bank.mobile:id/btn_borrar"));
    }

    private final BigDecimal monto;
    private final boolean usarTecladoVirtual;
    private final DecimalFormat formatoMonto;

    public IngresarMonto(BigDecimal monto) {
        this(monto, true);
    }

    public IngresarMonto(BigDecimal monto, boolean usarTecladoVirtual) {
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser null");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
        this.monto = monto;
        this.usarTecladoVirtual = usarTecladoVirtual;
        this.formatoMonto = new DecimalFormat("#0.00");
    }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        if (usarTecladoVirtual) {
            actor.attemptsTo(ingresarConTecladoVirtual(actor));
        } else {
            actor.attemptsTo(ingresarDirectamente(actor));
        }
    }

    private Interaction ingresarConTecladoVirtual(net.serenitybdd.screenplay.Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(CAMPO_MONTO_LOCATOR, ExpectedConditions.elementToBeClickable(CAMPO_MONTO_LOCATOR))
                .forNoMoreThan(15).seconds(),
            Click.on(CAMPO_MONTO_LOCATOR),
            WaitUntil.the(TECLADO_NUMERICO_LOCATOR, ExpectedConditions.visibilityOfElementLocated(TECLADO_NUMERICO_LOCATOR))
                .forNoMoreThan(10).seconds()
        );

        String montoFormateado = formatoMonto.format(monto);
        String[] digitos = montoFormateado.replace(".", "").split("");

        for (String digito : digitos) {
            if (BOTONES_TECLADO.containsKey(digito)) {
                actor.attemptsTo(Click.on(BOTONES_TECLADO.get(digito)));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return Click.on(By.id("com.bank.mobile:id/btn_confirmar_monto"));
    }

    private Interaction ingresarDirectamente(net.serenitybdd.screenplay.Actor actor) {
        String montoString = formatoMonto.format(monto);
        return Enter.theValue(montoString).into(CAMPO_MONTO_LOCATOR).thenPress(Keys.ENTER);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private BigDecimal monto;
        private boolean usarTecladoVirtual = true;

        public Builder conMonto(BigDecimal monto) {
            this.monto = monto;
            return this;
        }

        public Builder conMonto(String montoString) {
            try {
                this.monto = new BigDecimal(montoString.replace(",", "."));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Formato de monto inválido: " + montoString, e);
            }
            return this;
        }

        public Builder usarTecladoVirtual(boolean usar) {
            this.usarTecladoVirtual = usar;
            return this;
        }

        public IngresarMonto build() {
            if (monto == null) {
                throw new IllegalArgumentException("El monto es obligatorio para la transferencia");
            }
            return new IngresarMonto(monto, usarTecladoVirtual);
        }
    }
}