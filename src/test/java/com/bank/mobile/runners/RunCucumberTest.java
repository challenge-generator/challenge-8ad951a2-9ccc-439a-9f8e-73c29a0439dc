package com.bank.mobile.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.bank.mobile.steps"},
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "junit:target/cucumber-reports/cucumber-junit-report.xml"
    },
    tags = "@smoke or @regression",
    dryRun = false,
    strict = true,
    monochrome = true
)
public class RunCucumberTest {
    
    private static final String FEATURES_PATH = "src/test/resources/features";
    private static final String GLUE_PATH = "com.bank.mobile.steps";
    
    public static final String SMOKE_TAG = "@smoke";
    public static final String REGRESSION_TAG = "@regression";
    public static final String TRANSFERENCIA_TAG = "@transferencia";
    public static final String CONSULTA_TAG = "@consulta";
    
    public static void main(String[] args) {
        System.out.println("Ejecutando pruebas de automatizacion movil para la aplicacion de banca");
        System.out.println("Features path: " + FEATURES_PATH);
        System.out.println("Glue path: " + GLUE_PATH);
        System.out.println("Tags configurados: " + SMOKE_TAG + ", " + REGRESSION_TAG);
    }
}