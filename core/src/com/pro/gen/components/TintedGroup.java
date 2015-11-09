package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Gallo on 11/9/2015.
 */
public class TintedGroup extends Group {

    TintedImage image;

    public TintedGroup(String imageName, Color color){
        image = new TintedImage(imageName, color);
        addActor(image);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        image.setSize(width, height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        image.setPosition(x, y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        image.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        image.setY(y);
    }
}
