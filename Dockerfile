FROM openjdk:17
ADD target/Microservice.jar Microservice.jar
ENTRYPOINT ["java", "-jar", "Microservice.jar"]
