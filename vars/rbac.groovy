def call(){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/rbac/acl --data-binary @open-policy-agent/RBAC/ROLE_BASED/Role-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/rbac --data-binary @open-policy-agent/RBAC/ROLE_BASED/rbac.rego"
//String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/commit/policy/foo' --header 'Content-Type: application/json' --data-raw '{"input":{"name":"'${committername}'","branch_name":"'${branch}'"}}'""", returnStdout: true)
//println(response)
  
String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/rbac/policy/allow' --header 'Content-Type: application/json' --data-raw '{ "input" : { "user": "alice","path": "compute" , "method":"GET" } }'""", returnStdout: true)
println(response)
}


