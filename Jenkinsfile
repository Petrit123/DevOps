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
nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'PlaylistApplication', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'pom.xml']], mavenCoordinate: [artifactId: 'PlaylistApplication', groupId: 'com.PlaylistApplication', packaging: 'war', version: '0.0.1-SNAPSHOT']]]
			}
		}
		
	}
	post {
		   failure {
			   emailext body: 'The build failed', subject: 'Build failure', to: 'petritt.k@gmail.com'
		   }
	}
}
