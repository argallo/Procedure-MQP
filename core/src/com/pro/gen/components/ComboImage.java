package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by Gallo on 9/1/2015.
 */
public class ComboImage {

    private ArrayList<Texture> textures;
    private ArrayList<Integer> widths;
    private ArrayList<Integer> heights;
    private ArrayList<Integer> xs;
    private ArrayList<Integer> ys;
    private ArrayList<Color> colors;

    public ComboImage(){
        textures = new ArrayList<Texture>();
        xs = new ArrayList<Integer>();
        ys = new ArrayList<Integer>();
        widths = new ArrayList<Integer>();
        heights = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
    }

    public void addImage(Texture texture, int x, int y, int width, int height, Color color){
        textures.add(texture);
        xs.add(x);
        ys.add(y);
        widths.add(width);
        heights.add(height);
        colors.add(color);
    }

    public Texture getBaseTexture(){
        return textures.get(0);
    }

    public Color getBaseColor(){
        return colors.get(0);
    }

    public Texture getTexture(int index){
        return textures.get(index+1);
    }

    public Color getColor(int index){
        return colors.get(index+1);
    }

    public int getWidth(int index){
        return widths.get(index+1);
    }

    public int getHeight(int index){
        return heights.get(index+1);
    }

    public int getX(int index){
        return xs.get(index+1);
    }

    public int getY(int index){
        return ys.get(index+1);
    }

    public int getLength(){
        return textures.size()-1;
    }
}
