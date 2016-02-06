package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.*;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 1/13/2016.
 */
public class ShipDoor extends Group {

    private TintedImage leftDoor, rightDoor;
    private Action slideIn, slideOut;
    private boolean doorsActive;

    public ShipDoor(boolean doorsActive){
        this.doorsActive = doorsActive;
        leftDoor = new TintedImage(com.pro.gen.utils.Pic.Pixel, com.pro.gen.utils.Tint.PURPLE);
        rightDoor = new TintedImage(Pic.Pixel, com.pro.gen.utils.Tint.PURPLE);

        leftDoor.setSize(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT);
        rightDoor.setSize(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT);


        setDoors(doorsActive);

        addActor(leftDoor);
        addActor(rightDoor);

        buildActions();

        if(doorsActive){
            addAction(Actions.sequence(Actions.delay(0.3f),slideOut));
        }
    }

    public void setDoors(boolean door) {
        if(door) {
            leftDoor.setPosition(0, 0);
            rightDoor.setPosition(Constants.VIRTUAL_WIDTH/2, 0);

            leftDoor.setVisible(true);
            rightDoor.setVisible(true);
        } else {
            leftDoor.setPosition(-Constants.VIRTUAL_WIDTH / 2, 0);
            rightDoor.setPosition(Constants.VIRTUAL_WIDTH, 0);

            leftDoor.setVisible(false);
            rightDoor.setVisible(false);
        }
    }

    /**
     * Build Actions that can be used as animations during screen transitions
     */
    private void buildActions(){
        //Slides the screen sliders over the screen
        slideIn = new Action() {
            @Override
            public boolean act(float delta) {
                leftDoor.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(-leftDoor.getWidth(), 0), Actions.moveBy(leftDoor.getWidth(), 0, 0.4f, Interpolation.exp10In)));
                rightDoor.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(Constants.VIRTUAL_WIDTH, 0), Actions.moveBy(-rightDoor.getWidth(), 0, 0.4f, Interpolation.exp10In)));
                return true;
            }
        };

        //Slides the screen sliders off the screen
        slideOut = new Action() {
            @Override
            public boolean act(float delta) {
                leftDoor.addAction(Actions.sequence(Actions.moveBy(-leftDoor.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                rightDoor.addAction(Actions.sequence(Actions.moveBy(rightDoor.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                return true;
            }
        };
    }


    public Action getSlideIn() {
        return slideIn;
    }

    public Action getSlideOut() {
        return slideOut;
    }
}
