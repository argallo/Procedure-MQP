package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.GlobeObjectType;
import com.pro.gen.worldcomponents.PlanetAttr;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomPlanet {

    private GlobeMap Lands;
    private GlobeMap Clouds;

    public RandomPlanet(){

    }

    public Color getPlanetColor(){
        // earth return new Color(37f/255f,124f/255f,183f/255f,1f);
        return ColorHelper.generateGoodColor();
    }

    public GlobeMap getLands(){
        return new GlobeMap(GlobeObjectType.Land);
    }

    public GlobeMap getClouds(){
        return new GlobeMap(GlobeObjectType.Cloud);
    }

    public PlanetAttr getPlanetAttr(){
        return new PlanetAttr();
    }

}
