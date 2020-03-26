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
			}
		}
		stage ('Build Stage') {
			
			steps {
					sh 'mvn package'
			}
		}
		
		      stage("Quality Gate"){
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
      }
		
	}
}
