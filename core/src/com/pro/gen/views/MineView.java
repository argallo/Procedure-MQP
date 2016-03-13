package com.pro.gen.views;

import com.pro.gen.worldcomponents.OilRig;

/**
 * Created by Gallo on 1/21/2016.
 */
public class MineView extends LandView {

    private OilRig oilRig;

    @Override
    public void init() {
        super.init();
        oilRig = new OilRig();

    }

    @Override
    public void setSizes() {
        super.setSizes();
    }

    @Override
    public void setPositions() {
        super.setPositions();
    }

    @Override
    public void addActors() {
        addActor(landSky);
        addActor(bg1);
        addActor(bg2);
        addActor(bg3);
        addActor(oilRig);
        addActor(bgGround);
        addActor(bg4);
        addActor(bg5);
    }

    @Override
    public void handle(int outcome) {

    }

}
