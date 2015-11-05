package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 11/5/2015.
 */
public class BackgroundSky extends Group {
    TintedImage[] gradient, stars;
    private static final int GRAD_AMT = 8;

    public BackgroundSky(String background) {
        this(background, Color.WHITE);
    }

    public BackgroundSky(String background, Color color) {
        gradient = new TintedImage[GRAD_AMT];
        stars = new TintedImage[20];
        color = ColorHelper.lighten(new Color(color), 0.2f);
        for(int i = 0; i < gradient.length; i++){
            gradient[i] = new TintedImage(background, new Color(color));
            gradient[i].setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT / GRAD_AMT);
            gradient[i].setY(gradient[i].getHeight() * i);
            addActor(gradient[i]);
            color = ColorHelper.darken(color, 0.03f);
        }

        for(int i = 0; i < stars.length; i++){
            stars[i] = new TintedImage(Constants.CIRCLE_SMALL, ColorHelper.generateLightColor());
            float size = MathUtils.random(2f, 6f);
            stars[i].setSize(size, size);
            stars[i].setPosition(MathUtils.random(1, Constants.VIRTUAL_WIDTH - 7), MathUtils.random(Constants.VIRTUAL_HEIGHT / 2, Constants.VIRTUAL_HEIGHT - 7));
            addActor(stars[i]);
        }

        setPosition(0,0);
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }
}
