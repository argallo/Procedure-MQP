package com.pro.gen.simplealien;

import com.pro.gen.components.AnimationList;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Heads {

    String[] headImages = new String[]{Constants.CIRCLE, Constants.RECTANGLE, Constants.CURVERECT};


    public static CharacterPart simpleHead(float width, float height){
        TintedImage headImage = new TintedImage(Constants.CIRCLE, ColorHelper.generateGoodColor());
        ArrayList<CharacterPart> headComponents = Eyes.twoCircleEye(width, height / 3);
        headComponents.addAll(Mouth.getRandomMouth(width, height));
        //CharacterPart head = new CharacterPart(headImage, width, width, 0, height-height/3, headComponents, AnimationList.Float(3f, 20f));
        CharacterPart head = new CharacterPart(headImage, width, width, 0, height-height/3, headComponents, AnimationList.None());
        return head;
    }



}
