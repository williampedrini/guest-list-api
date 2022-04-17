FROM adoptopenjdk/openjdk11:slim
MAINTAINER "William Custodio"

ENV PORT 8080
EXPOSE 8080

COPY target/guest-list-api.jar /opt/application.jar

WORKDIR /opt
CMD ["sh", "-c", "java $APPLICATION_ARGS -jar application.jar"]
