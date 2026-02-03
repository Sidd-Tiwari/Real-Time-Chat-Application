FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/real-time-chat-app-0.0.1-SNAPSHOT.jar"]
