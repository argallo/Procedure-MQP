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
 * Created by Gallo on 10/5/2015.
 */
public class Eyes {


    public static ArrayList<CharacterPart> simpleCircleEye(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        CharacterPart leftEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), width/3, width/3, width/2-50, height*0.8f, new ArrayList<CharacterPart>(), AnimationList.None());
        CharacterPart rightEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), width/3, width/3, width/2+50, height*0.8f, new ArrayList<CharacterPart>(), AnimationList.None());
        return new ArrayList<CharacterPart>(Arrays.asList(leftEye, rightEye));
    }

    public static ArrayList<CharacterPart> twoCircleEye(float width, float height){

        float whiteSize = MathUtils.random(width*0.35f, width*0.45f);
        float whiteX = MathUtils.random(width*0.0f, width*0.2f);;
        float whiteY = MathUtils.random(height*0.65f, height*0.85f);;

        float eyeSize = MathUtils.random(whiteSize*0.6f, whiteSize*0.7f);
        float eyeX = whiteSize/2-eyeSize/2;
        float eyeY = whiteSize/2-eyeSize/2;

        float blackSize = MathUtils.random(eyeSize*0.6f, eyeSize*0.7f);
        float blackX = MathUtils.random(eyeSize*0.4f-blackSize*0.4f, eyeSize*0.6f-blackSize*0.6f);
        float blackY = MathUtils.random(eyeSize*0.4f-blackSize*0.4f, eyeSize*0.6f-blackSize*0.6f);

        float glareWidth = MathUtils.random(blackSize*0.16f, blackSize*0.7f);
        float glareHeight = MathUtils.random(blackSize*0.16f, blackSize*0.7f);
        float glareX = MathUtils.random(glareWidth*0.2f, blackSize*0.9f);
        float glareY = MathUtils.random(blackSize*0.05f, blackSize*0.8f);

        Color color = ColorHelper.generateGoodColor();

        CharacterPart leftGlare = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE), glareWidth, glareHeight, glareX,glareY);

        CharacterPart leftBlackImage = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, Color.BLACK), blackSize, blackSize, blackX,blackY,
                new ArrayList<CharacterPart>(Arrays.asList(leftGlare)), AnimationList.EyeLook(0.3f, 10));

        CharacterPart leftEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), eyeSize, eyeSize, eyeX, eyeY,
                new ArrayList<CharacterPart>(Arrays.asList(leftBlackImage)), AnimationList.None());

        CharacterPart leftWhiteEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE), whiteSize, whiteSize, width/2-whiteSize-whiteX, whiteY,
                new ArrayList<CharacterPart>(Arrays.asList(leftEye)), AnimationList.None());

        CharacterPart rightWhiteEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE), whiteSize, whiteSize, width/2+whiteX, whiteY,
                new ArrayList<CharacterPart>(Arrays.asList(CharacterPart.Clone(leftEye))), AnimationList.None());

        return new ArrayList<CharacterPart>(Arrays.asList(leftWhiteEye, rightWhiteEye));
    }




}
