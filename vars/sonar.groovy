def call(String msg = 'SONAR'){
  echo "${msg}"
 
  environment{
    scannerHome=tool 'sonarScanner'  
  }
  withSonarQubeEnv('sonarqube'){
                     sh 'mvn sonar:sonar'
                }
}
