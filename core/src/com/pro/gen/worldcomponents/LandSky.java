package com.pro.gen.worldcomponents;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.*;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/27/2016.
 */
public class LandSky extends Group {

    TintedImage bg, bg2, bg3, bg4;
    StarMap starMap;

    public LandSky(){
        Color c = ColorHelper.generateLandColor();
        ColorHelper.lighten(c, 0.1f);
        bg = new TintedImage(com.pro.gen.utils.Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg2 = new TintedImage(com.pro.gen.utils.Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg3 = new TintedImage(com.pro.gen.utils.Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg4 = new TintedImage(com.pro.gen.utils.Pic.Pixel, new Color(c));

        starMap = new StarMap(0, 40, new Item(com.pro.gen.utils.Pic.Circle_Small, new Range(1f,1f),1f,5f));


        bg.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg2.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg3.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg4.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);


        bg.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight());
        bg2.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*2);
        bg3.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*3);
        bg4.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*4);

        addActor(bg);
        addActor(bg2);
        addActor(bg3);
        addActor(bg4);
        addActor(starMap);
    }



}
