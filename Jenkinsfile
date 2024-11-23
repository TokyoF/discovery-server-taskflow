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
                bat './mvnw.cmd clean package -DskipTests' // Usa bat para Windows
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Construyendo la imagen Docker...'
                bat "docker build -t ${DOCKER_IMAGE} ." // Construye la imagen
            }
        }
        stage('Docker Push') {
            steps {
                echo 'Subiendo la imagen a Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    bat "docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%"
                    bat "docker push ${DOCKER_IMAGE}" // Sube la imagen a Docker Hub
                }
            }
        }
        stage('Docker Compose Down') {
            steps {
                echo 'Bajando cualquier instancia previa de Docker Compose...'
                bat 'docker rm -f discovery-server || true' // Elimina el contenedor discovery-server si existe
                bat 'docker-compose down' // Baja cualquier instancia previa de Docker Compose
            }
        }
        stage('Docker Compose Up') {
            steps {
                echo 'Levantando los contenedores con Docker Compose...'
                bat 'docker-compose up -d' // Levanta los contenedores en segundo plano
            }
        }
    }
    post {
        always {
            echo 'Finalizando el pipeline.'
        }
        success {
            echo 'Pipeline completado exitosamente.'
        }
        failure {
            echo 'Error en el pipeline.'
        }
    }
}

