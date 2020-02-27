import groovy.json.*

def call(jsondata){
def jsonString = jsondata
def jsonObj = readJSON text: jsonString
String a=jsonObj.scm.projects.project.repositories.repository.repo_name
String repoName=a.replaceAll("\\[", "").replaceAll("\\]","");
String b=jsonObj.scm.projects.project.project_key 
String Key=b.replaceAll("\\[", "").replaceAll("\\]","");
int ecount = jsonObj.config.emails.email.size()
println("No of users "+ ecount)
println(Key)
println(repoName)
// Date date = new Date() 
 withCredentials([usernamePassword(credentialsId: 'bitbucket_cred', passwordVariable: 'pass', usernameVariable: 'userId')]) {
  sh "curl -X GET  -H -d  -u $userId:$pass http://18.224.68.30:7990/rest/api/1.0/projects/'${Key}'/repos/'${repoName}'/commits -o output.json"
 } 
def jsonSlurper = new JsonSlurper()
def resultJson = jsonSlurper.parse(new File("/var/lib/jenkins/workspace/${JOB_NAME}/output.json"))
def total = resultJson.size
 echo "Total no.of commits in ${repoName} $total"
//def commiter=1
List<String> JSON = new ArrayList<String>();
//List<String> JCOPY = new ArrayList<String>();

for(i=0;i<ecount;i++)
 {
  for(j=0;j<total;j++)
  {
   if(jsonObj.config.emails.email[i]==resultJson.values.author[j].emailAddress)
   {
	JSON.add(JsonOutput.toJson(resultJson.values[j]))
	 //JSON[i]= resultJson.values[j]
	 // JSON[i].addAll(JCOPY[i])
	  ArrayList<String> searchList = new ArrayList<String>();
	  String search = "resultJson.values.author[j].emailAddress";
	  int searchListLength = searchList.size();
	  for (int i = 0; i < searchListLength; i++) {
	  if (searchList.get(i).contains(search)) {
		  echo " It is having the string you can make the array out of it "
//Do whatever you want here
		}
    
    }
//JCOPY[i]=JSON[i]	  
      }
}
println(JSON.size())
println(JSON)
//println JSON.findAll { map.id[0] == 'a3042a6b0427ab4b049f27dde71ef3f5340d1f57' }
//def resultJson = jsonSlurper.parse(JSON)
 /*if (JSON.author[0].name==jsonObj.config.emails.email[2])
	{
		println("GOT IT")
	}*/

}

