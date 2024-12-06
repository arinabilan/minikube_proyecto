FROM openjdk:17
ARG JAR_FILE=target/loanEvaluation.jar
COPY ${JAR_FILE} loanEvaluation.jar
ENTRYPOINT ["java","-jar","/loanEvaluation.jar"]