FROM openjdk:latest
EXPOSE 8082
ADD target/building.jar building.jar
ENTRYPOINT ["java","-jar","building.jar"]