package com.pro.gen.aliens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pro.gen.utils.Assets;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/12/2015.
 */
public class AlienParts {

    private ArrayList<AlienParts> subParts;
    private String imageName;
    private Color tint;
    private int x, y, width, height;

    public AlienParts(String imageName) {
        this(imageName, new ArrayList<AlienParts>());
    }

    public AlienParts(String imageName, Color tint) {
        this(imageName, tint, new ArrayList<AlienParts>());
    }

    public AlienParts(String imageName, ArrayList<AlienParts> subParts) {
        this(imageName, Color.WHITE, new ArrayList<AlienParts>());
    }

    public AlienParts(String imageName, Color tint, ArrayList<AlienParts> subParts) {
        this.subParts = subParts;
        this.imageName = imageName;
        this.tint = tint;
        x = y = width = height = 0;
    }

    public void addSubPart(AlienParts part){
        subParts.add(part);
    }

     public void setSize(int width, int height){
         this.width = width;
         this.height = height;
     }

    public void setPosition(int x, int y){
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getTint() {
        return tint;
    }

    public ArrayList<AlienParts> getSubParts() {
        return subParts;
    }

    public TextureRegion getImage() {
        return Assets.getInstance().getTextureRegion(imageName);
    }

    public static AlienParts Clone(AlienParts alienParts){

        AlienParts cloned = new AlienParts(alienParts.imageName);
        for(AlienParts part : alienParts.subParts) {
            cloned.addSubPart(AlienParts.Clone(part));
        }
        cloned.setSize(alienParts.getWidth(), alienParts.getHeight());
        cloned.setPosition(alienParts.getX(), alienParts.getY());
        //add animation when thats setup
        return cloned;
    }


}
