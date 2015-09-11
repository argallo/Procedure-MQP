package com.pro.gen.alien.alienfactories;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 9/11/2015.
 */

public class AlienHeadFactory extends AlienFactory {

    private final static int HEAD_TYPE_AMOUNT = 5;

    public enum HeadType {
        CIRCLE_HEAD, ROUNDED_SQUARE_HEAD, ROUNDED_TRIANGLE_HEAD, VERTICAL_OVAL_HEAD, GUMDROP_HEAD; //can add more

        public Group getHeadTypeImage(){
            Group group = new Group();
            switch (this){
                case CIRCLE_HEAD:
                    TintedImage head = new TintedImage(Constants.CIRCLE, ColorHelper.generateGoodColor());
                    group.addActor(head);
                    return group;
                case ROUNDED_SQUARE_HEAD:
                    return null;
                case ROUNDED_TRIANGLE_HEAD:
                    return null;
                case VERTICAL_OVAL_HEAD:
                    return null;
                case GUMDROP_HEAD:
                    return null;
                default:
                    return null;
            }
        }
    }

    @Override
    public Group generateFactoryItem(float seed) {
        return getHeadType(seed*HEAD_TYPE_AMOUNT);
    }

    public Group getHeadType(float seed){
        HeadType type;
        if(seed < 1){
            type = HeadType.CIRCLE_HEAD;
        } else if (seed < 2){
            type = HeadType.ROUNDED_SQUARE_HEAD;
        } else if (seed < 3){
            type = HeadType.ROUNDED_TRIANGLE_HEAD;
        } else if (seed < 4){
            type = HeadType.VERTICAL_OVAL_HEAD;
        } else {
            type = HeadType.GUMDROP_HEAD;
        }
        return type.getHeadTypeImage();
    }

}
