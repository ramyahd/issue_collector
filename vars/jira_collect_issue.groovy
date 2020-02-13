import groovy.json.* 

  
int totalIssues;

def issue(jsondata){
def jsonString = jsondata
//println(jsonString)
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
env.name = projectName

sh """curl  -X GET \
 'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}%20order%20by%20duedate&fields=id%2Ckey' \
  -H 'accept: application/json' \
  -H 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' -o output.json
"""
 
} 


@NoNcps
def create(){
  def jsonSlurper = new JsonSlurper()
  //def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"),"UTF-8"))
  
  def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/ouput.json"))
  
  def total = resultJson.total
  echo "=============================Total $total"
  pushToInflux(total);
  

 }


def pushToInflux(totalIssues) {
  
  sh """
    curl -w '%{http_code}' -s -i  -X POST \
      'http://ec2-13-58-47-71.us-east-2.compute.amazonaws.com:8086/write?db=Collector' \
      --data 'jira issues=${totalIssues}' > test2.txt
  """
 def response =new File('/var/lib/jenkins/workspace/' + JOB_NAME + '/test2.txt').text
  echo "======================== $response" 
  
  if (response == "204" || response == "200") {
      echo "DATA PUSHED TO INFLUX DB"
     } else {
     
       error("ERROR PUSHING DATA TO INFLUX")
     }
}


