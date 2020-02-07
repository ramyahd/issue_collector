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
def call(message)
{
  println(message)
  Date date = new Date() 
  sh " echo '${date}' JIRA  ${message} >>log.txt"
}
/*def call(){
def request = libraryResource 'data1.json'
createlog(request)
}*/
