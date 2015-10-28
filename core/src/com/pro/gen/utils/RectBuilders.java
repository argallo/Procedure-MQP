package com.pro.gen.utils;

/**
 * Created by Gallo on 10/27/2015.
 */
public class RectBuilders {

    private int x, y, width, height;
    private boolean left, right;

    public static final boolean UP = true;
    public static final boolean DOWN = false;

    public RectBuilders(int x, int y, int width, int height, boolean left){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.left = left;
        this.right = UP;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getLeft() {
        return left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getRight() {
        return right;
    }

}
