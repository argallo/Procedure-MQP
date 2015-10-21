package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 8/11/2015.
 */
public class TintedImage extends Actor {

    TextureRegion image;
    Color tint;
    Color batchColor;

    public TintedImage (String imageName, Color tint){
        this(Assets.getInstance().getTexture(imageName).getTexture(), tint);
    }

    public TintedImage (Texture texture, Color tint){
        image = new TextureRegion(texture);
        this.tint = tint;
    }

    public TintedImage (TextureRegion texture, Color tint){
        image = texture;
        this.tint = tint;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batchColor = batch.getColor();
        if(tint != Color.CLEAR) {
            batch.setColor(tint);
        }
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

    public void setImage(TextureRegion image) {
        this.image = image;
    }

    public void setImage(String image) {
        this.image = new TextureRegion(Assets.getInstance().getTexture(image));
    }


    public static TintedImage Clone(TintedImage tintedImage){
        return new TintedImage(tintedImage.image, tintedImage.tint);
    }
}
