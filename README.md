# RBT-MoneyTransfer

**Author:** Victor Barca

**Description:** RESTFul API to support money transfer between accounts for the Revolut Backend Test.

## Documentation

The API was developed using the lightweight framework Spark and Maven as building tool 
and JUnit and REST-Assured for testing purposes. 

The data is stored in memory, as the requirements of the test states, and the classes were designed separating concerns 
where "services" access the database and the "controllers" manage the business logic. The "data base" is a simple **singleton**
containing Map structures to store objects by ID. This architecture of classes allow to handle specific development aspects 
being able to change a database endpoint, for example, without affecting the rest of the structure.

As the requirement "3. Assume the API is invoked by multiple systems and services on behalf of end users" states, 
the API is particularly focused on the "transfer" method, allowing **concurrent calls** from different requests 
"locking" pairs of accounts every time a request is being processed to not create inconsistent states and loss of funds.
This is done locking the accounts always in the same order to avoid "deadlocks".


## Data Model

A very simple data model for this API was created to be able to "play" and simulate a real world relation.
The model consists in two entities **User** and **Account** with a relation "1 to Many". The API is consistent with this model
providing the logic to maintain this relationship. 

### Run server

With the code and test cases is provided a JAR artifact where all the dependencies are included. 
So no need of installation of any additional software to run the server as a standalone program.

Run command `java -jar RBT-MoneyTransfer-1.0-SNAPSHOT.jar`

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
- Testing
- Block remove used user 