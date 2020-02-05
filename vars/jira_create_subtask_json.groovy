def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName
  
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
 httpMode: 'POST', requestBody: """{
    "fields":
    {
        "project":
        {
            "key": "${projKey}"
        },
        "parent":
        {
            "key": "${projKey}-1"
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
 
 
 

/*curl -X POST \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/ \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 5c5f23bc-31cd-38f7-5645-52d4d60aca03' \
  -d '  {
    "fields": {
       "project":
       {
          "key": "SMA"
       },
       "summary": "this is summary 1.",
       "description": "this is description",
       "issuetype": {
          "name": "Task"
       }
   }
}'*/
