/*
 * Created by Victor on 22/02/2019.
 */
package com.victorbarca.rbtapp;

import com.google.gson.Gson;

import com.victorbarca.rbtapp.controllers.TestController;
import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.StandardResponse;
import com.victorbarca.rbtapp.data.TransferData;
import com.victorbarca.rbtapp.data.User;
import com.victorbarca.rbtapp.services.AccountService;
import com.victorbarca.rbtapp.services.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class Main {

    public static void main (String[] args) {
        port(8080);
        // Dependencies Todo: dependency injector
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        TestController testController = new TestController();

        // Get User
        get("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS", new Gson()
                            .toJsonTree(userService.getUser(request.params(":id")))));
        });

        // Create User
        post("/users", (request, response) -> {
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            userService.createUser(user);
            response.status(201);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        // Get all Accounts
        get("/accounts", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse("SUCCESS", new Gson()
                            .toJsonTree(accountService.getAccounts())));
        });

        // Get Account
        get("/accounts/:id", (request, response) -> {
            response.type("application/json");
            try {
                return new Gson().toJson(
                        new StandardResponse("SUCCESS", new Gson()
                                .toJsonTree(accountService.getAccount(Integer.valueOf(request.params(":id"))))));
            } catch (Exception e) {
                response.status(404); // Not Found Todo: be sure is not found
                return new Gson().toJson(new StandardResponse("ERROR", e.getMessage()));
            }
        });

        // Delete Account
        delete("/accounts/:id", (request, response) -> {
            response.type("application/json");
            try{
                accountService.deleteAccount(Integer.valueOf(request.params(":id")));
                return new Gson().toJson(new StandardResponse("SUCCESS"));
            } catch (Exception e) {
                response.status(404); //Todo: check if really not found
                return new Gson().toJson(new StandardResponse("ERROR", e.getMessage()));
            }
        });

        // Create Account
        post("/accounts", (request, response) -> {
            response.type("application/json");
            Account account = new Gson().fromJson(request.body(), Account.class);
            accountService.createAccount(account);
            response.status(201);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        // Deposit account
        post("/accounts/:accountId/deposit/:amount", (request, response) -> {
            response.type("application/json");
            accountService.deposit(Integer.valueOf(request.params(":accountId")), new BigDecimal(request.params(":amount")));
//            response.status(201);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        // Make transfer
        post("/accounts/transfer", (request, response) -> {
            response.type("application/json");
            TransferData transferData = new Gson().fromJson(request.body(), TransferData.class);
            try {
                accountService.transfer(transferData);
                List<Account> accounts = Arrays.asList(accountService.getAccount(transferData.accountIdFrom),
                        accountService.getAccount(transferData.accountIdTo));
                return new Gson().toJson(new StandardResponse("SUCCESS", new Gson().toJsonTree(accounts)));
            } catch (Exception e) {
                response.status(500);
                return new Gson().toJson(new StandardResponse("ERROR", e.getMessage()));
            }
        });

        get("/tests/1", (request, response) -> {
            response.type("application/json");
            testController.test1();
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });


    }
}
