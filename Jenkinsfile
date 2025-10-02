pipeline {
  agent { label 'Jenkin-Agent' }
  tools {
    jdk 'JDK17'
    maven 'MAven3'
  }
  stages{
    stage("Cleanup workspace"){
           steps {
           cleanWs()
           }
    }
    stage("Checkout from SCM"){
           steps {
           git branch: 'main' , credentialsId: 'github' , url: 'https://github.com/RamyaPvy/orangehrm-selenium-mini'
           }
    
    }

    stages{
    stage("Build Application"){
           steps {
           sh "mvn clean package"
           }
    }

stages{
    stage("Test Application"){
           steps {
           sh "mvn test"
           }
    }
      
    
  }
  
}

