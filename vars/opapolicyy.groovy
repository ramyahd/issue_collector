
def call(){
//sh "curl -X PUT http://18.221.205.57:8181/v1/data/jenkinss/acl --data-binary @opa/PIPELINE/BuildPolicy/pipeline-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/jenkinss --data-binary @opa/PIPELINE/BuildPolicy/pipeline-policy.rego"


  
/*String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/jenkinss/policy/foo' --header 'Content-Type: application/json' --data-raw '{ "servers": [
        {
            "branch_name": "master",
            "metrics": [
                {
                    "code-coverage": "50"
                }
            ],
            "name": "Alice"
        },
        {
            "branch_name": "feature",
            "metrics": [
                {
                    "code-coverage": "85"
                }
            ],
            "name": "Alice"
        },
        {
            "branch_name": "master",
            "metrics": [
                {
                    "code-coverage": "60"
                }
            ],
            "name": "Bob"
        },
        {
            "branch_name": "feature",
            "metrics": [
                {
                    "code-coverage": "80"
                }
            ],
            "name": "Bob"
        }
    ] }'""", returnStdout: true)
println(response)
if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}
}*/
sh """
  curl -X POST \
  http://18.221.205.57:8181/v1/data/jenkinss/policy/foo \
  --data-binary '{ "input" : { "branch_name": "master","name": "Alice" } }'
  
  """
//println(response)
/*if ( response == "{\"result\":true}" ){
println("You can build a job")

}
else{
error("Build failed beacuse you do not have permission to trigger a build")
}*/
}

