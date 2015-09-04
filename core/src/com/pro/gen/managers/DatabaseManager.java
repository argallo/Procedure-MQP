package com.pro.gen.managers;

/**
 * Created by Gallo on 9/3/2015.
 */
public class DatabaseManager {

    public static final String USERNAME = "username";

    private static final DatabaseManager INSTANCE = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return INSTANCE;
    }

    public int makeDBCall(String Route, String[] parameters){
        return 300;
    }


}
