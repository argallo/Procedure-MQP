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
    private TravelButton exploreBtn, manageBtn, bossbattleBtn, leaderboardBtn, shopBtn;
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

        TintedImage exploreIcon = new TintedImage(Pic.Exlopre_Icon);
        exploreIcon.setSize(90,90);
        exploreIcon.setPosition(150, 40);

        TintedImage manageIcon = new TintedImage(Pic.ManagePlanets_Icon);
        manageIcon.setSize(166, 49);
        manageIcon.setPosition(335, 60);

        TintedImage bossbattleIcon = new TintedImage(Pic.BossBattle_Icon);
        bossbattleIcon.setSize(24,84);
        bossbattleIcon.setPosition(625, 45);

        TintedImage leaderboardIcon = new TintedImage(Pic.Leaderboard_Icon);
        leaderboardIcon.setSize(70,89);
        leaderboardIcon.setPosition(820, 43);

        TintedImage shopIcon = new TintedImage(Pic.Shop_Icon);
        shopIcon.setSize(103,83);
        shopIcon.setPosition(1020, 45);

        //Menu Buttons
        exploreBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Explore", Assets.getInstance().getSmallFont(), ViewID.EXPLORE, shipDoor, exploreIcon);
        manageBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Manage Planets", Assets.getInstance().getXSmallFont(), ViewID.EXPLORE, shipDoor, manageIcon);
        bossbattleBtn = new TravelButton(Pic.Curve_square ,Tint.PURPLE, "Boss Battle", Assets.getInstance().getSmallFont(), ViewID.LEADERBOARD, shipDoor, bossbattleIcon);
        leaderboardBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Leaderboard", Assets.getInstance().getXSmallFont(), ViewID.CAPSULE, shipDoor, leaderboardIcon);
        shopBtn = new TravelButton(Pic.Curve_square, Tint.PURPLE, "Shop", Assets.getInstance().getSmallFont(), ViewID.EXPLORE, shipDoor, shopIcon);

    }

    @Override
    public void setSizes() {
        starsBehind.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        starsFront.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        ship.setSize(400, 200);
        exploreBtn.setSize(200,100);
        manageBtn.setSize(200,100);
        bossbattleBtn.setSize(200, 100);
        leaderboardBtn.setSize(200, 100);
        shopBtn.setSize(200, 100);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }


    @Override
    public void setPositions() {
        ship.setPosition(600,300);
        titleBar.setPosition(80,600);
        exploreBtn.setPosition(100, 35);
        manageBtn.setPosition(320, 35);
        bossbattleBtn.setPosition(540, 35);
        leaderboardBtn.setPosition(760, 35);
        shopBtn.setPosition(980, 35);
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
        addActor(bossbattleBtn);
        addActor(leaderboardBtn);
        addActor(shopBtn);
        addActor(shipDoor);

    }

    @Override
    public void handle(int outcome) {

    }


}
