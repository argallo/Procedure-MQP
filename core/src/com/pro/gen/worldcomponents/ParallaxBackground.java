package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo on 10/29/2015.
 */
public class ParallaxBackground  {

    TintedImage[] chuncks;

    public ParallaxBackground(BaseView baseView, Texture[] textures, Color color, float height, int chuckSize, float peaks){
        chuncks = new TintedImage[chuckSize];

        for(int i = 0; i < chuncks.length; i++){
            chuncks[i] = new TintedImage(textures[MathUtils.random(0, textures.length-1)], color);
            chuncks[i].setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT * peaks);
            chuncks[i].setPosition((i-(chuckSize/2)) * Constants.VIRTUAL_WIDTH, height);
            baseView.addActor(chuncks[i]);
        }

    }


    public void setX(float amount){
        for(TintedImage chunck : chuncks){
            chunck.setX(chunck.getX()+amount);
        }
    }

}
