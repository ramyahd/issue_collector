curl --request POST \
  --url http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/AVR-6/comment \
  --header 'accept: application/json' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 9cb64ee0-8c96-3d32-94b7-7c2005e1f495' \
  --data '{\n    "body": "This is a comment regarding the quality of the response."\n}'
