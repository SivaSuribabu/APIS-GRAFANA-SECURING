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
                withCredentials([usernamePassword(credentialsId: 'nexus-creds', passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                    sh "mvn deploy -DskipTests -Dnexus.username=${NEXUS_USERNAME} -Dnexus.password=${NEXUS_PASSWORD}"
                }
            }
        }
    }
}