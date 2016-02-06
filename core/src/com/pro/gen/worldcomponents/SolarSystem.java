package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.*;
import com.pro.gen.worldcomponents.MiniPlanet;
import com.pro.gen.worldcomponents.MiniPlanetSystem;

/**
 * Created by Gallo on 1/14/2016.
 */
public class SolarSystem extends Group {

    TintedImage sun;
    MiniPlanetSystem[] planets;
    MiniPlanet selectedPlanet;
    com.pro.gen.popups.AbsPopup popup;

    public SolarSystem(com.pro.gen.popups.AbsPopup popup) {
        this.popup = popup;
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        sun = new TintedImage(com.pro.gen.utils.Pic.Sun_large);
        sun.setSize(150, 150);
        sun.setPosition(getWidth() / 2 - sun.getWidth() / 2, getHeight() / 2 - sun.getHeight() / 2);
        addActor(sun);
        createPlanets();
    }

    public void createPlanets(){
        planets = new MiniPlanetSystem[MathUtils.random(1, 5)];
        if(planets.length == 1){
            planets[0] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 30);
            addActor(planets[0]);
        } else if (planets.length == 2){
            planets[0] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 20);
            planets[1] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 70);
            addActor(planets[0]);
            addActor(planets[1]);
        } else if (planets.length == 3){
            planets[0] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 20);
            planets[1] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 40);
            planets[2] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 70);
            addActor(planets[0]);
            addActor(planets[1]);
            addActor(planets[2]);
        }  else if (planets.length == 4){
            planets[0] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 10);
            planets[1] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 30);
            planets[2] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 50);
            planets[3] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 70);
            addActor(planets[0]);
            addActor(planets[1]);
            addActor(planets[2]);
            addActor(planets[3]);
        } else { // 5
            planets[0] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 0);
            planets[1] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 20);
            planets[2] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 40);
            planets[3] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 60);
            planets[4] = new MiniPlanetSystem(ColorHelper.generateGoodColor(), this, 80);
            addActor(planets[0]);
            addActor(planets[1]);
            addActor(planets[2]);
            addActor(planets[3]);
            addActor(planets[4]);
        }

    }

    public void planetClicked(MiniPlanet planet){
        selectedPlanet = planet;
        popup.activatePopup();
    }

    public void fadeAway(){
        sun.addAction(Actions.sequence(Actions.sizeTo(1, 1, 0.5f, Interpolation.exp10), Actions.visible(false)));
        for(MiniPlanetSystem planet: planets){
            planet.fadeOut(selectedPlanet);
        }
    }

    public MiniPlanet getSelectedPlanet() {
        return selectedPlanet;
    }
}
