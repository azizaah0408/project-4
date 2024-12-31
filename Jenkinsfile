pipeline {
    agent any
    parameters {
        string(name: 'master', defaultValue: 'master', description: 'Branch to build')
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout([$class: 'GitSCM',
                              branches: [[name: "refs/heads/${params.master}"]],
                              userRemoteConfigs: [[
                                  url: 'https://github.com/azizaah0408/project-4.git',
                                  credentialsId: 'dc435f98-f69c-44c1-afb2-81da6df75e29'
                              ]]
                    ])
                }
            }
        }
        stage('Build') {
            steps {
                bat 'echo Building on Windows'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying project...'
            }
        }
    }
}
