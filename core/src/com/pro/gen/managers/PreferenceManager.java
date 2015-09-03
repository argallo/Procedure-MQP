package com.pro.gen.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.pro.gen.utils.PreferenceKeys;

/**
 * Created by Gallo on 9/2/2015.
 */
public class PreferenceManager {

    private Preferences preferences;

    private static final PreferenceManager INSTANCE = new PreferenceManager();

    public static PreferenceManager getInstance() {
        return INSTANCE;
    }

    public PreferenceManager(){
        preferences = Gdx.app.getPreferences(PreferenceKeys.PREFERENCES);
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
