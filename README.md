# Microservicios dirigidos por eventos mediante colas

## Patrón de Mensajería
Este proyecto fue basado bajo el siguiente patrón

![Messaging Pattern](messaging_pattern.png)

Existen varias herramientas que implementan este patrón como: 
Apache Kafka, RabbitMQ, ActiveMQ

## Implementación
Para nuestro caso, la comunicación de los microservicios se 
implementará en colas de ActiveMQ

![Demo Implementation](demo_implementation.png)

De tal manera, los microservicios tendrían los siguientes roles:

### Cards Service
Servicio que alojará el evento (producer) en la cola.
Este evento será notificado luego de crear la tarjeta.

### Products Service
Servicio que se suscribirá al evento (consumer) 
alojado en la cola. Este realizará el calculo del total de productos 
contratados de Tarjeta de Crédito.
