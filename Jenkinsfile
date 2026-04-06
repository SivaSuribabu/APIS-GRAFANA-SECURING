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

    stage('Build & Deploy') {
    steps {
        script {
            def DATE = new Date().format('ddMMyyyy')
            def VERSION = "${DATE}-${env.BUILD_NUMBER}"

            configFileProvider([configFile(fileId: 'global-maven-setting', variable: 'MAVEN_SETTINGS')]) {
                sh """
                mvn clean deploy \
                -Drevision=${VERSION} \
                -s $MAVEN_SETTINGS \
                -DskipTests
                """
            }
        }
    }
}

    stage('Deploy to Tomcat') {
    steps {
        sh '''
        # stop existing app (optional)
        sudo systemctl stop tomcat10

        # copy JAR file
        sudo rm -rf /var/lib/tomcat10/webapps/ROOT/*
        sudo cp /var/lib/jenkins/workspace/java-nexus/target/deployment-app.jar /var/lib/tomcat10/webapps/ROOT
        # start tomcat
        sudo systemctl start tomcat10
        '''
    }
}
    }
}