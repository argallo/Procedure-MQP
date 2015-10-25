package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.screens.SolarSystemScreen;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/24/2015.
 */
public class MiniPlanet extends Button {

    SolarSystemScreen solarSystemScreen;

    public MiniPlanet(Color backgroundColor, SolarSystemScreen solarSystemScreen) {
        super(Constants.ABS_PLANET, backgroundColor);
        this.solarSystemScreen = solarSystemScreen;
        createAction();
    }

    public void createAction(){
        this.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                //draw Animation?
                solarSystemScreen.planetClicked(MiniPlanet.this);
            }
        });
    }
}
