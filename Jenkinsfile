pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 't0ky0le/discovery-server:1.0' // Nombre de la imagen en Docker Hub
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Clonando el repositorio...'
                git branch: 'main', url: 'https://github.com/TokyoF/discovery-server-taskflow.git' // Cambia con tu URL
            }
        }
        stage('Build') {
            steps {
                echo 'Construyendo el proyecto con Maven...'
                sh './mvnw clean package -DskipTests' // Usa sh para Linux/Mac, bat para Windows
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Construyendo la imagen Docker...'
                sh "docker build -t ${DOCKER_IMAGE} ." // Construye la imagen
            }
        }
        stage('Docker Push') {
            steps {
                echo 'Subiendo la imagen a Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                    sh "docker push ${DOCKER_IMAGE}" // Sube la imagen a Docker Hub
                }
            }
        }
    }
    post {
        always {
            echo 'Pipeline completado.'
        }
    }
}
