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




        planet = new Planet(new RandomPlanet());
        planet.setSize(600, 600);
        planet.setPosition(340, 60);



        //TintedImage image = new TintedImage(Constants.CIRCLE_SMALL, Color.RED);
        //image.setSize(150, 150);
       // image.setPosition(50,50);
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
        addActor(planet);

       // addActor(image);
    }

}
