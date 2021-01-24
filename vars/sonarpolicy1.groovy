@NonCPS
//getting the id of user who started the build.
def getuserid(){
def build = currentBuild.rawBuild
def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
def userid = cause.getUserName()
}

def call(){
sh "curl -X PUT http://18.224.228.236:8181/v1/data/sonar/acl --data-binary @opa/SONAR/BuildPolicy/sonargate.json"
sh "curl -X PUT http://18.224.228.236:8181/v1/policies/sonar --data-binary @opa/SONAR/BuildPolicy/sonar-policy.rego"

getuserid()

println(userid)


String response = sh(script:"""curl --location --request POST 'http://18.224.228.236:8181/v1/data/sonar/policy/gate' --header 'Content-Type: application/json' --data-raw '{ "input": { "user":'"${userid}"', "qualitygate": "50"} }'""" , returnStdout: true)
println(response)
/*if ( response == "{\"result\":true}" ){
println("You can build a job")
}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}*/
}
