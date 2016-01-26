package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.znewmqp.Tint;

/**
 * Created by Gallo on 10/21/2015.
 */
public class ShipMenu extends Group {

    private static final String EXPLORE_BTN = "Explore";
    private static final String INVENTORY_BTN = "Inventory";
    private static final String Home_BTN = "Fly Home";


    private Button exploreButton, inventoryButton, homeButton;
    private TintedImage shipBoarder;
    private ShipHUD shipScreen;

    public ShipMenu(ShipHUD shipScreen){
        this.shipScreen = shipScreen;
        init();
        setSizes();
        setPositions();
        setListeners();
        addActors();
    }


    private void init() {
        shipBoarder = new TintedImage(Constants.SHIP_BORDER, Color.WHITE);
        shipBoarder.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        exploreButton = new Button(Constants.RECTANGLE, Tint.PURPLE, EXPLORE_BTN, Assets.getInstance().getSmallFont());
        inventoryButton = new Button(Constants.RECTANGLE, Tint.PURPLE, INVENTORY_BTN, Assets.getInstance().getSmallFont());
        homeButton = new Button(Constants.RECTANGLE, Tint.PURPLE, Home_BTN, Assets.getInstance().getSmallFont());
    }

    private void setSizes() {
        exploreButton.setSize(200, 100);
        inventoryButton.setSize(200, 100);
        homeButton.setSize(200, 100);
    }

    private void setPositions() {
        exploreButton.setPosition(40, 35);
        inventoryButton.setPosition(250, 35);
        homeButton.setPosition(460, 35);
    }

    private void setListeners() {
        exploreButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                shipScreen.transitionTo(ShipHUD.ShipScreenTypes.EXPLORE);
                // make screen slide out.
                // make screen add stars.
                // add exit button to screen or make buttons select and deselectable
            }
        });
        inventoryButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                shipScreen.transitionTo(ShipHUD.ShipScreenTypes.INVENTORY);
            }
        });
    }

    private void addActors() {
        addActor(shipBoarder);
        addActor(exploreButton);
        addActor(inventoryButton);
        addActor(homeButton);
        //disable so screen is clickable
        shipBoarder.setTouchable(Touchable.disabled);
    }


}
