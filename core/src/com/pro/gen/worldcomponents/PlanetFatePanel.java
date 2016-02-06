package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.*;
import com.pro.gen.utils.Pic;
import com.pro.gen.components.ImgTextButton;

/**
 * Created by Gallo on 1/17/2016.
 */
public class PlanetFatePanel extends Group {


    private Button mineBtn, destroyBtn, armyBtn;
    private TintedImage panelBackground, titleBackground, btnBackground;
    private TextLabel panelTitle;

    public PlanetFatePanel(final com.pro.gen.popups.PlanetPopup popup){
        panelBackground = new TintedImage(com.pro.gen.utils.Pic.Curve_square, com.pro.gen.utils.Tint.MED_PURPLE);
        panelBackground.setSize(270, 720);
        panelBackground.setPosition(1005, 0);
        addActor(panelBackground);

        titleBackground = new TintedImage(com.pro.gen.utils.Pic.Curve_square, com.pro.gen.utils.Tint.PURPLE);
        titleBackground.setSize(220, 70);
        titleBackground.setPosition(1025, 630);
        addActor(titleBackground);

        panelTitle = new TextLabel("Choose Planet Fate", Assets.getInstance().getXSmallFont());
        panelTitle.setPosition(1045, 650);
        addActor(panelTitle);

        btnBackground = new TintedImage(com.pro.gen.utils.Pic.Pixel, com.pro.gen.utils.Tint.PURPLE);
        btnBackground.setSize(240, 550);
        btnBackground.setPosition(1015, 35);
        addActor(btnBackground);

        armyBtn = new ImgTextButton(com.pro.gen.utils.Pic.Curve_square, com.pro.gen.utils.Pic.Army_Icon, "Army" , com.pro.gen.utils.Tint.ORANGE, 1f);
        armyBtn.setSize(220, 120);
        armyBtn.setPosition(1025, 450);
        armyBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                popup.setupType(com.pro.gen.popups.PlanetPopup.ARMY);
                popup.activatePopup();
            }
        });
        addActor(armyBtn);

        destroyBtn = new ImgTextButton(com.pro.gen.utils.Pic.Curve_square, com.pro.gen.utils.Pic.Destroy_Icon, "Destroy" , com.pro.gen.utils.Tint.PINK, 2f);
        destroyBtn.setSize(220, 120);
        destroyBtn.setPosition(1025, 250);
        destroyBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                popup.setupType(com.pro.gen.popups.PlanetPopup.DESTROY);
                popup.activatePopup();
            }
        });
        addActor(destroyBtn);

        mineBtn = new ImgTextButton(com.pro.gen.utils.Pic.Curve_square, Pic.Mine_Icon, "Mine" , com.pro.gen.utils.Tint.LIGHT_BLUE, 0.9f);
        mineBtn.setSize(220, 120);
        mineBtn.setPosition(1025, 50);
        mineBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                popup.setupType(com.pro.gen.popups.PlanetPopup.MINE);
                popup.activatePopup();
            }
        });
        addActor(mineBtn);

        animate();
    }


    public void animate(){
        addAction(Actions.sequence(Actions.moveTo(200, 0), Actions.moveTo(0,0,2f, Interpolation.exp10)));
    }

}
