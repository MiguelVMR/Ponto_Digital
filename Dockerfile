FROM maven:3.6.3-openjdk-17
VOLUME /tmp
EXPOSE 8080
COPY target/ponto_digital-0.0.1-SNAPSHOT.jar ponto_digital.jar
ENTRYPOINT ["java","-jar","/ponto_digital.jar"]