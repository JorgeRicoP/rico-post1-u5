# Gestión de Tareas – Servlet, JSP, JSTL y Sesión

Proyecto Maven Web (Jakarta EE 10, Java 17, Tomcat 10.1) del laboratorio de la Unidad 5.

## Parte 1
- Modelo `Tarea` y `TareasServlet` (`/tareas`) con `doGet` y `doPost`.
- Lista de tareas en memoria: listar, agregar y eliminar.
- Validación en servidor y patrón Post/Redirect/Get.
- Vista `tareas.jsp` con JSTL.

## Ejecución
1. `mvn clean package`
2. Desplegar `target/gestion-tareas.war` en Tomcat 10.1
3. Abrir http://localhost:8080/gestion-tareas/tareas

## Parte 2
(Se completa en la siguiente parte: filtros, detalle y HttpSession.)