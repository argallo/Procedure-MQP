package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.Button;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 1/20/2016.
 */
public class ImgTextButton extends Button {

    private TintedImage icon;
    private float imageRatio;

    public ImgTextButton(String mainImage, String icon, String text, Color backgroundColor, float ratio ) {
        super(mainImage, backgroundColor, text, Assets.getInstance().getSmallFont());
        this.icon = new TintedImage(icon);
        this.imageRatio = ratio;
        buttonLabel.setAlignment(Align.bottom);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if(icon != null){
            icon.setSize(height*0.5f*imageRatio,height*0.5f);
            icon.setPosition(getX()+getWidth()/2-icon.getWidth()/2, getY()+getHeight()/2-icon.getHeight()/3);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if(icon != null){
            icon.setPosition(getX()+getWidth()/2-icon.getWidth()/2, getY()+getHeight()/2-icon.getHeight()/3);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(icon!= null && isVisible())
            icon.draw(batch, parentAlpha);
    }


}
