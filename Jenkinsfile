pipeline{
    agent any

    tools {
        maven 'maven-3.8.4'
        jdk 'jdk-17'
    }

    stages{
        stage("scm checkout"){
            steps{
              sh 'git clone https://github.com/SivaSuribabu/java-nexus-project.git'
            }
        }

        stage("build"){
            steps{
                sh 'mvn clean install -DskipTests'
            }
        }
    }
}