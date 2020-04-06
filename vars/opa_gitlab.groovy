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

}
if ( response == "{\"result\"allow"\":true}" ){    
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}
