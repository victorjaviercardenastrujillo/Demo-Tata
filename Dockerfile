# Fase de construcción (opcional)
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /workspace
COPY . .
RUN ./gradlew bootJar -x test --no-daemon

# Fase de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia el JAR desde la ruta exacta de Windows (adaptado para Docker)
COPY build/libs/demo-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]