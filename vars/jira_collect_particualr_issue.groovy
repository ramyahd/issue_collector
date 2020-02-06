def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.issue_name
String issueName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = issueName



sh """
curl --request GET \
  --url http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/3/issue/${issueName} \
  --header 'accept: application/json' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' | json_reforamt
  """
  }
