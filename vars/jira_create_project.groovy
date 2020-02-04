import groovy.json.JsonSlurper 

@NonCPS
createProject(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
 //def slurper = new groovy.json.JsonSlurper()
 //def resultJson = slurper.parseText(data)
  println resultJson
  println resultJson?.number
def projectName = '"'+resultJson.project_name+'"'
def project_typeKey= '"'+resultJson.project_typeKey+'"'
 def project_lead = '"'+resultJson.project_lead+'"'
def length = 3
def projLength = resultJson.name.size()
 if(projLength>=3){
  key=resultJson.name.substring(0, Math.min(projLength, length)).toUpperCase();
 }
 else {
   def appendStr = "";
   int currentLength=length;
   int len = currentLength%projLength;
   int repeat = currentLength/projLength;
   for (int i=0;i<repeat;i++) {
    appendStr = appendStr + resultJson.name;
   }
   appendStr=appendStr+resultJson.name.substring(0, Math.min(projectName.length(), len));
   key=appendStr.toUpperCase();
   }
 def projKey = '"'+key+'"'
 
 httpRequest authentication: 'jira_password', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'POST', requestBody: """{
    "key": ${projKey},
    "name": ${projectName},
    "projectTypeKey": ${project_typeKey},
    "projectTemplateKey": "com.atlassian.jira-core-project-templates:jira-core-project-management",
    "description": "Example Project description",
    "lead": ${project_lead},
    "assigneeType": "PROJECT_LEAD"
}""", responseHandle: 'NONE', url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project'

 
}
def call(){
 def request = libraryResource 'jira.json'
 createProject(request)
}
