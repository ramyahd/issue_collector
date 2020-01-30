import groovy.json.JsonSlurper 
@NonCPS
createlog(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key

def fileName = "jira.txt"
def inputFile = new File("C:\\"+fileName)
if(inputFile.exists())
  {
    inputFile.append("${projectName}")
  }
  else
  {
     inputFile.write("${projectName}")
  }
}
def call(){
def request = libraryResource 'data1.json'
createlog(request)
}
