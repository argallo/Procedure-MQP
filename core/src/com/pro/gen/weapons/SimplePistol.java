package com.pro.gen.weapons;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.Animation;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 11/6/2015.
 */
public class SimplePistol extends Weapon {

    CollisionChecker checker;
    public SimplePistol(CollisionChecker checker) {
        super(Constants.PISTOL, Color.WHITE, new SimpleBullet());
        this.checker = checker;
        setSize(80, 40);
        setPosition(53, 60);
    }

    @Override
    public void fire() {
        Bullet bullet = new Bullet(Constants.RECTANGLE, Color.RED, Animation.RIGHT, getX() + getWidth() - 6, getY() + getHeight() / 2+4, 15);
        checker.addCollider(bullet);
        this.addActor(bullet);
    }
}
