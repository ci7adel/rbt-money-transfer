#!/usr/bin/env bash
# This test simulate a normal operation of 2 users opening account and making a transfer

# Create user
curl -i -X POST http://localhost:8080/users -d '{"userId": 3, "name": TestUser1}' -H "Content-Type: application/json"
curl -i -X POST http://localhost:8080/users -d '{"userId": 4, "name": TestUser2}' -H "Content-Type: application/json"

# Create accounts
curl -i -X POST http://localhost:8080/accounts -d '{"accountId": 7, "userId": 3}' -H "Content-Type: application/json"
curl -i -X POST http://localhost:8080/accounts -d '{"accountId": 8, "userId": 4}' -H "Content-Type: application/json"

# Deposit funds
curl -i -X PUT http://localhost:8080/accounts/7/deposit/100

# Transfer between the created accounts
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 7, "accountIdTo": 8,"amount": 50}' -H "Content-Type: application/json"


## Result ##

# The users should be created and the accounts should show a balance of 50 each

# Get all users
curl -i http://localhost:8080/users

# HTTP/1.1 200 OK
# Date: Wed, 27 Feb 2019 23:42:21 GMT
# Content-Type: application/json
# Transfer-Encoding: chunked
# Server: Jetty(9.4.8.v20171121)
#
# {"status":"SUCCESS","data":[{"userId":1,"name":"Victor"},{"userId":2,"name":"Barca"},{"userId":3,"name":"TestUser1"},{"userId":4,"name":"TestUser2"}]}

# Get all accounts
curl -i http://localhost:8080/accounts

# HTTP/1.1 200 OK
# Date: Wed, 27 Feb 2019 23:08:46 GMT
# Content-Type: application/json
# Transfer-Encoding: chunked
# Server: Jetty(9.4.8.v20171121)
#
# {"status":"SUCCESS",
# "data":[{"accountId":1,"userId":1,"balance":100},
# {"accountId":2,"userId":1,"balance":100},
# {"accountId":3,"userId":1,"balance":100},
# {"accountId":4,"userId":1,"balance":100},
# {"accountId":5,"userId":1,"balance":100},
# {"accountId":6,"userId":1,"balance":100},
# {"accountId":7,"userId":2,"balance":50},
# {"accountId":8,"userId":3,"balance":50}]}