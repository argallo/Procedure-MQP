package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/5/2015.
 */
public class Eyes {

/*
    public static ArrayList<CharacterPart> simpleCircleEye(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        CharacterPart leftEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), width/3, width/3, width/2-50, height*0.8f, new ArrayList<CharacterPart>(), AnimationList.None());
        CharacterPart rightEye = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), width/3, width/3, width/2+50, height*0.8f, new ArrayList<CharacterPart>(), AnimationList.None());
        return new ArrayList<CharacterPart>(Arrays.asList(leftEye, rightEye));
    }
*/

    /**
     *
     float whiteSize = MathUtils.random(width*0.35f, width*0.45f);
     float whiteX = MathUtils.random(width*0.0f, width*0.2f) + headX;
     float whiteY = MathUtils.random(height*0.65f, height*0.85f) + headY;

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
     */

/*

    public static AlienParts[] CircleEyes(float headX, float headY, float width, float height){
        float whiteSize = MathUtils.random(width*0.35f, width*0.45f);
        float whiteX = MathUtils.random(width*0.0f, width*0.2f);
        float whiteY = MathUtils.random(height*0.65f, height*0.85f);

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

        AlienParts leftGlare = new AlienParts(Constants.CIRCLE_SMALL, Color.WHITE);
        leftGlare.setPosition(glareX, glareY);
        leftGlare.setSize(glareWidth, glareHeight);

        AlienParts leftBlackImage = new AlienParts(Constants.CIRCLE_SMALL, Color.BLACK);
        leftBlackImage.addSubPart(leftGlare);
        leftBlackImage.setSize(blackSize, blackSize);
        leftBlackImage.setPosition(blackX, blackY);
        //leftBlackImage.setAnimation(AnimationList.EyeLook(0.3f, 10));

        AlienParts leftEye = new AlienParts(Constants.CIRCLE_SMALL, color);
        leftEye.addSubPart(leftBlackImage);
        leftEye.setSize(eyeSize, eyeSize);
        leftEye.setPosition(eyeX, eyeY);

        AlienParts leftWhiteEye = new AlienParts(Constants.CIRCLE_SMALL, Color.WHITE);
        leftWhiteEye.addSubPart(leftEye);
        leftWhiteEye.setSize(whiteSize, whiteSize);
        leftWhiteEye.setPosition(width / 2 - whiteSize - whiteX, whiteY);

        AlienParts rightWhiteEye = new AlienParts(Constants.CIRCLE_SMALL, Color.WHITE);
        rightWhiteEye.addSubPart(AlienParts.Clone(leftEye));
        rightWhiteEye.setSize(whiteSize, whiteSize);
        rightWhiteEye.setPosition(width/2+whiteX, whiteY);

        return new AlienParts[]{leftWhiteEye, rightWhiteEye};
    }
*/

    public static AlienParts Eye(int width, int height, Color color){
        int whiteSize = MathUtils.random(Math.round(width * 0.5f), Math.round(width * 0.52f));

        AlienParts eye = new AlienParts(Constants.ALIEN_EYE, color);
        eye.setSize(whiteSize, whiteSize);
        eye.setPosition(width/2-whiteSize/2,height/2-whiteSize/2+7);
        return eye;
    }

}
