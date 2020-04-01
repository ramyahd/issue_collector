import groovy.json.*

@NonCPS
create(){
  def jsonSlurper = new JsonSlurper()
  def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/metrics.json"),"UTF-8"))
  def resultJson = jsonSlurper.parse(reader)
  def jsonBuilder = new groovy.json.JsonBuilder()
  jsonBuilder.sonar(
  "metrics" : resultJson
  )
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/metrics.json")
  file.write(jsonBuilder.toPrettyString())
  return jsonBuilder
}

def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString

String a = jsonObj.code_quality.projects.project.project_key
String ProjectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  println(ProjectName)
  
withCredentials([usernamePassword(credentialsId: 'sonar_cred1', passwordVariable: 'pass', usernameVariable: 'user')]) {
  sh "curl -u ${user}:${pass} -X GET 'http://ec2-3-133-107-212.us-east-2.compute.amazonaws.com:9000/api/measures/component?component=${ProjectName}&metricKeys=coverage,vulnerabilities,bugs,violations,complexity,tests,duplicated_lines,sqale_index' -o metrics.json"
  echo 'metrics collected'
}
  create()
}
