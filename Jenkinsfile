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
        stage('Deploying test'){
            steps {
                dir('deployment'){
                    echo 'Deploying to test'
                    sh '/usr/local/bin/ansible-playbook -i hosts main.yml'
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
