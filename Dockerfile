FROM gradle:jdk11-alpine AS builder
COPY . /app
WORKDIR /app
RUN gradle bootJar --no-daemon
RUN ls -lrthF /app/build/libs

FROM openjdk:11.0.5-jre-slim

COPY --from=builder /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-server", "-jar", "/app/app.jar"]