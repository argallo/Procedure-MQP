package com.pro.gen.views;

import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.LandSky;
import com.pro.gen.worldcomponents.OilRig;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 1/21/2016.
 */
public class MineView extends BaseView {


    TintedImage planetBG;
    LandSky landSky;
    OilRig oilRig;



    @Override
    public void init() {
        landSky = new LandSky();
        planetBG = new TintedImage(Pic.Planet_BG, ColorHelper.generateLandColor());
        oilRig = new OilRig();

    }

    @Override
    public void setSizes() {
        planetBG.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-200);


    }

    @Override
    public void setPositions() {
        
    }

    @Override
    public void addActors() {
        addActor(landSky);
        addActor(planetBG);
        addActor(oilRig);


    }

    @Override
    public void handle(int outcome) {

    }
}
