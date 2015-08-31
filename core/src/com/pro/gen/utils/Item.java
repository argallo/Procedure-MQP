package com.pro.gen.utils;

/**
 * Created by Gallo on 8/15/2015.
 */
public class Item {

    private String objectName;
    private Range aspectRatioRange;

    public static final Item CYLINDER = new Item(Constants.CYLINDER, new Range(2f,6f));
    public static final Item CIRCLE = new Item(Constants.CIRCLE, new Range(1f,1f));
    public static final Item CIRCLE_SMALL = new Item(Constants.CIRCLE_SMALL, new Range(1f,1f));
    public static final Item TWINKLE = new Item(Constants.TWINKLE, new Range(1f, 1f));

    public Item(String objectName, Range aspectRatioRange){
        this.objectName = objectName;
        this.aspectRatioRange = aspectRatioRange;
    }

    public String getObjectName() {
        return objectName;
    }

    public Range getAspectRatioRange() {
        return aspectRatioRange;
    }
}
