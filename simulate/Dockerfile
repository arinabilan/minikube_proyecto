FROM openjdk:17
ARG JAR_FILE=target/simulate.jar
COPY ${JAR_FILE} simulate.jar
ENTRYPOINT ["java","-jar","/simulate.jar"]