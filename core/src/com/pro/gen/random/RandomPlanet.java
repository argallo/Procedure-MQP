package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.GlobeObjectType;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomPlanet {

    private int planetSize; // The size stat of the planet
    private int planetEnergy; // The Energy Output of the planet
    private int globeRank; // The Globe Rank of the planet
    private int currentXP; // The Current XP that will be used to increase globe rank
    private int rankXP; // The overall amount of XP needed to increase globe rank

    private Color baseColor;
    private GlobeMap lands;
    private GlobeMap clouds;
    private PlanetEyes planetEyes;

    public RandomPlanet(int lowRank, int highRank){
        globeRank = MathUtils.random(lowRank, highRank);
        planetSize = MathUtils.random((int)Math.pow(globeRank, 1.3)*100+200, (int)Math.pow(globeRank, 1.3)*105+300);
        planetEnergy = MathUtils.random((int)Math.pow(globeRank, 1.3)*100+600, (int)Math.pow(globeRank, 1.3)*105+800);
        currentXP = 0;
        rankXP = (int)Math.pow(globeRank, 1.2)*100;
    }

    public Color getPlanetColor(){
        return ColorHelper.generateGoodColor();
    }

    public GlobeMap getLands(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Land));
    }

    public GlobeMap getClouds(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Cloud));
    }

    public PlanetEyes getPlanetEyes(){
        return null;
    }

}
