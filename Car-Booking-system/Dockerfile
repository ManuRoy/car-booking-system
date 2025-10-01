# Step 1: Use a lightweight JDK 17 base image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy the built Spring Boot JAR into the container
COPY target/Car-Booking-system-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the port Spring Boot runs on
EXPOSE 9090

# Step 5: Run the Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]
