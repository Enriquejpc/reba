FROM openjdk:11.0.5-jdk-slim as builder
COPY . /app
WORKDIR /app
RUN ./gradlew clean build

FROM openjdk:11.0.5-jre-slim
COPY --from=builder /app/build/libs/*.jar /app/challenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-server", "-jar", "/app/challenge-0.0.1-SNAPSHOT.jar"]