
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 9000
EXPOSE 9000

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
