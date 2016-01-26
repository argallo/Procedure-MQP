package com.pro.gen.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.worldcomponents.FlyGroundPopup;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 10/22/2015.
 */
public class PlanetScreen extends ShipScreen {

    Planet planet;
    FlyGroundPopup flyGroundPopup;

    public PlanetScreen(ShipHUD shipHUD) {
        super(shipHUD);
        this.setVisible(false);
        flyGroundPopup = new FlyGroundPopup(this);
    }


    public void createPlanetFromColor(Color planetBaseColor){
       // planet = new Planet(planetBaseColor, new RandomPlanet(), false, this);
       // planet.setSize(400,400);
       // planet.setPosition(Constants.VIRTUAL_WIDTH / 2 - 200, Constants.VIRTUAL_HEIGHT / 2 - 200);
      //  addActor(planet);
    }

    public void planetClicked(){
        addActor(flyGroundPopup);
        //fly animation into planet
        //fade out transition to side scroll view of planet
    }

    public void popUpFly(){
        flyGroundPopup.remove();
        this.addAction(Actions.scaleBy(20, 20, 2f, Interpolation.exp10));
        shipHUD.transitionTo(ShipHUD.ShipScreenTypes.LAND);
    }

    public void popUpCancel(){
        flyGroundPopup.remove();
    }

}
