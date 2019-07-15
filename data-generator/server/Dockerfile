FROM hirokimatsumoto/alpine-openjdk-11:latest

VOLUME /tmp

COPY target/server-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]