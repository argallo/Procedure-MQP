package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.XmlManager;
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
        rank = new Button(Pic.Pixel, Tint.LIGHT_BLUE, XmlManager.getInstance().getUsername(), Assets.getInstance().getXSmallFont());
        rank.setTouchable(Touchable.disabled);
        homePlanet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_1);
        homePlanet.setBackgroundTint(Tint.DARK_PURPLE);


    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        columnDivider.setSize(35, 515);
        header.setSize(300, 80);
        backBtn.setSize(60, 60);
        rank.setSize(250, 80);
        homePlanet.setSize(250,250);

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
        homePlanet.setPosition(800, 50);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(homePlanet);
        addActor(leaderBoardPanel);
        addActor(rowDivider);
        addActor(columnDivider);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);
        addActor(rank);
        addActor(backBtn);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {

    }
}
