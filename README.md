

 Prueba Técnica Backend - Microservicios de Productos e Inventario (Versión Senior)
 Descripción General



[Cliente (Postman o Swagger)]
↓
[API Gateway futuro opcional]
↓
┌──────────────┐         ┌──────────────────┐
│  Productos   │ <--→--→ │   Inventario     │
│   GET/POST   │         │ GET/PUT/POST     │
└──────────────┘         └──────────────────┘
↓                        ↓

Base H2                 Base H2
Este proyecto consiste en dos microservicios desacoplados (Productos e Inventario) desarrollados en Java con Spring Boot 3, que se comunican vía HTTP utilizando el estándar JSON:API.
Se ha implementado un flujo de compra con validaciones de stock y consistencia entre servicios.



🧱 Arquitectura del Proyecto


src/main/java/com/FullStack/FullStackJavaSprintBoot
├── FullStackJavaSprintBootApplication.java
├── controller/
│   ├── ProductoController.java
│   └── InventoryController.java
├── dto/
│   ├── CompraRequest.java
│   ├── CompraResponse.java
│   ├── UpdateCantidadRequest.java
│   └── CrearInventarioRequest.java
├── model/
│   ├── Producto.java
│   └── Inventory.java
├── repository/
│   ├── ProductoRepository.java
│   └── InventoryRepository.java
├── service/
│   ├── ProductoService.java
│   └── InventoryService.java
└── config/
├── AuthInterceptor.java
└── WebConfig.java



🔒 Seguridad Entre Servicios (API Key)
Los microservicios usan una API Key para autenticarse entre sí, incluida como cabecera X-API-KEY.
Esto protege la comunicación interna, evitando accesos externos no autorizados.

 Se define en application.properties:

properties



internal.api.key=miclave-supersecreta-123
🔐 Las llamadas internas usan esta cabecera:

http


X-API-KEY: miclave-supersecreta-123


                          Tecnologías Utilizadas

Tecnología	Descripción


-Java 17	Lenguaje principal
-Spring Boot 3	Framework para microservicios
-Spring Web / JPA	REST APIs y persistencia
-H2	Base de datos embebida
-Docker	Containerización
-JUnit + Mockito	Pruebas unitarias e integración
-Spring Actuator	Health checks de los microservicios
-Logback + Logstash	Logs estructurados en formato JSON

                  Instalación y Ejecución
-Requisitos

Java 17+

Docker

Maven

IntelliJ IDEA (recomendado)

Clonar el repositorio

bash

git clone https://github.com/mpinzonxiqu/FullStackJavaSprintBoot.git
cd FullStackJavaSprintBoot
Ejecutar ambos servicios con Docker

bash

docker-compose up --build
Esto inicia los microservicios en localhost:8080

                       Acceso a las APIs
Servicio	Endpoint

Productos	http://localhost:8080/api/productos


Inventario	http://localhost:8080/api/inventory/crear


Compra	http://localhost:8080/api/inventory/purchase

                            Endpoints
Productos  


Método	Ruta	Descripción


GET	/api/products	Listar productos

GET	/api/products/{id}	Obtener por ID

POST	/api/products	Crear nuevo producto

                          Inventario
Método	Ruta	Descripción 

GET	/api/inventory/{id}	Consultar inventario
PUT	/api/inventory/{id}	Actualizar stock
POST	/api/purchase	Comprar producto y descontar stock

                       Flujo de Compra


Cliente envía product_id y cantidad al endpoint /api/purchase.

El microservicio de Inventario:

Consulta si el producto existe en el microservicio de Productos.

Verifica stock disponible.

Descuenta inventario.

Retorna la información de la compra.

 Este endpoint fue implementado en el microservicio de Inventario, siguiendo el principio de responsabilidad única.

 -                        Mejoras Agregadas (Nivel Senior)
Mejora	Descripción
- Autenticación con API Key	Seguridad en llamadas internas.
🩺 Health Checks	Activados con Spring Boot Actuator en /actuator/health y /actuator/info.
- Docker optimizado	Multi-stage build en Dockerfile para imágenes más ligeras.
-Logs estructurados	Configurado logback-spring.xml con encoder JSON (logstash).
🧪 Cobertura de pruebas	JaCoCo configurado con +80% de cobertura. Reportes en target/site/jacoco.
🧩 Configuración modular	Interceptor registrado en WebConfig sin alterar controladores.

🧪 *Pruebas

mvn clean test
-Revisión del reporte de cobertura con JaCoCo:


open target/site/jacoco/index.html

🛠️ Documentación de la API
Swagger UI: http://localhost:8080/swagger-ui/index.html

Postman Collection: /docs/postman_collection.json

🧠 Decisiones Técnicas
API Key entre microservicios por simplicidad, seguridad y bajo acoplamiento.

H2 Database: ligera y fácil de usar para pruebas rápidas.

Compra en Inventario: siguiendo SRP, validación y modificación de stock deben ir en el servicio responsable de inventario.


Generar esqueleto de interceptores.

Optimizar Dockerfile.

Generar configuración de logs.

Asegurar el cumplimiento del estándar JSON:API.



Entrega
✨ Autor: Moises Pinzon Xiques
Backend Developer – Java Spring Boot


