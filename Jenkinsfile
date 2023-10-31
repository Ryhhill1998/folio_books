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
            stage('Package') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn package"
                }
            }
        }
}
