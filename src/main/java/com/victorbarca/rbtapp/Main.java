/*
 * Created by Victor on 22/02/2019.
 */
package com.victorbarca.rbtapp;

import com.google.gson.Gson;

import com.victorbarca.rbtapp.services.AccountService;
import com.victorbarca.rbtapp.services.UserService;

import static  spark.Spark.get;

public class Main {

    public static void main (String[] args){
        System.out.println("Hello world");

        // Dependencies Todo: dependency injector
        UserService userService = new UserService();
        AccountService accountService = new AccountService();

        get("/test", (request, response) -> "User: username: test, email=test@test.net");
        get("/user/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS",new Gson()
                            .toJsonTree(userService.getUser(request.params(":id")))));
        });

        get("/account/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS",new Gson()
                            .toJsonTree(accountService.getAccount(request.params(":id")))));
        });
    }
}
