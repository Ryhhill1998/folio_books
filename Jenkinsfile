pipeline {
        agent any

        stages {
            stage('Compile') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn clean compile"
                }
            }
            stage('Test') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn test"
                }
            }
            stage('SonarQube') {
                environment {
                    scannerHome = tool 'sonarqube'
                }

                steps {
                    withSonarQubeEnv('sonar-qube-1') {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
            }
            stage('Package') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn package"
                }
            }
        }
}
