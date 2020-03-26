node {
       
       def mvnHome = tool name: 'maven', type: 'maven'

       stage ('Compile Stage') {

              sh "${mvnHome}/bin/mvn clean compile"
       }
      
       stage ('Testing Stage') {
             sh "${mvnHome}/bin/mvn test"

       }
       
             stage ('SonarQube analysis') {
      sh "${mvnHome}/bin/mvn verify sonar:sonar"
      }
       
       //stage ('Deploy Stage') {
        //sh "${mvnHome}/bin/mvn deploy"
       //}
}