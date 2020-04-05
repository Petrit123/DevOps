node {
checkout scm

  stage('Push image to registry') {
docker.withDockerRegistry('https://hub.docker.com/repository/docker/petrit123/devops','6b58df30-d79b-4911-86cc-8c38349acd6d') {
    
    def customImage = docker.build("petrit123/devops")
    
    customImage.push()
}
}
}