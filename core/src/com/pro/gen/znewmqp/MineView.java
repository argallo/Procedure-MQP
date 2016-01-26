package com.pro.gen.znewmqp;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Range;
import com.pro.gen.views.BaseView;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/21/2016.
 */
public class MineView extends BaseView {

    TintedImage bg, bg2, bg3, bg4;
    TintedImage planetBG;
    StarMap starMap;


    @Override
    public void init() {
        Color c = ColorHelper.generateLandColor();
        ColorHelper.lighten(c, 0.1f);
        bg = new TintedImage(Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg2 = new TintedImage(Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg3 = new TintedImage(Pic.Pixel, new Color(c));
        ColorHelper.lighten(c, 0.06f);
        bg4 = new TintedImage(Pic.Pixel, new Color(c));

        starMap = new StarMap(0, 40, new Item(Pic.Circle_Small, new Range(1f,1f),1f,5f));


        planetBG = new TintedImage(Pic.Planet_BG, ColorHelper.generateLandColor());
    }

    @Override
    public void setSizes() {
        planetBG.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-200);

        bg.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg2.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg3.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);
        bg4.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/5);

    }

    @Override
    public void setPositions() {
        bg.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight());
        bg2.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*2);
        bg3.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*3);
        bg4.setPosition(0, Constants.VIRTUAL_HEIGHT-bg.getHeight()*4);

    }

    @Override
    public void addActors() {
        addActor(bg);
        addActor(bg2);
        addActor(bg3);
        addActor(bg4);
        addActor(starMap);
        addActor(planetBG);

    }
}
