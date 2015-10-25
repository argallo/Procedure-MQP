package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Base64Coder;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;

import java.security.MessageDigest;

/**
 * Created by Gallo on 9/3/2015.
 */
public class TextBox extends Group{

    public static final int ALL = 0;
    public static final int EMAIL = 1;
    public static final int CHARDIG = 2;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private TextField textField;
    private TextField.TextFieldStyle style;
    private TintedImage backgroundFocused;
    private String backgroundText;
    private boolean password;
    private int filterType;

    public TextBox(int maxLength, final String backgroundText, int filterType){
        this(maxLength, backgroundText, filterType, false);
    }

    public TextBox(int maxLength, final String backgroundText, final int filterType, boolean password){
        this.backgroundText = backgroundText;
        this.password = password;
        this.filterType = filterType;
        style = new TextField.TextFieldStyle();
        style.background = new TextureRegionDrawable(Assets.getInstance().getTextureRegion(Constants.CURVERECT));
        style.background.setLeftWidth(style.background.getLeftWidth() + 25);
        style.background.setRightWidth(style.background.getRightWidth() + 25);
        style.cursor = new TextureRegionDrawable(Assets.getInstance().getTextureRegion(Constants.BLUE));
        style.font = Assets.getInstance().getMidFont();
        style.fontColor = Color.LIGHT_GRAY;
        style.focusedFontColor = Color.BLACK;
        textField = new TextField(backgroundText, style);
        backgroundFocused = new TintedImage(Constants.CURVERECT, Constants.ORANGE);
        backgroundFocused.setPosition(-2,-2);
        backgroundFocused.setVisible(false);
        textField.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                backgroundFocused.setVisible(focused);
                style.fontColor = Color.LIGHT_GRAY;
                if(textField.getText().equals(backgroundText)){
                    removeBackgroundText();
                }
                if(!focused && textField.getText().isEmpty()){
                    setBackgroundText();
                }
            }
        });
        textField.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                switch (filterType){
                    case ALL:
                        return true;
                    case EMAIL:
                        if(Character.isDigit(c)||Character.isLetter(c) || c == '.'){
                            return true;
                        }
                        if(c == '@'){
                            if(textField.getText().contains("@")){
                                return false;
                            }
                            return true;
                        }
                        return false;
                    case CHARDIG:
                        return (Character.isDigit(c)||Character.isLetter(c)) ? true : false;
                }
                return true;
            }
        });
        textField.setMaxLength(maxLength);
        addActor(backgroundFocused);
        addActor(textField);
    }

    public TextBox(){
        this(0, "", TextBox.ALL);
    }

    public void setSize(float width, float height){
        super.setSize(width, height);
        textField.setSize(width, height);
        backgroundFocused.setSize(width + 4, height + 4);
    }

    public String getText(){
        return textField.getText();
    }

    private void setBackgroundText(){
        textField.getStyle().fontColor = Color.LIGHT_GRAY;
        textField.setText(backgroundText);
        textField.setPasswordMode(false);

    }

    private void removeBackgroundText(){
        textField.setText("");
        if(password){
            textField.setPasswordMode(true);
            textField.setPasswordCharacter('*');
        }
    }


    public boolean isValid() {
        backgroundFocused.setVisible(false);
        if(textField.getText().isEmpty()){
            textField.setText(backgroundText);
            textField.setPasswordMode(false);
        }

        if(textField.getText().equals(backgroundText)){
            textField.getStyle().fontColor = Color.RED;
            return false;
        }

        /**
         * Check if email is valid
         */
        if(filterType == EMAIL && !textField.getText().matches(EMAIL_PATTERN)){
            textField.getStyle().fontColor = Color.RED;
            return false;
        }

        return true;
    }


    public String getSecureString(){
        try {
            byte[] bytesOfMessage = textField.getText().getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Base64Coder.encodeLines(md.digest(bytesOfMessage));
        } catch (Exception e){
            LogUtils.Log(e.toString());
        }
        return null;
    }
}
