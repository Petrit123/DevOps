FROM openjdk:8
EXPOSE 8085
ADD target/playlistapplication-docker.war playlistapplication-docker.war
ENTRYPOINT ["java", "-jar", "/playlistapplication-docker.war"]