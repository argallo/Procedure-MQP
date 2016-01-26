package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.pro.gen.utils.Assets;

/**
 * Created by Gallo on 8/11/2015.
 */
public class TintedImage extends Image {

    private Color tint;
    private Color batchColor;
    private Color previousColor = Color.WHITE;

    public TintedImage(String imageName){
        this(imageName, Color.WHITE);
    }

    public TintedImage(String imageName, Color tint){
        this(Assets.getInstance().getTextureRegion(imageName), tint);
    }

    public TintedImage(TextureRegion texture, Color tint) {
        this(texture);
        this.tint = tint;
    }

    public TintedImage(Drawable drawable, Color tint){
        this(drawable);
        this.tint = tint;
    }

    private TintedImage(TextureRegion texture){
        super(texture);
    }

    private TintedImage (Drawable drawable) {
        super(drawable);
    }

    public TintedImage(Color tint){
        super();
        this.tint = tint;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        validate();

        batchColor = batch.getColor();
        batch.setColor(tint.r, tint.g, tint.b, tint.a * parentAlpha * this.getColor().a);

        float x = getX();
        float y = getY();
        float scaleX = getScaleX();
        float scaleY = getScaleY();

        if (getDrawable() instanceof TransformDrawable) {
            float rotation = getRotation();
            if (scaleX != 1 || scaleY != 1 || rotation != 0) {
                ((TransformDrawable)getDrawable()).draw(batch, x + getImageX(), y + getImageY(), getOriginX() - getImageX(), getOriginY() - getImageY(),
                        getImageWidth(), getImageHeight(), scaleX, scaleY, rotation);
                return;
            }
        }
        if (getDrawable() != null) getDrawable().draw(batch, x + getImageX(), y + getImageY(), getImageWidth() * scaleX, getImageHeight() * scaleY);

        batch.setColor(batchColor);
    }

    public void setTint(Color tint) {
        previousColor = this.tint;
        this.tint = tint;
    }

    public Color getTint() {
        return tint;
    }

    public void setPreviousColor(){
        this.tint = previousColor;
    }

    public void setImage(String image) {
        setDrawable(new TextureRegionDrawable(new TextureRegion(Assets.getInstance().getTextureRegion(image))));
    }

    public static TintedImage Clone(TintedImage tintedImage){
        return new TintedImage(tintedImage.getDrawable(), tintedImage.tint);
    }


    //Use for changing colors through animations

    @Override
    public Color getColor() {
        return getTint();
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        setTint(color);
    }

}
