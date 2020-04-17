FROM openjdk:8-jdk-alpine

ADD target/Application-01.jar Application-01.jar

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8666", "-jar", "Application-01.jar"]