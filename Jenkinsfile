pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred')
        IMAGE_NAME = "manu622/car-booking-system"
        APP_DIR = "car-booking-system/car-booking-system"  // folder containing pom.xml, mvnw, Dockerfile
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/ManuRoy/car-booking-system.git'
            }
        }

        stage('Build JAR') {
            steps {
                dir("${APP_DIR}") {
                    script {
                        // Ensure mvnw is executable
                        if (fileExists('mvnw')) {
                            sh 'chmod +x mvnw'
                            sh './mvnw clean package -DskipTests'
                        } else {
                            echo "mvnw not found, using system Maven"
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${APP_DIR}") {
                    sh """
                    docker build -t $IMAGE_NAME:\$BUILD_NUMBER .
                    """
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                dir("${APP_DIR}") {
                    sh """
                    echo "$DOCKERHUB_CREDENTIALS_PSW" | docker login -u "$DOCKERHUB_CREDENTIALS_USR" --password-stdin
                    docker push $IMAGE_NAME:\$BUILD_NUMBER
                    docker tag $IMAGE_NAME:\$BUILD_NUMBER $IMAGE_NAME:latest
                    docker push $IMAGE_NAME:latest
                    """
                }
            }
        }

        stage('Deploy to Target') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no user@TARGET_SERVER "
                  docker stop springboot-app || true &&
                  docker rm springboot-app || true &&
                  docker pull $IMAGE_NAME:\$BUILD_NUMBER &&
                  docker run -d --name springboot-app -p 9090:9090 $IMAGE_NAME:\$BUILD_NUMBER
                "
                """
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed! Check the logs."
        }
        success {
            echo "Pipeline completed successfully!"
        }
    }
}
