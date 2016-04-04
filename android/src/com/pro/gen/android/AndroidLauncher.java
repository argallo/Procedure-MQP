package com.pro.gen.android;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.pro.gen.App;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.text.DateFormat.getDateInstance;
import static java.text.DateFormat.getTimeInstance;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks {

    private boolean finished = false;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 6. Finally, replace the AndroidLauncher activity content with the Libgdx Fragment.
        GameFragment fragment = new GameFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, fragment);
        trans.commit();
    }

    // 4. Create a Class that extends AndroidFragmentApplication which is the Fragment implementation for Libgdx.
    @SuppressLint("ValidFragment")
    protected class GameFragment extends AndroidFragmentApplication
    {
        // 5. Add the initializeForView() code in the Fragment's onCreateView method.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {  return initializeForView(new App());   }
    }


    @Override
    public void exit() {}


	/*@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new App(), config);
	}
*/
/*
    @Override
    protected void onResume() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.OPENED, params, null);

        super.onResume();
    }

    @Override
    protected void onPause() {
        LogUtils.Log("ANDROID PAUSE");
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.CLOSED, params, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                LogUtils.Log("FINISHED CLOSING");
                finished = true;
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
        while (!finished){

        }
        finished = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    */
}
