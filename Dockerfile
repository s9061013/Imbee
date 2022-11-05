FROM openjdk:11.0.13
ADD application.yml application.yaml
COPY ./target/*.jar imbee.jar
EXPOSE 8081
CMD java -jar imbee.jar --spring.config.location=application.yaml