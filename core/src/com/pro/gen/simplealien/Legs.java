package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Legs {


    public static AlienParts simpleLegs(float width, float height){
        float legWidth = MathUtils.random(width * 0.16f, width * 0.2f);
        float legHeight = MathUtils.random(height*0.3f, height*0.4f);
        float legX = width/2-legWidth/2;
        float legY = (height*0.3f)-legHeight;

        Color color = ColorHelper.generateGoodColor();

        // change this later to return legs individually
        AlienParts rightLeg = new AlienParts(Constants.BULDGE, color);

        AlienParts leftLeg = new AlienParts(Constants.BULDGE, color);
        leftLeg.setSize(legWidth, legHeight);
        leftLeg.setPosition(-40, 0);

        rightLeg.addSubPart(leftLeg);
        rightLeg.setSize(legWidth, legHeight);
        rightLeg.setPosition(legX+20, legY);

        return rightLeg;
    }



}
