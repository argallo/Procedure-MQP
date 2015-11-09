package com.pro.gen.weapons;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.components.TintedGroup;

/**
 * Created by Gallo on 11/6/2015.
 */
public abstract class Weapon extends TintedGroup{

    public Weapon(String imageName, Color tint) {
        super(imageName, tint);
    }

    public abstract void fire();

}
