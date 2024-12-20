# Use a base image with Java 17
FROM openjdk:17-jdk-alpine

# Set the maintainer
LABEL maintainer="rajuchinthala.rc@gmail.com"

# Create a working directory
WORKDIR /app

# Copy the WAR file built by Maven into the image
COPY target/*.war AirlineReservationSystem.war

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "AirlineReservationSystem.war"]