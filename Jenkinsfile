pipeline {
   environment {
    registry = "petrit123/devops"
    registryCredential = "DockerHub"
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
          stage("SonarQube analysis") {
            steps {
              withSonarQubeEnv('sonar') {
                sh 'mvn sonar:sonar'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
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
		
		stage ('Build Docker Image') {
		 steps {
		    script {
		    docker.build registry + ":$BUILD_NUMBER"
		    }
		   }
		  } 
		  stage ('Push Image to registry') {
		   steps {
		    script {
		    docker.withRegistry('https://index.docker.io/v1/','DockerHub') {
		    dockerImage.push()
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
