package com.pro.gen.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.worldcomponents.Hexagon;
import com.pro.gen.worldcomponents.MiniPlanet;

/**
 * Created by Gallo on 10/22/2015.
 */
public class SolarSystemScreen extends ShipScreen {

    Button sun;
    TintedImage[] planets;

    Button planet2, planet3;
    MiniPlanet planet;
    Hexagon hexagon;

    float radiusX = 400;
    float radiusY = 120;
    float loop = 0;
    float loop2 = 1.4f;
    float loop3 = 0.3f;

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




        planet = new MiniPlanet(ColorHelper.generateGoodColor(), this);
        planet.setSize(1, 1);
        planet.setOrigin(0.5f,0.5f);
        this.addActor(planet);

        planet2 = new Button(Constants.ABS_PLANET, ColorHelper.generateGoodColor());
        planet2.setSize(1, 1);
        planet2.setOrigin(0.5f,0.5f);
        this.addActor(planet2);

        planet3 = new Button(Constants.ABS_PLANET, ColorHelper.generateGoodColor());
        planet3.setSize(1,1);
        planet3.setOrigin(0.5f,0.5f);
        this.addActor(planet3);
        /*
        planets = new TintedImage[5];
        for(TintedImage planet : planets){
            planet = new TintedImage(Constants.ABS_PLANET, ColorHelper.generateGoodColor());
            this.addActor(planet);
        }
        */


        TintedImage image = new TintedImage(Constants.RING, Color.WHITE);
        image.setSize(800, 240);
        image.setPosition(240, 240);
        this.addActorAt(0, image);

        TintedImage image2 = new TintedImage(Constants.RING, Color.WHITE);
        image2.setSize(860, 300);
        image2.setPosition(210, 210);
        this.addActorAt(0, image2);

        TintedImage image3 = new TintedImage(Constants.RING, Color.WHITE);
        image3.setSize(980, 420);
        image3.setPosition(150, 150);
        this.addActorAt(0, image3);


        hexagon = new Hexagon(Color.WHITE, 1);
        hexagon.setSize(1,1);
        hexagon.setOrigin(0.5f, 0.5f);
        hexagon.setVisible(false);
        this.addActor(hexagon);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        loop += delta;
        loop2 += delta;
        loop3 += delta *0.65f;
        planet.setPosition(radiusX * MathUtils.cos(loop) + 640, radiusY * MathUtils.sin(loop) + 360);


        planet2.setPosition((radiusX + 30) * MathUtils.cos(loop2) + 640, (radiusY + 30) * MathUtils.sin(loop2) + 360);
        planet3.setPosition((radiusX + 90) * MathUtils.cos(loop2) + 640, (radiusY + 90) * MathUtils.sin(loop2) + 360);


        planet.setScale((MathUtils.sin(loop) - 1.5f) * -30f, (MathUtils.sin(loop) - 1.5f) * -30f);
        planet2.setScale((MathUtils.sin(loop2) - 1.5f) * -30f, (MathUtils.sin(loop2) - 1.5f) * -30f);
        planet3.setScale((MathUtils.sin(loop2) - 1.5f) * -30f, (MathUtils.sin(loop2) - 1.5f) * -30f);


        hexagon.setPosition(planet.getX() - (planet.getScaleX() * 1.25f) / 2, planet.getY() - (planet.getScaleY() * 1.25f) / 2);
        hexagon.setSize(planet.getScaleX() * 1.25f, planet.getScaleY() * 1.25f);
        hexagon.setOrigin(hexagon.getWidth()/2, hexagon.getHeight()/2);
        //LogUtils.Log(planet.getX(), planet.getY(), planet.getWidth(), planet.getHeight());

    }

    public void planetClicked(MiniPlanet planet){
        hexagon.formHex();
        // display information in side box
        // with fly to button
    }


}
