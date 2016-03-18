package com.pro.gen.random;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;

import java.util.Arrays;

/**
 * Created by Gallo on 8/15/2015.
 */
public class RandomPlacement {

    private float avoidDiameter;

    private int size;
    private int WIDTH;
    private int HEIGHT;

    private float[] widths;
    private float[] heights;
    private float[] xs;
    private float[] ys;

    private Item item;
    private String imageName;

    public RandomPlacement(String imageName, float[] xs, float[] ys, float[] widths, float[] heights){
        this.imageName = imageName;
        this.size = xs.length;
        this.xs = xs;
        this.ys = ys;
        this.widths = widths;
        this.heights = heights;
    }

    public RandomPlacement(Item item, int width, int height, int size){
        this(item, width, height, size, 0);
    }


    public RandomPlacement(Item item, int width, int height, int size, float avoidDiameter){
        this.item = item;
        this.imageName = item.getObjectName();
        this.WIDTH = width;
        this.HEIGHT = height;
        this.size = size;
        this.avoidDiameter = avoidDiameter;
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
        widths[index] = MathUtils.random(item.getMaxSize()) + item.getMinSize();
        heights[index] = widths[index]/item.getAspectRatioRange().randomAspectInRange();
    }

    public void generatePosition(int index){
        float x = MathUtils.random(WIDTH-widths[index]);
        float y = MathUtils.random(HEIGHT-heights[index]);
        if(!overlapping(index, x, y) && avoided(x,y)) {
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
        return false;
    }

    /**
     * Determines whether the x y position avoids the avoidDiameter used for handling planets with stars
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if the x y position avoids the avoidDiameter in the center of the screen
     */
    public boolean avoided(float x, float y){
        if(x+item.getMaxSize()< Constants.VIRTUAL_WIDTH/2-avoidDiameter/2 || x > Constants.VIRTUAL_WIDTH/2+avoidDiameter/2 ||
            y+item.getMaxSize()< Constants.VIRTUAL_HEIGHT/2-avoidDiameter/2 || y > Constants.VIRTUAL_HEIGHT/2+avoidDiameter/2){
            return true;
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

    public String getWidths() {
        return Arrays.toString(widths);
    }

    public String getHeights() {
        return Arrays.toString(heights);
    }

    public String getXs() {
        return Arrays.toString(xs);
    }

    public String getYs() {
        return Arrays.toString(ys);
    }

    public String getName(){
        return imageName;
    }

    public int getSize(){
        return size;
    }
}
