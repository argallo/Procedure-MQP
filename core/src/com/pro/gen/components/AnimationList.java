package com.pro.gen.components;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.simplealien.CharacterPart;

/**
 * Created by Gallo on 10/3/2015.
 */
public class AnimationList {


    public static Animation Float(final float duration, final float distance){
        return new Animation() {
            @Override
            public void act(float delta, CharacterPart part) {
                deltaStartTime += delta;
                if(deltaStartTime>=duration) {
                    direction = !direction;
                    deltaStartTime = 0;
                }
                if(direction){
                    part.setY(part.getY() + ((distance / duration) * delta));
                } else {
                    part.setY(part.getY() - ((distance / duration) * delta));
                }
            }
        };
    }

    public static Animation EyeLook(final float duration, final float distance){
        return new Animation() {
            float counter = 0;
            boolean moving = true;
            boolean sign = true;
            float timeDelay = MathUtils.random(1f, 3f);
            @Override
            public void act(float delta, CharacterPart part) {
                deltaStartTime += delta;
                if(deltaStartTime>=duration) {
                    moving = false;
                    if((counter+=delta) > timeDelay) {
                        timeDelay = MathUtils.random(0.5f, 4f);
                        direction = !direction;
                        deltaStartTime = 0;
                        counter = 0;
                        moving = true;
                        if(direction)
                            sign = !sign;
                    }
                }
                if(direction && moving){
                    //part.setY(part.getY() - ((distance / duration) * delta));
                    if(sign) {
                        part.setX(part.getX() - ((distance / duration) * delta));
                    } else {
                        part.setX(part.getX() + ((distance / duration) * delta));
                    }
                } else if(!direction && moving){
                   //part.setY(part.getY() + ((distance / duration) * delta));
                    if(sign) {
                        part.setX(part.getX() + ((distance / duration) * delta));
                    } else {
                        part.setX(part.getX() - ((distance / duration) * delta));
                    }
                }
            }
        };
    }

    public static Animation None(){
        return new Animation() {
            @Override
            public void act(float delta, CharacterPart part) {

            }
        };
    }



}
