package com.pro.gen.simplealien;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.aliens.AlienParts;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Bodys {


    String[] bodyImages = new String[]{Constants.CIRCLE, Constants.RECTANGLE, Constants.CURVERECT};


    public static AlienParts simpleBody(float width, float height){
        float bodyWidth = MathUtils.random(width*0.8f, width);
        float bodyHeight = MathUtils.random(height*0.5f, height*0.6f);
        float bodyX = width/2-bodyWidth/2;
        float bodyY = (height*0.75f)-bodyHeight;

        AlienParts body = new AlienParts(Constants.BULDGE, ColorHelper.generateGoodColor());
        body.setSize(bodyWidth, bodyHeight);
        body.setPosition(bodyX, bodyY);
        return body;
    }


}
