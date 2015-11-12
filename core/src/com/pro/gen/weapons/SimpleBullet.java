package com.pro.gen.weapons;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.Animation;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 11/9/2015.
 */
public class SimpleBullet extends Bullet {

    public SimpleBullet() {
        super(Constants.RECTANGLE, Color.RED, Animation.RIGHT, 0, 0, 15); //fix later
    }


}
