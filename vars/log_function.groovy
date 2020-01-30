  
import groovy.json.JsonSlurper 
@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key

  echo "Project created with $projectName in JIRA">'jira.txt'
