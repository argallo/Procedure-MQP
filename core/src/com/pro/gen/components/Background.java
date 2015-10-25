package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 8/11/2015.
 */
public class Background extends TintedImage {

    public Background(String background) {
        this(background, Color.WHITE);
    }

    public Background(String background, Color color) {
        super(background, color);
        setPosition(0,0);
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }

}
