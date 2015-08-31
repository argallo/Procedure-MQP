package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.Item;

/**
 * Created by Gallo on 8/15/2015.
 */
public class Clouds {

    private MapItems cloudCylinder = new MapItems(Item.CYLINDER, 100, 50);
    private MapItems cloudCircle = new MapItems(Item.CIRCLE, 50, 30);
    private MapItems[][] allMaps;

    private static final Clouds INSTANCE = new Clouds();

    public static Clouds getInstance() {
        return INSTANCE;
    }

    public Clouds(){
        allMaps = new MapItems[][]{
                new MapItems[]{
                        cloudCylinder, cloudCylinder, cloudCylinder, cloudCylinder, cloudCylinder,
                        cloudCylinder, cloudCylinder, cloudCylinder, cloudCylinder, cloudCylinder,
                },
                new MapItems[]{
                        cloudCircle, cloudCircle, cloudCircle, cloudCircle, cloudCircle,
                        cloudCircle, cloudCircle, cloudCircle, cloudCircle, cloudCircle,
                },
        };
    }

    public MapItems[] getRandomClouds(){
        return allMaps[MathUtils.random(allMaps.length - 1)];
    }

    public float getSpeed(){
        return 1.5f;
    }

}
