pipeline {
    agent any

    environment {
        // Setup any global variables here
        DOCKER_COMPOSE_CMD = './docker-compose'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from Git repository (Configured in the Jenkins Job)
                checkout scm
                echo 'Source code checked out successfully.'
            }
        }

        stage('Install Docker Compose') {
            steps {
                echo 'Downloading docker-compose binary into the workspace...'
                sh 'curl -sSL "https://github.com/docker/compose/releases/download/v2.24.6/docker-compose-$(uname -s)-$(uname -m)" -o docker-compose'
                sh 'chmod +x docker-compose'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                echo 'Building and starting containers using Docker Compose...'
                // Stop any existing containers to avoid conflicts
                sh "${DOCKER_COMPOSE_CMD} down"
                
                // Build the image and start up in detached mode
                sh "${DOCKER_COMPOSE_CMD} up -d --build"
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution complete.'
        }
        success {
            echo 'Success! The application should be accessible at http://localhost:8086'
            echo 'The MySQL database is accessible on port 3306'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
