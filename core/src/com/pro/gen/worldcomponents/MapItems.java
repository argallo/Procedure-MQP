package com.pro.gen.worldcomponents;

import com.pro.gen.utils.Item;

/**
 * Created by Gallo on 8/15/2015.
 */
public class MapItems {

    private float maxSize;
    private float minSize;
    private Item item;

    public MapItems(Item item, float maxSize, float minSize){
        this.item = item;
        this.maxSize = maxSize;
        this.minSize = minSize;
    }

    public float getMaxSize() {
        return maxSize;
    }

    public float getMinSize() {
        return minSize;
    }

    public Item getItem() {
        return item;
    }


}
