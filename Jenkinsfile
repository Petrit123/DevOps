pipeline {
        stage('Deploying test'){
            steps {
                dir('deployment'){
                    echo 'Deploying to test'
                    sh 'ansible-playbook -i dev-servers site.yml'
                }
            }
        }
}
