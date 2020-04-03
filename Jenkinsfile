pipeline {

environment {
    registry = "petrit123/devops"
    registryCredential = '6b58df30-d79b-4911-86cc-8c38349acd6d'
}
	agent any
	tools {
        maven 'maven'
        docker 'org.jenkinsci.plugins.docker.commons.tools.DockerTool'
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
		sh 'docker build -t petrit123/devops:latest .'
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


