### First stage: create gradle cache
FROM gradle:5.1.1-jdk8-slim as cache-builder

# Copy files needed to download dependencies
RUN mkdir -p /home/gradle/src/grails-app/init/example/
COPY --chown=gradle:gradle gradle.properties /home/gradle/src/
COPY --chown=gradle:gradle build.gradle /home/gradle/src/
COPY --chown=gradle:gradle grails-app/init/example/Application.groovy /home/gradle/src/grails-app/init/example/Application.groovy

WORKDIR /home/gradle/src
# Run gradle without daemon, with build cache enabled and with custom cache dir inside WORKDIR
RUN gradle assemble --no-daemon --build-cache --gradle-user-home=/home/gradle/src/.gradle

### Second stage: builder container
FROM gradle:5.1.1-jdk8-slim as builder

# Copy cache contents from cache-builder into WORKDIR
COPY --from=cache-builder --chown=gradle:gradle /home/gradle/src/.gradle/caches /home/gradle/src/.gradle/caches
# Copy code into WORKDIR
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Run gradle offline, without daemon, with build cache enabled and custom cache dir inside WORKDIR
RUN gradle assemble --offline --no-daemon --build-cache --gradle-user-home=/home/gradle/src/.gradle

### Third stage: standalone application container
FROM openjdk:8-jre-slim

EXPOSE 8080

RUN mkdir /app
COPY --from=builder /home/gradle/src/build/libs/application.war /app/application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/application.jar"]