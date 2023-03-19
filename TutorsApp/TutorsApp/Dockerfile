# Use the official Gradle image with JDK 19 (Jammy release)
FROM gradle:7.6.0-jdk19-jammy AS build

# Set the working directory to /app
WORKDIR /app

# Copy the Gradle build files to the container
COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

# Download the dependencies needed to build the application
RUN ./gradlew --no-daemon --version

# Copy the rest of the application source code to the container
COPY src /app/src

# Build the application JAR file
RUN ./gradlew --no-daemon build --stacktrace

# Use a base image with the same version of Java as your app
FROM openjdk:19-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file to the container
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 4565

# Start the application when the container launches
CMD ["java", "-jar", "app.jar"]