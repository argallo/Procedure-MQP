package com.pro.gen.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Gallo on 9/2/2015.
 */
public class PreferenceManager {

    public static final String PREFERENCES = "Pref";
    public static final String HAS_ACCOUNT = "HasAcc";
    public static final String ACCOUNT_NAME = "AccName";
    public static final String ACCOUNT_ID = "AccID";
    public static final String RESET = "Reset";


    public static final String SLOT_1 = "PlanetSlot1";
    public static final String SLOT_2 = "PlanetSlot2";
    public static final String SLOT_3 = "PlanetSlot3";
    public static final String SLOT_4 = "PlanetSlot4";
    public static final String BATTLE_SLOT = "BattleSlot";
    public static final String HAT_LIST = "HatList";
    public static final String MEGA_CRYSTALS = "MegaCrystals";
    public static final String POWER_CRYSTALS = "PowerCrystals";
    public static final String FUEL_UNITS = "FuelUnits";
    public static final String FUEL_TIMER = "FuelTimer";
    public static final String FUEL_AMT = "FuelAmt";
    public static final String BOSS_LEVEL = "BossLevel";
    public static final String BOSS_SLOT = "BossSlot";
    public static final String SOLAR_SYSTEM = "SolarSystem";



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


    public void saveString(String key, String value){
        preferences.putString(key, value);
        preferences.flush();
    }

    public void saveInt(String key, int value){
        preferences.putInteger(key, value);
        preferences.flush();
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public int getInt(String key){
        return preferences.getInteger(key, 0);
    }

    public void clear(){
        preferences.clear();
        preferences.flush();
    }

}
