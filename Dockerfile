FROM openjdk:17-jdk-slim

WORKDIR /app

COPY out/artifacts/distinction_jar/distinction.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
