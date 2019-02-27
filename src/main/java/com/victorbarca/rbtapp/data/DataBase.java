package com.victorbarca.rbtapp.data;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase
{
    // static variable single_instance of type DataBase
    private static DataBase single_instance = null;

    //ConcurrentHashMap for multiple threads requests
    public ConcurrentHashMap<Integer, User> users;
    public ConcurrentHashMap<Integer, Account> accounts;

    // private constructor restricted to this class itself
    private DataBase()
    {
        this.users = new ConcurrentHashMap<>();
        this.users.put(1,new User(1,"Victor"));
        this.users.put(2,new User(2,"Barca"));

        accounts = new ConcurrentHashMap<>();
        accounts.put(1, new Account(1,1, BigDecimal.valueOf(100)));
        accounts.put(2, new Account(2,1, BigDecimal.valueOf(100)));
        accounts.put(3, new Account(3,1, BigDecimal.valueOf(100)));
        accounts.put(4, new Account(4,1, BigDecimal.valueOf(100)));
        accounts.put(5, new Account(5,1, BigDecimal.valueOf(100)));
        accounts.put(6, new Account(6,1, BigDecimal.valueOf(100)));

    }

    // static method to create instance of DataBase class
    public static DataBase getInstance()
    {
        if (single_instance == null) {
            single_instance = new DataBase();
            System.out.println("Database created...");
        }
        return single_instance;
    }
}
