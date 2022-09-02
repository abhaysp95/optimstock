# stage1
# base image containing java runtime
FROM openjdk:17-jdk-slim as build

# add maintainer info
LABEL maintainer="Abhay Shanker Pathak <abhaysp9955@gmail.com>"

# The application's jar file
ARG JAR_FILE

# add the application's jar to the container
COPY ${JAR_FILE} app.jar

# unpackage jar file
# unpacks the app.jar copied in previous step into the filesystem of the build image
RUN mkdir -p target/dependency &&  \
	(cd target/dependency; jar -xf /app.jar)

# stage2
# this new image contains the different layers of a spring boot app instead of the complete jar file

# same java runtime
FROM openjdk:17-jdk-slim

# add volume pointint to /tmp
VOLUME /tmp

# copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# execute the application
ENTRYPOINT [ "java", "-cp", "app:app/lib/*", "com.optimgrowth.license.LicenseServiceApplication" ]
