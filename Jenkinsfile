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
					sh 'mvn verify sonar:sonar'
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
		
	}
}
