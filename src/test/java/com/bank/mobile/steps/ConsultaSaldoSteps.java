package com.bank.mobile.steps;

import com.bank.mobile.questions.SaldoVisible;
import com.bank.mobile.tasks.ConsultarSaldo;
import com.bank.mobile.tasks.Login;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Enton;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.*;

public class ConsultaSaldoSteps {

    @Managed(driver = "appium")
    private WebDriver movilDriver;

    private Actor usuario = Actor.named("Usuario Cliente");

    @Before
    public void configurarActor() {
        usuario.can(BrowseTheWeb.with(movilDriver));
    }

    @Dado("que el usuario {string} está autenticado en la aplicación móvil")
    public void queElUsuarioEstaAutenticado(String nombreUsuario) {
        usuario.attemptsTo(
                Login.conCredenciales(nombreUsuario, "Password123")
        );
    }

    @Y("el usuario tiene una cuenta activa con número {string}")
    public void elUsuarioTieneUnaCuentaActiva(String numeroCuenta) {
        System.out.println("Verificando cuenta activa: " + numeroCuenta);
    }

    @Cuando("el usuario selecciona la opción de consultar saldo")
    public void elUsuarioSeleccionaOpcionConsultarSaldo() {
        usuario.attemptsTo(
                ConsultarSaldo.enLaPantallaPrincipal()
        );
    }

    @Entonces("el sistema debe mostrar el saldo disponible de la cuenta")
    public void elSistemaDebeMostrarSaldoDisponible() {
        usuario.should(
                seeThat("El saldo es visible", SaldoVisible.enPantalla(), is(notNullValue()))
        );
    }

    @Y("el saldo mostrado debe ser mayor o igual a {string}")
    public void elSaldoMostradoDebeSerMayorIgual(String saldoMinimo) {
        BigDecimal saldoEsperado = new BigDecimal(saldoMinimo);
        usuario.should(
                seeThat("El saldo es mayor o igual al mínimo",
                        SaldoVisible.enPantalla(),
                        is(greaterThanOrEqualTo(saldoEsperado)))
        );
    }

    @Cuando("el usuario consulta el saldo de la cuenta {string}")
    public void elUsuarioConsultaSaldoCuenta(String numeroCuenta) {
        usuario.attemptsTo(
                ConsultarSaldo.paraCuenta(numeroCuenta)
        );
    }

    @Entonces("el sistema muestra el saldo de la cuenta {string}")
    public void elSistemaMuestraSaldoCuenta(String numeroCuenta) {
        usuario.should(
                seeThat("La cuenta consultada es correcta",
                        SaldoVisible.cuentaActual(),
                        equalTo(numeroCuenta))
        );
    }

    @Y("el saldo disponible es {string}")
    public void elSaldoDisponibleEs(String saldoEsperado) {
        BigDecimal saldo = new BigDecimal(saldoEsperado);
        usuario.should(
                seeThat("El saldo coincide con el esperado",
                        SaldoVisible.enPantalla(),
                        equalTo(saldo))
        );
    }

    @Cuando("el usuario intenta consultar saldo sin conexión a internet")
    public void elUsuarioIntentaConsultarSaldoSinConexion() {
        usuario.attemptsTo(
                ConsultarSaldo.enLaPantallaPrincipal()
        );
    }

    @Entonces("el sistema debe mostrar un mensaje de error de conexión")
    public void elSistemaDebeMostrarMensajeErrorConexion() {
        usuario.should(
                seeThat("El mensaje de error de conexión es visible",
                        SaldoVisible.mensajeError(),
                        containsString("conexión"))
        );
    }

    @Y("el sistema no debe mostrar ningún valor de saldo")
    public void elSistemaNoDebeMostrarSaldo() {
        usuario.should(
                seeThat("El saldo no es visible",
                        SaldoVisible.enPantalla(),
                        is(nullValue()))
        );
    }

    @Cuando("el usuario consulta el saldo con credenciales incorrectas")
    public void elUsuarioConsultaSaldoCredencialesIncorrectas() {
        usuario.attemptsTo(
                Login.conCredenciales("usuario_invalido", "password_incorrecto")
        );
    }

    @Entonces("el sistema debe mostrar un mensaje de autenticación fallida")
    public void elSistemaDebeMostrarMensajeAutenticacionFallida() {
        usuario.should(
                seeThat("El mensaje de autenticación fallida es visible",
                        SaldoVisible.mensajeError(),
                        containsString("credenciales"))
        );
    }

    @Cuando("el usuario selecciona una cuenta con estado {string}")
    public void elUsuarioSeleccionaCuentaConEstado(String estadoCuenta) {
        usuario.attemptsTo(
                ConsultarSaldo.paraCuentaConEstado(estadoCuenta)
        );
    }

    @Entonces("el sistema debe mostrar el saldo correspondiente")
    public void elSistemaDebeMostrarSaldoCorrespondiente() {
        usuario.should(
                seeThat("El saldo se muestra correctamente",
                        SaldoVisible.enPantalla(),
                        is(notNullValue()))
        );
    }

    @Y("el estado de la cuenta debe ser {string}")
    public void elEstadoDebeSer(String estadoEsperado) {
        usuario.should(
                seeThat("El estado de la cuenta es correcto",
                        SaldoVisible.estadoCuenta(),
                        equalTo(estadoEsperado))
        );
    }
}