FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN "./mvnw clean package"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]