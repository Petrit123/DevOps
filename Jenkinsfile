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
              try {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
                                } catch (err) {
                  emailext body: 'The build failed', subject: 'Build Failure', to: 'petritt.k@gmail.com'
            }
            }
          }
        }
                  
      }
