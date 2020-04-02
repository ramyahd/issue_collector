
def call(){
sh "curl -X PUT http://18.224.228.236:8181/v1/data/sonarcode/acl --data-binary @open-policy-agent/SONAR/quality-gate/sonar-gate.json"
sh "curl -X PUT http://18.224.228.236:8181/v1/policies/sonarcode --data-binary @open-policy-agent/SONAR/quality-gate/sonar-policy.rego"

String response = sh(script:"""curl --location --request POST 'http://18.224.228.236:8181/v1/data/sonarcode/policy' --header 'Content-Type: application/json' --data-raw '{ "input" : {"project": "Wolvorines","branch": "Metrics", "auth" : "ramya", "vulnerability": "1","codecoverage":"90"} }'""", returnStdout: true)
println(response)
/*sh """
  curl -X POST \
  http://13.234.225.51:8181/v1/data/jenkinss/policy/foo \
  --data-binary '{ "input" : { "branch_name": "master","name": "Alice" } }' | json_reformat
  
  """*/
//println(response)
/*if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}*/
}

