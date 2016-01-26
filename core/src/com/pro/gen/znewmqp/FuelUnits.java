package com.pro.gen.znewmqp;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 1/13/2016.
 */
public class FuelUnits extends Group {

    private TintedImage background, fuelIcon;
    private TextLabel unitText;

    public FuelUnits(){
        background = new TintedImage(Pic.Pixel, Tint.PURPLE);
        fuelIcon = new TintedImage(Pic.Fuel_Unit_Icon);
        unitText = new TextLabel("3 Fuel Units", Assets.getInstance().getSmallFont());

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




}
