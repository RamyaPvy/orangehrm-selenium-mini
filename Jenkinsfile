pipeline {
  agent { label 'Jenkins-Agent' }        // must match your agent's label exactly

  tools {
    jdk   'Java17'                        // must match name in Manage Jenkins → Tools
    maven 'Maven3'                       // case-sensitive; change if your tool is named differently
  }

  environment {
    CI = 'true'                          // headless mode + no slow pauses
  }

  stages {

    stage('Cleanup workspace') {
      steps {
        // Requires "Workspace Cleanup" plugin. If missing, use: deleteDir()
        cleanWs()
      }
    }

    stage('Checkout from SCM') {
      steps {
        git branch: 'main',
            credentialsId: 'github',     // must match your Jenkins credentials ID
            url: 'https://github.com/RamyaPvy/orangehrm-selenium-mini.git'
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn -B -q clean test'        // use sh on Ubuntu agents; use bat on Windows
      }
    }

    stage('Reports') {
      steps {
        junit allowEmptyResults: true, testResults: '**/surefire-reports/*.xml'
        archiveArtifacts artifacts: '**/surefire-reports/**, **/test-output/**', fingerprint: true
      }
    }

  }

  post {
    always {
      echo 'Pipeline finished ✅'
    }
  }
}
