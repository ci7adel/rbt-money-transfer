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
This is done locking the accounts always in the same order to avoid "deadlocks". Two request involving 2 pairs of 
different accounts can be processed in parallel.


## Data Model

A very simple data model for this API was created to be able to "play" and simulate a real world relation.
The model consists in two entities **User** and **Account** with a relation "1 to Many". The API is consistent with this model
providing the logic to maintain this relationship. 

## Run server

With the code and test cases is provided a JAR artifact where all the dependencies are included. So no need of installation of any additional software to run the server as a standalone program.

Run command `java -jar RBT-MoneyTransfer-1.0-SNAPSHOT.jar`

The server now is running in `http://localhost:8080`

## Routes

**`POST /users`** Create user

```json
{
    "iserId": 1,
    "name": "Victor"
}
```

**`GET /users/{id}`** Get user by id
               
**`GET /users`** Get all users
          
**`PUT /users`** Update user

```json
{
    "iserId": 1,
    "name": "Victor"
}
```
                
**`DELETE /users/{id}`** Delete user bt id
    
**`POST /accounts`** Create account

```json
{
    "accountId": 1,
    "userId": 1
}
```
      
**`GET /accounts/{id}`** Get account by id
        
**`GET /accounts`** Get all accounts

**`PUT /accounts`** Update account

```json
{
    "accountId": 1,
    "userId": 1
}
```
          
**`DELETE /accounts/{id}`** Delete account
              
**`PUT /accounts/{Id}/deposit/{amount}`** Deposit account
          
**`POST /accounts/transfer`** Make transfer:

```json
{
    "accountIdFrom": 1,
    "accountIdTo": 2,
    "amount": 500
}
```
## Testing API
I tested the API with curl commands. Scripts are included in `/scr/test/scripts`.

Also, some test units are provided  to show the facilities of **REST-Assured** to test and validate the response of the API in a easy way. 
To run these tests is necessary Maven: `mvn test`


### Test cases:

`test_normal_user_journey` : Normal user test case where two users open accounts, fund an account and make a transfer between accounts.

`test_concurrency_lock` : Script to demonstrate that the API can process requests independently and concurrently.  
Two request involving 2 pairs of different accounts can be processed in parallel.

`test_concurrency_stress` : Three files to run in separate terminals to simulate multiple transfers requests.
At the end of the tests all accounts should show the same balance as the beginning, proving that there was 
no losses of funds between request under the stress test.


## ToDo:
- Testing
- Block remove used user 
- change hash map