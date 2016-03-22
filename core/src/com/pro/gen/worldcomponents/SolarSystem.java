package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.*;

import java.util.ArrayList;

/**
 * Created by Gallo on 1/14/2016.
 */
public class SolarSystem extends Group {

    TintedImage sun;
    ArrayList<MiniPlanetSystem> miniplanets;
    ArrayList<Planet> planets;
    MiniPlanet selectedPlanet;
    AbsPopup popup;
    private int lowRank, highRank;

    /**
     * Constructor for creating new SolarSystem
     */
    public SolarSystem(int lowRank, int highRank) {
        this.lowRank = lowRank;
        this.highRank = highRank;
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        sun = new TintedImage(Pic.Sun_large);
        sun.setSize(150, 150);
        sun.setPosition(getWidth() / 2 - sun.getWidth() / 2, getHeight() / 2 - sun.getHeight() / 2);
        addActor(sun);
        createPlanets();
        saveSystem();
    }

    /**
     * Constructor for loading SolarSystem
     */
    public SolarSystem(ArrayList<Planet> planets){
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        sun = new TintedImage(Pic.Sun_large);
        sun.setSize(150, 150);
        sun.setPosition(getWidth() / 2 - sun.getWidth() / 2, getHeight() / 2 - sun.getHeight() / 2);
        addActor(sun);
        this.planets = planets;
        miniplanets = new ArrayList<MiniPlanetSystem>();
        for(int i = 0; i < planets.size(); i++){
            miniplanets.add(new MiniPlanetSystem(planets.get(i).getBasePlanetColor(), this, getLayout(i,planets.size())));
            addActor(miniplanets.get(i));
        }
    }


    public void createPlanets(){
        int planetNum = MathUtils.random(1, 5);
        planets = new ArrayList<Planet>();
        miniplanets = new ArrayList<MiniPlanetSystem>();
        for(int i = 0; i < planetNum; i++){
            planets.add(new Planet(new RandomPlanet(lowRank, highRank))); //TODO: add rare param to random planet
            miniplanets.add(new MiniPlanetSystem(planets.get(i).getBasePlanetColor(), this, getLayout(i,planetNum)));
            addActor(miniplanets.get(i));
        }
    }

    public void planetClicked(MiniPlanet planet){
        selectedPlanet = planet;
        popup.activatePopup();
    }

    public void fadeAway(){
        sun.addAction(Actions.sequence(Actions.sizeTo(1, 1, 0.5f, Interpolation.exp10), Actions.visible(false)));
        for(MiniPlanetSystem planet: miniplanets){
            planet.fadeOut(selectedPlanet);
        }
    }

    public MiniPlanet getSelectedPlanet() {
        return selectedPlanet;
    }

    public int getLayout(int index, int size){
        switch(size){
            case 1:
                return 30;
            case 2:
                switch(index){
                    case 0: return 20;
                    case 1: return 70;
                }
            case 3:
                switch(index){
                    case 0: return 20;
                    case 1: return 40;
                    case 2: return 70;
                }
            case 4:
                switch(index){
                    case 0: return 10;
                    case 1: return 30;
                    case 2: return 50;
                    case 3: return 70;
                }
            case 5:
                switch(index){
                    case 0: return 0;
                    case 1: return 20;
                    case 2: return 40;
                    case 3: return 60;
                    case 4: return 80;
                }
        }
        return 0;
    }

    public void setPopup(AbsPopup popup){
        this.popup = popup;
    }

    public void saveSystem(){
        XmlManager.getInstance().saveSolarSystem(this);
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }
}
