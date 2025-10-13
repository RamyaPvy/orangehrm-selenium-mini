pipeline {
  agent { label 'Jenkins-Agent' }  // or: agent any

  tools {
    jdk   'Java17'   // must match Manage Jenkins → Global Tool Configuration
    maven 'Maven3'
  }

  environment {
    CI = 'true'      // your tests can switch to headless when CI=true
  }

  options {
    timestamps()
    // If AnsiColor plugin installed, keep this; otherwise comment it out.
    ansiColor('xterm')
  }

  stages {

    stage('Pre-flight: Versions') {
      steps {
        // This step doesn’t need bash -lc, so we keep it simple
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
        // Uses Workspace Cleanup plugin you installed
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
        // 🔴 IMPORTANT: Force bash for this step to allow `set -o pipefail`
        sh 'bash -lc "set -euxo pipefail; ' +
                     'echo Cleaning any leftover Chrome/Chromedriver...; ' +
                     'pkill -f chromedriver || true; ' +
                     'pkill -f \\"chrome --\\" || true; ' +
                     'echo Running Maven tests...; ' +
                     'mvn -B -q clean test"'
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
