package com.pro.gen.utils;

/**
 * Created by Gallo on 9/11/2015.
 */
public class PositionPair {

    private float x, y;

    public PositionPair(float x, float y){
        this.x = x;
        this.y = y;
    }

    public PositionPair(PositionPair pair){
        this.x = pair.getX();
        this.y = pair.getY();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

}
