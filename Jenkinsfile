pipeline {
    agent any
    parameters {
        string(name: 'jijahrapip', defaultValue: 'main', description: 'Branch to build')
    }
    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout([$class: 'GitSCM',
                              branches: [[name: "refs/heads/${params.jijahrapip}"]],
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

            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying project...'
            }
        }
    }
}
