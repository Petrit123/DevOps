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
             sshPublisher(publishers: [sshPublisherDesc(configName: 'Ansible', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '/usr/local/bin/ansible-playbook -i hosts /etc/ansible/main.yml', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
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
