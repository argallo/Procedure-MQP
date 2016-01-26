package com.pro.gen.znewmqp;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 1/12/2016.
 */
public class Ship extends TintedImage {

    private boolean direction = true;

    public Ship(String image){
        super(image);
    }


    @Override
    public void act(float delta) {

        if(direction) {
            setY(getY() + MathUtils.random(0.1f, 0.3f));
        } else {
            setY(getY() - MathUtils.random(0.1f, 0.3f));
        }
        if(MathUtils.random(0f, 1f)>0.993 || getY()<250 || getY()>350){
            direction = !direction;
        }
    }
}
