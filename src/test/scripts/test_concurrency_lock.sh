#!/usr/bin/env bash

# Transfer account 2 -> 3
# Transfer account 5 -> 6
# Transfer account 1 -> 2
# Each line must be executed is a different terminal to simulate concurrency

curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 2, "accountIdTo": 3,"amount": 1}' -H "Content-Type: application/json"

curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 5, "accountIdTo": 6,"amount": 1}' -H "Content-Type: application/json"

curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 1, "accountIdTo": 2,"amount": 1}' -H "Content-Type: application/json"


## Result ##
##### The account 2 for the third transfer is not locked until finish the first transfer
### The transfer 5 -> 6 is running independently

# [Thread qtp354959608-18] post /accounts/transfer request
# [Thread qtp354959608-18] Trying to lock account 2....
# [Thread qtp354959608-18] Account 2 LOCKED
# [Thread qtp354959608-18] Trying to lock account 3....
# [Thread qtp354959608-18] Account 3 LOCKED
# [Thread qtp354959608-18] Sending money...
# [Thread qtp354959608-20] post /accounts/transfer request
# [Thread qtp354959608-20] Trying to lock account 5....
### [Thread qtp354959608-20] Account 5 LOCKED
### [Thread qtp354959608-20] Trying to lock account 6....
### [Thread qtp354959608-20] Account 6 LOCKED
### [Thread qtp354959608-20] Sending money...
# [Thread qtp354959608-26] post /accounts/transfer request
# [Thread qtp354959608-26] Trying to lock account 1....
# [Thread qtp354959608-26] Account 1 LOCKED
##### [Thread qtp354959608-26] Trying to lock account 2....
##### [Thread qtp354959608-18] Transfer successful. Balance account 2: 99 Balance account 3: 101
##### [Thread qtp354959608-26] Account 2 LOCKED
# [Thread qtp354959608-26] Sending money...
### [Thread qtp354959608-20] Transfer successful. Balance account 5: 99 Balance account 6: 101
##### [Thread qtp354959608-26] Transfer successful. Balance account 1: 99 Balance account 2: 100