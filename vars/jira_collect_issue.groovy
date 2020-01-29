import groovy.json.JsonSlurper 

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
echo "$projectName"
 httpRequest authentication: 'jira_password', 
    customHeaders:[[maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'GET', 
    responseHandle: 'NONE', url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=&project="${projectName}"&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"'

 
}
def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}
