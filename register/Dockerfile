FROM openjdk:17
ARG JAR_FILE=target/register.jar
COPY ${JAR_FILE} register.jar
ENTRYPOINT ["java","-jar","/register.jar"]