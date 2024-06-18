FROM openjdk:21-jdk

# Build the project with Maven
#RUN mvn clean package

# Set the working directory for the application
WORKDIR /app

# Specify the JAR file to be used
ARG JAR_FILE=target/*.jar

# Copy the built JAR file to the /app directory
COPY ${JAR_FILE} /app/todolist.jar

# Expose the application's port
EXPOSE 8222

# Run the application
ENTRYPOINT ["java","-jar", "todolist.jar"]

