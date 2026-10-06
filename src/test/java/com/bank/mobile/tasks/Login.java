package com.bank.mobile.tasks;

import net.serenitybdd.core.steps.UIInteractions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;

public class Login extends UIInteractions implements Task {
    
    private final String username;
    private final String password;
    private final By usernameField = By.id("com.bank.mobile:id/username_input");
    private final By passwordField = By.id("com.bank.mobile:id/password_input");
    private final By loginButton = By.id("com.bank.mobile:id/btn_login");
    private final By loadingIndicator = By.id("com.bank.mobile:id/loading_progress");
    private final By errorMessage = By.id("com.bank.mobile:id/error_message");
    private final By homeScreen = By.id("com.bank.mobile:id/home_container");
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(30);
    
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public static Login withCredentials(String username, String password) {
        return new Login(username, password);
    }
    
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitForPageToLoad(),
            Enter.theValue(username).into(usernameField),
            Enter.theValue(password).into(passwordField),
            Click.on(loginButton),
            WaitForLoadingToFinish()
        );
        
        actor.should(
            seeThat("La pantalla principal debe ser visible",
                Text.of(homeScreen).as(actor),
                containsString("Bienvenido")
            )
        );
    }
    
    private WaitForPageToLoad WaitForPageToLoad() {
        return new WaitForPageToLoad();
    }
    
    private WaitForLoadingToFinish WaitForLoadingToFinish() {
        return new WaitForLoadingToFinish();
    }
    
    public static class WaitForPageToLoad extends UIInteractions {
        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT.toSeconds());
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("com.bank.mobile:id/splash_loading")
            ));
        }
    }
    
    public static class WaitForLoadingToFinish extends UIInteractions {
        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriverWait wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT.toSeconds());
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIndicator));
            } catch (Exception e) {
                System.out.println("Loading indicator no encontrado, continuando...");
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(homeScreen));
        }
    }
}