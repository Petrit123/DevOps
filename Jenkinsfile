      pipeline {
        agent any
        	tools {
        maven 'maven' 
        SonarQube Scanner 'Sonar'
    }
        stages {
          stage("build & SonarQube analysis") {
            steps {
                sh 'mvn clean package sonar:sonar'

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
      }
