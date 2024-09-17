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
                // Navigate to 'demo' folder and run the Maven build command on Windows
                bat '''
                    cd demo
                    mvn clean install
                '''
            }
        }
                     
        // stage('Run Unit Tests') {
        //     steps {
        //         // Navigate to 'demo' folder and run the tests
        //         bat '''
        //             cd demo
        //             mvn surefire-report:reportf
        //         '''
        //     }
        //     // post {
        //     //     always {
        //     //         // Publish test results to Jenkins
        //     //         junit 'demo/target/reports/surefire.html'
        //     //     }
        //     // }
        // }

        stage('SonarQube Analysis') {
            steps {
                // Navigate to 'demo' folder and run SonarQube analysis
                withSonarQubeEnv('SonarQube') {
                    bat '''
                        cd demo
                        mvn sonar:sonar -Dsonar.host.url=%SONARQUBE_URL% -Dsonar.login=%SONARQUBE_TOKEN%
                    '''
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
