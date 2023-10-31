pipeline {
        agent any

        tools {
            // Install the Maven version configured as "M3" and add it to the path.
            maven "M3"
            nodejs "node20"
        }

        stages {
            stage('Compile Maven') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn clean compile -f server/pom.xml"
                }
            }
            stage('Test Maven') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn test -f server/pom.xml"
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
            stage('Package Maven') {
                steps {
                    // Run Maven on a Unix agent.
                    sh "mvn package -f server/pom.xml"
                }
            }
            stage('Npm install') {
                steps {
                    dir('client') {
                        sh "npm install"
                    }
                }
            }
            stage('Npm build') {
                steps{
                    dir('client') {
                        sh "npm run build"
                    }
                }
            }
        }
}
