package com.pro.gen.utils;

import com.badlogic.gdx.Gdx;

/**
 * Created by Gallo on 8/11/2015.
 */
public class LogUtils {

    public static void Log() {
        Gdx.app.log("Log", "Empty Log");
    }

    public static void Log(String message) {
        Gdx.app.log("Log", message);
    }

    public static void Log(String logname, String message) {
        Gdx.app.log(logname, message);
    }

    public static void Log(int... numbers) {
        String message = "";
        for(int i = 0; i < numbers.length; i++){
            message += "var"+i+": "+numbers[i];
        }
        Log(message);
    }

    public static void Log(float... numbers) {
        String message = "";
        for(int i = 0; i < numbers.length; i++){
            message += "var"+i+": "+numbers[i];
        }
        Log(message);
    }

}
