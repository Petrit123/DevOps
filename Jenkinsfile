node {
checkout scm

  stage('Push image to registry') {
docker.withRegistry('https://index.docker.io/v1/','DockerHub') {
    
    def customImage = docker.build("petrit123/devops")
    
    customImage.push()
}
}
}