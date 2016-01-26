package com.pro.gen.screens;

import com.pro.gen.components.ShipHUD;

/**
 * Created by Gallo on 10/22/2015.
 */
public class ScreenFactory {

    private ShipHUD shipHUD;

    public ScreenFactory(ShipHUD shipHUD){
        this.shipHUD = shipHUD;
    }


    public ShipScreen createScreen(ShipHUD.ShipScreenTypes shipScreenTypes){
        switch (shipScreenTypes){
            case INSIDE:
                return new InsideShipScreen(shipHUD);
            case EXPLORE:
                return new ExploreScreen(shipHUD);
            case HOME:
                return new HomeScreen(shipHUD);
            case INVENTORY:
                return new InventoryScreen(shipHUD);
            case SOLARSYSTEM:
                //return new SolarSystemScreen(shipHUD);
                return null;
            case PLANET:
                return new PlanetScreen(shipHUD);
            default:
                return new InsideShipScreen(shipHUD);
        }
    }

}
