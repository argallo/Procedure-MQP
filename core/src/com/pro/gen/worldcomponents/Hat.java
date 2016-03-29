package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo-Desktop on 3/17/2016.
 */
public class Hat extends Group{

    public final static int LASER_BOOST = 0;
    public final static int COLOR_RED = 1;
    public final static int COLOR_BLUE = 2;
    public final static int COLOR_GREEN = 3;
    public final static int COLOR_ORANGE = 4;
    public final static int COLOR_PURPLE = 5;
    public final static int COLOR_YELLOW = 6;
    public final static int KO = 7;
    public final static int FIRST_SLOT = 8;
    public final static int SECOND_SLOT = 9;
    public final static int THIRD_SLOT = 10;


    public final static int TOP_HAT = 1;
    public final static int DURAG_HAT = 2;




    private String image;
    private int hatID;
    private Color hatColor;
    private int effect;
    private int powerAmt;
    private TintedImage hatImage;


    public Hat(String image, int hatID, Color hatColor, int effect, int powerAmt){
        this.image = image;
        this.hatID = hatID;
        this.hatColor = hatColor;
        this.powerAmt = powerAmt;
        this.effect = effect;
        hatImage = new TintedImage(image, hatColor);
        setHatSize();
        addActor(hatImage);
    }

    public Hat(int rankLevel){
        this.hatID = MathUtils.random(1,2);
        this.image = getRandomHat();
        this.hatColor = ColorHelper.generateGoodColor();
        this.effect = MathUtils.random(10);
        this.powerAmt = getPowerAmt(rankLevel);
        hatImage = new TintedImage(image, hatColor);
        setHatSize();
        addActor(hatImage);

    }

    public void setHatSize(){
        switch (hatID){
            case TOP_HAT:
                hatImage.setSize(200,60);
                hatImage.setPosition(425, 330);
                break;
            case DURAG_HAT:
                hatImage.setSize(250,90);
                hatImage.setPosition(355, 320);
                break;
        }
    }

    public void resizeHat(float width, float height){
        hatImage.setSize(hatImage.getWidth()*width/350, hatImage.getHeight()*height/350);
        hatImage.setPosition(hatImage.getX()*width/350, hatImage.getY()*height/350);

    }


    public int getPowerAmt(int rankLevel){
        if(rankLevel < 15) {
            if (effect > 0 && effect < 7) {
                return 0;
            } else if (effect == 7) {
                return MathUtils.random(1, 3);
            } else {
                return MathUtils.random(2, 6);
            }
        } else if (rankLevel >= 15 && rankLevel < 30) {
            if (effect > 0 && effect < 7) {
                return 0;
            } else if (effect == 7) {
                return MathUtils.random(4, 6);
            } else {
                return MathUtils.random(6, 15);
            }
        } else {
            if (effect > 0 && effect < 7) {
                return 0;
            } else if (effect == 7) {
                return MathUtils.random(6, 10);
            } else {
                return MathUtils.random(12, 30);
            }
        }
    }

    public String getRandomHat(){
        switch (hatID){
            case TOP_HAT:
                return Pic.Top_Hat;
            case DURAG_HAT:
                return Pic.DuRag_Hat;
            default:
                return null;
        }
    }

    public String getEffectDescription(){
        switch (effect){
            case LASER_BOOST:
                return "Increase Laser Boost";
            case COLOR_RED:
                return "Makes all enemies behave as a Red Planet";
            case COLOR_BLUE:
                return "Makes all enemies behave as a Blue Planet";
            case COLOR_GREEN:
                return "Makes all enemies behave as a Green Planet";
            case COLOR_ORANGE:
                return "Makes all enemies behave as a Orange Planet";
            case COLOR_PURPLE:
                return "Makes all enemies behave as a Purple Planet";
            case COLOR_YELLOW:
                return "Makes all enemies behave as a Yellow Planet";
            case KO:
                return "Chance for One-Hit KO";
            case FIRST_SLOT:
                return "Laser Boost when on a planet in 1nd slot";
            case SECOND_SLOT:
                return "Laser Boost when on a planet in 2nd slot";
            case THIRD_SLOT:
                return "Laser Boost when on a planet in 3nd slot";
        }
        return null;
    }

    //String image, int hatID, Color hatColor, int effect, int powerAmt

    public int getEffect() {
        return effect;
    }

    public int getPowerAmt() {
        return powerAmt;
    }

    public Color getHatColor() {
        return hatColor;
    }

    public String getImage() {
        return image;
    }

    public int getHatID() {
        return hatID;
    }

}
