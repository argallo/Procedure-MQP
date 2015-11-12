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
public class Legs {


    public static CharacterPart simpleLegs(float width, float height){
        float legWidth = MathUtils.random(width * 0.16f, width * 0.2f);
        float legHeight = MathUtils.random(height*0.3f, height*0.4f);
        float legX = width/2-legWidth/2;
        float legY = (height*0.3f)-legHeight;


        Color color = ColorHelper.generateGoodColor();
        CharacterPart leftLeg = new CharacterPart(new TintedImage(Constants.BULDGE, color), legWidth, legHeight, -40, 0, new ArrayList<CharacterPart>(), AnimationList.None());
        //CharacterPart rightLeg = new CharacterPart(new TintedImage(Constants.BULDGE, color), legWidth, legHeight, legX+20, legY, new ArrayList<CharacterPart>(Arrays.asList(leftLeg)), AnimationList.Float(3f, 20f));
        CharacterPart rightLeg = new CharacterPart(new TintedImage(Constants.BULDGE, color), legWidth, legHeight, legX+20, legY, new ArrayList<CharacterPart>(Arrays.asList(leftLeg)), AnimationList.None());

        return rightLeg;
    }



}
