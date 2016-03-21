package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.utils.ColorHelper;

/**
 * Created by Gallo on 9/2/2015.
 */
public class Button extends TintedImage {

    protected Label buttonLabel;
    Label.LabelStyle labelStyle;
    Color backgroundColor;
    Color pressedColor;
    ButtonAction buttonAction;
    TintedImage icon;

    public Button(String mainImage, Color backgroundColor){
        this(mainImage, backgroundColor, "", null, null);
    }

    public Button(String mainImage, final Color backgroundColor, String buttonText, BitmapFont fontType){
        this(mainImage, backgroundColor, buttonText, fontType, null);
    }

    public Button(String mainImage, final Color backgroundColor, String buttonText, BitmapFont fontType, TintedImage icon){
        super(mainImage, backgroundColor);
        pressedColor = (ColorHelper.darken(new Color(backgroundColor), 0.4f));
        if(fontType != null) {
            labelStyle = new Label.LabelStyle(fontType, Color.WHITE);
            buttonLabel = new Label(buttonText, labelStyle);
            buttonLabel.setAlignment(Align.center);
        }
        if(icon != null){
            this.icon = icon;
        }
        this.backgroundColor = backgroundColor;
        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setTint(pressedColor);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setTint(getBackgroundColor());
                if (buttonAction != null)
                    buttonAction.buttonPressed();
            }
        });
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if(buttonLabel!= null)
            buttonLabel.setPosition(x, y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        if(buttonLabel!= null)
            buttonLabel.setX(x);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if(buttonLabel!= null)
            buttonLabel.setSize(width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(icon != null)
            icon.draw(batch, parentAlpha);
        if(buttonLabel!= null && isVisible())
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
