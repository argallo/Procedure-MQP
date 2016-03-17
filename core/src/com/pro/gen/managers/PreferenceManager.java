package com.pro.gen.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.XmlWriter;

/**
 * Created by Gallo on 9/2/2015.
 */
public class PreferenceManager {

    public static final String PREFERENCES = "Pref";
    public static final String HAS_ACCOUNT = "HasAcc";
    public static final String ACCOUNT_NAME = "AccName";


    public static final String SLOT_1 = "PlanetSlot1";
    public static final String SLOT_2 = "PlanetSlot2";
    public static final String SLOT_3 = "PlanetSlot3";
    public static final String SLOT_4 = "PlanetSlot4";



    private Preferences preferences;

    private static final PreferenceManager INSTANCE = new PreferenceManager();

    public static PreferenceManager getInstance() {
        return INSTANCE;
    }

    public PreferenceManager(){
        preferences = Gdx.app.getPreferences(PREFERENCES);
    }

    public Preferences getPreferences() {
        return preferences;
    }

    /**
     * Determine if a user already has an account
     * @return true if the user currently has an account saved locally
     */
    public boolean hasAccount(){
        return preferences.getBoolean(HAS_ACCOUNT, false);
    }



}
