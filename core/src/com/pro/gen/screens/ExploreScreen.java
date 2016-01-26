package com.pro.gen.screens;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.worldcomponents.NewStarMap;
import com.pro.gen.worldcomponents.WarpToPopup;

/**
 * Created by Gallo on 10/22/2015.
 */
public class ExploreScreen extends ShipScreen{

    private NewStarMap map;
    private WarpToPopup warpToPopup;

    public ExploreScreen(ShipHUD shipHUD) {
        super(shipHUD);
        warpToPopup = new WarpToPopup(this);
       // map = new NewStarMap(200, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-100, this);
        addActor(map);
    }


    public void popUp(){
        map.setTouchable(Touchable.disabled);
        addActor(warpToPopup);
    }

    public void popUpFly(){
        //fly somewhere
        //hyper speed to solar system
        //animate selecting solar system planets
        //zoom into planet.
        //give details about planet.
        //screen transition to landing into 2d scroller.
        warpToPopup.remove();
        shipHUD.transitionTo(ShipHUD.ShipScreenTypes.SOLARSYSTEM);
    }

    public void popUpCancel(){
        map.setTouchable(Touchable.enabled);
        warpToPopup.remove();
    }

}
