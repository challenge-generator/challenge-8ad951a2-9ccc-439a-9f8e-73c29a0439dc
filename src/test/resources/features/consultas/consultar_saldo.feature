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