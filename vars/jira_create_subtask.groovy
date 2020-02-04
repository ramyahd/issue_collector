curl --request POST \
  --url http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/issue/ \
  --header 'accept: application/json' \
  --header 'authorization: Basic cmlnOmRpZ2l0YWxyaWdAMTIz' \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: ee09bb4e-1fe9-4d61-a33c-f354f3b111d7' \
  --data '{\n    "fields":\n    {\n        "project":\n        {\n            "key": "AVR"\n        },\n        "parent":\n        {\n            "key": "AVR-6"\n        },\n        "summary": "Sub-task of TEST-101",\n        "description": "Don'\''t forget to do this too.",\n        "issuetype":\n        {\n            "name":"Sub-task"\n        }\n    }\n}'
