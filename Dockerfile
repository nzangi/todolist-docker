FROM openjdk:21-jdk

RUN mvn clean package


WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/todolist.jar

EXPOSE 8222

ENTRYPOINT ["java","-jar", "todolist.jar"]

