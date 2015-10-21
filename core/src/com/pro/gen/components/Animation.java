package com.pro.gen.components;

import com.pro.gen.simplealien.CharacterPart;

/**
 * Created by Gallo on 9/14/2015.
 */
public abstract class Animation {
    public static final int CALM = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int NONE = 3;

    public float deltaStartTime = 0;
    public boolean direction = true;

    public abstract void act(float delta, CharacterPart part);

}
