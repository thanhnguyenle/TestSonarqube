pipeline {
    agent any

    environment {
        // Define environment variables for SonarQube settings
        SONARQUBE_URL = 'http://localhost:9000' // SonarQube server URL
        // Replace 'jenkin' with the correct ID of your SonarQube token in Jenkins credentials
        SONARQUBE_TOKEN = credentials('jenkin')
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Checkout code from the version control system (e.g., Git)
                git branch: 'main', url: 'https://github.com/thanhnguyenle/TestSonarqube.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven on Windows
                bat 'mvn clean install'
            }
        }

        stage('Run Unit Tests') {
            steps {
                // Run tests and ensure test results are captured
                bat 'mvn test'
            }
            post {
                always {
                    // Publish test results to Jenkins
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube scanner to analyze the code
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar -Dsonar.host.url=%SONARQUBE_URL% -Dsonar.login=%SONARQUBE_TOKEN%'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Check the Quality Gate status on SonarQube
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
