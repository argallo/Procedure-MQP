package com.pro.gen.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.planet.EyePreset;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.GlobeObjectType;
import com.pro.gen.worldcomponents.Hat;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomPlanet {

    private int planetSize; // The size stat of the planet
    private int planetEnergy; // The Energy Output of the planet
    private int globeRank; // The Globe Rank of the planet
    private int currentXP; // The Current XP that will be used to increase globe rank
    private int rankXP; // The overall amount of XP needed to increase globe rank
    private boolean inhabitable; //whether the planet is inhabitable
    private long timeStart; // the time at which it became inhabitable
    private long amtofTime; // the amount of time from timeStart to become habitable
    private String planetName;

    private String colorType;
    private Color baseColor;
    private GlobeMap lands;
    private GlobeMap clouds;
    private PlanetEyes planetEyes;
    private Hat hat;


    public RandomPlanet(int lowRank, int highRank){
        globeRank = MathUtils.random(lowRank, highRank);
        planetSize = MathUtils.random((int)Math.pow(globeRank, 1.3)*100+200, (int)Math.pow(globeRank, 1.3)*105+300);
        planetEnergy = MathUtils.random((int)Math.pow(globeRank, 1.3)*100+600, (int)Math.pow(globeRank, 1.3)*105+800);
        currentXP = 0;
        rankXP = (int)Math.pow(globeRank, 1.2)*100;
        colorType = ColorHelper.generatePlanetColor();
        baseColor = Color.valueOf(colorType.substring(colorType.indexOf(":") + 1));
        colorType = colorType.substring(0, colorType.indexOf(":"));
        inhabitable = true;
        timeStart = 0;
        amtofTime = 0;
        planetName = generateRandomName();
        hat = new Hat();//TODO: make empty hat and something that uses star rarity to randomly generate hats
    }

    public String generateRandomName(){
        String alphabet = "ABCDEFGHIJKLMNOPQURTUVWXYZ1234567890-_";
        int length = MathUtils.random(5,6);
        String name = "";
        for(int i = 0; i < length; i++){
            name += alphabet.charAt(MathUtils.random(alphabet.length()-1));
        }
        return name;
    }

    public Color getPlanetColor(){
        return baseColor;
    }

    public String getColorType() {
        return colorType;
    }

    public GlobeMap getLands(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Land));
    }

    public GlobeMap getClouds(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Cloud));
    }

    public PlanetEyes getPlanetEyes(){
        return new PlanetEyes();
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public int getGlobeRank() {
        return globeRank;
    }

    public int getPlanetEnergy() {
        return planetEnergy;
    }

    public int getPlanetSize() {
        return planetSize;
    }

    public int getRankXP() {
        return rankXP;
    }

    public long getAmtofTime() {
        return amtofTime;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public boolean isInhabitable() {
        return inhabitable;
    }

    public Hat getHat() {
        return hat;
    }

    public String getPlanetName() {
        return planetName;
    }
}
