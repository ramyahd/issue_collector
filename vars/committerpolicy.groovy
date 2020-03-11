def call(committername,branch){
sh "curl -X PUT http://18.221.205.57:8181/v1/data/metrics/acl --data-binary @open-policy-agent/METRICS/COMMITTER_BASED/data.json"
sh "curl -X PUT http://18.221.205.57:8181/v1/policies/metrics --data-binary @open-policy-agent/METRICS/COMMITTER_BASED/metrics.rego"
String response = sh(script:"""curl --location --request POST 'http://18.221.205.57:8181/v1/data/metrics/policy/foo' --header 'Content-Type: application/json' --data-raw '{"input":[{"name":"'${committername}'","branch_name":"'${branch}'"}]}'""", returnStdout: true)
println(response)
}
