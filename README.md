 Prueba Técnica Backend - Microservicios de Productos e Inventario


 Descripción General

Este proyecto consiste en dos microservicios desacoplados (Productos e Inventario) desarrollados en Java con Spring Boot, que se comunican vía HTTP utilizando el estándar JSON:API. Se implementan funcionalidades para la creación de productos, gestión de inventario y un flujo de compra con validaciones de disponibilidad y consistencia de datos entre servicios.

                  Arquitectura del Proyecto

Microservicio Productos
Expone endpoints para crear, obtener y listar productos.

Microservicio Inventario

Administra las cantidades disponibles, expone un endpoint para realizar compras y actualiza el inventario.


Comunicación entre servicios

Los servicios interactúan mediante REST API bajo el formato JSON:API, y la autenticación se realiza mediante API Key incluida en las cabeceras.


Tecnologías Utilizadas

Descripción

-Java 17	Lenguaje de programación principal
-Spring Boot 3	Framework para microservicios
-Spring Web	Manejo de endpoints REST
-Spring Data JPA	Persistencia con H2
-H2	Base de datos embebida en memoria
-Docker	Containerización de los servicios
-JUnit & Mockito	Pruebas unitarias e integración

 Instalación y Ejecución

Prerrequisitos
Java 17+

Docker

IntelliJ IDEA

Clonar el repositorio
bash

git clone https://github.com/mpinzonxiqu/FullStackJavaSprintBoot.git



Acceso a las APIs

Productos: http://localhost:8081/api/products

Inventario: http://localhost:8082/api/inventory



   Endpoints

Productos

Método	Endpoint	Descripción

GET	/api/products	Listar productos

GET	/api/products/{id}	Obtener producto por ID

POST	/api/products	Crear nuevo producto



Inventario


Método	Endpoint	Descripción
GET	/api/inventory/{id}	Obtener cantidad por producto

PUT	/api/inventory/{id}	Actualizar cantidad disponible

POST	/api/purchase	Comprar producto y descontar inventario

Flujo de Compra
Cliente envía product_id y cantidad al endpoint de compra (/api/purchase).

El microservicio de inventario:

Verifica la existencia del producto mediante solicitud al microservicio de productos.

Consulta si hay cantidad suficiente en stock.

Descuenta la cantidad del inventario y responde con la información completa de la compra.

 Este endpoint fue implementado en el microservicio de Inventario, ya que es el responsable de validar y modificar el stock.



Ejecutar pruebas con:

bash

mvn test


📌 Decisiones Técnicas
H2 Database fue utilizada por simplicidad, velocidad de desarrollo y facilidad de pruebas.

Endpoint de compra se colocó en Inventario para seguir principios de responsabilidad única.

JSON:API fue usado para mantener consistencia y facilitar interoperabilidad.

📝 Documentación de la API

Swagger UI habilitado por defecto en:

http://localhost:8081/swagger-ui.html (Productos)


http://localhost:8082/swagger-ui.html (Inventario)

También se incluye una colección de Postman en docs/postman_collection.json.

📅 Entrega
✨ Autor : Moises Pinzon Xiques 

Backend Developer – Java Spring Boot
