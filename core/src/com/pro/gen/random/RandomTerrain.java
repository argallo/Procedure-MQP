package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.RectBuilders;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/27/2015.
 */
public class RandomTerrain {

    private Pixmap pixmap;
    private Texture[] texture;
    private ArrayList<RectBuilders> rectangles;

    private static final int TERRAIN_WIDTH = 1024;
    private static final int TERRAIN_HEIGHT = 256;

    public RandomTerrain(int textureAmts){
        texture = new Texture[textureAmts];
        for(int i = 0; i < textureAmts; i++) {
            rectangles = new ArrayList<RectBuilders>();
            initPixmap();
            createRectangles();
            createTriangleCuts();
            texture[i] = new Texture(pixmap);
            pixmap.dispose();
        }

    }

    public void createRectangles(){
        int currentX = 0;
        while (currentX < TERRAIN_WIDTH){
            int width = MathUtils.random(30, 80);
            int y;

            //int width = MathUtils.random(10,100);
            //int y = MathUtils.random(350,500);
            //if(currentX<300 || currentX>1000){
                //y = MathUtils.random(200, 720);
          //  } else if (currentX<500 || currentX>700){
          //      y = MathUtils.random(300, 720);
          //  } else {
               y = MathUtils.random(0, 200);
           // }
            //int width = MathUtils.random(1, 10);
            //int y = MathUtils.random(700, 920);


            pixmap.fillRectangle(currentX, 0, width, y);

            if(rectangles.isEmpty()){
                rectangles.add(new RectBuilders(currentX, 0, width, TERRAIN_HEIGHT-y, RectBuilders.UP));
            } else {
                int height = y;
                boolean left = rectangles.get(rectangles.size()-1).getHeight() < height ? RectBuilders.UP : RectBuilders.DOWN;
                rectangles.get(rectangles.size()-1).setRight(!left);
                rectangles.add(new RectBuilders(currentX, 0, width, height, left));
            }
            currentX += width;
        }
    }

    public void createTriangleCuts(){

        for(int i = 0; i < rectangles.size()-1; i++){
            if(rectangles.get(i).getRight() == RectBuilders.DOWN){
                int startCutX = MathUtils.random(rectangles.get(i).getX(), rectangles.get(i).getX()+rectangles.get(i).getWidth());
                int startCutY = rectangles.get(i).getHeight();

                int endCutX = rectangles.get(i).getX() + rectangles.get(i).getWidth();
                int endCutY = MathUtils.random(rectangles.get(i).getHeight(), rectangles.get(i+1).getHeight()); // change to height of next rect

                int topX = rectangles.get(i).getX() + rectangles.get(i).getWidth();
                int topY = rectangles.get(i).getHeight();

                pixmap.fillTriangle(startCutX, startCutY, topX, topY, endCutX, endCutY );

            }
            if(rectangles.get(i).getLeft() == RectBuilders.DOWN && i>0){
                int startCutX = MathUtils.random(rectangles.get(i).getX(), rectangles.get(i).getX()+rectangles.get(i).getWidth());
                int startCutY = rectangles.get(i).getHeight();

                int endCutX = rectangles.get(i).getX();
                int endCutY = MathUtils.random(rectangles.get(i).getHeight(), rectangles.get(i-1).getHeight()); // change to height of next rect

                int topX = rectangles.get(i).getX();
                int topY = rectangles.get(i).getHeight();

                pixmap.fillTriangle(startCutX, startCutY, topX, topY, endCutX, endCutY );
            }
        }
    }

    public void initPixmap(){
        pixmap = new Pixmap(TERRAIN_WIDTH, TERRAIN_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixmap.setColor(Color.CLEAR);
    }

    public Texture getRandomTexture() {
        return texture[MathUtils.random(0,texture.length-1)];
    }

    public Texture[] getTextures() {
        return texture;
    }
}
