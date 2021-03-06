package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public TextLabel(String title, BitmapFont font){
        this(title, Color.WHITE, font);
    }

    public TextLabel(String title, BitmapFont font, int align){
        this(title, Color.WHITE, font, align);
    }

    public TextLabel(String title, Color color){
        this(title, color, Assets.getInstance().getMidFont());
    }

    public TextLabel(String title){
        this(title, Color.WHITE, Assets.getInstance().getMidFont());
    }

    public TextLabel(String title, Color color, BitmapFont font){
        this(title, color, font, Align.center);
    }

    public TextLabel(String title, Color color, BitmapFont font, int align){
        style = new Label.LabelStyle(font, color);
        label = new Label(title, style);
        label.setAlignment(align);
        addActor(label);
        this.setSize(label.getPrefWidth(), label.getPrefHeight());
    }

    @Override
    public void setPosition(float x, float y) {
        label.setPosition(x, y);
    }

    public String getText(){
        return label.getText().toString();
    }

    public void setText(String text){
        label.setText(text);
        this.setSize(label.getPrefWidth(), label.getPrefHeight());
    }

    public void wrapText(boolean wrap, float width, float height){
        label.setWrap(wrap);
        label.setSize(width, height);
        this.setSize(width, height);
    }

    public void setTint(Color color){
        style.fontColor = color;
    }

    public void setAlign(int align){
        label.setAlignment(align);
    }

}
