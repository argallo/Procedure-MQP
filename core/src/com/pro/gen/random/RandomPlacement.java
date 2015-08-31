package com.pro.gen.random;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.worldcomponents.MapItems;

/**
 * Created by Gallo on 8/15/2015.
 */
public class RandomPlacement {


    private float[] widths;
    private float[] heights;
    private float[] xs;
    private float[] ys;

    private int size;
    private MapItems[] mapItems;
    private float recWidth;
    private float recHeight;
    private float planetDiameter;

    public RandomPlacement(MapItems[] mapItems, float recWidth, float recHeight, float planetDiameter){
        this.size = mapItems.length;
        this.mapItems = mapItems;
        this.recWidth = recWidth;
        this.recHeight = recHeight;
        this.planetDiameter = planetDiameter;
        init();
    }

    public RandomPlacement(MapItems[] mapItems, float recWidth, float recHeight){
        this(mapItems, recWidth, recHeight, 0);
    }

    public void init(){
        widths = new float[size];
        heights = new float[size];
        xs = new float[size];
        ys = new float[size];

        for(int index = 0; index < size; index++){
            generateSize(index);
            generatePosition(index);
        }
    }

    public void generateSize(int index){
        widths[index] = MathUtils.random(mapItems[index].getMaxSize())+mapItems[index].getMinSize();
        heights[index] = widths[index]/mapItems[index].getItem().getAspectRatioRange().randomAspectInRange();
    }

    public void generatePosition(int index){
        float x = MathUtils.random(recWidth-widths[index]);
        float y = MathUtils.random(recHeight-heights[index]);
        if(!overlapping(index, x, y)) {
            xs[index] = x;
            ys[index] = y;
        } else {
            generatePosition(index);
        }
    }

    /**
     * Determines whether current index is overlapping any previous indexes
     * @param index current index
     * @param x test x coordinate
     * @param y test y coordinate
     * @return true if overlapping occurs
     */
    public boolean overlapping(int index, float x, float y){
        for (int i = 0; i < index; i++){
            if(x+widths[index] > xs[i] && x < xs[i]+widths[i]){
                if(y+heights[index] > ys[i] && y < ys[i]+heights[i]){
                    return true;
                }
            }
        }
        if(planetDiameter > 0){
            if(x+widths[index] > planetDiameter && x < planetDiameter*2){
                if(y+heights[index] > planetDiameter && y < planetDiameter*2){
                    return true;
                }
            }
        }
        return false;
    }

    public float getHeight(int index) {
        return heights[index];
    }

    public float getWidth(int index) {
        return widths[index];
    }

    public float getX(int index) {
        return xs[index];
    }

    public float getY(int index) {
        return ys[index];
    }

    public MapItems getMapItem(int index){
        return mapItems[index];
    }

    public int getSize(){
        return size;
    }
}
