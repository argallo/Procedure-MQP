package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 9/3/2015.
 */
public class TextBox extends Group{

    private TextField textField;
    private TextField.TextFieldStyle style;
    private TintedImage backgroundFocused;

    public TextBox(int maxLength){
        style = new TextField.TextFieldStyle();
        style.background = new TextureRegionDrawable(Assets.getInstance().getTexture(Constants.CURVERECT));
        style.background.setLeftWidth(style.background.getLeftWidth() + 25);
        style.background.setRightWidth(style.background.getRightWidth() + 25);
        style.cursor = new TextureRegionDrawable(Assets.getInstance().getTexture(Constants.BLUE));
        style.font = Assets.getInstance().getMidFont();
        style.fontColor = Color.WHITE;
        style.focusedFontColor = Color.BLACK;
        textField = new TextField("", style);
        backgroundFocused = new TintedImage(Constants.CURVERECT, Constants.ORANGE);
        backgroundFocused.setPosition(-2,-2);
        backgroundFocused.setVisible(false);
        textField.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                backgroundFocused.setVisible(focused);
            }
        });
        textField.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return (Character.isDigit(c)||Character.isLetter(c)) ? true : false;
            }
        });
        textField.setMaxLength(maxLength);
        addActor(backgroundFocused);
        addActor(textField);
    }

    public TextBox(){
        this(0);
    }

    public void setSize(float width, float height){
        super.setSize(width, height);
        textField.setSize(width, height);
        backgroundFocused.setSize(width + 4, height + 4);
    }

    public String getText(){
        return textField.getText();
    }

}
