pipeline {

environment {
    registry = "petrit123/devops"
    registryCredential = "docker"
}
	agent any
	tools {
        maven 'maven' 
        docker 'docker'
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
		withDockerRegistry(credentialsId: '4b399007-197d-4bc9-b10f-4ef417cad31c', toolName: 'docker', url: 'https://hub.docker.com/repository/docker/petrit123/devops') {
		'docker build -t petrit123/playlistapplication:latest .'
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
