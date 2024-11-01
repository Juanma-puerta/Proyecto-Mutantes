# Usa una imagen de OpenJDK como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
# Asegúrate de que `target/mi-aplicacion.jar` coincida con el nombre del archivo JAR generado por tu proyecto
COPY target/apirest.jar app.jar

# Expone el puerto que usa tu aplicación (por defecto, Spring Boot usa el puerto 8080)
EXPOSE 9000

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
