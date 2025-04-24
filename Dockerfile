FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
ENV client_secret=secreto
ENV key=xxxxx
ENV token_expiration_time=600
EXPOSE 8080
ADD ./build/libs/ese-msa-security-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java" , "-jar", "/app.jar"]