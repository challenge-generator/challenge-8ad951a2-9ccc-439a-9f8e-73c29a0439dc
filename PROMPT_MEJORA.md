# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/bank/mobile/tasks/Login.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bank/mobile/tasks/RealizarTransferencia.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bank/mobile/steps/TransferenciaSteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers.containsString pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/bank/mobile/steps/ConsultaSaldoSteps.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.5`: net.serenity-bdd:serenity-bom declara la version 4.1.5, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Seniority Advanced

### Brecha de conocimiento
Genera la automatización de aplicaciones móviles con al menos una (1) tecnología como: Appium, Katalon, UI Automator y en al menos un (1) sistema operativo como: Android o iOS

### Misión / candidato
Candidato con experiencia en automatización, enfocado en pruebas de calidad

### Reto
- Tema: Automatización de pruebas móviles
- Seniority: advanced-l1
- Tipo: practical
- Título: Automatización de pruebas para aplicación móvil de banca
- Tiempo estimado: 20 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y modelado del dominio — objetivo: Comprender los flujos críticos de la aplicación y modelar los casos de prueba necesarios. — entregable (NO resolver): Diagrama de flujo de los casos de prueba y criterios de aceptación.
- Fase 2: Implementación de scripts de automatización — objetivo: Generar scripts de automatización que cubran los casos de prueba modelados. — entregable (NO resolver): Scripts de automatización funcionales para los casos de prueba.
- Fase 3: Ejecución y validación de los scripts — objetivo: Ejecutar los scripts de automatización y validar su correcto funcionamiento. — entregable (NO resolver): Reporte de ejecución y validación de los scripts de automatización.
- Fase 4: Revisión y mejora continua — objetivo: Revisar los scripts de automatización y proponer mejoras continuas. — entregable (NO resolver): Documentación de mejoras propuestas para los scripts de automatización.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.bank.mobile</groupId>
    <artifactId>mobile-bank-tests</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.1.5</serenity.version>
        <cucumber.version>7.15.0</cucumber.version>
        <selenium.version>4.18.1</selenium.version>
        <junit.version>5.10.0</junit.version>
        <appium.version>9.2.2</appium.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <scope>compile</scope>
        </dependency>

        <!-- Appium -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>${appium.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- JUnit -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Runner.java</include>
                    </includes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/test/java/com/bank/mobile/models/Transferencia.java ===
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

// === ARCHIVO: src/test/resources/config/serenity.conf ===
webdriver {
  driver = appium
  android {
    autoWebview = true
    autoWebviewTimeout = 4000
  }
}

appium {
  url = "http://localhost:4723/wd/hub"
  httpTimeout = 120000
  retryTimeout = 30000
  enableMultiwindows = true
  autoAcceptAlerts = true
  autoGrantPermissions = true
}

capabilities {
  browserName = ""
  platformName = "Android"
  platformVersion = "13"
  deviceName = "Pixel_7_Pro"
  automationName = "UiAutomator2"
  app = "C:\\Users\\Tester\\Downloads\\bank-mobile-app.apk"
  appPackage = "com.bank.mobile.app"
  appActivity = "com.bank.mobile.app.ui.splash.SplashActivity"
  noReset = true
  fullReset = false
  autoGrantPermissions = true
  autoAcceptAlerts = true
  ignoreUnimportantViews = true
  disableWindowAnimation = true
  skipUnlock = true
  chromedriverExecutable = "C:\\Users\\Tester\\AppData\\Local\\Programs\\appium\\chromedriver.exe"
  webviewDevtoolsPort = 9222
  nativeWebScreenshot = true
  disableAnimations = true
}

serenity {
  take.screenshots = AFTER_EACH_STEP
  compress.screenshots = false
  project.name = "Mobile Bank Tests"
  test.skip = false
  restart.browser.for.each = feature
  browser.maximized = false
  save.screenshots.of.failure = true
  numbered.screenshots = true
  screenshot.folder = target\\site\\serenity
  report.headless = true
  log.javascript = true
  preserve.selenide. Cookies = true
  browser.wait.for.timeout = 10000
  default.timeout = 30000
  implicit.timeout = 5000
}

environments {
  default {
    webdriver.base.url = "http://localhost:4723"
    appium.server = "http://localhost:4723/wd/hub"
  }
  dev {
    webdriver.base.url = "http://192.168.1.100:4723"
    appium.server = "http://192.168.1.100:4723/wd/hub"
  }
  qa {
    webdriver.base.url = "http://192.168.1.200:4723"
    appium.server = "http://192.168.1.200:4723/wd/hub"
  }
  staging {
    webdriver.base.url = "http://192.168.1.50:4723"
    appium.server = "http://192.168.1.50:4723/wd/hub"
  }
}

// === ARCHIVO: src/test/resources/features/transferencias/realizar_transferencia.feature ===
# language: es
Característica: Realizar Transferencias
  Como usuario de la aplicación móvil del banco
  Quiero poder realizar transferencias a otros usuarios
  Para poder enviar dinero de manera rápida y segura

  @transferencia-exitosa
  Escenario: Transferencia exitosa a beneficiario registrado
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    Y selecciona un beneficiario previamente registrado con nombre "Juan Pérez"
    E ingresa el monto de "5000.00" pesos
    Y selecciona la cuenta de origen "1234567890"
    Y ingresa el concepto "Pago de servicios"
    Y confirma la transferencia
    Entonces debería ver un mensaje de confirmación con referencia "TRF-"
    Y el saldo de la cuenta origen debería disminuir en "5000.00"

  @transferencia-beneficiario-no-registrado
  Escenario: Transferencia a beneficiario no registrado
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    Y selecciona la opción de agregar nuevo beneficiario
    E ingresa los datos del beneficiario:
      | nombre     | tipoDocumento | numeroDocumento |
      | Maria López | CC            | 12345678        |
    Y ingresa el monto de "2500.00" pesos
    Y ingresa el concepto "Donación"
    Y confirma la transferencia
    Entonces debería ver un mensaje de confirmación
    Y debería poder guardar el beneficiario para futuras transferencias

  @transferencia-monto-insuficiente
  Escenario: Transferencia rechazada por saldo insuficiente
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene un saldo disponible de "1000.00" pesos en la cuenta "1234567890"
    Cuando navega a la sección de transferencias
    Y selecciona el beneficiario "Juan Pérez"
    E ingresa el monto de "5000.00" pesos
    Y confirma la transferencia
    Entonces debería ver el mensaje de error "Saldo insuficiente para realizar esta transferencia"
    Y la transferencia no debería ser procesada

  @transferencia-monto-maximo-excedido
  Escenario: Transferencia rechazada por monto máximo excedido
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene un límite de transferencia diaria de "50000.00" pesos
    Y ya ha realizado transferencias por "45000.00" pesos hoy
    Cuando navega a la sección de transferencias
    Y selecciona el beneficiario "Juan Pérez"
    E ingresa el monto de "10000.00" pesos
    Y confirma la transferencia
    Então debería ver el mensaje de error "Ha excedido el límite de transferencia diaria"
    Y debería mostrar el monto restante disponible "5000.00" pesos

  @transferencia-cuenta-invalida
  Escenario: Transferencia a cuenta destino inválida
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    E ingresa manualmente el número de cuenta destino "9999999999"
    Y ingresa el monto de "1000.00" pesos
    Y confirma la transferencia
    Entonces debería ver el mensaje de error "La cuenta de destino no existe"

  @transferencia-datos-incompletos
  Escenario: Transferencia con datos de beneficiario incompletos
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    E intenta confirmar sin completar todos los campos obligatorios
    Entonces debería ver mensajes de error para los campos obligatorios:
      | campo        | mensaje                              |
      | beneficiario | Seleccione un beneficiario          |
      | monto        | Ingrese el monto a transferir        |
      | concepto     | Ingrese un concepto para la transfer|

  @transferencia-moneda-extranjera
  Escenario: Transferencia en moneda extranjera
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta en pesos y otra en dólares
    Cuando navega a la sección de transferencias
    Y selecciona el beneficiario "John Smith"
    E selecciona la opción de transferencia internacional
    E ingresa el monto de "500.00" dólares
    Y selecciona la cuenta de origen en dólares "0987654321"
    E ingresa el concepto "Payment for services"
    Y confirma la transferencia
    Entonces debería ver un mensaje de confirmación con el tipo de cambio aplicado
    Y debería mostrar el equivalente en pesos del monto transferido

  @transferencia-programada
  Escenario: Programar transferencia para fecha futura
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    Y selecciona la opción de transferencia programada
    E selecciona el beneficiario "Juan Pérez"
    E ingresa el monto de "3000.00" pesos
    E selecciona la fecha "15/12/2024"
    E ingresa el concepto "Pago programado"
    Y confirma la programación
    Entonces debería ver un mensaje de confirmación de la programación
    Y debería poder ver la transferencia programada en la lista de pendientes

  @transferencia-recurrente
  Escenario: Configurar transferencia recurrente
    Dado que el usuario está autenticado en la aplicación móvil
    Cuando navega a la sección de transferencias
    Y selecciona la opción de transferencia recurrente
    E selecciona el beneficiario "Juan Pérez"
    E ingresa el monto de "10000.00" pesos
    E selecciona la frecuencia "Mensual"
    E selecciona la fecha de inicio "01/01/2025"
    E selecciona la fecha de fin "31/12/2025"
    E ingresa el concepto "Pago de租金 mensual"
    Y confirma la configuración
    Entonces debería ver un mensaje de confirmación
    Y debería poder ver la transferencia recurrente en la lista de servicios programados

  @transferencia-anular
  Escenario: Anular transferencia antes de confirmación
    Dado que el usuario está autenticado en la aplicación móvil
    Y ha iniciado una transferencia por "2000.00" pesos
    Cuando decide cancelar la transferencia antes de confirmar
    Entonces debería volver a la pantalla principal de transferencias
    Y no debería existir ninguna transferencia registrada

  @transferencia-comprobante
  Escenario: Descargar comprobante de transferencia exitosa
    Dado que el usuario ha realizado una transferencia exitosa
    Cuando accede al historial de transferencias
    Y selecciona la transferencia más reciente
    Y solicita el comprobante en PDF
    Entonces debería poder descargar el comprobante
    Y el comprobante debería contener: referencia, monto, beneficiario, fecha, hora

// === ARCHIVO: src/test/resources/features/consultas/consultar_saldo.feature ===
# language: es
Característica: Consultar Saldo de Cuentas
  Como usuario de la aplicación móvil del banco
  Quiero poder consultar el saldo de mis cuentas
  Para conocer el estado de mis finanzas en cualquier momento

  @consulta-saldo-exitosa
  Escenario: Consulta de saldo exitoso en cuenta activa
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta activa número "1234567890" con saldo de "15000.00" pesos
    Cuando consulta el saldo de la cuenta "1234567890"
    Entonces debería visualizar el saldo disponible de "15000.00" pesos
    Y debería ver el saldo contable de "15000.00" pesos
    Y debería ver la fecha de última actualización

  @consulta-multiples-cuentas
  Escenario: Consulta de saldo de múltiples cuentas
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene las siguientes cuentas:
      | numeroCuenta | tipo       | saldo    |
      | 1234567890   | Ahorros    | 15000.00 |
      | 0987654321   | Corriente  | 50000.00 |
      | 5678901234   | Inversión   | 100000.00|
    Cuando consulta el saldo de todas sus cuentas
    Entonces debería ver el listado con todas las cuentas
    Y debería ver el saldo total consolidado de "165000.00" pesos

  @consulta-cuenta-inactiva
  Escenario: Consulta de saldo en cuenta inactiva
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta inactiva número "1111111111"
    Cuando consulta el saldo de la cuenta "1111111111"
    Entonces debería ver el mensaje de información "Cuenta inactiva"
    Y debería mostrar el último saldo conocido de "5000.00" pesos

  @consulta-cuenta-bloqueada
  Escenario: Consulta de saldo en cuenta bloqueada
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta bloqueada número "2222222222"
    Cuando consulta el saldo de la cuenta "2222222222"
    Entonces debería ver el mensaje de error "Cuenta bloqueada. Contacte al banco"
    Y debería mostrar la opción de contactar al servicio al cliente

  @consulta-cuenta-sin-fondos
  Escenario: Consulta de saldo en cuenta sin fondos
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta con saldo de "0.00" pesos
    Cuando consulta el saldo de esa cuenta
    Entonces debería visualizar el saldo de "0.00" pesos
    Y debería ver un mensaje de alerta "Su cuenta no tiene fondos disponibles"

  @consulta-saldo-con-movimientos
  Escenario: Consultar saldo con últimos movimientos
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta con saldo actual de "25000.00" pesos
    Y tiene los siguientes movimientos recientes:
      | fecha     | descripción    | monto     | tipo  |
      | 15/12/2024| Transferencia | -5000.00  | Débito|
      | 14/12/2024| Depósito      | +15000.00 | Crédito|
      | 13/12/2024| Pago servicios| -2000.00  | Débito|
    Cuando consulta el saldo de la cuenta
    Entonces debería ver el saldo actual de "25000.00" pesos
    Y debería ver los últimos tres movimientos

  @consulta-saldo-dolares
  Escenario: Consulta de saldo en dólares
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta en dólares número "5555555555" con saldo de "1500.00" USD
    Cuando consulta el saldo de la cuenta "5555555555"
    Entonces debería visualizar el saldo de "1500.00" dólares
    Y debería ver el equivalente en pesos según el tipo de cambio actual

  @consulta-historial-completo
  Escenario: Consultar historial completo de movimientos
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta con múltiples movimientos en el mes
    Cuando accede al historial completo de la cuenta
    Entonces debería ver todos los movimientos del mes actual
    Y debería poder filtrar por tipo de movimiento
    Y debería poder buscar por descripción o referencia

  @consulta-saldo-offline
  Escenario: Consulta de saldo sin conexión a internet
    Dado que el usuario está autenticado en la aplicación móvil
    Y la aplicación tiene datos en caché del último saldo consultado
    Y pierde la conexión a internet
    Cuando consulta el saldo de su cuenta
    Entonces debería mostrar el último saldo guardado en caché
    Y debería indicar la fecha de la última actualización "Última actualización: hace 2 horas"

  @consulta-actualizacion-saldo
  Escenario: Forzar actualización de saldo
    Dado que el usuario está autenticado en la aplicación móvil
    Y ha consultado el saldo hace más de 5 minutos
    Cuando realiza un gesto de deslizar hacia abajo para actualizar
    Entonces debería actualizar el saldo desde el servidor
    Y debería mostrar el indicador de carga durante la actualización
    Y debería mostrar el nuevo saldo actualizado

  @consulta-limites-disponibles
  Escenario: Consultar saldo y límites de crédito
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una tarjeta de crédito con límite de "50000.00" pesos
    Y ha usado "20000.00" pesos del límite
    Cuando consulta el resumen de su cuenta de crédito
    Entonces debería ver el límite total de "50000.00" pesos
    Y debería ver el monto usado de "20000.00" pesos
    Y debería ver el disponible de "30000.00" pesos

  @consulta-saldo-compartido
  Escenario: Consultar cuenta compartida con otros usuarios
    Dado que el usuario está autenticado en la aplicación móvil
    Y tiene una cuenta compartida con "Pedro Gómez"
    Y el saldo total de la cuenta es "100000.00" pesos
    Y la participación del usuario es "50%"
    Cuando consulta el saldo de la cuenta compartida
    Entonces debería ver el saldo total de "100000.00" pesos
    Y debería ver la participación del usuario "50000.00" pesos
    Y debería ver el nombre del otro titular "Pedro Gómez"

// === ARCHIVO: src/test/java/com/bank/mobile/runners/RunCucumberTest.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/tasks/Login.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/tasks/RealizarTransferencia.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/tasks/ConsultarSaldo.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/questions/SaldoVisible.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/questions/MensajeConfirmacion.java ===
package com.bank.mobile.questions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MensajeConfirmacion implements Question<String> {

    private static final Pattern PATRON_REFERENCIA = Pattern.compile(
            "Referencia:\\s*([A-Z0-9]{10,20})",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PATRON_FECHA = Pattern.compile(
            "Fecha:\\s*(\\d{2}/\\d{2}/\\d{4})"
    );

    private static final Pattern PATRON_HORA = Pattern.compile(
            "Hora:\\s*(\\d{2}:\\d{2}:\\d{2})"
    );

    private String mensajeEsperado;
    private boolean verificarReferencia;
    private boolean verificarMonto;
    private String montoEsperado;

    public MensajeConfirmacion() {
    }

    public static MensajeConfirmacion elMensaje() {
        return Instrumented.instanceOf(MensajeConfirmacion.class).newInstance();
    }

    public static MensajeConfirmacion es(String mensaje) {
        MensajeConfirmacion question = Instrumented.instanceOf(MensajeConfirmacion.class).newInstance();
        question.mensajeEsperado = mensaje;
        return question;
    }

    public MensajeConfirmacion queContiene(String texto) {
        this.mensajeEsperado = texto;
        return this;
    }

    public MensajeConfirmacion conReferencia() {
        this.verificarReferencia = true;
        return this;
    }

    public MensajeConfirmacion conMonto(String monto) {
        this.verificarMonto = true;
        this.montoEsperado = monto;
        return this;
    }

    @Override
    public String answeredBy(Actor actor) {
        WebElement contenedorMensaje = buscarContenedorConfirmacion(actor);

        if (contenedorMensaje == null) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "No se encontró el contenedor del mensaje de confirmación de transferencia"
            );
        }

        if (!contenedorMensaje.isDisplayed()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación está presente pero no es visible en pantalla"
            );
        }

        String textoMensaje = contenedorMensaje.getText();

        if (textoMensaje == null || textoMensaje.trim().isEmpty()) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación está vacío"
            );
        }

        if (!textoMensaje.toLowerCase().contains("transferencia") &&
                !textoMensaje.toLowerCase().contains("envío") &&
                !textoMensaje.toLowerCase().contains("enviado")) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación no contiene texto relacionado con transferencia: " + textoMensaje
            );
        }

        if (!textoMensaje.toLowerCase().contains("éxito") &&
                !textoMensaje.toLowerCase().contains("exitoso") &&
                !textoMensaje.toLowerCase().contains("completado") &&
                !textoMensaje.toLowerCase().contains("realizado")) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje no indica que la transferencia fue exitosa: " + textoMensaje
            );
        }

        if (mensajeEsperado != null && !textoMensaje.toLowerCase().contains(mensajeEsperado.toLowerCase())) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    String.format(
                            "El mensaje de confirmación no contiene el texto esperado. Esperado: '%s', Obtenido: '%s'",
                            mensajeEsperado,
                            textoMensaje
                    )
            );
        }

        if (verificarReferencia && !contieneReferenciaValida(textoMensaje)) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    "El mensaje de confirmación no contiene una referencia de transferencia válida"
            );
        }

        if (verificarMonto && montoEsperado != null && !textoMensaje.contains(montoEsperado)) {
            throw new net.serenitybdd.screenplay.exceptions.GivenWhenThenException(
                    String.format(
                            "El mensaje de confirmación no contiene el monto esperado. Esperado: '%s', Obtenido: '%s'",
                            montoEsperado,
                            textoMensaje
                    )
            );
        }

        return textoMensaje;
    }

    private WebElement buscarContenedorConfirmacion(Actor actor) {
        List<By> localizadores = List.of(
                By.id("mensajeConfirmacion"),
                By.id("confirmacionTransferencia"),
                By.xpath("//div[contains(@class, 'confirmacion')]"),
                By.xpath("//div[contains(@class, 'mensaje-exito')]"),
                By.xpath("//div[contains(@class, 'resultado-transferencia')]"),
                By.cssSelector("[data-testid='mensaje-confirmacion']"),
                By.xpath("//android.widget.TextView[contains(@text, 'Transferencia')]")
        );

        for (By localizador : localizadores) {
            try {
                List<WebElement> elementos = actor.getDriver().findElements(localizador);
                for (WebElement elemento : elementos) {
                    if (elemento.isDisplayed()) {
                        return elemento;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }

        return null;
    }

    private boolean contieneReferenciaValida(String texto) {
        Matcher matcher = PATRON_REFERENCIA.matcher(texto);
        return matcher.find();
    }

    private boolean contieneFechaValida(String texto) {
        Matcher matcher = PATRON_FECHA.matcher(texto);
        return matcher.find();
    }

    private boolean contieneHoraValida(String texto) {
        Matcher matcher = PATRON_HORA.matcher(texto);
        return matcher.find();
    }
}

// === ARCHIVO: src/test/java/com/bank/mobile/interactions/SeleccionarBeneficiario.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/interactions/IngresarMonto.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/steps/TransferenciaSteps.java ===
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

// === ARCHIVO: src/test/java/com/bank/mobile/steps/ConsultaSaldoSteps.java ===
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

// === ARCHIVO: src/test/resources/features/transferencias/datos_transferencia.csv ===
numerocuentaorigen;numerocuentadestino;nombrebeneficiario;tipodocumento;numerodocumento;monto;moneda;concepto;rezon;resultadoesperado
1234567890;0987654321;Maria Garcia;CC;12345678;50000.00;COP;Pago servicios;Prueba transferencia valida;EXITO
1234567890;0987654321;Juan Perez;CC;87654321;100000.50;COP;Transferencia ahorros;Transferencia con monto mayor;EXITO
1234567890;0987654321;Ana Lopez;TI;11223344;25000.00;USD;Pago internacional;Transferencia en dólares;EXITO
1234567890;5555555555;Carlos Rodriguez;CC;99887766;0.00;COP;Transferencia cero;Monto cero no permitido;FALLO
1234567890;6666666666;Pedro Gomez;CC;44332211;-5000.00;COP;Monto negativo;Monto negativo no permitido;FALLO
1234567890;7777777777;Luisa Fernandez;CC;66554433;999999999.99;COP;Monto excede limite;Monto mayor al saldo disponible;FALLO
0000000000;0987654321;Maria Garcia;CC;12345678;50000.00;COP;Cuenta origen invalida;Cuenta origen no existe;FALLO
1234567890;0000000000; beneficiario;CC;12345678;50000.00;COP;Beneficiario sin nombre;Nombre de beneficiario requerido;FALLO
1234567890;0987654321;;CC;12345678;50000.00;COP;Sin nombre beneficiario;Nombre de beneficiario requerido;FALLO
1234567890;0987654321;Pedro Gomez;XX;44332211;50000.00;COP;Tipo documento invalido;Tipo de documento no valido;FALLO
1234567890;0987654321;Pedro Gomez;CC;;50000.00;COP;Sin numero documento;Numero de documento requerido;FALLO
1234567890;0987654321;Pedro Gomez;CC;44332211;50000.00;XXX;Moneda invalida;Moneda no soportada;FALLO
1234567890;0987654321;Pedro Gomez;CC;44332211;50000.00;;Sin moneda;Moneda requerida;FALLO
1234567890;0987654321;Pedro Gomez;CC;44332211;50000.00;COP;;Concepto vacio;Concepto requerido;FALLO
1234567890;0987654321;Pedro Gomez;CC;44332211;50000.00;COP;Transferencia con caracteres especiales <>&;Concepto con caracteres especiales;FALLO

// === ARCHIVO: src/test/resources/features/consultas/datos_saldo.csv ===
numerocuenta;tipocuenta;estado;moneda;saldoinicial;tipodocumento;numerodocumento;resultadoesperado;mensajeesperado
1234567890;Ahorros;Activa;COP;1500000.00;CC;12345678;EXITO;Saldo disponible
1234567890;Corriente;Activa;COP;500000.00;CC;12345678;EXITO;Saldo disponible
1234567890;Ahorros;Activa;USD;2500.50;CC;12345678;EXITO;Saldo en dólares
1234567890;Ahorros;Inactiva;COP;1500000.00;CC;12345678;FALLO;Cuenta inactiva
1234567890;Corriente;Bloqueada;COP;500000.00;CC;12345678;FALLO;Cuenta bloqueada
1234567890;Ahorros;Cancelada;COP;0.00;CC;12345678;FALLO;Cuenta cancelada
0000000000;Ahorros;Activa;COP;1500000.00;CC;12345678;FALLO;Cuenta no encontrada
1234567890;Ahorros;Activa;EUR;1000.00;CC;12345678;FALLO;Moneda no soportada
1234567890;Inversión;Activa;COP;3000000.00;CC;12345678;EXITO;Saldo inversión
1234567890;Ahorros;Activa;COP;-50000.00;CC;12345678;FALLO;Saldo negativo
9999999999;Corriente;Activa;COP;750000.00;CC;87654321;FALLO;Cuenta no pertenece al usuario
1234567890;Ahorros;Pendiente;Ahorros;100000.00;CC;12345678;FALLO;Cuenta pendiente de activación
```
