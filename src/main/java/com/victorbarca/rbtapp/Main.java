/*
 * Created by Victor on 22/02/2019.
 */
package com.victorbarca.rbtapp;

import com.google.gson.Gson;

import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.Transaction;
import com.victorbarca.rbtapp.data.User;
import com.victorbarca.rbtapp.services.AccountService;
import com.victorbarca.rbtapp.services.UserService;

import static  spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main (String[] args){
        System.out.println("Hello world");

        // Dependencies Todo: dependency injector
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
//        OperationsService operationsService = new OperationsService(accountService);

        get("/test", (request, response) -> "User: username: test, email=test@test.net");

        get("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS",new Gson()
                            .toJsonTree(userService.getUser(request.params(":id")))));
        });

        post("/users", (request, response) -> {
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            userService.createUser(user);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        get("/accounts/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS",new Gson()
                            .toJsonTree(accountService.getAccount(request.params(":id")))));
        });

        post("/accounts", (request, response) -> {
            response.type("application/json");
            Account account = new Gson().fromJson(request.body(), Account.class);
            accountService.createAccount(account);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        post("/accounts/transaction", (request, response) -> {
            response.type("application/json");
            Transaction transaction = new Gson().fromJson(request.body(), Transaction.class);
            try{
                accountService.transfer(transaction);
                return new Gson().toJson(new StandardResponse("SUCCESS"));
            }catch (Exception e){
                return new Gson().toJson(new StandardResponse("ERROR",e.getMessage()));
            }
        });
    }
}
