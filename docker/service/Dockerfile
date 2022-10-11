FROM openjdk:18-jdk-oraclelinux8
FROM maven:3.8.6
#WORKDIR ./
COPY ./UserService-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java","-jar","/UserService.jar"]