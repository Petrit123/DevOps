node {
checkout scm

docker.withDockerRegistry(credentialsId: '6b58df30-d79b-4911-86cc-8c38349acd6d', url: 'https://hub.docker.com/repository/docker/petrit123/devops') {
    
    def customImage = docker.build("petrit123/devops")
    
    customImage.push()
}
}