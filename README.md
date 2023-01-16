# Customers
api curls 


curl -X POST \
'localhost:8080/api/customer' \
--header 'Accept: */*' \
--header 'User-Agent: Thunder Client (https://www.thunderclient.com)' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Bank",
"plan": "Silver",
"bill": 0
}'


curl -X POST \
'localhost:8080/api/customer' \
--header 'Accept: */*' \
--header 'User-Agent: Thunder Client (https://www.thunderclient.com)' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Shop",
"plan": "Gold",
"bill": 0
}'

curl -X GET \
'localhost:8080/api/customer?name=Bank' \
--header 'Accept: */*' \
--header 'User-Agent: Thunder Client (https://www.thunderclient.com)'


curl -X POST \
'localhost:8080/api/sendSMS?name=Bank&message=hello how are you doing&messageCount=400000' \
--header 'Accept: */*' \
--header 'User-Agent: Thunder Client (https://www.thunderclient.com)'

curl -X GET \
'localhost:8080/api/bill/Bank' \
--header 'Accept: */*' \
--header 'User-Agent: Thunder Client (https://www.thunderclient.com)'


docker build -t custoomer -f DockerFile.app .