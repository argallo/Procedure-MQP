package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.worldcomponents.LandSky;

/**
 * Created by Gallo on 2/6/2016.
 */
public abstract class LandView extends BaseView {

    protected TintedImage bg1, bg2, bg3, bg4, bg5, bgGround;
    protected LandSky landSky;

    @Override
    public void init() {
        landSky = new LandSky();

        Color c = ColorHelper.generateDarkColor();
        bg1 = new TintedImage(Pic.Bg1, new Color(c));
        c = ColorHelper.lighten(c, 0.2f);
        bg4 = new TintedImage(Pic.Bg4, new Color(c));
        c = ColorHelper.lighten(c, 0.2f);
        bg5 = new TintedImage(Pic.Bg5, new Color(c));
        bg2 = new TintedImage(Pic.Bg2, new Color(c));
        c = ColorHelper.lighten(c, 0.2f);
        bg3 = new TintedImage(Pic.Bg3, new Color(c));
        c = ColorHelper.darken(c, 0.3f);
        bgGround = new TintedImage(Pic.Pixel, new Color(c));
    }

    @Override
    public void setSizes() {
        bg1.setSize(Constants.VIRTUAL_WIDTH, 427);
        bg2.setSize(Constants.VIRTUAL_WIDTH, 466);
        bg3.setSize(Constants.VIRTUAL_WIDTH, 232);
        bg4.setSize(1005, 246);
        bg5.setSize(Constants.VIRTUAL_WIDTH, 154);
        bgGround.setSize(Constants.VIRTUAL_WIDTH, 130);
    }

    @Override
    public void setPositions() {
        bg1.setPosition(0,100);
        bg2.setPosition(0,100);
        bg3.setPosition(0,100);
        bg4.setPosition(265,0);
    }


}
