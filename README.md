# RBT-MoneyTransfer

**Author:** Victor Barca

**Description:** RESTFul API to support money transfer between accounts.

## Documentation

### Run server

All dependencies are included in the jar

`java -jar RBT-MoneyTransfer-1.0-SNAPSHOT.jar`

### Routes

`GET /accounts/:id`

`POST /accounts/transfer` sending the transfer object:

```json
{"accountIdFrom": 1,"accountIdTo": 2,"amount": 500}
```

## Testing API

### Make a transfer

`curl -d '{"accountIdFrom": 1, "accountIdTo": 2,"amount": "500"}' -H "Content-Type: application/json" -X POST http://localhost:8080/accounts/transfer`

### Get info of an existing account

`curl -i http://localhost:8080/account/1`

## ToDo:
- Create entities: users, accounts...
- Create DB
- Create CRUD for entities.
- Create API.
- Create transaction process. 