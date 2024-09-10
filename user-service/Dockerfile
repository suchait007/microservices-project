FROM openjdk:17.0.1-jdk-slim
RUN apt-get update && apt-get install -y curl
COPY build/libs/*.jar app.jar
COPY jar/*.jar elastic-apm-agent.jar
ENTRYPOINT ["java","-javaagent:/elastic-apm-agent.jar","-jar","/app.jar"]