# Stage 1: Build the application
FROM maven:3.9.8-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Download and set up the Maven wrapper
RUN mvn -N io.takari:maven:wrapper

# Copy the Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download the dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-alpine

# Set the maintainer
LABEL maintainer="rajuchinthala.rc@gmail.com"

# Create a working directory
WORKDIR /app

# Copy the built WAR file from the build stage
COPY --from=build /app/target/AirlineReservationSystem.war /app/AirlineReservationSystem.war

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "AirlineReservationSystem.war"]