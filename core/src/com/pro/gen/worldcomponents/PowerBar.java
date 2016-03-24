package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 3/24/2016.
 */
public class PowerBar extends Group {

    private TintedImage barBackground;
    private TintedImage bar;
    private Button powerBtn;

    private final static int powerDistance = 796;


    public PowerBar(final MiniGame miniGame){
        barBackground = new TintedImage(Pic.Power_Bar);
        barBackground.setSize(833, 192);
        barBackground.setPosition(Constants.VIRTUAL_WIDTH / 2 - barBackground.getWidth() / 2, 200);

        bar = new TintedImage(Pic.Pixel, Tint.ORANGE);
        bar.setSize(0, 17);
        bar.setPosition(242, 310);

        powerBtn = new Button(Pic.Pixel, Tint.BLAST_RED, "Power", Assets.getInstance().getMidFont());
        powerBtn.setSize(250, 70);
        powerBtn.setPosition(Constants.VIRTUAL_WIDTH / 2 - powerBtn.getWidth() / 2, 80);

        bar.addAction(Actions.forever(Actions.sequence(Actions.sizeBy(powerDistance, 0, 0.7f, Interpolation.exp5In), Actions.sizeBy(-powerDistance, 0, 0.7f, Interpolation.exp5Out))));

        addActor(barBackground);
        addActor(bar);
        addActor(powerBtn);

        powerBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                bar.clearActions();
                miniGame.finished();
            }
        });
    }


    public float getOutputpercent(){
        return bar.getWidth()/powerDistance;
    }



}
