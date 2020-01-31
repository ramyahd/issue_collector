import groovy.json.JsonSlurper 
@NonCPS
/*createlog(String data, string message){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
sh "echo ${message} >log.txt"
/*def fileName = "jira.txt"
def inputFile = new File(fileName)
if(inputFile.exists())
  {
    inputFile.append("${projectName}")
  }
  else
  {
     inputFile.write("${projectName}")
  }
}*/
//}
def call(message)
{
 println(message)
 def request = libraryResource 'data1.json'
 def jsonSlurper = new JsonSlurper() 
 def resultJson = jsonSlurper.parseText(request)
 def projectName = resultJson.key
  Date date = new Date() 
  sh " echo '${date}' JIRA project with the projectname '${projectName}' ${message} >>log.txt"
}
/*def call(){
def request = libraryResource 'data1.json'
createlog(request)
}*/
