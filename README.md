# RBT-MoneyTransfer

**Author:** Victor Barca

**Description:** RESTFul API to support money transfer between users.

## ToDo:
- Create entities: users, accounts...
- Create DB
- Create CRUD for entities.
- Create API.
- Create transaction process. 


`curl -d '{"userId": "U00002", "name": "Pedro"}' -H "Content-Type: application/json" -X POST http://localhost:4567/users`

`curl -i http://localhost:4567/users/U00002`