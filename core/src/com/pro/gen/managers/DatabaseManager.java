package com.pro.gen.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.pro.gen.utils.LogUtils;

import java.util.Map;

/**
 * Created by Gallo on 9/3/2015.
 */
public class DatabaseManager {

    public static final String CREATE = "create1.php";
    public static final String OPENED = "opened1.php";
    public static final String CLOSED = "closed1.php";
    public static final String OPTION = "options1.php";
    public static final String WEEKLY = "weeklysteps1.php";
    public static final String SCORE = "leaderboard1.php";
    public static final String INIT = "leaderboardinit1.php";
    public static final String LEADERBOARD = "getleaderboard1.php";


    private static final DatabaseManager INSTANCE = new DatabaseManager();

    public static DatabaseManager getInstance() {
        return INSTANCE;
    }

    public void makeDBCall(String route, Map<String, String> params, Net.HttpResponseListener listener){
        LogUtils.Log("made it data");

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl("http://2firestudios.com/www/" + route);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setContent(HttpParametersUtils.convertHttpParameters(params));
        Gdx.net.sendHttpRequest(httpPost, listener);
    }

}
