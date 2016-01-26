package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.screens.SolarSystemScreen;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.znewmqp.Tint;

/**
 * Created by Gallo on 10/26/2015.
 */
public class FlyPlanetPopup extends Group {
    private final static String YES = "Yes";
    private final static String NO = "Cancel";
    private final static String POPTEXT = "Fly to: ";

    private TintedImage border;
    private Button yes, no;
    private TextLabel textLabel;

    SolarSystemScreen screen;

    //take in random name of star?
    public FlyPlanetPopup(SolarSystemScreen screen){
        this.screen = screen;
        init();
        setSizes();
        setPositions();
        setListeners();
        addActors();
    }

    public void init(){
        border = new TintedImage(Constants.RECTANGLE, Tint.PURPLE);
        yes = new Button(Constants.RECTANGLE, Color.GRAY, YES, Assets.getInstance().getXSmallFont());
        no = new Button(Constants.RECTANGLE, Color.GRAY, NO, Assets.getInstance().getXSmallFont());
        textLabel = new TextLabel(POPTEXT, Assets.getInstance().getSmallFont());
        //textLabel.wrapText(true, Constants.VIRTUAL_WIDTH/2-40, 300);
    }

    public void setSizes(){
        border.setSize(200, 350);
        yes.setSize(80,50);
        no.setSize(80,50);
    }

    public void setPositions(){
        border.setPosition(950, 100);
        yes.setPosition(960, 120);
        no.setPosition(1050, 120);
        textLabel.setPosition(1000, 350);
    }

    public void setListeners(){
        yes.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                //screen.popUpFly();
            }
        });

        no.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                //screen.popUpCancel();
            }
        });
    }

    public void addActors(){
        addActor(border);
        addActor(textLabel);
        addActor(yes);
        addActor(no);
    }
}
