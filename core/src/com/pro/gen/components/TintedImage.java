package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pro.gen.utils.Assets;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/11/2015.
 */
public class TintedImage extends Actor {

    TextureRegion image;
    Color tint;
    Color batchColor;
    ArrayList<TintedImage> tintedImages;

    public TintedImage (String imageName, Color tint){
        image = new TextureRegion(Assets.getInstance().getTexture(imageName));
        this.tint = tint;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batchColor = batch.getColor();
        batch.setColor(tint);
        batch.draw(image, getX(), getY(), getWidth(), getHeight());
        batch.setColor(batchColor);
        //super.draw(batch, parentAlpha); //This line causes MAJOR LAG on devices
    }

    public void setTint(Color tint) {
        this.tint = tint;
    }

    public Color getTint() {
        return tint;
    }
}