FROM openjdk:17
ARG JAR_FILE=target/eurekaService.jar
COPY ${JAR_FILE} eurekaService.jar
ENTRYPOINT ["java","-jar","/eurekaService.jar"]