#
# BUILD STAGE
#
FROM maven:3-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests


#
# PACKAGE STAGE
#
FROM eclipse-temurin:21-alpine
COPY --from=build /target/*.jar /app/app.jar
EXPOSE 8003
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -Dserver.port=8003 -Duser.timezone=UTC -jar /app/app.jar"]

