package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.worldcomponents.GlobeObjectType;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomGlobeMapInfo {

    private ArrayList<TintedImage> globePieces;
    private Color color;
    private RandomPlacement rp;
    private float speed;
    private GlobeObjectType globeObjectType;


    public RandomGlobeMapInfo(GlobeObjectType globeObjectType){
        this.globeObjectType = globeObjectType;
        selectGlobeObject(globeObjectType);
        initGlobeGeneration();
    }

    public void selectGlobeObject(GlobeObjectType globeObjectType){
        switch (globeObjectType){
            case Land:
                rp = new RandomPlacement((MathUtils.random(0,1) == 1) ? Item.LandCylinder : Item.LandCircle, 1125, 500, 10);
                speed = Constants.VIRTUAL_WIDTH/300;
                color = ColorHelper.generateGoodColor();
                break;
            case Cloud:
                rp = new RandomPlacement((MathUtils.random(0,1) == 1) ? Item.CloudCylinder : Item.CloudCircle, 1125, 500, 10);
                speed = Constants.VIRTUAL_WIDTH/400;
                color = ColorHelper.generateLightColor();
                break;
        }
    }


    public void initGlobeGeneration(){
        globePieces = new ArrayList<TintedImage>();
        for(int i = 0; i < rp.getSize(); i++) {
            TintedImage piece = new TintedImage(rp.getName(), color);
            piece.setSize(rp.getWidth(i), rp.getHeight(i));
            piece.setPosition(rp.getX(i), rp.getY(i));
            globePieces.add(piece);
        }
    }


    public float getSpeed(){
        return speed;
    }

    public ArrayList<TintedImage> getGlobePieces() {
        return globePieces;
    }

    public GlobeObjectType getGlobeObjectType() {
        return globeObjectType;
    }
}
