# Sistema de Gestión de Turnos – Java Spring Boot

## Descripción técnica

Este proyecto implementa un sistema de gestión de turnos para atención al cliente. Permite:

- Solicitar turnos
- Atender turnos en orden (FIFO)
- Cancelar turnos
- Registrar y deshacer acciones del cliente
- Utilizar mensajería con RabbitMQ para simular eventos de creación de turnos
- Persistencia de datos con MySQL

### Arquitectura general

El sistema sigue una arquitectura en capas:

- **DTO** → Mapeo de datos de entrada/salida  
- **Entity** → Clases JPA para persistencia  
- **Repository** → Interfaces que extienden JpaRepository  
- **Service** → Lógica de negocio  
- **Controller** → Exposición de endpoints REST  
- **Messaging** → Envío/consumo de mensajes con RabbitMQ  

## Estructuras de datos usadas

### Estructuras en memoria

#### `ArbolServicios`
- Servicios son almacenados en estructura de arbol usando maps y hashmap.

#### `ColaDeTurnos` (FIFO)
- Estructura de cola para atender turnos que utiliza Queue y linkedList de java.util.

#### `PilaDeAcciones`
- Estructura de pila(stack) que almacena las acciones de un cliente usando stack de java.util.

#### `ListaHistorial`
- Lista que almacena el historial de turnos atendidos.

## Endpoints para turnos

| Método | Ruta                 | Descripción                          |
|--------|----------------------|--------------------------------------|
| POST   | `/api/turno`         | Solicitar un nuevo turno             |
| GET    | `/api/turno/atender` | Atender siguiente turno              |
| DELETE | `/api/turno/{id}`    | Cancelar turno (si está pendiente)   |

Ejemplo JSON para crear un turno:
```json
{
  "nombreCliente": "Ana García",
  "clienteId": 1,
  "servicioId": 2
}
```

## Cómo ejecutar el sistema

### Paso 1: Clona el repositorio

```bash
git clone https://github.com/Brandon23-ai/Sistema-de-Turnos-Java-SpringBoot
```
 
### Paso 2: Configuración del archivo `docker-compose.yml`

Edita tu archivo `/docker-compose.yml` con tus credenciales de MySQL y RabbitMQ. También puedes copiar el archivo `docker-compose.yml` incluido y renombrarlo:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tu_base_de_datos
    username: tu_usuario
    password: tu_contraseña

rabbitmq:
  host: localhost
  port: 5672
  username: guest
  password: guest
  turno:
    queue: turnos.queue
    exchange: turnos.exchange
    routing-key: turnos.creado
```

```bash
docker-compose up -d
```

### Paso 3: Configura `application.properties`

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

# RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=tu_usuario
spring.rabbitmq.password=tu_contrase;a
```

### Paso 4: Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

O desde tu IDE (IntelliJ, VSCode, etc.) corriendo la clase `AppointmentSystem.java`.

## Acceso a interfaces

- API REST → http://localhost:8080/api/turno

## Extras

- Insercion, busqueda y eliminacion de clientes desde un endpoint.
- Creacion de servicios padre y servicios hijos.
