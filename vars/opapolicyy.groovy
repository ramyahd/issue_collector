
def call(){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/jenkinss/acl --data-binary @opa/PIPELINE/BuildPolicy/pipeline-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/jenkinss --data-binary @opa/PIPELINE/BuildPolicy/pipeline-policy.rego"

String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/jenkinss/policy/foo' --header 'Content-Type: application/json' --data-raw '{ "input" : [{ "branch_name": "master","name": "Alice" }] }'""", returnStdout: true)
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

