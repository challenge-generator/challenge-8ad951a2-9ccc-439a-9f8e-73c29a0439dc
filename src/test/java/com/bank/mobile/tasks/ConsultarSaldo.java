package com.bank.mobile.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.time.Duration;

public class ConsultarSaldo implements Task {

    private final String numeroCuenta;
    private final String nip;

    public ConsultarSaldo(String numeroCuenta, String nip) {
        this.numeroCuenta = numeroCuenta;
        this.nip = nip;
    }

    public static ConsultarSaldo conDatos(String numeroCuenta, String nip) {
        return Instrumented.instanceOf(ConsultarSaldo.class)
                .withProperties(numeroCuenta, nip);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on("#btnConsultarSaldo"),
                WaitUntil.the(By.id("inputNumeroCuenta"), ExpectedConditions.visibilityOfElementLocated())
                        .forNoMoreThan(10).seconds(),
                Enter.theValue(numeroCuenta).into(By.id("inputNumeroCuenta")),
                Enter.theValue(nip).into(By.id("inputNip")),
                Click.on(By.id("btnConfirmarConsulta")),
                WaitUntil.the(By.id("panelSaldo"), ExpectedConditions.visibilityOfElementLocated())
                        .forNoMoreThan(15).seconds()
        );

        WebElement saldoPanel = actor.asksFor(
                org.openqa.selenium.By.id("panelSaldo")
        );

        if (saldoPanel == null || !saldoPanel.isDisplayed()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El panel de saldo no se visualizó después de la consulta"
            );
        }

        String textoSaldo = saldoPanel.getText();
        if (textoSaldo == null || textoSaldo.trim().isEmpty()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El saldo shown está vacío o no es legible"
            );
        }

        if (!textoSaldo.contains("$") && !textoSaldo.contains("USD") && !textoSaldo.contains("MXN")) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El formato del saldo no corresponde a una cantidad monetaria válida"
            );
        }
    }

    public static class WaitUntil {
        private final By locator;
        private final org.openqa.selenium.support.ui.ExpectedCondition<WebElement> condition;
        private Duration timeout = Duration.ofSeconds(30);

        private WaitUntil(By locator, org.openqa.selenium.support.ui.ExpectedCondition<WebElement> condition) {
            this.locator = locator;
            this.condition = condition;
        }

        public static WaitUntil the(By locator, org.openqa.selenium.support.ui.ExpectedCondition<WebElement> condition) {
            return new WaitUntil(locator, condition);
        }

        public WaitUntil forNoMoreThan(int seconds) {
            this.timeout = Duration.ofSeconds(seconds);
            return this;
        }

        public Task forNoMoreThan(long seconds) {
            this.timeout = Duration.ofSeconds(seconds);
            return buildTask();
        }

        private Task buildTask() {
            return new Task() {
                @Override
                public <T extends Actor> void performAs(T actor) {
                    org.openqa.selenium.WebDriver driver = net.serenitybdd.core.pages.WebElementFacadeImpl.class
                            .cast(actor).getDriver();
                    org.openqa.selenium.support.ui.WebDriverWait wait =
                            new org.openqa.selenium.support.ui.WebDriverWait(driver, timeout.getSeconds());
                    wait.until(condition);
                }
            };
        }
    }
}