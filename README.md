# RBT-MoneyTransfer

**Author:** Victor Barca

**Description:** RESTFul API to support money transfer between accounts for the Revolut Backend Test.

## Documentation

The API was develop using the lightweight framework Spark and Maven as building tool 
and JUnit and REST-Assured for testing purposes.

### Run server

As long with the code and test cases is provided an JAR artifact where all the dependencies are included. 
So no need to install any additional software to run the server.

`java -jar RBT-MoneyTransfer-1.0-SNAPSHOT.jar`

### Routes

`GET /accounts/:id`

`POST /accounts/transfer` sending the transfer object:

```json
{"accountIdFrom": 1,"accountIdTo": 2,"amount": 500}
```

## Testing API
Testing the API can be done running the test units included with the source code using Maven.
I used REST-Assured to test and validate the response of the API in a easy way. 

To run these tests is necessary Maven:

`mvn test`

Or can be done using CURL or PostMan:

### Make a transfer

`curl -d '{"accountIdFrom": 1, "accountIdTo": 2,"amount": 500}' -H "Content-Type: application/json" -X POST http://localhost:8080/accounts/transfer`

### Get info of an existing account

`curl -i http://localhost:8080/accounts/1`

### Deposit fund into an account

`curl -i -X PUT http://localhost:8080/accounts/1/deposit/1`

## ToDo:
- Create entities: users, accounts...
- Create DB
- Create CRUD for entities.
- Create API.
- Create transaction process. 