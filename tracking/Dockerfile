FROM openjdk:17
ARG JAR_FILE=target/tracking.jar
COPY ${JAR_FILE} tracking.jar
ENTRYPOINT ["java","-jar","/tracking.jar"]