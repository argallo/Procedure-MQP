package com.pro.gen.znewmqp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 1/12/2016.
 */
public class TravelButton extends Button {

    private ViewID travelTo;
    private ShipDoor shipDoor;

    public TravelButton(String mainImage, Color backgroundColor, String buttonText, BitmapFont fontType, ViewID travelTo, ShipDoor shipDoor) {
        super(mainImage, backgroundColor, buttonText, fontType);
        this.travelTo = travelTo;
        this.shipDoor = shipDoor;
        createButtonAction();
    }


    public void createButtonAction(){
        setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                addAction(Actions.sequence(shipDoor.getSlideIn(), Actions.delay(0.5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(travelTo, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
            }
        });
    }



}
