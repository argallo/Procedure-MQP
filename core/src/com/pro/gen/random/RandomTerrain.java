package com.pro.gen.random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.RectBuilders;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/27/2015.
 */
public class RandomTerrain {

    private Pixmap pixmap;
    private Texture texture;
    private ArrayList<RectBuilders> rectangles;

    private static final int TERRAIN_WIDTH = 6000;

    public RandomTerrain(){
        rectangles = new ArrayList<RectBuilders>();
        initPixmap();
        createRectangles();
        createTriangleCuts();
        texture = new Texture(pixmap);
        pixmap.dispose();

    }

    public void createRectangles(){
        int currentX = 0;
        while (currentX < TERRAIN_WIDTH){
            int width = MathUtils.random(30, 200);
            int y;
            //if(currentX<300 || currentX>1000){
            //    y = MathUtils.random(200, 720);
          //  } else if (currentX<500 || currentX>700){
          //      y = MathUtils.random(300, 720);
          //  } else {
                y = MathUtils.random(500, 720);
           // }



            pixmap.fillRectangle(currentX, 0, width, y);

            if(rectangles.isEmpty()){
                rectangles.add(new RectBuilders(currentX, 0, width, Constants.VIRTUAL_HEIGHT-y, RectBuilders.UP));
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
        //Texture tex = Assets.getInstance().getTexture(Constants.RECTANGLE);
        //tex.getTextureData().prepare();
       // Pixmap p = tex.getTextureData().consumePixmap();

        pixmap = new Pixmap(TERRAIN_WIDTH, Constants.VIRTUAL_HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.drawPixmap(p, 0,0,tex.getWidth(),tex.getHeight(),0,0,Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
       // p.dispose();
        pixmap.setBlending(Pixmap.Blending.None);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixmap.setColor(Color.CLEAR);
    }

    public Texture getTexture() {
        return texture;
    }

}
