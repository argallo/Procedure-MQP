package com.pro.gen.views;

import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.StarMap;
import com.pro.gen.utils.Pic;
import com.pro.gen.worldcomponents.Ship;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.utils.Tint;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;

/**
 * Created by Gallo on 10/21/2015.
 */
public class MainMenuView extends BaseView {

    private Background background;
    private StarMap starsBehind, starsFront;
    private TintedImage shipUI;
    private Ship ship;
    private ShipDoor shipDoor;
    private TravelButton exploreBtn, manageBtn, leaderBtn, dwarfBtn, InventoryBtn;
    private TitleBar titleBar;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        starsBehind = new StarMap(2, 100, new Item(Pic.Circle_Small, new Range(1f,1f),2f, 8f));
        starsFront = new StarMap(2, 50, new Item(Pic.Circle_Small, new Range(1f,1f),6f, 12f));
        shipUI = new TintedImage(Pic.UI_Full);
        ship = new Ship(Pic.Ship);
        titleBar = new TitleBar("Main Menu");
        shipDoor = new ShipDoor(true);

        //Menu Buttons
        exploreBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Explore", Assets.getInstance().getSmallFont(), ViewID.EXPLORE, shipDoor);
        manageBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Manage Planets", Assets.getInstance().getXSmallFont(), ViewID.EXPLORE, shipDoor);
        leaderBtn = new TravelButton(Pic.Curve_square ,Tint.PURPLE, "Leaderboard", Assets.getInstance().getSmallFont(), ViewID.EXPLORE, shipDoor);
        dwarfBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Drawf Capsules", Assets.getInstance().getXSmallFont(), ViewID.CAPSULE, shipDoor);
        InventoryBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Inventory", Assets.getInstance().getSmallFont(), ViewID.EXPLORE, shipDoor);

    }

    @Override
    public void setSizes() {
        starsBehind.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        starsFront.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        ship.setSize(400, 200);
        exploreBtn.setSize(200,100);
        manageBtn.setSize(200,100);
        leaderBtn.setSize(200,100);
        dwarfBtn.setSize(200,100);
        InventoryBtn.setSize(200,100);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }


    @Override
    public void setPositions() {
        ship.setPosition(600,300);
        titleBar.setPosition(80,600);
        exploreBtn.setPosition(100, 35);
        manageBtn.setPosition(320, 35);
        leaderBtn.setPosition(540, 35);
        dwarfBtn.setPosition(760, 35);
        InventoryBtn.setPosition(980, 35);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(starsBehind);
        addActor(ship);
        addActor(starsFront);
        addActor(shipUI);
        addActor(titleBar);
        addActor(exploreBtn);
        addActor(manageBtn);
        addActor(leaderBtn);
        addActor(dwarfBtn);
        addActor(InventoryBtn);
        addActor(shipDoor);

    }

    @Override
    public void handle(int outcome) {

    }


}
