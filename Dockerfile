FROM openjdk:8
VOLUME /tmp
ADD target/WeatherSpringBootApp-0.0.1-SNAPSHOT.jar WeatherSpringBootApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/WeatherSpringBootApp-0.0.1-SNAPSHOT.jar" ]
EXPOSE 8080