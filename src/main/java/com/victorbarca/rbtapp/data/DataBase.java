package com.victorbarca.rbtapp.data;

import java.math.BigDecimal;
import java.util.HashMap;

public class DataBase
{
    // static variable single_instance of type DataBase
    private static DataBase single_instance = null;
    public HashMap<Integer, User> users;
    public HashMap<Integer, Account> accounts;

    // private constructor restricted to this class itself
    private DataBase()
    {
        this.users = new HashMap<>();
        this.users.put(1,new User(1,"Victor"));

        accounts = new HashMap<>();
        accounts.put(1, new Account(1,1, BigDecimal.valueOf(100)));
        accounts.put(2, new Account(2,1, BigDecimal.valueOf(100)));
        accounts.put(3, new Account(3,1, BigDecimal.valueOf(100)));
        accounts.put(4, new Account(4,1, BigDecimal.valueOf(100)));

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
