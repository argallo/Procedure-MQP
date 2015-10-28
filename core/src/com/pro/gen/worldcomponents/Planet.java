package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.screens.PlanetScreen;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;

/**
 * Created by Gallo on 8/12/2015.
 */
public class Planet extends Group {

    private final static float DEFAULT_SIZE = 600;

    private TintedImage basePlanet;
    private TintedImage planetShadow;
    private GlobeMap lands;
    private GlobeMap clouds;
    private PlanetAttr planetAttr;
    private TintedImage circleHole;
    private StarMap planetStars;
    private boolean hasStars;
    private PlanetScreen planetScreen;

    /**
     * Planet base constructor. Normally called directly only for testing or possibly for reseting saved planet.
     * @param planetColor the color of the base planet
     * @param lands the land that will move around the planet
     * @param clouds the clouds that will float around the planet
     * @param planetAttr planet attributes
     */
    public Planet (Color planetColor, GlobeMap lands, GlobeMap clouds, PlanetAttr planetAttr, boolean hasStars, PlanetScreen planetScreen){
        basePlanet = new TintedImage(Constants.CIRCLE, planetColor);
        planetShadow = new TintedImage(Constants.CIRCLE_SHADOW, ColorHelper.shadowOf(planetColor));
        this.lands = lands;
        this.clouds = clouds;
        this.planetAttr = planetAttr;
        this.hasStars = hasStars;
        this.planetScreen = planetScreen;
        initCircleHole();
        attachActors();
        createAction();
    }

    /**
     * Generates random planet characteristics and sends them to the planet constructor
     */
    public Planet(RandomPlanet rp, boolean hasStars, PlanetScreen planetScreen){
        this(rp.getPlanetColor(), rp.getLands(), rp.getClouds(), rp.getPlanetAttr(), hasStars, planetScreen);
    }

    public Planet(Color planetColor, RandomPlanet rp, boolean hasStars, PlanetScreen planetScreen){
        this(planetColor, rp.getLands(), rp.getClouds(), rp.getPlanetAttr(), hasStars, planetScreen);
    }

    /**
     * Initializes the circle hole which is used to give planets revolving effect
     */
    public void initCircleHole(){
        circleHole = new TintedImage(Constants.CIRCLE_HOLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        if(hasStars)
            planetStars = new StarMap(DEFAULT_SIZE*3, DEFAULT_SIZE, false);
    }

    /**
     * Attach four actors that make up a planet
     */
    public void attachActors(){
        addActor(basePlanet);
        addActor(lands);
        addActor(clouds);
        addActor(planetShadow);
        addActor(circleHole);
        if(hasStars)
            addActor(planetStars);
    }

    /**
     * Circle whole is 3 times the size of the planet to ensure land and clouds hide properly
     * This will set the size of the planet directly and scale group size and circle hole accordingly
     * @param width the width of the planet
     * @param height the height of the planet
     */
    @Override
    public void setSize(float width, float height) {
        super.setSize(width * 3, height * 3);
        circleHole.setSize(width * 3, height * 3);
        resizePlanet(width, height);
    }

    /**
     * Overrides the function to set the position of the actual planet rather than the group
     * @param x the x position of the planet
     * @param y the y position of the planet
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getWidth() / 3, y - getWidth() / 3);
    }

    /**
     * Resize the planet and all its actors that scale with the base planet size
     * @param width width of planet
     * @param height height of planet
     */
    public void resizePlanet(float width, float height){
        basePlanet.setSize(width, height);
        basePlanet.setPosition(this.getX() + width, this.getY() + height);
        planetShadow.setSize(width, height);
        planetShadow.setPosition(this.getX() + width, this.getY() + height);
        lands.resizeLands(width, height);
        clouds.resizeLands(width, height);
        if(hasStars)
            planetStars.resizeStars(width * 3, height * 3);
    }

    /**
     * Creates action when user clicks on the planet
     */
    public void createAction(){
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isPlanetClick(x, y)) {
                    LogUtils.Log("Planet was clicked. Handle action");
                    planetScreen.planetClicked();
                }
            }
        });
    }

    /**
     * Determines whether click was inside basePlanet or not
     * @param x the x position of the click
     * @param y the y position of the click
     * @return true if the base planet was clicked
     */
    public boolean isPlanetClick(float x, float y){
        float centerX = basePlanet.getX()+basePlanet.getWidth()/2;
        float centerY = basePlanet.getY()+basePlanet.getHeight()/2;
        float px = (x - centerX);
        float py = (y - centerY);
        if ((px*px) + (py*py) <= ((basePlanet.getWidth()/2)*(basePlanet.getWidth()/2))){
            return true;
        }
        return false;
    }

}
