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
    public static final Color PURPLE = new Color(102/255f, 95/255f, 110/255f, 1f);


    //Image Paths
    public static final String BLUE = "Background/blue.png";
    public static final String WHITE = "Background/white.png";
    public static final String SPLASH = "Background/splash.png";

    /* Large */
    public static final String CIRCLE_HOLE = "Objects/Large/white_circlehole_large.png";
    public static final String SHIP_BORDER = "Objects/Large/spacescreen_large.png";

    /* Medium */
    public static final String ABS_PLANET = "Objects/Medium/abs_planet_medium.png";
    public static final String RECTANGLE = "Objects/Medium/white_rectangle_medium.png";
    public static final String CIRCLE = "Objects/Medium/white_circle_medium.png";
    public static final String RING = "Objects/Medium/white_ring_medium.png";
    public static final String CIRCLE_SHADOW = "Objects/Medium/white_shadow_medium.png";
    public static final String CYLINDER = "Objects/Medium/white_cyl_medium.png";
    public static final String CURVERECT = "Objects/Medium/white_curverectangle_medium.png";
    public static final String BULDGE = "Objects/Medium/white_buldge_medium.png";
    public static final String SUN = "Objects/Medium/white_sun_medium.png";

    /* Small */
    public static final String TRIANGLE = "Objects/Small/white_triangle_small.png";
    public static final String LINE = "Objects/Small/white_line_small.png";

    /* X-Small */
    public static final String TWINKLE = "Objects/XSmall/white_twinkle_xsmall.png";
    public static final String CIRCLE_SMALL = "Objects/XSmall/white_circle_xsmall.png";



    public static final String[] APP_IMAGES = new String[]{

    };

    public static final String[] COMMON_IMAGES = new String[]{
            RECTANGLE, BLUE, CURVERECT, WHITE, CIRCLE, CIRCLE_SHADOW, CIRCLE_HOLE, CYLINDER, TWINKLE, CIRCLE_SMALL, TRIANGLE, BULDGE, SHIP_BORDER, ABS_PLANET, SUN, RING, LINE
    };

    public static final String[] SPLASH_IMAGES = new String[]{
            SPLASH
    };

    public static final String[] CREATE_ACCOUNT_IMAGES = new String[]{

    };

    public static final String[] CREATE_ALIEN_IMAGES = new String[]{

    };
}
