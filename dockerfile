# Use a small JDK 17 image
FROM eclipse-temurin:17-jdk-jammy as build

WORKDIR /app

# Copy Maven wrapper and pom
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Give execute permission to mvnw
RUN chmod +x mvnw

# Download dependencies and build the jar (tests skipped to speed up)
COPY src src
RUN ./mvnw clean package -DskipTests

# ---- Runtime image ----
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Start Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
