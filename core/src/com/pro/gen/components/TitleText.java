package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 9/3/2015.
 */
public class TitleText extends Group {
    private Label label;
    private Label labelShaow;
    private Label.LabelStyle style;
    private Label.LabelStyle styleShadow;

    public TitleText(String title){
        style = new Label.LabelStyle(Assets.getInstance().getLargeFont(), Color.WHITE);
        styleShadow = new Label.LabelStyle(Assets.getInstance().getLargeFont(), Color.GRAY);
        label = new Label(title, style);
        labelShaow = new Label(title, styleShadow);
        label.setAlignment(Align.center);
        labelShaow.setAlignment(Align.center);
        addActor(labelShaow);
        addActor(label);

        this.setSize(label.getPrefWidth(), label.getPrefHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        label.setPosition(x,y);
        labelShaow.setPosition(x + 5, y - 5);
    }

}
