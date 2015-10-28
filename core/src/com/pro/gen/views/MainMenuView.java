package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.ShipMenu;
import com.pro.gen.components.ShipHUD;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/21/2015.
 */
public class MainMenuView extends BaseView {

    private Background background;
    private ShipMenu menu;
    private ShipHUD shipHUD;


    @Override
    public void init() {
        background = new Background(Constants.PIXEL, Constants.UNIVERSE_BACKGROUND_COLOR);
        shipHUD = new ShipHUD();
        menu = new ShipMenu(shipHUD);
    }

    @Override
    public void setSizes() {
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        menu.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(shipHUD);
        addActor(menu);


        //Disable so screen is clickable and children buttons of menu are too
        menu.setTouchable(Touchable.childrenOnly);
    }


}
