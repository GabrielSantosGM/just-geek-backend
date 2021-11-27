pipeline {
    agent any
    stages {
        stage('Info Maven') {
            steps {
                sh "mvn -v"
            }
        }
        
        stage('Compile and Clean') {
            steps {
                sh "mvn clean compile"
            }
        }

        stage('Generate Package') {
            steps {
                sh "mvn package"
            }
        }

        stage('Clean everything') {
            steps {
                sh "docker system prune --all --volumes --force"
            }
        }

        stage('Build Docker image'){
            steps {
                sh 'docker build -t akinicchi/just-geek-backend:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            steps {
                withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u akinicchi -p ${Dockerpwd}"
                }
            }
        }

        stage('Docker Push'){
            steps {
                sh 'docker push akinicchi/just-geek-backend:${BUILD_NUMBER}'
            }
        }

        stage('Stop container in use'){
            steps {
                sh "docker stop just_geek"
                sh "docker rm just_geek"
            }
        }

        stage('Docker deploy'){
            steps {
                sh "docker run -itd -p 80:8083 --name just_geek akinicchi/just-geek-backend:${BUILD_NUMBER}"
            }
        }

        stage('Archving') {
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
