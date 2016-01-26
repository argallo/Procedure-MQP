package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Heads {

    String[] headImages = new String[]{Constants.CIRCLE, Constants.RECTANGLE, Constants.CURVERECT};


    public static AlienParts simpleHead(int width, int height, Color color){

        AlienParts head = new AlienParts(Constants.ALIEN_HEAD, color);
        head.setSize(width, Math.round(width*1.2f));
        head.setPosition(0, 0);

        head.addSubPart(Eyes.Eye(head.getWidth(), head.getHeight(), ColorHelper.generateGoodColor()));

        //head.addSubPart(Mouth.getRandomMouth(width, height));

        return head;
    }



}
