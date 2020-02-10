FROM openjdk:8-jdk-alpine
ARG JAR_FILE
ADD target/Application-01.jar Application-01.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","Application-01.jar"]