package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 8/12/2015.
 */
public class Planet extends Group {

    private TintedImage basePlanet;
    private TintedImage planetShadow;
    private GlobeMap lands;
    private GlobeMap clouds;
    private TintedImage circleHole_left, circleHole_Right;



    public Planet(RandomPlanet randomPlanet){
       this(randomPlanet, Tint.UNIVERSE_BACKGROUND_COLOR);
    }

    public Planet(RandomPlanet randomPlanet, Color background){
        this(randomPlanet.getPlanetColor(), randomPlanet.getLands(), randomPlanet.getClouds(), background);
    }

    /**
     * Default with Universe background color
     */
    public Planet (Color planetColor, GlobeMap lands, GlobeMap clouds) {
       this(planetColor, lands, clouds, Tint.UNIVERSE_BACKGROUND_COLOR);
    }

    /**
     * Planet base constructor. Normally called directly only for testing or possibly for reseting saved planet.
     * @param planetColor the color of the base planet
     * @param lands the land that will move around the planet
     * @param clouds the clouds that will float around the planet
     */
    public Planet (Color planetColor, GlobeMap lands, GlobeMap clouds, Color background){
        basePlanet = new TintedImage(Pic.Circle_Large, planetColor);
        planetShadow = new TintedImage(Pic.Planet_Shadow, ColorHelper.shadowOf(planetColor));
        this.lands = lands;
        this.clouds = clouds;
        circleHole_Right = new TintedImage(Pic.Circle_Hole_Right, background);
        circleHole_left = new TintedImage(Pic.Circle_Hole, background);
        attachActors();
    }

    /**
     * Attach four actors that make up a planet
     */
    public void attachActors() {
        addActor(basePlanet);
        addActor(lands);
        addActor(clouds);
        addActor(planetShadow);
        addActor(circleHole_left);
        addActor(circleHole_Right);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width * 3, height);
        circleHole_left.setSize(width * 1.5f, height);
        circleHole_Right.setSize(width * 1.5f, height);
        basePlanet.setSize(width, height);
        planetShadow.setSize(width, height);
        lands.resizeGlobe(width * 3, height);
        clouds.resizeGlobe(width * 3, height);


        circleHole_Right.setX(width * 1.5f);
        basePlanet.setX(width * 0.5f + basePlanet.getWidth() / 2);
        planetShadow.setX(width * 0.5f + basePlanet.getWidth() / 2);


    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getWidth() / 3, y);
    }

    public void burn(){
        addAction(Actions.sequence(Actions.delay(6.8f), new Action() {
            @Override
            public boolean act(float delta) {
                basePlanet.addAction(Actions.color(Tint.DEAD_PLANET_RED, 8f));
                planetShadow.addAction(Actions.color(Tint.DEAD_PLANET_SHADOW));
                lands.burn(Tint.DEAD_PLANET_LAND);
                clouds.burn(Tint.DEAD_PLANET_CLOUD);
                return true;
            }
        }));
    }

}
