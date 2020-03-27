    pipeline {
        agent any
         	tools {
        maven 'maven' 
            }
        stages {
          stage("build & SonarQube analysis") {
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
        }
        
        post {
            failure {
            	emailext body: 'This build failed', subject: 'Failure', to: 'petritt.k@gmail.com'
            }
        }
                  
      }


