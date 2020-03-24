def call(){
def process=sh "curl --location --request GET 'http://ec2-3-133-107-212.us-east-2.compute.amazonaws.com:9000/api/measures/component?metricKeys=vulnerabilities&component=comrades.bmi%3ABMI'  --header 'Authorization: Basic YWRtaW46YWRtaW4=' -o ouput.json"
}
