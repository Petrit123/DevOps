FROM openjdk:8
ADD target/PlaylistApplication-0.0.1-SNAPSHOT.war PlaylistApplication-0.0.1-SNAPSHOT.war
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/PlaylistApplication-0.0.1-SNAPSHOT.war"]