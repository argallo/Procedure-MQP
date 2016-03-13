package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.CurrencyPanel;
import com.pro.gen.worldcomponents.LeaderBoardPanel;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.ShipDoor;

/**
 * Created by Gallo on 2/5/2016.
 */
public class LeaderboardView extends BaseView {

    private Background background;
    private TintedImage shipUI;
    private TintedImage rowDivider, columnDivider;
    private LeaderBoardPanel leaderBoardPanel;
    private Button backBtn;
    private Button rank;
    private Planet homePlanet;
    private CurrencyPanel vCurrencyPanel;
    private TextLabel mined, armed, destroyed;
    private TintedImage alien;
    private TintedImage header;
    private TextLabel headerText;
    private ShipDoor shipDoor;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.DARK_PURPLE);
        shipDoor = new ShipDoor(true);
        rowDivider = new TintedImage(Pic.Pixel, Tint.MED_PURPLE);
        columnDivider = new TintedImage(Pic.Pixel, Tint.MED_PURPLE);
        leaderBoardPanel = new LeaderBoardPanel();
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        header = new TintedImage(Pic.Header_Bar, Tint.PURPLE);
        headerText = new TextLabel("LeaderBoard", Assets.getInstance().getSmallFont());
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        rank = new Button(Pic.Pixel, Tint.LIGHT_BLUE, "Rank 23", Assets.getInstance().getSmallFont());
        rank.setTouchable(Touchable.disabled);
        homePlanet = new Planet(new RandomPlanet(), Tint.DARK_PURPLE);
        mined = new TextLabel("Mined 18", Assets.getInstance().getXSmallFont());
        destroyed = new TextLabel("Destroyed 12", Assets.getInstance().getXSmallFont());
        armed = new TextLabel("Armed 24", Assets.getInstance().getXSmallFont());
        vCurrencyPanel = new CurrencyPanel(CurrencyPanel.VERTICAL);

    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        columnDivider.setSize(35, 515);
        header.setSize(300, 80);
        backBtn.setSize(60, 60);
        rank.setSize(250, 80);
        homePlanet.setSize(120,120);
        vCurrencyPanel.setSize(150, 270);
    }

    @Override
    public void setPositions() {
        leaderBoardPanel.setPosition(15,15);
        rowDivider.setPosition(0,513);
        columnDivider.setPosition(565,0);
        header.setPosition(Constants.VIRTUAL_WIDTH/2-header.getWidth()/2, 620);
        headerText.setPosition(Constants.VIRTUAL_WIDTH/2-headerText.getWidth()/2, 640);
        backBtn.setPosition(50,590);
        rank.setPosition(800, 420);
        homePlanet.setPosition(1100, 150);
        mined.setPosition(1110, 100);
        destroyed.setPosition(1110, 70);
        armed.setPosition(1110, 40);
        vCurrencyPanel.setPosition(610, 50);
        vCurrencyPanel.init();
        vCurrencyPanel.updateAmounts(20,20, 20, 20, 20);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(leaderBoardPanel);
        addActor(rowDivider);
        addActor(columnDivider);
        addActor(homePlanet);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);
        addActor(rank);
        addActor(mined);
        addActor(destroyed);
        addActor(armed);
        addActor(vCurrencyPanel);
        addActor(backBtn);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {

    }
}
