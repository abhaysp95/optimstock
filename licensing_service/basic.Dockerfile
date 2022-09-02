# base image containing java runtime
# docker image to use in our docker run time
FROM openjdk:17-jdk-slim

# add maintainer info
LABEL maintainer="Abhay Shanker Pathak <abhaysp9955@gmail.com>"

# The application's jar file
# defines JAR_FILE variable set by dockerfile-maven-plugin
# if this variable is not set (in pom.xml) you can set it here too
ARG JAR_FILE

# add the application's jar to the container
COPY ${JAR_FILE} app.jar

# execute the application
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
