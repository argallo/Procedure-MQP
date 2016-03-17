package com.pro.gen.planet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.ColorHelper;

/**
 * Created by Gallo-Desktop on 3/12/2016.
 */
public class RandomEye {

    private EyePreset eyePreset;
    private float size, distance, pupilSize;
    private Color color;

    public RandomEye(){
        eyePreset = new EyePreset(MathUtils.random(0,4));
        size = MathUtils.random(0, 1.0f);
        distance = MathUtils.random(0, 1.0f);
        pupilSize = MathUtils.random(0, 0.5f);
        color = ColorHelper.generateGoodColor();
    }

    public Color getColor() {
        return color;
    }

    public EyePreset getEyePreset() {
        return eyePreset;
    }

    public float getDistance() {
        return distance;
    }

    public float getPupilSize() {
        return pupilSize;
    }

    public float getSize() {
        return size;
    }
}
