
def call(){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/metrics --data-binary @opa/PIPELINE/BuildPolicy/pipeline-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/metrics --data-binary @opa/PIPELINE/BuildPolicy/pipeline-policy.rego"

  
sh """
  curl -X POST \
  http://18.221.205.57:8181/v1/data/metrics/policy/result \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"servers": {"branch_name": "master","name": "Alice"}}'
  
  """
//println(response)
/*if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}*/
}
