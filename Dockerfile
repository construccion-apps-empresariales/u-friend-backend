FROM maven:3.8.3-openjdk-17
ARG JAR_FILE=target/*.jar
RUN "./mvnw clean package"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]