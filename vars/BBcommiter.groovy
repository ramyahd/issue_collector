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

    
    }
//JCOPY[i]=JSON[i]	  
      }
}
	
	
	
	/* ArrayList<List<String,String>> retList = new ArrayList<>();
    int startIndex = -1;
    string currentSelection;

    for (int i = 0; i < JSON.size(); i++) {
        if (startIndex < 0) {
            startIndex = i;
            currentSelection = JSON.author.emailAddress;

            continue;
        }

        if (currentSelection != JSON.auhtor(i).emailAddress) {
            retList.add(new List<>(startIndex, i - 1));
            startIndex = i;
            currentSelection = JSON.author(i).emailAddress;

            continue;
        }
    }
    
    */
	
	/*	for (int i = 0; i < JSON.size(); i++) {
			List<String> l = JSON[i];
			echo(l);
	
		}*/
	int[] foo;
	ArrayList<Object> JCOPY = new ArrayList<Object>(JSON);
	
	for (i = 0; i < JSON.size(); i++) 
	{
		for( j=1;j < JCOPY.size();j++)
		{
			if(JSON[i].id == JCOPY[j].id)
			   {
				    foo[i] += JSON[i]
			   }
			   else
			   {
				echo "working on it"
			   }
		}
	}
			   
			   println(foo);
			  /* Iterator i = JCOPY.iterator();
	  while (i.hasNext()) {
         	println(i.next());
      }*/
	//println(JSON.size())
	//println(JSON)
//println JSON.findAll { map.id[0] == 'a3042a6b0427ab4b049f27dde71ef3f5340d1f57' }
//def resultJson = jsonSlurper.parse(JSON)
 /*if (JSON.author[0].name==jsonObj.config.emails.email[2])
	{
		println("GOT IT")
	}*/

}

