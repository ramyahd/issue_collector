import groovy.json.* 
  
def inprogress(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

  withCredentials([usernamePassword(credentialsId: 'jira_password', passwordVariable: 'password', usernameVariable:'username')]){
def done = sh """
     curl -X GET \
    -H -d -u $username:$password \
     http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20AND%20(status%3D'\''In%20Progress'\'') \
  -H 'cache-control: no-cache' > 'Taskdone.json'
  """
   
  }
}


@NonCPS
def create(){
  def jsonSlurper = new JsonSlurper()
  //def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
  
  def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/Taskdone.json"))
  
  def total = resultJson.total
  echo "=============================Total $total"
  pushToInflux(total);
  

 }

def pushToInflux(progresstasks) {
  
  sh """
    curl -w '%{http_code}' -s -i -o test.txt -X POST \
      'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' \
      --data 'jira tasks_inprogress=${progresstasks}' > test2.txt
  """
 def response =new File('/var/lib/jenkins/workspace/' + JOB_NAME + '/test2.txt').text
  echo "======================== $response" 
  if (response == "204" || response == "200") {
      echo "DATA PUSHED TO INFLUX DB"
     } else {
     
       error("ERROR PUSHING DATA TO INFLUX")
     }
}
