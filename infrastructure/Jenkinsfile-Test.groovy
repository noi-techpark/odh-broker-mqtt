pipeline {
    agent any

    environment {
        DOCKER_PROJECT_NAME = "odh-broker-mqtt"
        DOCKER_IMAGE = "eclipse-mosquitto"
        DOCKER_TAG = "2.0.12"

        SERVER_PORT = "1883"

        MOSQUITTO_CONF = "./docker/mosquitto/mosquitto.conf"
        MOSQUITTO_INCLUDE_DIR = "./docker/mosquitto/conf.d"
        MOSQUITTO_PORT = "1883"
    }

    stages {
        stage('Configure') {
            steps {
                sh """
                    rm -f .env
                    cp env.example .env
                    echo '' >> .env
                    echo 'COMPOSE_PROJECT_NAME=${DOCKER_PROJECT_NAME}' >> .env
                    echo 'DOCKER_IMAGE=${DOCKER_IMAGE}' >> .env
                    echo 'DOCKER_TAG=${DOCKER_TAG}' >> .env

                    echo 'SERVER_PORT=${SERVER_PORT}' >> .env

                    echo 'MOSQUITTO_CONF=${MOSQUITTO_CONF}' >> .env
                    echo 'MOSQUITTO_INCLUDE_DIR=${MOSQUITTO_INCLUDE_DIR}' >> .env
                    echo 'MOSQUITTO_PORT=${MOSQUITTO_PORT}' >> .env
                """
            }
        }

        stage('Test') {
            steps {
                sh '''
                    echo "There are no tests in this project"
                '''
            }
        }
        stage('Build') {
            steps {
                sh '''
                    echo "There is no build step in this project"
                '''
            }
        }
        stage('Deploy') {
            steps {
               sshagent(['jenkins-ssh-key']) {
                    sh """
                        (ls)
                    """
                }
            }
        }
    }
}