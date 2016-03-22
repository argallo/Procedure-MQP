package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Tint;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/15/2015.
 */
public class StarMap extends Group {

    private boolean pause = false;
    private boolean speedBoost = false;
    private float endSpeed = 0.5f;
    private float StarSpeed = 0.5f;

    private int animation;
    private ArrayList<TintedImage> stars;


    public StarMap(int animation,int starSize, Item item){
        this(animation, starSize, item, 0);
    }

    public StarMap(int animation,int starSize, Item item, float avoidDiameter){
        this.animation = animation;
        RandomPlacement rp = new RandomPlacement(item, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, starSize, avoidDiameter);
        initStarMap(rp);
    }


    private void initStarMap(RandomPlacement rp){
        stars = new ArrayList<TintedImage>();
        for(int i = 0; i < rp.getSize(); i++) {
            int starType = MathUtils.random(2);
            TintedImage star;
            switch(starType){
                case 0:
                    star = new TintedImage(rp.getName(), Tint.STAR_WHITE);
                    break;
                case 1:
                    star = new TintedImage(rp.getName(), Tint.STAR_BLUE);
                    break;
                case 2:
                    star = new TintedImage(rp.getName(), Tint.STAR_RED);
                    break;
                default:
                    star = null;
            }
            star.setSize(rp.getWidth(i), rp.getHeight(i));
            star.setPosition(rp.getX(i), rp.getY(i));
            stars.add(star);
            addActor(star);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(speedBoost){
            StarSpeed+=0.1f;
            if(StarSpeed>endSpeed)
                speedBoost = false;
        }
        if(!pause) {
            if (animation == 1) {
                throughSpace();
            } else if (animation == 2) {
                leftToRight();
            }
        }
    }

    private void leftToRight(){
        for(TintedImage star: stars) {
            float speed = StarSpeed+ star.getWidth()/4;
            star.setPosition(star.getX()+speed,star.getY());
            if(star.getX()>Constants.VIRTUAL_WIDTH){
                randomStar(star, -100, 100, 0, Constants.VIRTUAL_HEIGHT);
            }
        }
    }

    private void throughSpace(){
        for(TintedImage star: stars) {
            float rise = (star.getY() - Constants.VIRTUAL_HEIGHT/2);
            float run =  (star.getX() - Constants.VIRTUAL_WIDTH/2);
            float mag = (float)Math.sqrt((rise*rise) + (run*run));
            float speed = StarSpeed* star.getWidth()/15;
            star.setPosition(star.getX()+(run/mag)*speed,star.getY()+(rise/mag)*speed);
            if(outOfBounds(star)){
                randomStar(star, Constants.VIRTUAL_WIDTH/2-200, Constants.VIRTUAL_WIDTH/2+200,
                        Constants.VIRTUAL_HEIGHT/2 -200, Constants.VIRTUAL_HEIGHT/2+200);
            }
        }
    }

    private boolean outOfBounds(TintedImage star){
        if(star.getX() < -12 || star.getX() > Constants.VIRTUAL_WIDTH ||
                star.getY() < -12 || star.getY() > Constants.VIRTUAL_HEIGHT)
            return true;
        return false;
    }

    private void randomStar(TintedImage star, float minX, float maxX, float minY, float maxY){
        star.setPosition(MathUtils.random(minX, maxX), MathUtils.random(minY,maxY));
    }

    public void pause(){
        pause = true;
    }

    public void unpause(){
        pause = false;
    }

    public void setStarSpeed(float starSpeed) {
        StarSpeed = starSpeed;
    }

    public float getStarSpeed() {
        return StarSpeed;
    }

    public ArrayList<TintedImage> getStars() {
        return stars;
    }

    public void increaseSpeed(float endSpeed){
        speedBoost = true;
        this.endSpeed = endSpeed;
    }

}


