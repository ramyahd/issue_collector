def call()
{
curl --request GET \
  --url http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/agile/1.0/board \
  --header 'accept: application/json' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: c56c57b3-4529-6938-19aa-a3184cc6c584'
  }
