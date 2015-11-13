package com.pro.gen.simplealien;

import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Heads {

    String[] headImages = new String[]{Constants.CIRCLE, Constants.RECTANGLE, Constants.CURVERECT};


    public static AlienParts simpleHead(float width, float height){

        AlienParts head = new AlienParts(Constants.CIRCLE, ColorHelper.generateGoodColor());
        head.setSize(width, width);
        head.setPosition(0, height - height / 3);

        AlienParts[] eyes = Eyes.CircleEyes(head.getX(), head.getY(), head.getWidth(), head.getHeight());

        head.addSubPart(eyes[0]);
        head.addSubPart(eyes[1]);
        head.addSubPart(Mouth.getRandomMouth(width, height));



        //head.setAnimation(AnimationList.None());


        //ArrayList<CharacterPart> headComponents = Eyes.twoCircleEye(width, height / 3);
        //headComponents.addAll(Mouth.getRandomMouth(width, height));
        //CharacterPart head = new CharacterPart(headImage, width, width, 0, height-height/3, headComponents, AnimationList.Float(3f, 20f));
        //CharacterPart head = new CharacterPart(headImage, width, width, 0, height-height/3, headComponents, AnimationList.None());
        return head;
    }



}
