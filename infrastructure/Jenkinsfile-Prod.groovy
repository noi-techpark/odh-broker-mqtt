pipeline {
    agent any

    environment {
        DOCKER_PROJECT_NAME = "odh-broker-mqtt"
        DOCKER_IMAGE = "755952719952.dkr.ecr.eu-west-1.amazonaws.com/odh-broker-mqtt"
        DOCKER_TAG = "test-$BUILD_NUMBER"

        SERVER_PORT = "1883"

        AUTH_USER = credentials('odh-broker-mqtt-test-auth-username')
        AUTH_PASSWORD = credentials('odh-broker-mqtt-test-auth-password')
        TOPIC_ANONYMOUS = credentials('odh-broker-mqtt-test-topic-anonymous')
        TOPIC_RESTRICTED = credentials('odh-broker-mqtt-test-topic-restricted')
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

                    echo 'AUTH_USER=${AUTH_USER}' >> .env
                    echo 'AUTH_PASSWORD=${AUTH_PASSWORD}' >> .env
                    echo 'TOPIC_ANONYMOUS=${TOPIC_ANONYMOUS}' >> .env
                    echo 'TOPIC_RESTRICTED=${TOPIC_RESTRICTED}' >> .env
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
                        (cd infrastructure/ansible && ansible-galaxy install -f -r requirements.yml)
                        (cd infrastructure/ansible && ansible-playbook --limit=test deploy.yml --extra-vars "release_name=${BUILD_NUMBER}")
                    """
                }
            }
        }
    }
}