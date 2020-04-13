pipeline {
   environment {
    registry = "petrit123/devops"
    registryCredential = "DockerHub"
    dockerImage = ''
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
		    dockerImage = docker.build registry + ":$BUILD_NUMBER"
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
			
			stage('Deploy to production'){
            steps {               
             sshPublisher(publishers: [sshPublisherDesc(configName: 'Ansible', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '/usr/local/bin/ansible-playbook -e JENKINS_BUILD_NUMBER=$BUILD_NUMBER -i /etc/ansible/hosts /etc/ansible/main.yml', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '', usePty: true)], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
		
	}
	post {
		         success {  
               emailext body: '', subject: '', to: 'petritt.k@gmail.com'
         }  
		
		   failure {
			   emailext body: '', subject: '', to: 'petritt.k@gmail.com'
		   }
		   
	}
}
