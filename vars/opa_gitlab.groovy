    
import groovy.json.*
import groovy.json.JsonOutput
@NonCPS
//getting the id of user who started the build.
def getuserid(){
def build = currentBuild.rawBuild
def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
def userid = cause.getUserName()
}

def call(){
sh "curl -X PUT http://18.224.228.236:8181/v1/data/gitlab/acl --data-binary @open-policy-agent/GITLAB/gitlab_data.json"
sh "curl -X PUT http://18.224.228.236:8181/v1/policies/gitlab --data-binary @open-policy-agent/GITLAB/gitlab_policy.rego"

String response = sh(script:"""curl --location --request POST 'http://18.224.228.236:8181/v1/data/gitlab/policy' --header 'Content-Type: application/json' --data-raw '{ "input" : {"ticker": "master","user": "'${userid}'"
} }'""", returnStdout: true)
println(response)
def resultJson= readJSON text: response	
/*println(resultJson.result.allow)
  
if ( resultJson.result.allow == "true")
{    
println("Authorization is successfull")
exit
}
else
{
error("Authorization failed and you cannot move to next stage")
}*/
}
