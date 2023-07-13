# Use the official Maven image as the base image
FROM maven AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the application source code to the container
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight OpenJDK image as the base image for the final image
FROM eclipse-temurin:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage to the final image
COPY --from=builder /app/target/recipe-discovery-0.0.1-SNAPSHOT.jar .

# Expose the desired port (adjust as per your application's needs)
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "recipe-discovery-0.0.1-SNAPSHOT.jar"]
