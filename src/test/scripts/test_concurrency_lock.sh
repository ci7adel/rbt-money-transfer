#!/usr/bin/env bash

# Transfer account 2 -> 3
# Transfer account 1 -> 2
# Each line must be executed is a different terminal to simulate concurrency

curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 2, "accountIdTo": 3,"amount": 1}' -H "Content-Type: application/json"
curl -i -X POST http://localhost:8080/accounts/transfer -d '{"accountIdFrom": 1, "accountIdTo": 2,"amount": 1}' -H "Content-Type: application/json"

## Result ##
# The account 2 for the second transfer is not locked until finnish the fist transfer

#[Thread qtp1472546203-27] post /accounts/transfer request
#[Thread qtp1472546203-27] Trying to lock account 2....
#[Thread qtp1472546203-27] Account 2 LOCKED
#[Thread qtp1472546203-27] Trying to lock account 3....
#[Thread qtp1472546203-27] Account 3 LOCKED
#[Thread qtp1472546203-27] Sending money...
#[Thread qtp1472546203-16] post /accounts/transfer request
#[Thread qtp1472546203-16] Trying to lock account 1....
#[Thread qtp1472546203-16] Account 1 LOCKED
#[Thread qtp1472546203-16] Trying to lock account 2....
#[Thread qtp1472546203-27] Transfer successful. Balance account 2: 99 Balance account 3: 101
#[Thread qtp1472546203-16] Account 2 LOCKED
#[Thread qtp1472546203-16] Sending money...
#[Thread qtp1472546203-16] Transfer successful. Balance account 1: 99 Balance account 2: 100