FROM openjdk:8-jdk-alpine
ADD target/Application-01.jar Application-01.jar
ENTRYPOINT ["java","-jar","Application-01.jar"]