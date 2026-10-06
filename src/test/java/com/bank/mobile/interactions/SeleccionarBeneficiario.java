package com.bank.mobile.interactions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class SeleccionarBeneficiario implements Interaction {

    private final String nombreBeneficiario;
    private final By listaBeneficiariosLocator;
    private final By elementoBeneficiarioLocator;

    public SeleccionarBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
        this.listaBeneficiariosLocator = By.id("com.bank.mobile:id/lista_beneficiarios");
        this.elementoBeneficiarioLocator = By.xpath(
            "//android.widget.TextView[@resource-id='com.bank.mobile:id/nombre_beneficiario' and @text='" + 
            nombreBeneficiario + "']"
        );
    }

    @Override
    public <T extends net.serenitybdd.screenplay.Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(listaBeneficiariosLocator, org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(listaBeneficiariosLocator))
                .forNoMoreThan(30).seconds(),
            buscarYSelccionarBeneficiario(actor)
        );
    }

    private Interaction buscarYSelccionarBeneficiario(net.serenitybdd.screenplay.Actor actor) {
        List<WebElement> beneficiarios = actor.asksFor(
            net.serenitybdd.screenplay.questions.Target.the("Lista de beneficiarios")
                .locatedBy(listaBeneficiariosLocator)
        ).resolveAll();

        List<String> nombresBeneficiarios = beneficiarios.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        if (nombresBeneficiarios.contains(nombreBeneficiario)) {
            WebElement beneficiarioEncontrado = beneficiarios.stream()
                .filter(b -> b.getText().equals(nombreBeneficiario))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Beneficiario no encontrado: " + nombreBeneficiario));

            beneficiarioEncontrado.click();
        } else {
            throw new RuntimeException(
                String.format("El beneficiario '%s' no existe en la lista. Beneficiarios disponibles: %s", 
                    nombreBeneficiario, 
                    String.join(", ", nombresBeneficiarios))
            );
        }

        return Click.on(elementoBeneficiarioLocator);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String nombreBeneficiario;

        public Builder conNombre(String nombreBeneficiario) {
            this.nombreBeneficiario = nombreBeneficiario;
            return this;
        }

        public SeleccionarBeneficiario build() {
            if (nombreBeneficiario == null || nombreBeneficiario.isBlank()) {
                throw new IllegalArgumentException("El nombre del beneficiario es obligatorio");
            }
            return new SeleccionarBeneficiario(nombreBeneficiario);
        }
    }
}