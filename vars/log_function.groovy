/*og(String data, string message){
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
def call(message,jsondata)
{
  
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
println(jsonObj.alm)

String a=jsonObj.alm.projects.project.project_name
String projectName=a.replaceAll("\\[", "").replaceAll("\\]","");
  
 env.name = projectName
 println(message)
  Date date = new Date() 
  sh " echo '${date}' JIRA '${projectName}' ${message} >>log.txt"
}
/*def call(){
def request = libraryResource 'data1.json'
createlog(request)
}*/
