package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Legs {

    /*
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
*/

    public static AlienParts Legs(int width, int height, Color color){
        AlienParts leg = new AlienParts(Constants.ALIEN_LEG, color);
        leg.setSize(Math.round(width*0.8f), Math.round(width*1.2f));
        leg.setPosition(width/2-leg.getWidth()/2,height-leg.getHeight());
        return leg;
    }


}
