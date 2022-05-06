FROM openjdk:8
VOLUME /tmp 
COPY target/todo-list-api-0.0.1-SNAPSHOT.jar todo-list-api.jar 
ENTRYPOINT ["java","-jar","todo-list-api.jar"]
EXPOSE 8081