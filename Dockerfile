FROM maven:3.6.0-jdk-11
MAINTAINER Gabriel Santos

WORKDIR /vvv/www
COPY target/*.jar /vvv/www/app.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","app.jar"]