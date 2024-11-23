
# Usar una imagen base de JDK para ejecutar Spring Boot
FROM openjdk:17-jdk-alpine

# Copiar el archivo JAR generado en el proceso de construcción
COPY target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar

# Exponer el puerto de Eureka
EXPOSE 8761

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/discovery-server.jar"]
