FROM gradle:5.1.1-jdk8-slim as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle -q --no-daemon assemble

FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app
COPY --from=builder /home/gradle/src/build/libs/application.war /app/application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/application.jar"]