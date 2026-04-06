pipeline{
    agent any

    tools {
        maven 'maven-3.8.4'
        jdk 'jdk-17'
    }

    stages{
        stage("scm checkout"){
            steps{
              checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/SivaSuribabu/java-nexus-project.git']])
            }
        }

        stage("build"){
            steps{
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('mvn deploy'){
            steps{
                sh 'mvn deploy -DskipTests'
            }
        }
    }
}