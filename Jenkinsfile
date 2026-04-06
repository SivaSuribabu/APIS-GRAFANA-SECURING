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

    stage('Deploy to Nexus') {
        steps {
            configFileProvider([configFile(fileId: 'global-maven-setting', variable: 'MAVEN_SETTINGS')]) {
                sh '''
                mvn clean deploy \
                -s $MAVEN_SETTINGS \
                -DskipTests
                '''
            }
        }
    }
    }
}