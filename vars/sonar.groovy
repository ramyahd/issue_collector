def call(jsondata,rig){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString

String a = jsonObj.code_quality.projects.project.project_key
String ProjectKey=a.replaceAll("\\[", "").replaceAll("\\]","");
  
  def jsonObja = readJSON text: rig
	def IP=jsonObja.url
	def user=jsonObja.userName
	def pass=jsonObja.password
  def response = sh(script: """curl -X GET -L -w '%{http_code}\\n' -u ${user}:${pass} '${IP}/api/measures/component?component=${ProjectKey}&metricKeys=coverage,vulnerabilities,bugs,violations,complexity,tests,duplicated_lines,sqale_index' -o metrics.json""", returnStdout: true)
  //println("RESPONSE   "+response) 
	try
	{
  //sh "curl -u ${user}:${pass} -X GET '${IP}/api/measures/component?component=${ProjectKey}&metricKeys=coverage,vulnerabilities,bugs,violations,complexity,tests,duplicated_lines,sqale_index' -o metrics.json"
  echo 'metrics collected'
	
	def resultJson = readJSON file: 'metrics.json'
  def jsonBuilder = new groovy.json.JsonBuilder()
  jsonBuilder.Sonar(
  "Metrics" : resultJson
  )
  File file = new File("/var/lib/jenkins/workspace/${JOB_NAME}/metrics.json")
  file.write(jsonBuilder.toPrettyString())
  return jsonBuilder

}
	catch(Exception e)
{
	e.printStackTrace()
	
}
	 finally{
		
		if(response.contains("200"))
		println("data collected scuccesslfully")	
	if(response.contains("404"))
	println("Not found")
	if(response.contains("400"))
	println("Bad Request")
        if(response.contains("401"))
	println("Unauthorized")
	if(response.contains("403"))
		println("Forbidden")
	if(response.contains("500"))
		println("Internal Server Error")
		 }
}
