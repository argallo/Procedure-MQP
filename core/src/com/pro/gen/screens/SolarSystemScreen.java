package com.pro.gen.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.worldcomponents.FlyPlanetPopup;
import com.pro.gen.worldcomponents.MiniPlanet;
import com.pro.gen.worldcomponents.MiniPlanetSystem;

/**
 * Created by Gallo on 10/22/2015.
 */
public class SolarSystemScreen extends ShipScreen {

    Button sun;
    MiniPlanetSystem[] planets;
    FlyPlanetPopup flyPlanetPopup;
    Color selectedPlanetColor = Color.CLEAR;

    public SolarSystemScreen(ShipHUD shipHUD) {
        super(shipHUD);
        sun = new Button(Constants.SUN, ColorHelper.generateLightColor());
        sun.setSize(150, 150);
        sun.setPosition(getWidth() / 2 - sun.getWidth() / 2, getHeight() / 2 - sun.getHeight() / 2);
        sun.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                LogUtils.Log("SUNSUNSUNS");
            }
        });
        this.addActor(sun);
        createPlanets();
        flyPlanetPopup = new FlyPlanetPopup(this);
    }

    public void createPlanets(){
        planets = new MiniPlanetSystem[MathUtils.random(1,5)];
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
        for(int i = 0; i < planets.length; i++){
            if(!planets[i].getMiniPlanet().equals(planet))
                planets[i].getMiniPlanet().setSelected(false);
        }
        selectedPlanetColor = planet.getTint();
        addActor(flyPlanetPopup);
    }

    public void popUpFly(){
        shipHUD.transitionTo(ShipHUD.ShipScreenTypes.PLANET);
        removeActor(flyPlanetPopup);
    }

    public void popUpCancel(){
        removeActor(flyPlanetPopup);
    }

    public Color getSelectedPlanetColor() {
        return selectedPlanetColor;
    }
}
