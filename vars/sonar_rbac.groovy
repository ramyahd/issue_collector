def call(){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/sonarrbac/acl --data-binary @open-policy-agent/SONARRBAC/ROLE_BASED/sonar-acl.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/sonarrbac --data-binary @open-policy-agent/SONARRBAC/ROLE_BASED/sonar-rbac.rego"
//String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/commit/policy/foo' --header 'Content-Type: application/json' --data-raw '{"input":{"name":"'${committername}'","branch_name":"'${branch}'"}}'""", returnStdout: true)
//println(response)
  
String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/sonarrbac/policy/allow' --header 'Content-Type: application/json' --data-raw '{ "input" : {"auth": "ramya"} }'""", returnStdout: true)
println(response)
if ( response == "{\"result\":true}" ){
String response1 = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/sonarrbac/policy/result' --header 'Content-Type: application/json' --data-raw '{ "input" : { "user": "alice","branch": "master", "auth" : "ramya"} }'""", returnStdout: true)
println(response1)  

}
else{
String response2 = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/sonarrbac/policy/deny' --header 'Content-Type: application/json' --data-raw '{ "input" : {"auth": "ramya"} }'""", returnStdout: true)  
println(response2)
  error("you cannot see the result")
}  

}
