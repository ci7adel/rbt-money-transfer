#!/usr/bin/env bash
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 2, "accountIdTo": 3,"amount": 1}' -H "Content-Type: application/json"



