FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/dckr_db-0.0.1-SNAPSHOT.jar
WORKDIR /opr/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]