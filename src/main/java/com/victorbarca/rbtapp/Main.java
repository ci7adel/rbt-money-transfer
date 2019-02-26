/*
 * Created by Victor on 22/02/2019.
 */
package com.victorbarca.rbtapp;

import com.google.gson.Gson;

import com.victorbarca.rbtapp.controllers.AccountController;
import com.victorbarca.rbtapp.controllers.TestController;
import com.victorbarca.rbtapp.data.Account;
import com.victorbarca.rbtapp.data.StandardResponse;
import com.victorbarca.rbtapp.data.TransferData;
import com.victorbarca.rbtapp.data.User;
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
//        AccountService accountService = new AccountService();

        AccountController accountController = new AccountController();

        TestController testController = new TestController();

        // Get User
        get("/", (request, response) -> {
            response.type("text/html");
            return "<html><body>RBT-API up and running...</body></html>";
        });


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
                            .toJsonTree(accountController.getAccounts())));
        });

        // Get Account
        get("/accounts/:id", (request, response) -> {
            response.type("application/json");
            try {
                return new Gson().toJson(
                        new StandardResponse("SUCCESS", new Gson()
                                .toJsonTree(accountController.getAccount(Integer.valueOf(request.params(":id"))))));
            } catch (Exception e) {
                response.status(404); // Not Found Todo: be sure is not found
                return new Gson().toJson(new StandardResponse("ERROR", e.getMessage()));
            }
        });

        // Delete Account
        delete("/accounts/:id", (request, response) -> {
            response.type("application/json");
            try{
                accountController.deleteAccount(Integer.valueOf(request.params(":id")));
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
            accountController.createAccount(account);
            response.status(201);
            return new Gson().toJson(new StandardResponse("SUCCESS"));
        });

        // Deposit account
//        put("/accounts/:accountId/deposit/:amount", (request, response) -> {
//            response.type("application/json");
//            accountController.deposit(Integer.valueOf(request.params(":accountId")), new BigDecimal(request.params(":amount")));
////            response.status(201);
//            return new Gson().toJson(new StandardResponse("SUCCESS"));
//        });

        // Make transfer
        post("/accounts/transfer", (request, response) -> {
            System.out.println("post /accounts/transfer request");
            response.type("application/json");
            TransferData transferData = new Gson().fromJson(request.body(), TransferData.class);
            try {
                accountController.transfer(transferData);
                List<Account> accounts = Arrays.asList(accountController.getAccount(transferData.accountIdFrom),
                        accountController.getAccount(transferData.accountIdTo));
                return new Gson().toJson(new StandardResponse("SUCCESS", new Gson().toJsonTree(accounts)));
            } catch (Exception e) {
                response.status(500);
                return new Gson().toJson(new StandardResponse("ERROR", e.getMessage()));
            }
        });

//        get("/tests/1", (request, response) -> {
//            response.type("application/json");
//
//            return new Gson().toJson(new StandardResponse("SUCCESS"));
//        });


    }
}
