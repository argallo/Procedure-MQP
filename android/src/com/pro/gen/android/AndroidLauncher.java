package com.pro.gen.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.pro.gen.App;
import com.pro.gen.managers.DatabaseManager;

import java.util.HashMap;
import java.util.Map;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new App(), config);
	}

    @Override
    protected void onDestroy() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", "Jim");
        DatabaseManager.getInstance().makeDBCall(DatabaseManager.CLOSED, params, null);
        super.onDestroy();
    }
}
