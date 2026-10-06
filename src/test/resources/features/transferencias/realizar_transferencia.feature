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