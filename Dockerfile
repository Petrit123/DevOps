FROM openjdk:8
EXPOSE 8085
ADD target/PlaylistApplication-docker.jar PlaylistApplication-docker.jar
ENTRYPOINT ["java", "-jar", "/PlaylistApplication-docker.jar"]