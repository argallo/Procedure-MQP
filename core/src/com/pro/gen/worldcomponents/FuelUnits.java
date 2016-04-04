package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.pro.gen.App;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.*;
import com.pro.gen.utils.Pic;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo on 1/13/2016.
 */
public class FuelUnits extends Group {

    public final static long FUEL_REPLENISH_TIME = 1200000; // 20 minutes
    public final static int MAX_FUEL_REPLENISH = 3; // replenish 3 on timer than stop
    public final static int REPLENISH = 11;


    private TintedImage background, fuelIcon;
    private Button replenishBtn;
    private TextLabel unitText;
    private int units;
    private BaseView baseView;

    public FuelUnits(final BaseView baseView){
        this.baseView = baseView;
        units = XmlManager.getInstance().getFuelUnits();
        background = new TintedImage(Pic.Pixel, Tint.PURPLE);
        fuelIcon = new TintedImage(Pic.Fuel_Unit_Icon);


        replenishBtn = new Button(Pic.Pixel, Tint.GLOBE_RANK_GREEN, "Replenish Fuel", Assets.getInstance().getXSmallFont());
        replenishBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                baseView.handle(REPLENISH);
            }
        });
        if(units > 0){
            replenishBtn.setVisible(false);
        }  else {
            if (XmlManager.getInstance().getFuelAmt() == 1200000) {
                if (XmlManager.getInstance().getFuelTimer() + 1200000 < TimeUtils.millis()) {
                    replenishBtn.setVisible(false);
                    units = 3;
                    XmlManager.getInstance().saveFuelUnits(units);
                } else {
                    replenishBtn.setTouchable(Touchable.disabled);
                    replenishBtn.setText("20 mins");
                }
            } else if (XmlManager.getInstance().getFuelAmt() == 300) {
                int steps = App.stepCallback.getStepsSince(XmlManager.getInstance().getFuelTimer());
                if (steps >= 300) {
                    replenishBtn.setVisible(false);
                    units = 3;
                    XmlManager.getInstance().saveFuelUnits(units);
                } else {
                    replenishBtn.setTouchable(Touchable.disabled);
                    replenishBtn.setText("300 Steps");
                }
            }
        }

        unitText = new TextLabel(units+" Fuel Units", Assets.getInstance().getSmallFont());
        background.setSize(300, 100);
        fuelIcon.setSize(50, 54);
        replenishBtn.setSize(250, 80);

        fuelIcon.setPosition(10, 25);
        unitText.setPosition(80, 30);
        replenishBtn.setPosition(16, 100);

        addActor(background);
        addActor(fuelIcon);
        addActor(unitText);
        addActor(replenishBtn);

        setSize(300, 100);
        setPosition(Constants.VIRTUAL_WIDTH, 10);
        addAction(Actions.sequence(Actions.delay(0.5f), Actions.moveTo(980, 10, 0.5f, Interpolation.exp5)));

    }

    public void walker(){
        replenishBtn.setTouchable(Touchable.disabled);
        replenishBtn.setText("300 Steps");
    }

    public void waiter(){
        replenishBtn.setTouchable(Touchable.disabled);
        replenishBtn.setText("20 mins");
    }

    public int getUnits() {
        return units;
    }
}
