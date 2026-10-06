# Automatización de pruebas para aplicación móvil de banca

La aplicación móvil de un banco necesita una suite de pruebas automatizadas para garantizar la calidad en la entrega de nuevas versiones. La aplicación permite a los usuarios realizar transferencias, consultar saldos y acceder a servicios de atención al cliente. El candidato debe generar scripts de automatización que cubran flujos críticos como la realización de transferencias y la consulta de saldos, asegurando la correcta funcionalidad y la identificación de posibles errores.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Automatización de pruebas móviles |
| **Nivel** | advanced-l1 |
| **Tipo** | practical |
| **Tiempo estimado** | 20 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y modelado del dominio

**Objetivo:** Comprender los flujos críticos de la aplicación y modelar los casos de prueba necesarios.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Identifica los flujos críticos de la aplicación móvil (transferencias, consultas de saldo, servicios de atención al cliente).
- Modela los casos de prueba necesarios para cubrir estos flujos, incluyendo criterios de aceptación.

**Entregable:** Diagrama de flujo de los casos de prueba y criterios de aceptación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes estados de la aplicación y cómo transitan entre ellos.
- Piensa en posibles edge cases y cómo manejarlos en los casos de prueba.

</details>

### Fase 2: Implementación de scripts de automatización

**Objetivo:** Generar scripts de automatización que cubran los casos de prueba modelados.

**Tiempo estimado:** 10 horas

**Instrucciones:**

- Implementa scripts de automatización para los casos de prueba identificados en la fase anterior.
- Asegura que los scripts cubran los criterios de aceptación y manejen correctamente los edge cases.

**Entregable:** Scripts de automatización funcionales para los casos de prueba.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza una tecnología de automatización como Appium, Katalon o UI Automator.
- Considera la robustez y mantenibilidad de tus scripts.

</details>

### Fase 3: Ejecución y validación de los scripts

**Objetivo:** Ejecutar los scripts de automatización y validar su correcto funcionamiento.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Ejecuta los scripts de automatización en un entorno de pruebas.
- Valida que los scripts cumplan con los criterios de aceptación y manejen correctamente los edge cases.

**Entregable:** Reporte de ejecución y validación de los scripts de automatización.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza un entorno de pruebas que refleje el entorno de producción lo más posible.
- Considera la generación de reportes de ejecución para facilitar la revisión.

</details>

### Fase 4: Revisión y mejora continua

**Objetivo:** Revisar los scripts de automatización y proponer mejoras continuas.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Revisa los scripts de automatización y propone mejoras para su robustez, mantenibilidad y cobertura.
- Documenta las mejoras propuestas y justifica su necesidad.

**Entregable:** Documentación de mejoras propuestas para los scripts de automatización.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la incorporación de nuevos casos de prueba basados en feedback de usuarios o cambios en la aplicación.
- Piensa en cómo mejorar la mantenibilidad de los scripts a largo plazo.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas automatizadas y por qué son importantes en el desarrollo de aplicaciones móviles?
- **paraQueSirve**: ¿Para qué sirven los scripts de automatización en el contexto de la aplicación móvil del banco?
- **comoSeUsa**: ¿Cómo se utilizan las tecnologías de automatización como Appium, Katalon o UI Automator para generar scripts de prueba?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar scripts de automatización y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la generación de scripts de automatización para una aplicación móvil de banca?

## Criterios de Evaluacion

- Comprensión de los flujos críticos de la aplicación móvil.
- Modelado correcto de los casos de prueba y criterios de aceptación.
- Implementación de scripts de automatización funcionales y robustos.
- Ejecución y validación exitosa de los scripts de automatización.
- Propuesta de mejoras continuas para los scripts de automatización.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
