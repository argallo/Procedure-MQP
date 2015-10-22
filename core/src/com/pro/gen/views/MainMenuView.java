package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.ShipMenu;
import com.pro.gen.components.ShipScreen;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/21/2015.
 */
public class MainMenuView extends BaseView {

    private Background background;
    private ShipMenu menu;
    private ShipScreen shipScreen;


    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        shipScreen = new ShipScreen();
        menu = new ShipMenu(shipScreen);
    }

    @Override
    public void setSizes() {
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipScreen.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        menu.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }

    @Override
    public void setPositions() {

    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(shipScreen);
        addActor(menu);

        //Disable so screen is clickable and children buttons of menu are too
        menu.setTouchable(Touchable.childrenOnly);
    }


}
