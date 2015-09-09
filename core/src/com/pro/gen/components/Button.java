package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Gallo on 9/2/2015.
 */
public class Button extends TintedImage {

    Label buttonLabel;
    Label.LabelStyle labelStyle;
    Color backgroundColor;
    ButtonAction buttonAction;

    public Button(String mainImage, Color backgroundColor, String buttonText, BitmapFont fontType){
        super(mainImage, backgroundColor);
        labelStyle = new Label.LabelStyle(fontType, Color.WHITE);
        buttonLabel = new Label(buttonText, labelStyle);
        buttonLabel.setAlignment(Align.center);
        this.backgroundColor = backgroundColor;
        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setTint(Color.RED);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setTint(getBackgroundColor());
                if(buttonAction != null)
                    buttonAction.buttonPressed();
            }
        });
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        buttonLabel.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        buttonLabel.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        buttonLabel.draw(batch, parentAlpha);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setButtonAction(ButtonAction action){
        buttonAction = action;
    }

    public String getText(){
        return buttonLabel.getText().toString();
    }

    public void setText(String text){
        buttonLabel.setText(text);
    }

}
