package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pro.gen.components.Animation;
import com.pro.gen.components.BackgroundSky;
import com.pro.gen.components.Button;
import com.pro.gen.components.CameraUpdater;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomTerrain;
import com.pro.gen.simplealien.FinalAlien;
import com.pro.gen.simplealien.RandomAlien;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.ParallaxBackground;

/**
 * Created by Gallo on 10/27/2015.
 */
public class GroundView extends BaseView{

    CameraUpdater cameraUpdater;
    BackgroundSky background;
    RandomTerrain randomTerrain;
    ParallaxBackground terrain;
    TintedImage land, ship;
    FinalAlien alien;

    Button leave, buttonLeft, buttonRight;
    boolean leftDown = false;
    boolean rightDown = false;



    @Override
    public void init() {

        Color color = ColorHelper.generateDarkColor();
        Color backgroundColor = ColorHelper.changeHue(new Color(color), 0.3f);
        background = new BackgroundSky(Constants.PIXEL, new Color(backgroundColor));
        randomTerrain = new RandomTerrain(15);
        terrain = new ParallaxBackground(this, randomTerrain.getTextures(), color, 290, 10, 0.4f, 3);

        ship = new TintedImage(Constants.SMALL_SHIP, Color.WHITE);
        ship.setSize(250, 500);
        ship.setPosition(Constants.VIRTUAL_WIDTH / 2 - ship.getWidth() / 2, 100);

        leave = new Button(Constants.CURVERECT, Color.GREEN, "Leave", Assets.getInstance().getSmallFont());
        leave.setSize(150, 50);
        leave.setPosition(Constants.VIRTUAL_WIDTH / 2 - leave.getWidth() / 2, 620);

        land = new TintedImage(Constants.PIXEL, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);

        alien = new FinalAlien(100,250,new RandomAlien());
        alien.setPosition(Constants.VIRTUAL_WIDTH / 2 - alien.getWidth() / 2, 250);
        alien.setScale(0.5f, 0.5f);

        buttonLeft = new Button(Constants.CIRCLE, Color.BLUE,"Left", Assets.getInstance().getSmallFont());
        buttonRight = new Button(Constants.CIRCLE, Color.BLUE,"Right", Assets.getInstance().getSmallFont());

        buttonLeft.setSize(100,100);
        buttonRight.setSize(100,100);

        buttonLeft.setPosition(20, 20);
        buttonRight.setPosition(Constants.VIRTUAL_WIDTH - buttonRight.getWidth() - 20, 20);

        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alien.switchDirections(Animation.LEFT);
                leftDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (!rightDown) {
                    alien.switchDirections(Animation.CALM);
                } else {
                    alien.switchDirections(Animation.RIGHT);
                }
                leftDown = false;
            }
        });

        buttonRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alien.switchDirections(Animation.RIGHT);
                rightDown = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (!leftDown) {
                    alien.switchDirections(Animation.CALM);
                } else {
                 alien.switchDirections(Animation.LEFT);
                }
                rightDown = false;
            }
        });


        cameraUpdater = new CameraUpdater(alien);
        cameraUpdater.register(background);
        cameraUpdater.register(land);
        cameraUpdater.register(buttonRight);
        cameraUpdater.register(buttonLeft);
        cameraUpdater.register(terrain);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        cameraUpdater.updateCamera(delta);
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
