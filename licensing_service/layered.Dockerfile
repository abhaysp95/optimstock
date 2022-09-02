FROM openjdk:17-jdk-slim as build

WORKDIR application

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

RUN java -Djarmode=layertools -jar application.jar extract

# ------------------------------------------------------

FROM openjdk:17-jdk-slim

WORKDIR application

# copies each layer displayed as a result of the jarmode command
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./

# execute the application
ENTRYPOINT [ "java", "org.springframework.boot.loader.JarLauncher" ]
