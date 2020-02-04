import groovy.json.JsonSlurper 
@NonCPS
createIssues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = '"'+resultJson.name+'"'
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
    
    
httpRequest authentication: 'Jira_Cred', customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], [maskValue: false, name: 'Accept', value: 'application/json']], httpMode: 'POST', requestBody: '''{
    "fields": {
       "project":
       {
          "key": "SAK"
       },
       "summary": "this is summary 1",
       "description": "this is description",
       "issuetype": {
          "name": "Task"
       }
   }
}''', responseHandle: 'NONE', url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/'
}

def call(){
 def request = libraryResource 'data.json'
 createIssues(request)
}
