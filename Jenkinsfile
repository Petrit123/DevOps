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
                  def qg = waitForQualityGate()
                  if (qg.status != 'OK') {
        error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
               // waitForQualityGate abortPipeline: true
              }
                            
            }
          }
        }
        
//        post {
            //failure {
            //	emailext body: 'This build failed', subject: 'Failure', to: 'markodonoghue230@gmail.com'
            //}
       // }
                  
      }


