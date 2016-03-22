package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.*;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 1/13/2016.
 */
public class FuelUnits extends Group {

    public final static long FUEL_REPLENISH_TIME = 1200000; // 20 minutes
    public final static int MAX_FUEL_REPLENISH = 3; // replenish 3 on timer than stop


    private TintedImage background, fuelIcon;
    private TextLabel unitText;
    private int units;

    public FuelUnits(){
        units = XmlManager.getInstance().getFuelUnits();
        background = new TintedImage(Pic.Pixel, Tint.PURPLE);
        fuelIcon = new TintedImage(Pic.Fuel_Unit_Icon);
        unitText = new TextLabel(units+" Fuel Units", Assets.getInstance().getSmallFont());

        background.setSize(300, 100);
        fuelIcon.setSize(50, 54);

        fuelIcon.setPosition(10, 25);
        unitText.setPosition(80, 30);

        addActor(background);
        addActor(fuelIcon);
        addActor(unitText);

        setSize(300, 100);
        setPosition(Constants.VIRTUAL_WIDTH, 10);
        addAction(Actions.sequence(Actions.delay(0.5f), Actions.moveTo(980, 10, 0.5f, Interpolation.exp5)));

    }

    public void useFuel(){
        units--;
        unitText.setText(units+" Fuel Units");
        XmlManager.getInstance().saveFuelUnits(units);
    }




}
