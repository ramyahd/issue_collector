def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName


sh """curl --request GET \
  --url 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D{projectName}%20AND%20(status%3DDONE)' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'postman-token: 87efe61f-ee85-21b5-b79d-bdad2c36140b'
"""
}
