package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.ShipDoor;

/**
 * Created by Gallo on 3/25/2016.
 */
public class ShopView extends BaseView {



    private Background background;
    private TintedImage shipUI;
    private TintedImage rowDivider;
    private Button backBtn;
    private TintedImage alien;
    private TintedImage header;
    private TextLabel headerText;
    private ShipDoor shipDoor;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.DARK_PURPLE);
        shipDoor = new ShipDoor(true);
        rowDivider = new TintedImage(Pic.Pixel, Tint.MED_PURPLE);

        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        header = new TintedImage(Pic.Header_Bar, Tint.PURPLE);
        headerText = new TextLabel("Shop", Assets.getInstance().getSmallFont());
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);


    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        header.setSize(400, 80);
        backBtn.setSize(60, 60);
    }

    @Override
    public void setPositions() {
        rowDivider.setPosition(0,513);
        header.setPosition(Constants.VIRTUAL_WIDTH/2-header.getWidth()/2, 620);
        headerText.setPosition(Constants.VIRTUAL_WIDTH/2-headerText.getWidth()/2, 640);
        backBtn.setPosition(50,590);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(rowDivider);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);
        addActor(backBtn);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {

    }
}
