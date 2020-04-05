node {
checkout scm

  stage('Push image to registry') {
docker.withRegistry('https://hub.docker.com/repository/docker/petrit123/devops','DockerHub') {
    
    def customImage = docker.build("petrit123/devops")
    
    customImage.push()
}
}
}