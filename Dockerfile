FROM gradle:7.0.2-jdk11 AS builder
COPY . .
RUN gradle build -x test

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
COPY --from=builder home/gradle/build/libs/streetfair-0.0.1-SNAPSHOT.jar app/streetfair.jar
#COPY build/libs/streetfair-0.0.1-SNAPSHOT.jar app/streetfair.jar
WORKDIR app
ENTRYPOINT ["java", "-Dspring.profiles.active=${APP_ENV}", "-jar", "streetfair.jar"]
