pipeline {
  agent { label 'Jenkins-Agent' }  // or: agent any

  tools {
    jdk   'Java17'     // matches Manage Jenkins → Global Tool Configuration
    maven 'Maven3'
  }

  environment {
    CI = 'true'        // your tests can switch to headless when CI=true
  }

  options {
    timestamps()
    // Comment this line if AnsiColor plugin isn't installed
    ansiColor('xterm')
  }

  stages {

    stage('Pre-flight: Versions') {
      steps {
        sh '''
echo "== Java =="
java -version || true
echo "== Maven =="
mvn -v || true
echo "== Git =="
git --version || true
        '''
      }
    }

    stage('Cleanup workspace') {
      steps {
        // If you ever remove Workspace Cleanup plugin, replace with deleteDir()
        cleanWs()
      }
    }

    stage('Checkout from SCM') {
      steps {
        git branch: 'main',
            credentialsId: 'github',
            url: 'https://github.com/RamyaPvy/orangehrm-selenium-mini.git'
      }
    }

    stage('Build & Test') {
      steps {
        // 🔒 Wrap the ENTIRE script in bash -lc and a single multi-line string
        sh '''bash -lc "
set -euxo pipefail

echo Running Maven tests...
mvn -B -q clean test
"'''
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
