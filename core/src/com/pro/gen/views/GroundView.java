package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomTerrain;
import com.pro.gen.simplealien.FinalAlien;
import com.pro.gen.simplealien.RandomAlien;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/27/2015.
 */
public class GroundView extends BaseView{

    Background background;
    RandomTerrain randomTerrain;
    TintedImage image, image2, image3;
    TintedImage land;
    FinalAlien alien;

    @Override
    public void init() {
        background = new Background(Constants.PIXEL, ColorHelper.generateLightColor());

        Color color = ColorHelper.generateGoodColor();
        color = ColorHelper.lighten(color, 0.01f);


        image3 = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image3.setSize(Constants.VIRTUAL_WIDTH*2, Constants.VIRTUAL_HEIGHT);
        image3.setY(200);


        color = ColorHelper.lighten(color, 0.2f);
        image2 = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image2.setSize(Constants.VIRTUAL_WIDTH*2, Constants.VIRTUAL_HEIGHT);
        image2.setY(100);

        color = ColorHelper.lighten(color, 0.2f);

        image = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image.setSize(Constants.VIRTUAL_WIDTH*2, Constants.VIRTUAL_HEIGHT);


        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO: fix out of memory error from doing silly testing, optimize random terrain code.
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(new RandomTerrain().getTexture())));
                image2.setDrawable(new TextureRegionDrawable(new TextureRegion(new RandomTerrain().getTexture())));
                image3.setDrawable(new TextureRegionDrawable(new TextureRegion(new RandomTerrain().getTexture())));
                Color color = ColorHelper.generateGoodColor();
                color = ColorHelper.lighten(color, 0.01f);
                image.setTint(new Color(color));
                color = ColorHelper.lighten(color, 0.2f);
                image2.setTint(new Color(color));
                color = ColorHelper.lighten(color, 0.2f);
                image3.setTint(new Color(color));
                image.setX(0);
                image2.setX(0);
                image3.setX(0);
                removeActor(alien);
                alien = new FinalAlien(100,200,new RandomAlien());
                alien.setPosition(100,100);
                addActor(alien);
                land.setTint(ColorHelper.generateGoodColor());
            }
        });

        land = new TintedImage(Constants.RECTANGLE, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);

        alien = new FinalAlien(100,250,new RandomAlien());
        alien.setPosition(100,100);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        image.setX(image.getX() - (125 * delta));
        image2.setX(image2.getX() - (75 * delta));
        image3.setX(image3.getX()-(50*delta));
        alien.setX(alien.getX()+(50*delta));
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(image3);
        addActor(image2);
        addActor(image);
        addActor(land);
        addActor(alien);
    }


}
