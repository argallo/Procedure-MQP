package com.pro.gen.views;

import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.CapsuleSpinner;
import com.pro.gen.worldcomponents.CurrencyPanel;
import com.pro.gen.worldcomponents.ShipDoor;

/**
 * Created by Gallo on 2/5/2016.
 */
public class CapsuleView extends BaseView {

    private Background background;
    private ShipDoor shipDoor;
    private TintedImage shipUI;
    private TitleBar titleBar;
    private TintedImage capsule;
    private TextLabel capsuleLabel;
    private Button openBtn;
    private TravelButton backBtn;
    private CurrencyPanel currencyPanel;
    private CapsuleSpinner capsuleSpinner;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.PINK);
        shipDoor = new ShipDoor(true);
        shipUI = new TintedImage(Pic.UI_Full);
        titleBar = new TitleBar("Dwarf Capsule");
        capsule = new TintedImage(Pic.Capsule);
        capsuleLabel = new TextLabel("3 Capsules", Assets.getInstance().getSmallFont());
        openBtn = new Button(Pic.Pixel, Tint.MED_PURPLE, "Open Capsule", Assets.getInstance().getSmallFont());
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        currencyPanel = new CurrencyPanel();
        capsuleSpinner = new CapsuleSpinner();

        openBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(0);
            }
        });
    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        capsule.setSize(60, 60);
        openBtn.setSize(250, 100);
        backBtn.setSize(60, 60);
        currencyPanel.setSize(700, 40);
        currencyPanel.init();
        capsuleSpinner.setSize(Constants.VIRTUAL_WIDTH, 170);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        capsule.setPosition(Constants.VIRTUAL_WIDTH/2-capsule.getWidth()/2, 600);
        openBtn.setPosition(Constants.VIRTUAL_WIDTH/2-openBtn.getWidth()/2, 200);
        capsuleLabel.setPosition(Constants.VIRTUAL_WIDTH/2-capsuleLabel.getWidth()/2, 550);
        currencyPanel.setPosition(Constants.VIRTUAL_WIDTH/2-currencyPanel.getWidth()/2-10, -15);
        backBtn.setPosition(50,55);
        capsuleSpinner.setPosition(0, 320);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(capsuleSpinner);
        addActor(shipUI);
        addActor(titleBar);
        addActor(capsule);
        addActor(capsuleLabel);
        addActor(currencyPanel);
        addActor(openBtn);
        addActor(backBtn);
        addActor(shipDoor);

    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case 0:
                capsuleSpinner.spin();
                break;
        }
    }


}
