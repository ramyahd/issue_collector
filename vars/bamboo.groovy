import groovy.json.*
 import groovy.json.JsonOutput


def call(JSON,IP)
{
def jsonString = JSON
def jsonObj = readJSON text: jsonString
def mailcount = jsonObj.config.emails.email.size()

 sh """ curl -X GET \
  'http://18.220.143.53:8085/rest/api/latest/result/LAT-WEB.json?max-result=50&expand=results.result.artifacts&expand=changes.change.files&start-index=0' \
  -H 'authorization: Basic cmlnOnJpZ2FEYXB0QGRldk9wcw==' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 50b866a3-885a-2d59-ea9d-b76fb8b13a16'  -o output.json """

//  sh "curl -G -X GET -s -u rig:rigaDapt@devOps ${IP}/rest/api/latest/result/LAT-WEB.json?max-result=50%26expand=results.result.artifacts%26expand=changes.change.files%26start-index=0  -o output.json"
def jsonSlurper = new JsonSlurper()
def reader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"),"UTF-8"))
def resultJson = jsonSlurper.parse(reader)
//def state=resultJson.results.result[0].buildCompletedDate
  //println(state)
 def cns=0
 def cnf=0
 
    List<String> SUCCESS = new ArrayList<String>();
    List<String> FAILURE = new ArrayList<String>();
 
    HashMap<String,Integer> var  = new HashMap<>();
    List<String> USER = new ArrayList<String>();
 

println(mailcount)
   for(j=0;j<mailcount;j++)
   {
    
  for(i=0;i<50;i++)
  {
 
   def date=resultJson.results.result[i].buildCompletedDate
   //println(date)
   def state=resultJson.results.result[i].buildState
  // println(state)
  
 
   def email=jsonObj.config.emails.email[j]
    //println(email)
   if(resultJson.results.result[i].buildReason.contains(email) && state.equals("Successful"))
   {
 
   USER.add(JsonOutput.toJson(resultJson.results.result[i]))
    //map.put(JsonOutput.toJson(resultJson.results.result[i]),"USER"+j)
   
    
   }
 def USER=USER+i
    println(USER)
   }
  }
 //println(cnt)
// println(Success)
  // USER.get(0).add(JsonOutput.toJson(resultJson.results.result[i]))

//println(USER)
 
 /*for(i=0;i<mailcount;i++)
 {
   var.put("USER[i]"+i)
 }
println(var)*/
 
 //echo "$cnt"
}
