package com.pro.gen.utils;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Gallo on 8/13/2015.
 */
public class Range {

    private float minimumRatio;
    private float maximumRatio;

    public Range(float minimumRatio, float maximumRatio){
        this.minimumRatio = minimumRatio;
        this.maximumRatio = maximumRatio;
    }

    /**
     * check whether a width and height are within the range
     * @param width given width
     * @param height given height
     * @return returns true if aspect ratio is within the range
     */
    public boolean withinRange(float width, float height){
        if(width/height >= minimumRatio && width/height <= maximumRatio){
            return true;
        }
        return false;
    }

    /**
     * Generate an aspect ratio within the range
     * @return a float aspect ratio
     */
    public float randomAspectInRange(){
        return MathUtils.random(maximumRatio-minimumRatio)+minimumRatio;
    }
}
