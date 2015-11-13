package com.pro.gen.twod.uibuttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pro.gen.aliens.Alien;
import com.pro.gen.components.Animation;
import com.pro.gen.components.Button;
import com.pro.gen.components.CameraUpdater;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 11/9/2015.
 */
public class UIButtonGroup extends Group{

    MoveButton buttonLeft, buttonRight;
    Button buttonFire;


    public UIButtonGroup(CameraUpdater cameraUpdater, final Alien alien){
        this.setSize(Constants.VIRTUAL_WIDTH-40, 100);
        this.setPosition(20, 20);

        buttonLeft = new MoveButton(Constants.CIRCLE, Color.BLUE, "Left", Assets.getInstance().getSmallFont(), Animation.LEFT, cameraUpdater);
        buttonRight = new MoveButton(Constants.CIRCLE, Color.BLUE, "Right", Assets.getInstance().getSmallFont(), Animation.RIGHT, cameraUpdater);
        buttonFire = new Button(Constants.CIRCLE, Color.GREEN,"Fire", Assets.getInstance().getSmallFont());

        cameraUpdater.register(this);//remove later

        buttonLeft.setSize(100, 100);
        buttonRight.setSize(100, 100);
        buttonFire.setSize(100, 100);

        buttonLeft.setX(0);
        buttonRight.setX(130);
        buttonFire.setX(getWidth() - buttonFire.getWidth());


        buttonFire.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                alien.getWeapon().fire();
                return true;
            }
        });

        addActor(buttonLeft);
        addActor(buttonRight);
        addActor(buttonFire);
    }



}
