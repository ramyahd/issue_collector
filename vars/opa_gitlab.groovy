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
if ( response == {"result":{"allow":false,"object_attributes":{"feature":{"devcan":"merge","permission":"granted"},"master":{"admincan":"merge","permission":"granted"}},"user_attributes":{"alice":{"commits":15,"role":"admin"},"bob":{"commits":5,"role":"developer"}}}} ){    
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}
