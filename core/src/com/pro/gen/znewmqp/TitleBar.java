package com.pro.gen.znewmqp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 1/12/2016.
 */
public class TitleBar extends Group {

    private TextLabel label;
    TintedImage bar;

    public TitleBar(String title){
        bar = new TintedImage(Pic.Title_Bar);
        bar.setSize(0,50);
        label = new TextLabel(title, Color.WHITE, Assets.getInstance().getSmallFont());
        label.setVisible(false);
        label.setPosition(20, 5);
        addActor(bar);
        addActor(label);
        animateInTextbar();
    }

    public void animateInTextbar(){
        bar.addAction(Actions.sequence(Actions.delay(0.75f),Actions.sizeTo(300, 50, 1f, Interpolation.exp10)));
        label.addAction(Actions.sequence(Actions.delay(1.5f),Actions.visible(true)));
    }

    public void animateOutTextbar(){
        label.setVisible(false);
        bar.addAction(Actions.sizeTo(0,50,1f,Interpolation.exp10));
    }

}
