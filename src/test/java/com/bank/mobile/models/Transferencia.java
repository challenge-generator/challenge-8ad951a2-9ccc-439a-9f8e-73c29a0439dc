package com.bank.mobile.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Modelo de datos que representa una transferencia bancaria realizada desde la aplicación móvil.
 * Contiene la información necesaria para identificar el beneficiario, el monto y el concepto
 * de la transferencia, así como metadatos para auditoría.
 */
public class Transferencia {
    private final String numeroCuentaOrigen;
    private final String numeroCuentaDestino;
    private final String nombreBeneficiario;
    private final String tipoDocumentoBeneficiario;
    private final String numeroDocumentoBeneficiario;
    private final BigDecimal monto;
    private final String moneda;
    private final String concepto;
    private final LocalDateTime fechaHora;
    private final String referencia;
    private final String canal;

    private Transferencia(Builder builder) {
        this.numeroCuentaOrigen = builder.numeroCuentaOrigen;
        this.numeroCuentaDestino = builder.numeroCuentaDestino;
        this.nombreBeneficiario = builder.nombreBeneficiario;
        this.tipoDocumentoBeneficiario = builder.tipoDocumentoBeneficiario;
        this.numeroDocumentoBeneficiario = builder.numeroDocumentoBeneficiario;
        this.monto = builder.monto;
        this.moneda = builder.moneda;
        this.concepto = builder.concepto;
        this.fechaHora = builder.fechaHora;
        this.referencia = builder.referencia;
        this.canal = builder.canal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public String getTipoDocumentoBeneficiario() {
        return tipoDocumentoBeneficiario;
    }

    public String getNumeroDocumentoBeneficiario() {
        return numeroDocumentoBeneficiario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getConcepto() {
        return concepto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getCanal() {
        return canal;
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "numeroCuentaOrigen='" + numeroCuentaOrigen + '\'' +
                ", numeroCuentaDestino='" + numeroCuentaDestino + '\'' +
                ", nombreBeneficiario='" + nombreBeneficiario + '\'' +
                ", tipoDocumentoBeneficiario='" + tipoDocumentoBeneficiario + '\'' +
                ", numeroDocumentoBeneficiario='" + numeroDocumentoBeneficiario + '\'' +
                ", monto=" + monto +
                ", moneda='" + moneda + '\'' +
                ", concepto='" + concepto + '\'' +
                ", fechaHora=" + fechaHora +
                ", referencia='" + referencia + '\'' +
                ", canal='" + canal + '\'' +
                '}';
    }

    public static class Builder {
        private String numeroCuentaOrigen;
        private String numeroCuentaDestino;
        private String nombreBeneficiario;
        private String tipoDocumentoBeneficiario;
        private String numeroDocumentoBeneficiario;
        private BigDecimal monto;
        private String moneda;
        private String concepto;
        private LocalDateTime fechaHora;
        private String referencia;
        private String canal = "APP_MOVIL";

        public Builder conCuentaOrigen(String numeroCuentaOrigen) {
            this.numeroCuentaOrigen = numeroCuentaOrigen;
            return this;
        }

        public Builder conCuentaDestino(String numeroCuentaDestino) {
            this.numeroCuentaDestino = numeroCuentaDestino;
            return this;
        }

        public Builder conBeneficiario(String nombreBeneficiario, String tipoDocumento, String numeroDocumento) {
            this.nombreBeneficiario = nombreBeneficiario;
            this.tipoDocumentoBeneficiario = tipoDocumento;
            this.numeroDocumentoBeneficiario = numeroDocumento;
            return this;
        }

        public Builder conMonto(BigDecimal monto, String moneda) {
            this.monto = monto;
            this.moneda = moneda;
            return this;
        }

        public Builder conConcepto(String concepto) {
            this.concepto = concepto;
            return this;
        }

        public Builder conFechaHora(LocalDateTime fechaHora) {
            this.fechaHora = fechaHora;
            return this;
        }

        public Builder conReferencia(String referencia) {
            this.referencia = referencia;
            return this;
        }

        public Builder conCanal(String canal) {
            this.canal = canal;
            return this;
        }

        public Transferencia build() {
            validarCamposObligatorios();
            if (fechaHora == null) {
                fechaHora = LocalDateTime.now();
            }
            return new Transferencia(this);
        }

        private void validarCamposObligatorios() {
            if (numeroCuentaOrigen == null || numeroCuentaOrigen.isEmpty()) {
                throw new IllegalStateException("El número de cuenta origen es obligatorio");
            }
            if (numeroCuentaDestino == null || numeroCuentaDestino.isEmpty()) {
                throw new IllegalStateException("El número de cuenta destino es obligatorio");
            }
            if (nombreBeneficiario == null || nombreBeneficiario.isEmpty()) {
                throw new IllegalStateException("El nombre del beneficiario es obligatorio");
            }
            if (tipoDocumentoBeneficiario == null || tipoDocumentoBeneficiario.isEmpty()) {
                throw new IllegalStateException("El tipo de documento del beneficiario es obligatorio");
            }
            if (numeroDocumentoBeneficiario == null || numeroDocumentoBeneficiario.isEmpty()) {
                throw new IllegalStateException("El número de documento del beneficiario es obligatorio");
            }
            if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalStateException("El monto debe ser mayor que cero");
            }
            if (moneda == null || moneda.isEmpty()) {
                throw new IllegalStateException("La moneda es obligatoria");
            }
            if (concepto == null || concepto.isEmpty()) {
                throw new IllegalStateException("El concepto es obligatorio");
            }
        }
    }
}