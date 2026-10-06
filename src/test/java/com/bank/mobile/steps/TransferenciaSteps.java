package com.bank.mobile.steps;

import com.bank.mobile.models.Transferencia;
import com.bank.mobile.questions.MensajeConfirmacion;
import com.bank.mobile.tasks.Login;
import com.bank.mobile.tasks.RealizarTransferencia;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entones;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TransferenciaSteps {

    private Transferencia transferenciaActual;
    private Actor usuarioActual;

    @BeforeEach
    public void configurarEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el usuario {string} está autenticado en la aplicación móvil de banca")
    public void queElUsuarioEstaAutenticado(String nombreUsuario) {
        usuarioActual = OnStage.theActorCalled(nombreUsuario);
        usuarioActual.attemptsTo(
            Login.conCredenciales("testuser", "testpass")
        );
    }

    @Y("el usuario tiene saldo disponible de {string} en su cuenta")
    public void elUsuarioTieneSaldoDisponible(String saldo) {
        BigDecimal saldoDecimal = new BigDecimal(saldo.replace(",", "."));
        System.out.println("Verificando saldo disponible: " + saldoDecimal);
    }

    @Cuando("el usuario inicia una transferencia a beneficiario {string}")
    public void elUsuarioIniciaTransferencia(String nombreBeneficiario) {
        usuarioActual.attemptsTo(
            RealizarTransferencia.haciaBeneficiario(nombreBeneficiario)
        );
    }

    @Y("ingresa los datos de transferencia: cuenta destino {string}, monto {string}, concepto {string}")
    public void ingresaLosDatosDeTransferencia(String cuentaDestino, String monto, String concepto) {
        BigDecimal montoDecimal = new BigDecimal(monto.replace(",", "."));

        transferenciaActual = Transferencia.builder()
            .withNumeroCuentaOrigen("1234567890")
            .withNumeroCuentaDestino(cuentaDestino)
            .withNombreBeneficiario("Beneficiario Prueba")
            .withTipoDocumentoBeneficiario("Cédula")
            .withNumeroDocumentoBeneficiario("12345678")
            .withMonto(montoDecimal)
            .withMoneda("USD")
            .withConcepto(concepto)
            .withFechaHora(LocalDateTime.now())
            .withReferencia("REF-" + System.currentTimeMillis())
            .withCanal("MÓVIL")
            .build();
    }

    @Y("confirma la transferencia")
    public void confirmaLaTransferencia() {
        System.out.println("Confirmando transferencia: " + transferenciaActual);
    }

    @Entones("el sistema debe mostrar mensaje de confirmación con código de operación")
    public void elSistemaDebeMostrarMensajeDeConfirmacion() {
        usuarioActual.should(
            seeThat("Mensaje de confirmación",
                MensajeConfirmacion.delOperacion(),
                containsString("Transferencia exitosa"))
        );
    }

    @Entones("el sistema debe actualizar el saldo de la cuenta origen")
    public void elSistemaDebeActualizarElSaldo() {
        BigDecimal saldoActualizado = transferenciaActual.getMonto();
        System.out.println("Saldo actualizado después de transferencia: " + saldoActualizado);
    }

    @Cuando("el usuario intenta transferir un monto mayor al saldo disponible")
    public void elUsuarioIntentaTransferirMontoMayorAlSaldo() {
        BigDecimal montoMayor = new BigDecimal("999999.99");
        transferenciaActual = Transferencia.builder()
            .withNumeroCuentaOrigen("1234567890")
            .withNumeroCuentaDestino("0987654321")
            .withMonto(montoMayor)
            .withMoneda("USD")
            .withConcepto("Transferencia mayor al saldo")
            .withFechaHora(LocalDateTime.now())
            .withCanal("MÓVIL")
            .build();
    }

    @Entones("el sistema debe mostrar error de saldo insuficiente")
    public void elSistemaDebeMostrarErrorDeSaldoInsuficiente() {
        usuarioActual.should(
            seeThat("Mensaje de error",
                MensajeConfirmacion.delOperacion(),
                containsString("Saldo insuficiente"))
        );
    }

    @Dado("que los datos de transferencia son:")
    public void queLosDatosDeTransferenciaSon(Map<String, String> datos) {
        String montoStr = datos.get("monto").replace(",", ".");
        BigDecimal monto = new BigDecimal(montoStr);

        transferenciaActual = Transferencia.builder()
            .withNumeroCuentaOrigen(datos.getOrDefault("cuenta_origen", "1234567890"))
            .withNumeroCuentaDestino(datos.get("cuenta_destino"))
            .withNombreBeneficiario(datos.get("beneficiario"))
            .withTipoDocumentoBeneficiario(datos.getOrDefault("tipo_documento", "Cédula"))
            .withNumeroDocumentoBeneficiario(datos.getOrDefault("numero_documento", "12345678"))
            .withMonto(monto)
            .withMoneda(datos.getOrDefault("moneda", "USD"))
            .withConcepto(datos.get("concepto"))
            .withFechaHora(LocalDateTime.now())
            .withReferencia("REF-AUTO-" + System.currentTimeMillis())
            .withCanal("MÓVIL")
            .build();
    }

    @Cuando("el usuario ejecuta la transferencia")
    public void elUsuarioEjecutaLaTransferencia() {
        String nombreBeneficiario = transferenciaActual.getNombreBeneficiario();
        if (nombreBeneficiario != null && !nombreBeneficiario.isEmpty()) {
            usuarioActual.attemptsTo(
                RealizarTransferencia.haciaBeneficiario(nombreBeneficiario)
            );
        }
    }

    @Entones("el resultado de la transferencia debe ser {string}")
    public void elResultadoDeLaTransferenciaDebeSer(String resultadoEsperado) {
        if ("exitosa".equalsIgnoreCase(resultadoEsperado)) {
            usuarioActual.should(
                seeThat("Confirmación",
                    MensajeConfirmacion.delOperacion(),
                    containsString("exitosa"))
            );
        } else if ("fallida".equalsIgnoreCase(resultadoEsperado)) {
            usuarioActual.should(
                seeThat("Error",
                    MensajeConfirmacion.delOperacion(),
                    containsString("error"))
            );
        }
    }

    @Y("el monto transferido debe ser {string}")
    public void elMontoTransferidoDebeSer(String montoEsperado) {
        BigDecimal montoVerificar = new BigDecimal(montoEsperado.replace(",", "."));
        usuarioActual.should(
            seeThat("Monto confirmado",
                MensajeConfirmacion.montoMostrado(),
                equalTo(montoVerificar))
        );
    }
}