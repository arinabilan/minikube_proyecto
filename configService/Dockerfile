# Usar la imagen base de OpenJDK 17 (Debian/Ubuntu)
FROM openjdk:17-buster

# Actualizar el sistema e instalar Git (en Debian/Ubuntu)
RUN apt-get update && apt-get install -y git

# Definir una variable de argumento para el archivo .jar (usando el valor predeterminado)
ARG JAR_FILE=target/configService.jar

# Copiar el archivo .jar desde tu máquina local al contenedor Docker
COPY ${JAR_FILE} /configService.jar

# Exponer el puerto donde tu aplicación estará escuchando
EXPOSE 8082

# Definir el comando que se ejecutará cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "/configService.jar"]