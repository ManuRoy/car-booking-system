pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred')  
        IMAGE_NAME = "manu622/car-booking-system"   // Your Docker Hub repo
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ManuRoy/car-booking-system.git'
            }
        }

        stage('Build JAR') {
            steps {
                // Run Maven to build Spring Boot JAR inside target/
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                // Build image using Dockerfile (which copies target/*.jar to app.jar)
                sh """
                docker build -t $IMAGE_NAME:\$BUILD_NUMBER .
                """
            }
        }

        stage('Push to Docker Hub') {
            steps {
                sh """
                echo "$DOCKERHUB_CREDENTIALS_PSW" | docker login -u "$DOCKERHUB_CREDENTIALS_USR" --password-stdin
                docker push $IMAGE_NAME:\$BUILD_NUMBER
                docker tag $IMAGE_NAME:\$BUILD_NUMBER $IMAGE_NAME:latest
                docker push $IMAGE_NAME:latest
                """
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
}
