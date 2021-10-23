FROM openjdk:8-jdk-alpine
COPY target/lab-0.0.1-SNAPSHOT.jar lab-app.jar
ENTRYPOINT ["java", "-jar", "/lab-app.jar"]
