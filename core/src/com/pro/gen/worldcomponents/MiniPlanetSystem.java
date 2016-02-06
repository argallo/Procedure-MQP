package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 10/26/2015.
 */
public class MiniPlanetSystem extends Group {

    private MiniPlanet miniPlanet;
    private TintedImage orbit;


    public MiniPlanetSystem(Color color, SolarSystem solarSystem, int radiusAdd){
        miniPlanet = new MiniPlanet(color, solarSystem, radiusAdd);
        orbit = new TintedImage(Pic.Ring, Color.WHITE);
        orbit.setSize((MiniPlanet.radiusX + radiusAdd)*2, (MiniPlanet.radiusY + radiusAdd)*2);
        orbit.setPosition((MiniPlanet.radiusY * 2) - radiusAdd, (MiniPlanet.radiusY * 2) - radiusAdd);
        orbit.setTouchable(Touchable.disabled);
        addActor(orbit);
        addActor(miniPlanet);
    }

    public MiniPlanet getMiniPlanet() {
        return miniPlanet;
    }

    public void fadeOut(MiniPlanet selectedPlanet){
        if (!miniPlanet.equals(selectedPlanet))
            addAction(Actions.sequence(Actions.sizeTo(1,1,0.5f, Interpolation.exp10), Actions.visible(false)));
        else
            orbit.addAction(Actions.sequence(Actions.sizeTo(1, 1, 0.5f, Interpolation.exp10), Actions.visible(false)));
    }
}
