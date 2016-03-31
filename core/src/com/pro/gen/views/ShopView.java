package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.Hat;
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

    private Hat specialHat1, specialHat2, specialHat3, specialHat4;
    private TextLabel effect1, effect2, effect3, effect4;
    private TextLabel price1, price2, price3, price4;
    private TintedImage power1, power2, power3, power4;
    private Button buy1, buy2, buy3, buy4;

    private TextLabel currentCrystalsLabel;
    private TintedImage currentCrystals;

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

        specialHat1 = new Hat(Pic.Santa_Hat, Hat.SANTA_HAT, Color.WHITE, Hat.LASER_BOOST, 15);
        specialHat2 = new Hat(Pic.Paper_Hat, Hat.PAPER_HAT, Color.WHITE, Hat.LASER_BOOST, 20);
        specialHat3 = new Hat(Pic.WPI_Grad_Hat, Hat.WPI_GRAD_HAT, Color.WHITE, Hat.FIRST_SLOT, 25);
        specialHat4 = new Hat(Pic.Champ_Hat, Hat.CHAMP_HAT, Color.WHITE, Hat.KO, 15);

        effect1 = new TextLabel(specialHat1.getEffectDescription()+"\n", Assets.getInstance().getXSmallFont(), Align.center);
        effect2 = new TextLabel(specialHat2.getEffectDescription()+"\n", Assets.getInstance().getXSmallFont(), Align.center);
        effect3 = new TextLabel(specialHat3.getEffectDescription(), Assets.getInstance().getXSmallFont(), Align.center);
        effect4 = new TextLabel(specialHat4.getEffectDescription()+"\n", Assets.getInstance().getXSmallFont(), Align.center);

        power1 = new TintedImage(Pic.Power_Crystal);
        power2 = new TintedImage(Pic.Power_Crystal);
        power3 = new TintedImage(Pic.Power_Crystal);
        power4 = new TintedImage(Pic.Power_Crystal);

        price1 = new TextLabel("35 Crystals", Assets.getInstance().getXSmallFont());
        price2 = new TextLabel("50 Crystals", Assets.getInstance().getXSmallFont());
        price3 = new TextLabel("75 Crystals", Assets.getInstance().getXSmallFont());
        price4 = new TextLabel("100 Crystals", Assets.getInstance().getXSmallFont());

        int money = XmlManager.getInstance().getPowerCrystals();
        if(money < 35) {
            buy1 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy2 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy3 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy4 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy1.setTouchable(Touchable.disabled);
            buy2.setTouchable(Touchable.disabled);
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <50){
            buy1 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy2 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy3 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy4 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy2.setTouchable(Touchable.disabled);
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <75){
            buy1 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy2 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy3 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy4 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <100){
            buy1 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy2 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy3 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy4 = new Button(Pic.Pixel, Tint.MED_PURPLE, "Buy", Assets.getInstance().getXSmallFont());
            buy4.setTouchable(Touchable.disabled);
        } else {
            buy1 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy2 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy3 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
            buy4 = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Buy", Assets.getInstance().getXSmallFont());
        }

        buy1.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(0);
            }
        });
        buy2.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(1);
            }
        });
        buy3.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(2);
            }
        });
        buy4.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(3);
            }
        });

        currentCrystalsLabel = new TextLabel("Current Crystals: "+ XmlManager.getInstance().getPowerCrystals(), Assets.getInstance().getSmallFont());
        currentCrystals = new TintedImage(Pic.Power_Crystal);


    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        header.setSize(400, 80);
        backBtn.setSize(60, 60);

        power1.setSize(25, 50);
        power2.setSize(25, 50);
        power3.setSize(25, 50);
        power4.setSize(25, 50);

        buy1.setSize(100, 40);
        buy2.setSize(100, 40);
        buy3.setSize(100, 40);
        buy4.setSize(100, 40);

        currentCrystals.setSize(30,60);
    }

    @Override
    public void setPositions() {
        rowDivider.setPosition(0, 513);
        header.setPosition(Constants.VIRTUAL_WIDTH / 2 - header.getWidth() / 2, 620);
        headerText.setPosition(Constants.VIRTUAL_WIDTH / 2 - headerText.getWidth() / 2, 640);
        backBtn.setPosition(50, 590);

        specialHat1.setPosition(100, 300);
        specialHat2.setPosition(440, 300);
        specialHat3.setPosition(740, 300);
        specialHat4.setPosition(980, 300);

        effect1.setPosition(50, 250);
        effect2.setPosition(380, 250);
        effect3.setPosition(680, 250);
        effect4.setPosition(980, 250);

        power1.setPosition(100, 200);
        power2.setPosition(440, 200);
        power3.setPosition(740, 200);
        power4.setPosition(980, 200);

        price1.setPosition(130, 200);
        price2.setPosition(470, 200);
        price3.setPosition(770, 200);
        price4.setPosition(1010, 200);

        buy1.setPosition(130, 150);
        buy2.setPosition(470, 150);
        buy3.setPosition(770, 150);
        buy4.setPosition(1010, 150);

        currentCrystalsLabel.setPosition(700, 540);
        currentCrystals.setPosition(1050, 540);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(rowDivider);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);

        addActor(specialHat1);
        addActor(specialHat2);
        addActor(specialHat3);
        addActor(specialHat4);
        addActor(effect1);
        addActor(effect2);
        addActor(effect3);
        addActor(effect4);

        addActor(power1);
        addActor(power2);
        addActor(power3);
        addActor(power4);

        addActor(price1);
        addActor(price2);
        addActor(price3);
        addActor(price4);

        addActor(buy1);
        addActor(buy2);
        addActor(buy3);
        addActor(buy4);

        addActor(currentCrystals);
        addActor(currentCrystalsLabel);

        addActor(backBtn);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case 0:
                XmlManager.getInstance().savePowerCrystals(XmlManager.getInstance().getPowerCrystals()-35);
                XmlManager.getInstance().addToHatsList(new Hat(specialHat1.getImage(), specialHat1.getHatID(), specialHat1.getHatColor(), specialHat1.getEffect(), specialHat1.getPowerAmt()));
                currentCrystalsLabel.setText("Current Crystals: "+ XmlManager.getInstance().getPowerCrystals());
                checkBuy();
                break;
            case 1:
                XmlManager.getInstance().savePowerCrystals(XmlManager.getInstance().getPowerCrystals()-50);
                XmlManager.getInstance().addToHatsList(new Hat(specialHat2.getImage(), specialHat2.getHatID(), specialHat2.getHatColor(), specialHat2.getEffect(), specialHat2.getPowerAmt()));
                currentCrystalsLabel.setText("Current Crystals: "+ XmlManager.getInstance().getPowerCrystals());
                checkBuy();
                break;
            case 2:
                XmlManager.getInstance().savePowerCrystals(XmlManager.getInstance().getPowerCrystals()-75);
                XmlManager.getInstance().addToHatsList(new Hat(specialHat3.getImage(), specialHat3.getHatID(), specialHat3.getHatColor(), specialHat3.getEffect(), specialHat3.getPowerAmt()));
                currentCrystalsLabel.setText("Current Crystals: "+ XmlManager.getInstance().getPowerCrystals());
                checkBuy();
                break;
            case 3:
                XmlManager.getInstance().savePowerCrystals(XmlManager.getInstance().getPowerCrystals()-100);
                XmlManager.getInstance().addToHatsList(new Hat(specialHat4.getImage(), specialHat4.getHatID(), specialHat4.getHatColor(), specialHat4.getEffect(), specialHat4.getPowerAmt()));
                currentCrystalsLabel.setText("Current Crystals: "+ XmlManager.getInstance().getPowerCrystals());
                checkBuy();
                break;
        }
    }

    public void checkBuy(){
        int money = XmlManager.getInstance().getPowerCrystals();
        if(money < 35) {
            buy1.setTint(Tint.MED_PURPLE);
            buy2.setTint(Tint.MED_PURPLE);
            buy3.setTint(Tint.MED_PURPLE);
            buy4.setTint(Tint.MED_PURPLE);
            buy1.setTouchable(Touchable.disabled);
            buy2.setTouchable(Touchable.disabled);
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <50){
            buy1.setTint(Tint.MEDIUM_GRAY);
            buy2.setTint(Tint.MED_PURPLE);
            buy3.setTint(Tint.MED_PURPLE);
            buy4.setTint(Tint.MED_PURPLE);
            buy2.setTouchable(Touchable.disabled);
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <75){
            buy1.setTint(Tint.MEDIUM_GRAY);
            buy2.setTint(Tint.MEDIUM_GRAY);
            buy3.setTint(Tint.MED_PURPLE);
            buy4.setTint(Tint.MED_PURPLE);
            buy3.setTouchable(Touchable.disabled);
            buy4.setTouchable(Touchable.disabled);
        } else if (money <100){
            buy1.setTint(Tint.MEDIUM_GRAY);
            buy2.setTint(Tint.MEDIUM_GRAY);
            buy3.setTint(Tint.MEDIUM_GRAY);
            buy4.setTint(Tint.MED_PURPLE);
            buy4.setTouchable(Touchable.disabled);
        } else {
            buy1.setTint(Tint.MEDIUM_GRAY);
            buy2.setTint(Tint.MEDIUM_GRAY);
            buy3.setTint(Tint.MEDIUM_GRAY);
            buy4.setTint(Tint.MEDIUM_GRAY);
        }
    }

}
