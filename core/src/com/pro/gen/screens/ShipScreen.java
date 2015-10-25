package com.pro.gen.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/22/2015.
 */
public abstract class ShipScreen extends Group{

    protected ShipHUD shipHUD;


    public ShipScreen(ShipHUD shipHUD){
        this.shipHUD = shipHUD;
        this.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-100);
        this.setPosition(0, 100);
        this.setOrigin(getWidth()/2, (getHeight()+getY())/2);
    }


}
