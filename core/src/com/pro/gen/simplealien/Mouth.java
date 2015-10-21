package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.AnimationList;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Gallo on 10/20/2015.
 */
public class Mouth {

    public static ArrayList<CharacterPart> getRandomMouth(float width, float height){
        switch (MathUtils.random(1,3)){
            case 1:
                return simpleCircleMouth(width, height);
            case 2:
                return simpleCurveTeethMouth(width, height);
            case 3:
                return simpleCurvRectMouth(width, height);
            default: return null;
        }
    }

    public static ArrayList<CharacterPart> simpleCurvRectMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        float mouthWidth = MathUtils.random(width * 0.35f, width * 0.45f);
        float mouthHeight = MathUtils.random(height * 0.05f, height * 0.10f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.05f, height * 0.20f)-mouthHeight/2;

        CharacterPart mouth = new CharacterPart(new TintedImage(Constants.CURVERECT, color), mouthWidth, mouthHeight, mouthX, mouthY, new ArrayList<CharacterPart>(), AnimationList.None());
        return new ArrayList<CharacterPart>(Arrays.asList(mouth));
    }

    public static ArrayList<CharacterPart> simpleCircleMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        float mouthWidth = MathUtils.random(width * 0.15f, width * 0.35f);
        float mouthHeight = MathUtils.random(width * 0.05f, width * 0.40f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.02f, height * 0.20f)-mouthHeight/2;

        CharacterPart mouth = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), mouthWidth, mouthHeight, mouthX, mouthY, new ArrayList<CharacterPart>(), AnimationList.None());
        return new ArrayList<CharacterPart>(Arrays.asList(mouth));
    }

    public static ArrayList<CharacterPart> simpleCurveTeethMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        Color teethColor = ColorHelper.generateLightColor();
        float mouthWidth = MathUtils.random(width * 0.35f, width * 0.45f);
        float mouthHeight = MathUtils.random(height * 0.02f, height * 0.08f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.06f, height * 0.20f)-mouthHeight/2;

        float teethHeight = MathUtils.random(mouthHeight * 0.85f, mouthHeight * 1.2f);
        float teethWidth = MathUtils.random(teethHeight * 0.85f, teethHeight * 1.2f);
        float teethX =  MathUtils.random(mouthWidth/2 * 0.35f, mouthWidth/2 * 0.85f);
        float teethy = mouthY;

        CharacterPart mouth = new CharacterPart(new TintedImage(Constants.CURVERECT, color), mouthWidth, mouthHeight, mouthX, mouthY, new ArrayList<CharacterPart>(), AnimationList.None());
        CharacterPart leftTooth = new CharacterPart(new TintedImage(Constants.TRIANGLE, teethColor), teethWidth, teethHeight, (mouthX+mouthWidth/2) - teethX, teethy, new ArrayList<CharacterPart>(), AnimationList.None());
        CharacterPart rightTooth = new CharacterPart(new TintedImage(Constants.TRIANGLE, teethColor), teethWidth, teethHeight, (mouthX+mouthWidth/2) + teethX - teethWidth, teethy, new ArrayList<CharacterPart>(), AnimationList.None());

        return new ArrayList<CharacterPart>(Arrays.asList(mouth,leftTooth,rightTooth));
    }

}
