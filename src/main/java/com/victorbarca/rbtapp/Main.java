/*
 * Created by Victor on 22/02/2019.
 */
package com.victorbarca.rbtapp;

import static  spark.Spark.get;

public class Main {
    public static void main (String[] args){
        System.out.println("Hello world");
        get("/test", (request, response) -> "User: username: test, email=test@test.net");
    }
}
