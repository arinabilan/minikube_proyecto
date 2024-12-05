FROM openjdk:17
ARG JAR_FILE=target/configService.jar
COPY ${JAR_FILE} configService.jar
ENTRYPOINT ["java","-jar","/configService.jar"]