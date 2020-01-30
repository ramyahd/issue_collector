import groovy.json.JsonSlurper 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
echo "$projectName"
 httpRequest authentication: 'jira_password',
  customHeaders: [[maskValue: false, name: 'Accept', value: 'application/json']], 
     httpMode: 'GET', url:"http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"

final Logger logger = LoggerFactory.getLogger("myGroovyLogger");
logger.trace("Test log output message");
}
def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}
