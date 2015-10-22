package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.ShipScreen;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/22/2015.
 */
public class WarpToPopup extends Group {

    private final static String YES = "Yes";
    private final static String NO = "No";
    private final static String POPTEXT = "Would you like to use a hyper-speed to travel to this star?";

    private TintedImage border;
    private Button yes, no;
    private TextLabel textLabel;

    ShipScreen shipScreen;

    //take in random name of star?
    public WarpToPopup(ShipScreen shipScreen){
        this.shipScreen = shipScreen;
        init();
        setSizes();
        setPositions();
        setListeners();
        addActors();
    }

    public void init(){
        border = new TintedImage(Constants.CURVERECT, Constants.PURPLE);
        yes = new Button(Constants.RECTANGLE, Color.GRAY, YES, Assets.getInstance().getSmallFont());
        no = new Button(Constants.RECTANGLE, Color.GRAY, NO, Assets.getInstance().getSmallFont());
        textLabel = new TextLabel(POPTEXT, Assets.getInstance().getSmallFont());
        textLabel.wrapText(true, Constants.VIRTUAL_WIDTH/2-40, 300);
    }

    public void setSizes(){
        border.setSize(Constants.VIRTUAL_WIDTH/2, 400);
        yes.setSize(150,80);
        no.setSize(150,80);
    }

    public void setPositions(){
        border.setPosition(Constants.VIRTUAL_WIDTH/4, 100);
        yes.setPosition(450, 180);
        no.setPosition(700, 180);
        textLabel.setPosition(340, 280);
    }

    public void setListeners(){
        yes.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                shipScreen.popUpFly();
            }
        });

        no.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
               shipScreen.popUpCancel();
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
