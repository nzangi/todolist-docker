FROM openjdk:21-jdk


WORKDIR /app


COPY target/todolist-docker-0.0.1-SNAPSHOT.jar /app/todolist.jar
EXPOSE 8222

ENTRYPOINT ["java","-jar", "todolist.jar"]

