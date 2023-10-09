FROM openjdk:11-jre-slim

WORKDIR /app

COPY app/build/libs/*.jar app.jar

CMD ["java", "-jar", "/app/app.jar"]
