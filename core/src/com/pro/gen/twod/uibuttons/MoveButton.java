package com.pro.gen.twod.uibuttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pro.gen.components.Button;
import com.pro.gen.components.CameraUpdater;

/**
 * Created by Gallo on 11/9/2015.
 */
public class MoveButton extends Button {
    private int direction;
    private CameraUpdater cameraUpdater;

    public MoveButton(String mainImage, Color backgroundColor, int direction, CameraUpdater cameraUpdater) {
        super(mainImage, backgroundColor);
        this.direction = direction;
        this.cameraUpdater = cameraUpdater;
        setupListener();
    }

    public MoveButton(String mainImage, Color backgroundColor, String buttonText, BitmapFont fontType, int direction, CameraUpdater cameraUpdater) {
        super(mainImage, backgroundColor, buttonText, fontType);
        this.direction = direction;
        this.cameraUpdater = cameraUpdater;
        setupListener();
    }

    /**
     * Move left and right by calling being movement on the camera which will handle player movement
     */
    public void setupListener(){
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cameraUpdater.beginMovement(direction);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                cameraUpdater.stopMovement(direction);
            }
        });
    }

}
