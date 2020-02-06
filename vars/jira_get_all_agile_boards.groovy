def call()
{
sh """
curl --request GET \
  --url  'http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/project' \
  --header 'accept: application/json' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' 
"""
}
