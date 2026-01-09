# Fase 1: Compilación
FROM eclipse-temurin:17-jdk AS build
COPY . .
RUN ./mvnw clean install -DskipTests

# Fase 2: Ejecución
FROM eclipse-temurin:17-jre
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]