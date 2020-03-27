import groovy.json.*
import groovy.json.JsonOutput

def call(rig,tool)
{
 
  
 //sh "curl -X POST  -H  Accept:application/json -H  Content-Type:application/json -d @resources/'${rig}'.json  http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json"
 def response = sh(script:"""curl -X POST  -L -w '%{http_code}\n' -H  Accept:application/json -H  Content-Type:application/json  -d '{ \"rigletName\":\"${rig}\", \"toolName\":\"${tool}\"}' http://3.134.156.211:3013/api/riglets/connectorServerDetails -o rigoutput.json""", returnStdout: true)
 println(response)
 
/* sh """
   curl -X POST \
  http://3.134.156.211:3013/api/riglets/connectorServerDetails \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
    "rigletName":"${rig}",
    "toolName":"${tool}"
}' -o rigoutput.json
"""*/
 try
 {
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/rigoutput.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
 
return JsonOutput.toJson(resultJson)
}
catch(Exception e)
{
	//println(response)
	e.printStackTrace()
	
}
	 finally{
		//println(response)
		if(response.contains("200"))
		{
		println("data collected scuccesslfully")	
		
		}
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
