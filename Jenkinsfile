pipeline {

environment {
    registry = "petrit123/devops"
    registryCredential = "docker"
}
	agent any
	tools {
        maven 'maven' 
    }
	stages {
		stage ('Compile Stage') {
			
			steps {
					sh 'mvn clean compile'
			}
		}
		
		stage ('Testing Stage') {
			
			steps {
					sh 'mvn test'
			}
		}
				stage ('SonarCloud Analysis') {
			
			steps {
withSonarQubeEnv('sonar') {
sh 'mvn clean package sonar:sonar'
}

}
		}
			
		stage ('Build Stage') {
		 steps {
		sh 'mvn package'
		}
		}
		
		stage ('Push to Nexus') {
			steps {
			sh 'mvn deploy'
			}
		}
		
		stage ('Build docker image') {
		steps {
		       script {
		withDockerRegistry(credentialsId: '6b58df30-d79b-4911-86cc-8c38349acd6d', toolName: 'docker', url: 'https://hub.docker.com/repository/docker/petrit123/devops') {
		'docker build -t petrit123/devops:latest .'
}
		}
		}
		
		}
		
		
	}
	post {
		         success {  
               emailext body: 'The build succeeded', subject: 'Build Success', to: 'petritt.k@gmail.com'
         }  
		
		   failure {
			   emailext body: 'The build failed', subject: 'Build failure', to: 'petritt.k@gmail.com'
		   }
		   
	}
}
