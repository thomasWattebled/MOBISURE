# Stage 1: Build the Maven project
FROM maven:3.8.5-openjdk-17
WORKDIR /app
# Copy only the pom.xml first to leverage caching
COPY pom.xml .
RUN mvn dependency:go-offline
# Copy the rest of the application source code
COPY src ./src
# Build the application
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]