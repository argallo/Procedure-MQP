package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.TintedImage;
import com.pro.gen.screens.SolarSystemScreen;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/26/2015.
 */
public class MiniPlanetSystem extends Group {

    private MiniPlanet miniPlanet;
    private TintedImage orbit;


    public MiniPlanetSystem(Color color, SolarSystemScreen solarSystemScreen, int radiusAdd){
        miniPlanet = new MiniPlanet(color, solarSystemScreen, radiusAdd);
        orbit = new TintedImage(Constants.RING, Color.WHITE);
        orbit.setSize((MiniPlanet.radiusX + radiusAdd)*2, (MiniPlanet.radiusY + radiusAdd)*2);
        orbit.setPosition((MiniPlanet.radiusY * 2) - radiusAdd, (MiniPlanet.radiusY * 2) - radiusAdd);
        orbit.setTouchable(Touchable.disabled);
        addActor(orbit);
        addActor(miniPlanet);
    }

    public MiniPlanet getMiniPlanet() {
        return miniPlanet;
    }
}
