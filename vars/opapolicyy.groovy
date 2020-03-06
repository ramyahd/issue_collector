
def call(){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/metrics --data-binary @opa/PIPELINE/BuildPolicy/pipeline-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/metrics --data-binary @opa/PIPELINE/BuildPolicy/pipeline-policy.rego"


String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/metrics/policy/result' --header 'Content-Type: application/json' --data-raw '{"servers": [{"branch_name": "master","name": "Alice"}]}'""" , returnStdout: true)
println(response)
/*if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}*/
}
