FROM openjdk:8-jre-alpine

WORKDIR /var/david-apis

COPY target/cabin-0.0.1-SNAPSHOT.jar /var/david-apis/app.jar
COPY config.yml /var/david-apis/config.yml

EXPOSE 19000 19001

ENTRYPOINT ["java", "-jar", "app.jar", "server", "config.yml"]
