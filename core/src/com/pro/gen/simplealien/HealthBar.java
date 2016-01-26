package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 11/11/2015.
 */
public class HealthBar {

    private int healthPoints;
    private final int initHealthPoints;
    TintedImage backgroundBar, forgroundBar;

    public HealthBar(int healthPoints){
        this.healthPoints = healthPoints;
        initHealthPoints = healthPoints;
        backgroundBar = new TintedImage(Constants.RECTANGLE, Color.RED);
        forgroundBar = new TintedImage(Constants.RECTANGLE, Color.GREEN);

        backgroundBar.setSize(100,10);
        forgroundBar.setSize(100,10);
    }

    public void lowerHealth(int hitamt){
        healthPoints = Math.max(0, healthPoints-hitamt);
        forgroundBar.setWidth((float)healthPoints/initHealthPoints * 100);
    }

    public boolean depleated(){
        return healthPoints == 0;
    }

    public void setPosition(float x, float y){
        backgroundBar.setPosition(x, y);
        forgroundBar.setPosition(x, y);
    }

    public void setX(float x){
        backgroundBar.setX(x);
        forgroundBar.setX(x);
    }

    public void addToActor(Group group){
        group.addActor(backgroundBar);
        group.addActor(forgroundBar);
    }

    public void remove(){
        backgroundBar.remove();
        forgroundBar.remove();
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
