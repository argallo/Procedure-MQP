package com.pro.gen.screens;

import com.pro.gen.components.ShipHUD;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 10/22/2015.
 */
public class PlanetScreen extends ShipScreen {

    Planet planet;

    public PlanetScreen(ShipHUD shipHUD) {
        super(shipHUD);

    }


    public void createPlanetFromColor(Color planetBaseColor){
        planet = new Planet(planetBaseColor, new RandomPlanet(), false);
    }
}
