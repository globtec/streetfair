FROM gradle:7.0.2-jdk11 AS builder
COPY . .
RUN gradle build

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
COPY --from=builder home/gradle/build/libs/streetfair-0.0.1-SNAPSHOT.jar app/streetfair.jar
WORKDIR app
ENTRYPOINT ["java", "-jar", "streetfair.jar"]

