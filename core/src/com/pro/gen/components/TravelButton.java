package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.ShipDoor;

/**
 * Created by Gallo on 1/12/2016.
 */
public class TravelButton extends Button {

    private ViewID travelTo;
    private ShipDoor shipDoor;


    public TravelButton(String mainImage, ViewID travelTo, ShipDoor shipDoor) {
        super(mainImage, Color.WHITE);
        this.travelTo = travelTo;
        this.shipDoor = shipDoor;
        createButtonAction();
    }

    public TravelButton(String mainImage, Color backgroundColor, String buttonText, BitmapFont fontType, ViewID travelTo, ShipDoor shipDoor) {
        this(mainImage, backgroundColor, buttonText, fontType, travelTo, shipDoor, null);
    }

    public TravelButton(String mainImage, Color backgroundColor, String buttonText, BitmapFont fontType, ViewID travelTo, ShipDoor shipDoor, TintedImage icon) {
        super(mainImage, backgroundColor, buttonText, fontType, icon);
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

    public void setShipDoor(ShipDoor shipDoor) {
        this.shipDoor = shipDoor;
    }
}
