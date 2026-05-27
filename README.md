# 🚀 Guía de Inicio Rápido - LearnHub

Este proyecto consta de un backend desarrollado en Java y un frontend desarrollado en Angular. A continuación, se detallan los pasos necesarios para desplegar y probar la aplicación en un entorno local.

---

## ☕ Arranque del Backend

### Requisitos previos:
* **JDK:** Asegúrate de tener instalado JDK 17.
* **Puerto:** El puerto 8080 debe estar libre y disponible.

### Pasos para iniciar:
1. Una vez cumplidos los requisitos, localiza el archivo principal del proyecto.
2. Ejecuta la clase LearnHubApplication.

---

## 🅰️ Arranque del Frontend

El frontend ha sido desarrollado utilizando Angular 21.

### Pasos para iniciar:
1. Accede a la carpeta "webApp" desde tu IDE de preferencia.
2. Abre la terminal o CLI y asegúrate de estar posicionado dentro del directorio "webApp".
3. Ejecuta el comando: npm start
4. Una vez compilado, abre en tu navegador la dirección IP local mostrada en la consola (localhost).

---

## 🔑 Gestión de Administradores

### Crear el primer Admin:
* Para registrar el usuario administrador principal, debes utilizar obligatoriamente el correo electrónico: admin@gmail.com
* Una vez registrado, este usuario tendrá la potestad de otorgar permisos de administrador a cualquier otro usuario del sistema.

### Funciones del Admin:
El usuario con rol de administrador tendrá acceso a un Panel de Administración exclusivo, desde el cual podrá:
* 👥 Dar permisos de administrador a otros usuarios.
* 📚 Crear nuevos cursos.
* ✏️ Editar los cursos existentes.

---

## 💳 Entorno de Pruebas (Pasarela de Pago)

Para realizar pruebas de flujo de pago dentro de la aplicación, utiliza los siguientes datos de prueba:

* Número de tarjeta: 4242 4242 4242 4242

---

## 👥 Contribuciones del Equipo

Este proyecto ha sido posible gracias a la colaboración y el esfuerzo de los siguientes miembros del equipo:

### Christian Fernández (Desarrollador Backend)
Christian ha sido responsable de la arquitectura y el desarrollo de gran parte del backend. Sus contribuciones clave incluyen:
- **Arquitectura y Tecnología:** Definición de la arquitectura hexagonal, el stack tecnológico (Java 17, Spring Boot) y la promoción de buenas prácticas como los principios SOLID.
- **Diseño del Agente:** Creación del `agentebackend.md` para guiar el desarrollo y asegurar la coherencia del código.
- **Funcionalidad Core:** Desarrollo de la lógica de negocio y los módulos de `login/register`, `cursos` y `reseñas`.
- **Base de Datos y Monitorización:** Implementación de la base de datos en memoria H2 para el desarrollo local y la integración de **Prometheus** para la monitorización de métricas.
- **Pasarela de Pagos:** Integración de la API de **Stripe** para procesar los pagos de los cursos.
- **Documentación y Pruebas:** Creación de la documentación de la API con **Swagger** y realización de pruebas de los endpoints con **Postman**.

### Christian Carr-Rolitt (Diseñador Frontend)
Christian ha sido el encargado de dar forma a la experiencia de usuario y al aspecto visual de la aplicación. Su foco principal ha sido:
- **Diseño de la Experiencia de Usuario:** Creación de una interfaz intuitiva y atractiva utilizando **Angular**, definiendo el layout, la paleta de colores y la navegación general.
- **Maquetación y Estilos:** Traducción de los conceptos de diseño a componentes visuales, asegurando una presentación coherente y atractiva en toda la plataforma.

### David (Desarrollador Frontend)
David ha sido el responsable de la implementación técnica y la lógica del lado del cliente. Sus principales tareas han incluido:
- **Implementación de Componentes:** Desarrollo de los componentes de Angular, la gestión del estado del carrito y la lógica de las vistas.
- **Conexión con el Backend:** Integración de los servicios para consumir la API del backend, permitiendo que la interfaz muestre datos dinámicos y envíe información al servidor.
- **Flujo de Usuario:** Programación de la interactividad de las vistas de registro, inicio de sesión, detalles de curso y el panel de administración.

### Daria (Diseñadora de Base de Datos y Desarrolladora Backend)
Daria ha jugado un papel fundamental en la estructura de datos y la lógica de negocio del lado del servidor. Sus aportaciones principales son:
- **Diseño del Modelo de Datos:** Definición de la estructura de las tablas, las relaciones entre entidades (usuarios, cursos, compras, etc.) y la integridad de la base de datos.
- **Funcionalidad de Administrador:** Desarrollo en el backend de la lógica de negocio para el rol de administrador, incluyendo la gestión de permisos y la supervisión de la plataforma.
