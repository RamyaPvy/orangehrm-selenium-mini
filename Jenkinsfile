pipeline {
  agent { label 'Jenkins-Agent' }     // must match your node label

  // If you mapped tool locations on the agent: Java17 -> /usr/lib/jvm/java-17-openjdk-amd64, Maven3 -> /usr/share/maven
  tools {
    jdk   'Java17'                    // name must match Manage Jenkins → Global Tool Configuration
    maven 'Maven3'
  }

  environment {
    CI = 'true'                       // your DriverFactory should switch to headless when CI=true
  }

  options {
    timestamps()
    ansiColor('xterm')
  }

  stages {

    stage('Cleanup workspace') {
      steps {
        // If you don't have the Workspace Cleanup plugin, replace cleanWs() with deleteDir()
        cleanWs()
      }
    }

    stage('Checkout from SCM') {
      steps {
        git branch: 'main',
            credentialsId: 'github',  // Jenkins credential ID for your GitHub PAT
            url: 'https://github.com/RamyaPvy/orangehrm-selenium-mini.git'
      }
    }

    stage('Build & Test') {
      steps {
        sh '''
          set -euxo pipefail
          echo "Cleaning any leftover Chrome/Chromedriver..."
          pkill -f chromedriver || true
          pkill -f "chrome --" || true

          echo "Running Maven tests..."
          mvn -B -q clean test
        '''
      }
    }

    stage('Reports') {
      steps {
        // JUnit parser will pick up TestNG XMLs created by surefire
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
