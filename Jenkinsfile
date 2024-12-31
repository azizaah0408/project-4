pipeline {
    agent any
    parameters {
        string(name: 'jijahrapip', defaultValue: 'main', description: 'Branch to build')
    }
    stages {
        stage('Checkout') {
            steps {
                // Menggunakan parameter BRANCH_NAME untuk memilih branch
                git branch: "${params.jijahrapip}", url: 'https://github.com/azizah/myproject.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying project...'
                // Langkah deploy lainnya
            }
        }
    }
}
