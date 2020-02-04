def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

//String a=jsonObj.alm.projects.project.project_name
String a=jsonObj.alm.projects.project.name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");

//String b=jsonObj.alm.projects.project.project_typeKey
String b=jsonObj.alm.projects.project.projectTypeKey
 String projectTypeKey=b.replaceAll("\\[", "").replaceAll("\\]","");

//String c=jsonObj.alm.projects.project.project_lead
String c=jsonObj.alm.projects.project.lead
 String lead=a.replaceAll("\\[", "").replaceAll("\\]","");

env.name = projectName
env.projectTypeKey = projectTypeKey
env.lead = lead

//sh "rm -rf Text.xml"
 println(a)
 println(projectName)
 println (projectTypeKey)
 println(name)


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
// def project_TypeKey= '"'+resultJson.projectTypeKey+'"'
 //def proj_lead = '"'+resultJson.lead+'"'
 
 
   sh """
      curl --request POST \
  --url http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --data '{\r\n          \r\n          "name": "${projectName}",\r\n          "key":"${projKey}",\r\n          "projectTypeKey": "${projectTypeKey}",\r\n          "lead": "${lead}"\r\n}\r\n\r\n'"""
           
 
/* httpRequest authentication: 'jira_password', 
    customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'], 
                    [maskValue: false, name: 'Accept', value: 'application/json']], 
    httpMode: 'POST', requestBody: '''{
    "key": "${projKey}",
    "name": "${projectName}",
    "projectTypeKey": "${projectTypeKey}",
    "projectTemplateKey": "com.atlassian.jira-core-project-templates:jira-core-project-management",
    "description": "Example Project description",
    "lead": "${lead}",
    "assigneeType": "PROJECT_LEAD"
}''', responseHandle:'NONE' , url: 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project'

 */
}


