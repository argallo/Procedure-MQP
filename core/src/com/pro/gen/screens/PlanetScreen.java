package com.pro.gen.screens;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 10/22/2015.
 */
public class PlanetScreen extends ShipScreen {

    Planet planet;

    public PlanetScreen(ShipHUD shipHUD) {
        super(shipHUD);
        this.setVisible(false);
    }


    public void createPlanetFromColor(Color planetBaseColor){
        planet = new Planet(planetBaseColor, new RandomPlanet(), false);
        planet.setSize(400,400);
        planet.setPosition(Constants.VIRTUAL_WIDTH/2-200, Constants.VIRTUAL_HEIGHT/2 - 200);
        addActor(planet);
    }
}
