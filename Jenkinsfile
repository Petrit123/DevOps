pipeline {
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
withSonarQubeEnv('Petrit12345') {

 
sh 'mvn clean package sonar:sonar'
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
nexusArtifactUploader artifacts: [[artifactId: 'PlaylistApplication', classifier: '', file: 'pom.xml', type: '.zip']], credentialsId: '76d13b2b-4b62-4d32-8451-b8cfea700553', groupId: 'com.PlaylistApplication', nexusUrl: '18.202.248.194:8081/', nexusVersion: 'nexus3', protocol: 'http', repository: 'PlaylistApplication', version: '0.0.1-SNAPSHOT'
			}
		}
		
	}
	post {
		   failure {
			   emailext body: 'The build failed', subject: 'Build failure', to: 'petritt.k@gmail.com'
		   }
	}
}