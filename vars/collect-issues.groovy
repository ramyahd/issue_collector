import groovy.json.JsonSlurper 

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = '"'+resultJson.key+'"'

 httpRequest authentication: 'jira_password', 
    customHeaders:[[maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'GET', requestBody: """{
    "name": ${projectName},
}""", responseHandle: 'NONE', url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project'

 
}
def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}
