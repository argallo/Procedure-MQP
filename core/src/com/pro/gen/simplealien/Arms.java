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
 * Created by Gallo on 10/3/2015.
 */
public class Arms {



    public static CharacterPart simpleArms(float width, float height){
        float armWidth = MathUtils.random(width * 0.16f, width * 0.2f);
        float armHeight = armWidth;
        float armX = width/2-armWidth/2;
        float armY = MathUtils.random(width * 0.8f, width * 1.1f);


        Color color = ColorHelper.generateGoodColor();
        CharacterPart leftArm = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), armWidth, armHeight, -140, 0, new ArrayList<CharacterPart>(), AnimationList.None());
        //CharacterPart rightArm = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), armWidth, armHeight, armX+70, armY, new ArrayList<CharacterPart>(Arrays.asList(leftArm)), AnimationList.Float(3f, 20f));
        CharacterPart rightArm = new CharacterPart(new TintedImage(Constants.CIRCLE_SMALL, color), armWidth, armHeight, armX+70, armY, new ArrayList<CharacterPart>(Arrays.asList(leftArm)), AnimationList.None());

        return rightArm;
    }



}
