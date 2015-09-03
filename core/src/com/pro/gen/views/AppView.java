package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 8/11/2015.
 */
public class AppView extends BaseView {
    Planet planet;

    public AppView() {

    }

    @Override
    public void init() {
        planet = new Planet(new RandomPlanet());
    }

    @Override
    public void setSizes() {
        planet.setSize(600, 600);
    }

    @Override
    public void setPositions() {
        planet.setPosition(340, 60);
    }

    @Override
    public void addListeners() {
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                removeActor(planet);
                planet = new Planet(new RandomPlanet());
                planet.setSize(600, 600);
                planet.setPosition(340, 60);
                addActor(planet);
            }
        });
    }

    @Override
    public void addActors() {
        addActor(planet);
    }
}
