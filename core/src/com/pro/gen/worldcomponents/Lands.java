package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.Item;

/**
 * Created by Gallo on 8/15/2015.
 */
public class Lands {

    private MapItems landCylinder = new MapItems(Item.CYLINDER, 200, 50);
    private MapItems landCircle = new MapItems(Item.CIRCLE, 110, 50);
    private MapItems[][] allMaps;

    private static final Lands INSTANCE = new Lands();

    public static Lands getInstance() {
        return INSTANCE;
    }

    public Lands(){
        allMaps = new MapItems[][]{
                new MapItems[]{
                        landCylinder, landCylinder, landCylinder, landCylinder,landCylinder,
                        landCylinder, landCylinder, landCylinder, landCylinder,landCylinder,
                },
                new MapItems[]{
                        landCircle, landCircle, landCircle, landCircle,landCircle,
                        landCircle, landCircle, landCircle
                },
        };
    }

    public MapItems[] getRandomLands(){
        return allMaps[MathUtils.random(allMaps.length-1)];
    }

    public float getSpeed(){
        return 1.7f;
    }

}
