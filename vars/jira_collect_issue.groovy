import groovy.json.JsonSlurper 

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
echo "$projectName"
 httpRequest authentication: 'jira_password',
  customHeaders: [[maskValue: false, name: 'Accept', value: 'application/json']], 
     httpMode: 'GET', url:"http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"
     def connection = url.toURL().openConnection()
   
  try {
      connection.connect()
     def line = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine()
     return jsonSlurper.parseText(line)
  } finally {
        connection.disconnect();
    }
}
def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}
