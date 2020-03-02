import groovy.json.*
  
def call(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

  withCredentials([usernamePassword(credentialsId: 'jira_password', passwordVariable: 'password', usernameVariable:'username')]){
sh """
     curl -X GET \
    -H -d -u $username:$password \
     'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3D'\'"In%20Progress"\'')%20order%20by%20duedate&fields=id%2Ckey%2Cpriority'  
  """
  }

def jsonSlurper = new JsonSlurper()
def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"))
def total = resultJson.total
 echo "Total no.of commits in ${repoName} $total"
}
