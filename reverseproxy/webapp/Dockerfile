## Build stage
#FROM gradle:jdk25 AS builder
#
#WORKDIR /app
#
## Copy only dependency-related files first
#COPY build.gradle settings.gradle gradlew ./
#COPY gradle gradle
#
## Download dependencies (cached layer)
#RUN gradle dependencies --no-daemon
#
## Now copy source code
#COPY src src
#
## Build app
#RUN gradle bootJar --no-daemon
#
## Runtime stage
#FROM eclipse-temurin:25-jre
#
#WORKDIR /app
#
#COPY --from=builder /app/build/libs/*.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]


# syntax=docker/dockerfile:1.7
#FROM gradle:jdk25 AS builder
#
#WORKDIR /app
#
#COPY build.gradle settings.gradle gradlew ./
#COPY gradle gradle
#
#RUN --mount=type=cache,target=/home/gradle/.gradle \
#    gradle dependencies --no-daemon
#
#COPY src src
#
#RUN --mount=type=cache,target=/home/gradle/.gradle \
#    gradle bootJar --no-daemon
#export DOCKER_BUILDKIT=1
#DOCKER_BUILDKIT=1 docker compose up --build