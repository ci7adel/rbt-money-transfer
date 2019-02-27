#!/usr/bin/env bash
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 3, "accountIdTo": 1,"amount": 1}' -H "Content-Type: application/json"

