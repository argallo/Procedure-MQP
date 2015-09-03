package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 9/3/2015.
 */
public class TextBox {

    private TextField textField;
    private TextField.TextFieldStyle style;

    public TextBox(){
        style = new TextField.TextFieldStyle();
        style.background = new TextureRegionDrawable(Assets.getInstance().getTexture(Constants.CURVERECT));
        style.background.setLeftWidth(style.background.getLeftWidth() + 25);
        style.background.setRightWidth(style.background.getRightWidth() + 25);
        style.cursor = new TextureRegionDrawable(Assets.getInstance().getTexture(Constants.BLUE));
        style.font = Assets.getInstance().getMidFont();
        style.fontColor = Color.WHITE;
        style.focusedFontColor = Color.BLACK;
        textField = new TextField("", style);

    }

    public TextField getActor(){
        return textField;
    }

    public void setSize(float width, float height){
        textField.setSize(width, height);
    }

    public void setPosition(float x, float y){
        textField.setPosition(x,y);
    }

    public float getWidth(){
        return textField.getWidth();
    }

    public float getHeight(){
        return textField.getHeight();
    }
}
