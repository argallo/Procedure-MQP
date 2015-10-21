package com.pro.gen.simplealien;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.AnimationList;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/3/2015.
 */
public class Bodys {


    String[] bodyImages = new String[]{Constants.CIRCLE, Constants.RECTANGLE, Constants.CURVERECT};


    public static CharacterPart simpleBody(float width, float height){
        float bodyWidth = MathUtils.random(width*0.8f, width);
        float bodyHeight = MathUtils.random(height*0.5f, height*0.6f);
        float bodyX = width/2-bodyWidth/2;
        float bodyY = (height*0.75f)-bodyHeight;


        TintedImage bodyImage = new TintedImage(Constants.BULDGE, ColorHelper.generateGoodColor());
        CharacterPart body = new CharacterPart(bodyImage, bodyWidth, bodyHeight, bodyX, bodyY, new ArrayList<CharacterPart>(), AnimationList.Float(3f, 20f));
        return body;
    }


}
