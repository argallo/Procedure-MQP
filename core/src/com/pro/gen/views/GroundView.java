package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pro.gen.components.BackgroundSky;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomTerrain;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.ParallaxBackground;

import java.util.Arrays;

/**
 * Created by Gallo on 10/27/2015.
 */
public class GroundView extends BaseView{

    BackgroundSky background;
    RandomTerrain randomTerrain;
    ParallaxBackground terrain1, terrain2, terrain3;
    TintedImage land, ship;
    //FinalAlien alien;
    Button leave, buttonLeft, buttonRight;
    boolean leftDown = false;
    boolean rightDown = false;

    TintedImage alien;

    int move = 0;
    int right = 1;
    int left = 2;

    float warmup = 1;
    float interpolate = 0;

    @Override
    public void init() {
        Color color = ColorHelper.generateDarkColor();

        Color backgroundColor = ColorHelper.changeHue(new Color(color), 0.3f);

        background = new BackgroundSky(Constants.PIXEL, new Color(backgroundColor));

        color = ColorHelper.lighten(color, 0.0f);
        randomTerrain = new RandomTerrain(15);
        terrain3 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 0, 4), new Color(color), 290, 10, 0.4f);
        color = ColorHelper.lighten(color, 0.4f);
        terrain2 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 5, 9), new Color(color), 265,10, 0.3f);
        color = ColorHelper.lighten(color, 0.3f);
        terrain1 = new ParallaxBackground(this, Arrays.copyOfRange(randomTerrain.getTextures(), 10, 14), new Color(color), 240,10, 0.2f);

        ship = new TintedImage(Constants.SMALL_SHIP, Color.WHITE);
        ship.setSize(250, 500);
        ship.setPosition(Constants.VIRTUAL_WIDTH / 2 - ship.getWidth() / 2, 100);

        leave = new Button(Constants.CURVERECT, Color.GREEN, "Leave", Assets.getInstance().getSmallFont());
        leave.setSize(150, 50);
        leave.setPosition(Constants.VIRTUAL_WIDTH / 2 - leave.getWidth() / 2, 620);
        leave.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                Color color = ColorHelper.generateDarkColor();
                Color backgroundColor = ColorHelper.changeHue(new Color(color), 0.3f);
                removeActor(background);
                background = new BackgroundSky(Constants.PIXEL, backgroundColor);
                addActorAt(0, background);
                color = ColorHelper.lighten(color, 0.0f);
                terrain3.setTint(new Color(color));
                color = ColorHelper.lighten(color, 0.4f);
                terrain2.setTint(new Color(color));
                color = ColorHelper.lighten(color, 0.3f);
                terrain1.setTint(new Color(color));
                land.setTint(ColorHelper.generateGoodColor());
            }
        });

        land = new TintedImage(Constants.PIXEL, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);


        //alien = new FinalAlien(100,250,new RandomAlien());
       // alien.setPosition(Constants.VIRTUAL_WIDTH / 2 - alien.getWidth() / 2, 250);
       // alien.setScale(0.5f, 0.5f);

        alien = new TintedImage(Constants.ALIEN, Color.WHITE);
        alien.setPosition(Constants.VIRTUAL_WIDTH / 2 - alien.getWidth() / 2, 250);
        alien.setScale(0.2f, 0.2f);

        buttonLeft = new Button(Constants.CIRCLE, Color.BLUE,"Left", Assets.getInstance().getSmallFont());
        buttonRight = new Button(Constants.CIRCLE, Color.BLUE,"Right", Assets.getInstance().getSmallFont());

        buttonLeft.setSize(100,100);
        buttonRight.setSize(100,100);

        buttonLeft.setPosition(20, 20);
        buttonRight.setPosition(Constants.VIRTUAL_WIDTH-buttonRight.getWidth()-20,20);

        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                move = left;
                alien.setImage(Constants.ALIENT_RIGHT);
                interpolate = 0;
                leftDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(!rightDown) {
                    move = 0;
                    alien.setImage(Constants.ALIEN);
                } else {
                    move = right;
                    alien.setImage(Constants.ALIEN_LEFT);
                }
                leftDown = false;
            }
        });

        buttonRight.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                move = right;
                alien.setImage(Constants.ALIEN_LEFT);
                interpolate = 0;
                rightDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(!leftDown) {
                    move = 0;
                    alien.setImage(Constants.ALIEN);
                } else {
                    move = left;
                    alien.setImage(Constants.ALIENT_RIGHT);
                }
                rightDown = false;
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
                buttonLeft.setX(buttonLeft.getX()+ (300 * delta));
                buttonRight.setX(buttonRight.getX()+ (300 * delta));
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
                buttonLeft.setX(buttonLeft.getX()- (300 * delta));
                buttonRight.setX(buttonRight.getX()- (300 * delta));
                terrain3.setX(-(100 * delta));
                terrain2.setX(-(50 * delta));
            } else {
                alien.setX(alien.getX() - (300 * delta));
            }
        }
    }


    @Override
    public void addActors() {
        addActorAt(0, background);
        addActor(ship);
        addActor(leave);
        addActor(land);
        addActor(alien);
        addActor(buttonLeft);
        addActor(buttonRight);
    }


}
