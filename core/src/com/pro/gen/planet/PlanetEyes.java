package com.pro.gen.planet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo-Desktop on 3/12/2016.
 */
public class PlanetEyes extends Group {

    private EyePreset eyePreset;
    private float size, distance, pupilSize;
    private Color color;

    private Group leftEye, rightEye;


    public PlanetEyes(){

    }

    public PlanetEyes(RandomEye randomEye){
        this(randomEye.getEyePreset(), randomEye.getSize(), randomEye.getColor(), randomEye.getDistance(), randomEye.getPupilSize());
    }

    public PlanetEyes(EyePreset eyePreset, float size, Color color, float distance, float pupilSize){
        this.eyePreset = eyePreset;
        this.size = size;
        this.color = color;
        this.distance = distance;
        this.pupilSize = pupilSize;

        eyePreset.setColor(color);
        eyePreset.setPupilSize(pupilSize);
        eyePreset.setSize(size);

        leftEye = eyePreset.generateEye();
        rightEye = eyePreset.generateEye();

        rightEye.setPosition(distance, 0);

    }






}
