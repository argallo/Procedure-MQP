package com.pro.gen.weapons;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 11/6/2015.
 */
public abstract class Weapon extends TintedImage{

    Bullet bullet;

    public Weapon(String imageName, Color tint, Bullet bullet) {
        super(imageName, tint);
        this.bullet = bullet;
    }

    public abstract void fire();

}
