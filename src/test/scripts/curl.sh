#!/usr/bin/env bash

# Create accounts
curl -i -X POST http://localhost:8080/accounts -d '{"accountId": 5, "userId": 1}' -H "Content-Type: application/json"
curl -i -X POST http://localhost:8080/accounts -d '{"accountId": 6, "userId": 1}' -H "Content-Type: application/json"

# Deposit funds
curl -i -X PUT http://localhost:8080/accounts/5/deposit/100

# Transfer between the created accounts
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 5, "accountIdTo": 6,"amount": 1}' -H "Content-Type: application/json"
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 5, "accountIdTo": 5,"amount": 1}' -H "Content-Type: application/json"

#curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 3, "accountIdTo": 4,"amount": 1}' -H "Content-Type: application/json"
#curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 4, "accountIdTo": 3,"amount": 1}' -H "Content-Type: application/json"

pause