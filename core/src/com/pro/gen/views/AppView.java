package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pro.gen.components.Background;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.random.RandomPlanet;

/**
 * Created by Gallo on 8/11/2015.
 */
public class AppView extends BaseView {
    Planet planet;

    public AppView() {
        Background background = new Background(Constants.APP_BACKGROUND);

        planet = new Planet(new RandomPlanet());
        planet.setSize(600, 600);
        planet.setPosition(340, 60);
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
        addActor(background);
        addActor(planet);
    }

}
