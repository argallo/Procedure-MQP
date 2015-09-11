package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 9/3/2015.
 */
public class TextLabel extends Group {

    private Label label;
    private Label.LabelStyle style;

    public TextLabel(String title){
        this(title, Color.WHITE);
    }

    public TextLabel(String title, Color color){
        style = new Label.LabelStyle(Assets.getInstance().getMidFont(), color);
        label = new Label(title, style);
        label.setAlignment(Align.center);
        addActor(label);
        this.setSize(label.getPrefWidth(), label.getPrefHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        label.setPosition(x,y);
    }

    public String getText(){
        return label.getText().toString();
    }

    public void setText(String text){
        label.setText(text);
    }


}
