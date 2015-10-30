package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomTerrain;
import com.pro.gen.simplealien.FinalAlien;
import com.pro.gen.simplealien.RandomAlien;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.ParallaxBackground;

import java.util.Arrays;

/**
 * Created by Gallo on 10/27/2015.
 */
public class GroundView extends BaseView{

    Background background;
    RandomTerrain randomTerrain;
    ParallaxBackground terrain1, terrain2, terrain3;
    TintedImage land, ship;
    FinalAlien alien;
    Button leave;

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

        randomTerrain = new RandomTerrain(15);


        terrain3 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 0, 4), new Color(color), 290, 10, 0.4f);
        //image3 = new TintedImage(randomTerrain.getRandomTexture(), new Color(color));
        //image3.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/4);
        //image3.setY(300);


        color = ColorHelper.lighten(color, 0.1f);
        terrain2 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 5, 9), new Color(color), 265,10, 0.3f);
        //image2 = new TintedImage(randomTerrain.getRandomTexture(), new Color(color));
        //image2.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/4);
        //image2.setY(270);

        color = ColorHelper.lighten(color, 0.2f);
        terrain1 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 10, 14), new Color(color), 240,10, 0.2f);
        //image = new TintedImage(randomTerrain.getRandomTexture(), new Color(color));
        //image.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/4);
        //image.setY(240);


        /*
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
                alien = new FinalAlien(100, 250, new RandomAlien());
                alien.setPosition(100, 250);
                alien.setScale(0.5f, 0.5f);
                addActor(alien);
                land.setTint(ColorHelper.generateGoodColor());
                background.setTint(ColorHelper.generateLightColor());
            }
        });
        */
        ship = new TintedImage(Constants.SMALL_SHIP, Color.WHITE);
        ship.setSize(250, 500);
        ship.setPosition(Constants.VIRTUAL_WIDTH / 2 - ship.getWidth() / 2, 100);

        leave = new Button(Constants.CURVERECT, Color.GREEN, "Leave", Assets.getInstance().getSmallFont());
        leave.setSize(150, 50);
        leave.setPosition(Constants.VIRTUAL_WIDTH / 2 - leave.getWidth() / 2, 620);

        land = new TintedImage(Constants.PIXEL, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);

        alien = new FinalAlien(100,250,new RandomAlien());
        alien.setPosition(Constants.VIRTUAL_WIDTH/2-alien.getWidth()/2, 250);
        alien.setScale(0.5f, 0.5f);

        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(x > getStage().getCamera().position.x){
                    move = right;
                } else {
                    move = left;
                }
                interpolate = 0;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if((move == right && x > getStage().getCamera().position.x) ||(move == left && x < getStage().getCamera().position.x)) {
                    move = 0;
                }
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(move == right){
            if(alien.getX() > getStage().getCamera().position.x+50) {
                getStage().getCamera().translate(300 * delta, 0, 0);
                background.setX(background.getX() + 300 * delta);
                alien.setX(alien.getX() + (300 * delta));
                land.setX(land.getX() + (300 * delta));
                terrain3.setX((100 * delta));
                terrain2.setX((50 * delta));
            } else {
                alien.setX(alien.getX() + (300 * delta));
            }
        } else if(move == left){
            if(alien.getX() < getStage().getCamera().position.x-50-alien.getWidth()/2) {
                getStage().getCamera().translate(-300 * delta, 0, 0);
                background.setX(background.getX() - 300 * delta);
                alien.setX(alien.getX() - (300 * delta));
                land.setX(land.getX() - (300 * delta));
                terrain3.setX(-(100 * delta));
                terrain2.setX(-(50 * delta));
            } else {
                alien.setX(alien.getX() - (300 * delta));
            }
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
        addActorAt(0, background);
        //addActor(image3);
       // addActor(image2);
       // addActor(image);
        addActor(ship);
        addActor(leave);
        addActor(land);
        addActor(alien);
    }


}
