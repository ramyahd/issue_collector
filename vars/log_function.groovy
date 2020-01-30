import groovy.json.JsonSlurper 
@NonCPS
createlog(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key

  echo "$projectName">'jira.txt'
}
def call(){
def request = libraryResource 'data1.json'
createlog(request)
}
