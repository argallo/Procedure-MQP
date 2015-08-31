package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.Clouds;
import com.pro.gen.worldcomponents.GlobeObjectType;
import com.pro.gen.worldcomponents.Lands;

/**
 * Created by Gallo on 8/13/2015.
 */
public class RandomGlobeMapInfo {

    private TintedImage[] globePieces;
    private Color color;
    private RandomPlacement rp;
    private float speed;


    public RandomGlobeMapInfo(GlobeObjectType globeObjectType){
        selectGlobeObject(globeObjectType);
        initGlobeGeneration();
    }

    public void selectGlobeObject(GlobeObjectType globeObjectType){
        switch (globeObjectType){
            case Land:
                rp = new RandomPlacement(Lands.getInstance().getRandomLands(), Constants.GLOBE_REC_WIDTH, Constants.GLOBE_REC_HEIGHT);
                speed = Lands.getInstance().getSpeed();
                color = ColorHelper.generateGoodColor();
                break;
            case Cloud:
                rp = new RandomPlacement(Clouds.getInstance().getRandomClouds(), Constants.GLOBE_REC_WIDTH, Constants.GLOBE_REC_HEIGHT);
                speed = Clouds.getInstance().getSpeed();
                color = ColorHelper.generateLightColor();
                break;
        }
    }

    public void initGlobeGeneration(){
        globePieces = new TintedImage[rp.getSize()];
        for(int index = 0; index < globePieces.length; index++){
            generateTintedImage(index);
        }
    }


    public void generateTintedImage(int index){
        TintedImage globePiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), color);
        globePiece.setSize(rp.getWidth(index), rp.getHeight(index));
        globePiece.setPosition(rp.getX(index), rp.getY(index));
        globePieces[index] = globePiece;
    }

    public TintedImage[] getMapActors(){
        return globePieces;
    }

    public float getSpeed(){
        return speed;
    }

}
