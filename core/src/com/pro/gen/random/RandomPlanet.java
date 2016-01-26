package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.GlobeObjectType;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomPlanet {

    private GlobeMap Lands;
    private GlobeMap Clouds;

    public Color getPlanetColor(){
        return ColorHelper.generateGoodColor();
    }

    public GlobeMap getLands(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Land));
    }

    public GlobeMap getClouds(){
        return new GlobeMap(new RandomGlobeMapInfo(GlobeObjectType.Cloud));
    }

}
