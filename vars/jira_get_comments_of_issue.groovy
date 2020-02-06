def call(jsondata)
{
  def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.issue_name
String issueName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = issueName
  
sh """
curl -X GET \
  http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/${issueName}/comment \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json'
"""
}
