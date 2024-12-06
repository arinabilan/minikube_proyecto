FROM openjdk:17
ARG JAR_FILE=target/solicitude.jar
COPY ${JAR_FILE} solicitude.jar
ENTRYPOINT ["java","-jar","/solicitude.jar"]