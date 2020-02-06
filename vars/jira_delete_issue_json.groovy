def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.issue_name
String issueName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = issueName
  
  
httpRequest authentication: 'jira_password',
customHeaders: [[maskValue: false, name: 'Content-Type', value: 'application/json'],
                 [maskValue: false, name: 'Accept', value: 'application/json']], 
 httpMode: 'DELETE',  url: " http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/${issueName}?deleteSubtasks=true"
}
  

/*curl -X DELETE \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/EDN-1 \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: d93188c9-8f17-5dff-7177-98ecad39a3ef'
*/
