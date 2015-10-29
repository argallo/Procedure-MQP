package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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

    int move = 0;
    int right = 1;
    int left = 2;

    float warmup = 1;
    float interpolate = 0;

    @Override
    public void init() {
        background = new Background(Constants.PIXEL, ColorHelper.generateLightColor());

        Color color = ColorHelper.generateGoodColor();
        color = ColorHelper.lighten(color, 0.0f);


        image3 = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image3.setSize(6000, Constants.VIRTUAL_HEIGHT);
        image3.setY(250);


        color = ColorHelper.lighten(color, 0.1f);
        image2 = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image2.setSize(6000, Constants.VIRTUAL_HEIGHT);
        image2.setY(220);

        color = ColorHelper.lighten(color, 0.2f);

        image = new TintedImage(new RandomTerrain().getTexture(), new Color(color));
        image.setSize(6000, Constants.VIRTUAL_HEIGHT);
        image.setY(200);



        /*image.addListener(new ClickListener() {
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
                alien = new FinalAlien(100, 250, new RandomAlien());
                alien.setPosition(100, 250);
                alien.setScale(0.5f, 0.5f);
                addActor(alien);
                land.setTint(ColorHelper.generateGoodColor());
                background.setTint(ColorHelper.generateLightColor());
            }
        });

        */

        land = new TintedImage(Constants.PIXEL, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);

        alien = new FinalAlien(100,250,new RandomAlien());
        alien.setPosition(100, 250);
        alien.setScale(0.5f, 0.5f);

        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(x > Constants.VIRTUAL_WIDTH/2){
                    move = right;
                } else {
                    move = left;
                }
                interpolate = 0;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if((move == right && x > Constants.VIRTUAL_WIDTH/2) ||(move == left && x < Constants.VIRTUAL_WIDTH/2)) {
                    move = 0;
                }
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(move == right){
            getStage().getCamera().translate(300 * delta, 0, 0);
            background.setX(background.getX() + 300*delta);
            alien.setX(alien.getX()+(600*delta));
        } else if(move == left){
            getStage().getCamera().translate(-300*delta, 0,0);
        }

/*        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            interpolate = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            move = right;

        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            move = left;
        } else {
            if(move == right){
                alien.addAction(Actions.moveBy(40, 0, 0.3f, Interpolation.pow5Out));
            }
            move = 0;
        }
*/
        /*
        //TODO: USE CAMERA TO DO SCROLLING OF SCENE RATHER THAN MOVING ALL THINGS
        if(move == right){
            if(alien.getX()>900) {
                //image.setX(image.getX() - (400 * delta));
                //image2.setX(image2.getX() - (200 * delta));
                //image3.setX(image3.getX() - (100 * delta));
                this.getStage().getCamera().translate(300*delta,0,0);
                background.setX(background.getX()+300*delta);
            } else {
                if(interpolate < 1)
                    interpolate += delta*3;
                alien.setX(alien.getX()+(300*(interpolate)*delta));
            }
        } else if(move == left){
            if(alien.getX() < 300) {
                image.setX(image.getX() + (400 * delta));
                image2.setX(image2.getX() + (200 * delta));
                image3.setX(image3.getX() + (100 * delta));
            } else {
                if(interpolate < 1)
                    interpolate += delta*3;
                alien.setX(alien.getX()-(300*(interpolate)*delta));
            }
        }
        */
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
