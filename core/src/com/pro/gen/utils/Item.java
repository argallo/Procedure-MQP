package com.pro.gen.utils;

/**
 * Created by Gallo on 8/15/2015.
 */
public class Item {

    private String objectName;
    private Range aspectRatioRange;
    private float minSize, maxSize;

    public static final Item CloudCylinder = new Item(Pic.Cylinder, new Range(2f,6f), 50, 100);
    public static final Item CloudCircle = new Item(Pic.Circle_Large, new Range(1f,1f), 30, 50);

    public static final Item LandCylinder = new Item(Pic.Cylinder, new Range(2f,6f), 50, 200);
    public static final Item LandCircle = new Item(Pic.Circle_Large, new Range(1f,1f), 50, 110);


    public Item(String objectName, Range aspectRatioRange, float minSize, float maxSize){
        this.objectName = objectName;
        this.aspectRatioRange = aspectRatioRange;
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public String getObjectName() {
        return objectName;
    }

    public Range getAspectRatioRange() {
        return aspectRatioRange;
    }

    public float getMinSize() {
        return minSize;
    }

    public float getMaxSize() {
        return maxSize;
    }
}
