pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/neekhu/jgsu-spring-petclinic.git',  branch: 'main'
            }
        }
        stage('Build') {
            steps{
                bat 'mvn clean compile'
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
