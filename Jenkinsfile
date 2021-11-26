pipeline {
    agent any
    stages {
        stage('Restart docker') {
            steps {
                sh "sudo systemctl restart docker"
            }
        }

        stage('Info') {
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

        stage('Docker deploy'){
            steps {
                sh 'docker run -itd -p 8081:8083 akinicchi/just-geek-backend:${BUILD_NUMBER}'
            }
        }


        stage('Archving') {
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
