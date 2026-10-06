# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Automatización de pruebas para aplicación móvil de banca**.

| | |
|---|---|
| Tema | Automatización de pruebas móviles |
| Nivel | advanced-l1 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD 4.1.5 |
| Patron arquitectonico | Screenplay con Page Object Model para pruebas móviles |
| Tiempo estimado | 20 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.1.5
- net.serenity-bdd:serenity-cucumber 4.1.5
- io.appium:java-client 9.2.2
- io.cucumber:cucumber-java 7.15.0
- io.cucumber:cucumber-junit 7.15.0
- org.seleniumhq.selenium:selenium-java 4.18.1
- org.junit.jupiter:junit-jupiter 5.10.0
- org.apache.maven.plugins:maven-surefire-plugin 3.2.5
- net.serenity-bdd:serenity-maven-plugin 4.1.5

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración y modelado del dominio**: Diagrama de flujo de los casos de prueba y criterios de aceptación.
- **Fase 2 — Implementación de scripts de automatización**: Scripts de automatización funcionales para los casos de prueba.
- **Fase 3 — Ejecución y validación de los scripts**: Reporte de ejecución y validación de los scripts de automatización.
- **Fase 4 — Revisión y mejora continua**: Documentación de mejoras propuestas para los scripts de automatización.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (5)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/test/java/com/bank/mobile/tasks/Login.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bank/mobile/tasks/RealizarTransferencia.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bank/mobile/steps/TransferenciaSteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers.containsString pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/bank/mobile/steps/ConsultaSaldoSteps.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.5`
      net.serenity-bdd:serenity-bom declara la version 4.1.5, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (17)

- `pom.xml`
- `src/test/java/com/bank/mobile/models/Transferencia.java`
- `src/test/resources/config/serenity.conf`
- `src/test/resources/features/transferencias/realizar_transferencia.feature`
- `src/test/resources/features/consultas/consultar_saldo.feature`
- `src/test/java/com/bank/mobile/runners/RunCucumberTest.java`
- `src/test/java/com/bank/mobile/tasks/Login.java`
- `src/test/java/com/bank/mobile/tasks/RealizarTransferencia.java`
- `src/test/java/com/bank/mobile/tasks/ConsultarSaldo.java`
- `src/test/java/com/bank/mobile/questions/SaldoVisible.java`
- `src/test/java/com/bank/mobile/questions/MensajeConfirmacion.java`
- `src/test/java/com/bank/mobile/interactions/SeleccionarBeneficiario.java`
- `src/test/java/com/bank/mobile/interactions/IngresarMonto.java`
- `src/test/java/com/bank/mobile/steps/TransferenciaSteps.java`
- `src/test/java/com/bank/mobile/steps/ConsultaSaldoSteps.java`
- `src/test/resources/features/transferencias/datos_transferencia.csv`
- `src/test/resources/features/consultas/datos_saldo.csv`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/test/java/com/bank/mobile`
- `src/test/java/com/bank/mobile/tasks`
- `src/test/java/com/bank/mobile/questions`
- `src/test/java/com/bank/mobile/interactions`
- `src/test/java/com/bank/mobile/models`
- `src/test/java/com/bank/mobile/runners`
- `src/test/java/com/bank/mobile/steps`
- `src/test/resources/features`
- `src/test/resources/features/transferencias`
- `src/test/resources/features/consultas`
- `src/test/resources/config`

## Verificacion

```bash
mvn clean test-compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Screenplay con Page Object Model para pruebas móviles**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Seniority Advanced
- Brecha que el reto ataca: Genera la automatización de aplicaciones móviles con al menos una (1) tecnología como: Appium, Katalon, UI Automator y en al menos un (1) sistema operativo como: Android o iOS
- Mision: Candidato con experiencia en automatización, enfocado en pruebas de calidad

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
