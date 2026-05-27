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

* Número de tarjeta: 4242 4242 4242
