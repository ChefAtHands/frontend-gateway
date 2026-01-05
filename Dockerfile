FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/frontend-gateway-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]