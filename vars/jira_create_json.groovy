def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");

String b=jsonObj.alm.projects.project.project_typeKey
String projecttypeKey=b.replaceAll("\\[", "").replaceAll("\\]","");

String c=jsonObj.alm.projects.project.project_lead
String projectlead=a.replaceAll("\\[", "").replaceAll("\\]","");

env.name = projectName
env.projectTypeKey = projecttypeKey
env.lead = projectlead

//sh "rm -rf Text.xml"
 println "projectName"
 println "projecttypeKey"


def length = 3
def projLength = name.size()
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
 def projKey = '"'+key+'"'
// def project_TypeKey= '"'+resultJson.projectTypeKey+'"'
 //def proj_lead = '"'+resultJson.lead+'"'
 
 httpRequest authentication: 'jira_password', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'POST', requestBody: """{
    "key": ${projKey},
    "name": ${projectName},
    "projectTypeKey": "${projectTypeKey}",
    "projectTemplateKey": "com.atlassian.jira-core-project-templates:jira-core-project-management",
    "description": "Example Project description",
    "lead": "${lead}",
    "assigneeType": "PROJECT_LEAD"
}""", responseHandle: 'NONE', url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project'

 
}


