package com.pro.gen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;


/**
 * Created by Gallo on 8/11/2015.
 */
public class Constants {

    public static final int APP_WIDTH = Gdx.graphics.getWidth();
    public static final int APP_HEIGHT = Gdx.graphics.getHeight();

    //default Sizes
    public static final int VIRTUAL_WIDTH = 1280;
    public static final int VIRTUAL_HEIGHT = 720;

    public static final int GLOBE_REC_WIDTH = 1000;
    public static final int GLOBE_REC_HEIGHT = 500;

    //default Colors
    public static final Color UNIVERSE_BACKGROUND_COLOR = new Color(22/255f,37/255f,51/255f,1f);
    public static final Color STAR_WHITE = new Color(213/255f,213/255f,218/255f,1f);
    public static final Color STAR_PURPLE = new Color(110/255f,36/255f,185/255f,1f);
    public static final Color STAR_PURPLE_BRIGHT = new Color(173/255f,0,239/255f,1f);
    public static final Color PINK = new Color(240/255f, 87/255f, 109/255f, 1f);
    public static final Color ORANGE = new Color(240/255f, 148/255f, 87/255f, 1f);

    //Image Paths
    public static final String BLUE = "Background/blue.png";
    public static final String WHITE = "Background/white.png";
    public static final String SPLASH = "Background/splash.png";

   /* public static final String CIRCLE = "Objects/Large/white_circle_large.png";
    public static final String CIRCLE_HOLE = "Objects/Large/white_circlehole_large.png";
    public static final String CIRCLE_SHADOW = "Objects/Large/white_shadow_large.png";
    public static final String CYLINDER = "Objects/Large/white_cyl_large.png";
    public static final String TWINKLE = "Objects/Large/white_twinkle_large.png";

    public static final String CIRCLE = "Objects/Medium/white_circle_medium.png";
    public static final String CIRCLE_HOLE = "Objects/Large/white_circlehole_large.png";
    public static final String CIRCLE_SHADOW = "Objects/Medium/white_shadow_medium.png";
    public static final String CYLINDER = "Objects/Medium/white_cyl_medium.png";
    public static final String TWINKLE = "Objects/Medium/white_twinkle_medium.png";

    public static final String CIRCLE = "Objects/Small/white_circle_small.png";
    public static final String CIRCLE_HOLE = "Objects/Large/white_circlehole_large.png";
    public static final String CIRCLE_SHADOW = "Objects/Small/white_shadow_small.png";
    public static final String CYLINDER = "Objects/Small/white_cyl_small.png";
    public static final String TWINKLE = "Objects/Small/white_twinkle_small.png";
*/

    public static final String RECTANGLE = "Objects/Medium/white_rectangle_medium.png";
    public static final String CIRCLE = "Objects/Medium/white_circle_medium.png";
    public static final String CIRCLE_HOLE = "Objects/Large/white_circlehole_large.png";
    public static final String CIRCLE_SHADOW = "Objects/Medium/white_shadow_medium.png";
    public static final String CYLINDER = "Objects/Medium/white_cyl_medium.png";
    public static final String CURVERECT = "Objects/Medium/white_curverectangle_medium.png";
    public static final String TWINKLE = "Objects/XSmall/white_twinkle_xsmall.png";
    public static final String CIRCLE_SMALL = "Objects/XSmall/white_circle_xsmall.png";



    public static final String[] APP_IMAGES = new String[]{
            WHITE, CIRCLE, CIRCLE_SHADOW, CIRCLE_HOLE, CYLINDER, TWINKLE, CIRCLE_SMALL
    };

    public static final String[] COMMON_IMAGES = new String[]{
            RECTANGLE, BLUE, CYLINDER, CURVERECT
    };

    public static final String[] SPLASH_IMAGES = new String[]{
            SPLASH
    };

    public static final String[] CREATE_ACCOUNT_IMAGES = new String[]{

    };
}
