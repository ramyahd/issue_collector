import groovy.json.*

@NonCPS
create(String projectName){
 
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/body.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
def keyId = resultJson.id
def length = 3
def projLength = name.size()
 println(projLength)
 if(projLength>=3){
  key=name.substring(0, Math.min(projLength, length)).toUpperCase();
 }
 else {
   def appendStr = "";
   int currentLength=length;
   int len = currentLength%projLength;
   int repeat = currentLength/projLength;
   for (int i=0;i<repeat;i++) {
    appendStr = appendStr + name;
   }
   appendStr=appendStr+name.substring(0, Math.min(projectName.length(), len));
   key=appendStr.toUpperCase();
   }
 def projKey = key
 
 println(projKey)
 
 httpRequest authentication: 'jira_password',
 customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'],
                 [maskValue: false, name: 'Accept', value: 'application/json']], 
 httpMode: 'POST' -v, requestBody: """{
    "fields":
    {
        "project":
        {
            "key": "${projKey}"
        },
        "parent":
        {
            "key": "${keyId}"
        },
        "summary": "Sub-task of TEST-101",
        "description": "Don't forget to do this too.",
        "issuetype":
        {
            "name":"Sub-task"
        }
    }
}""", url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/'
 
 }
 
 
 
 
def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");

create(projectName)
 }
