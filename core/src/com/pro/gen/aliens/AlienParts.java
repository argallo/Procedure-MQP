package com.pro.gen.aliens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.pro.gen.components.TintedImage;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/12/2015.
 */
public class AlienParts extends TintedImage {

    ArrayList<AlienParts> subParts;
    String imageName;
    int currentDirection;

    public AlienParts(String imageName, Color tint) {
        this(imageName, tint, new ArrayList<AlienParts>());
    }

    public AlienParts(String imageName, Color tint, ArrayList<AlienParts> subParts) {
        super(imageName, tint);
        this.subParts = subParts;
        this.imageName = imageName;
    }

    public void addSubPart(AlienParts part){
        subParts.add(part);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for(AlienParts part : subParts){
            part.act(delta);
        }
    }

    public void draw(Batch batch, float parentAlpha, float parentX, float parentY, float scaleX, float scaleY) {
        setScale(scaleX, scaleY);
        setPosition(getX()*scaleX, getY()*scaleY);
        setPosition(getX()+parentX, getY()+parentY);
        super.draw(batch, parentAlpha);
        for(AlienParts part : subParts){
            part.draw(batch, parentAlpha, getX(), getY(), getScaleX(), getScaleY());
        }
        setPosition(getX()-parentX, getY()-parentY);
        setPosition(getX()/scaleX, getY()/scaleY);
        setScale(1,1);
    }

    @Override
    public void setTint(Color tint) {
        super.setTint(tint);
        for(AlienParts part : subParts){
            part.setTint(tint);
        }
    }

    public void directionChanged(int direction){
        currentDirection = direction;
        for(AlienParts part : subParts){
            part.directionChanged(direction);
        }
    }

    public static AlienParts Clone(AlienParts alienParts){

        AlienParts cloned = new AlienParts(alienParts.imageName, alienParts.getTint());
        for(AlienParts part : alienParts.subParts) {
            cloned.addSubPart(AlienParts.Clone(part));
        }
        cloned.setSize(alienParts.getWidth(), alienParts.getHeight());
        cloned.setPosition(alienParts.getX(), alienParts.getY());
        //add animation when thats setup
        return cloned;
    }


}
