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
    public final static int GRAD_HAT = 3;
    public final static int BASEBALL_HAT = 4;
    public final static int PAPER_HAT = 5;
    public final static int SANTA_HAT = 6;
    public final static int WPI_GRAD_HAT = 7;
    public final static int CHAMP_HAT = 8;




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
        if(rankLevel > 0) {
            this.hatID = MathUtils.random(1, 4);
            this.image = getRandomHat();
            this.effect = MathUtils.random(10);
            this.powerAmt = getPowerAmt(rankLevel);
            this.hatColor = ColorHelper.generateGoodColor();
            hatImage = new TintedImage(image, hatColor);
            setHatSize();
            addActor(hatImage);
        }
        else {
            this.hatID = 0;
            this.image = Pic.Pixel;
            this.hatColor = Color.CLEAR;
            this.effect = -1;
            this.powerAmt = 0;
            hatImage = new TintedImage(image, hatColor);
        }

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x,y);
        hatImage.setPosition(0,0);
    }

    @Override
    public void setSize(float width, float height) {
        hatImage.setSize(width,height);
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
            case GRAD_HAT:
                hatImage.setSize(130,100);
                hatImage.setPosition(460, 335);
                break;
            case PAPER_HAT:
                hatImage.setSize(140,150);
                hatImage.setPosition(455, 330);
                break;
            case SANTA_HAT:
                hatImage.setSize(140,150);
                hatImage.setPosition(455, 330);
                break;
            case WPI_GRAD_HAT:
                hatImage.setSize(130,100);
                hatImage.setPosition(460, 335);
                break;
            case BASEBALL_HAT:
                hatImage.setSize(200,120);
                hatImage.setPosition(435, 301);
                break;
            case CHAMP_HAT:
                hatImage.setSize(200,120);
                hatImage.setPosition(435, 301);
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
            case GRAD_HAT:
                return Pic.Grad_Hat;
            case PAPER_HAT:
                return Pic.Paper_Hat;
            case SANTA_HAT:
                return Pic.Santa_Hat;
            case WPI_GRAD_HAT:
                return Pic.WPI_Grad_Hat;
            case BASEBALL_HAT:
                return Pic.Baseball_Hat;
            case CHAMP_HAT:
                return Pic.Champ_Hat;
            default:
                return null;
        }
    }

    public String getEffectDescription(){
        switch (effect){
            case LASER_BOOST:
                return "Increase Laser Boost by "+powerAmt+"%";
            case COLOR_RED:
                return "Makes all enemies behave\nas a Red Planet";
            case COLOR_BLUE:
                return "Makes all enemies behave\nas a Blue Planet";
            case COLOR_GREEN:
                return "Makes all enemies behave\nas a Green Planet";
            case COLOR_ORANGE:
                return "Makes all enemies behave\nas a Orange Planet";
            case COLOR_PURPLE:
                return "Makes all enemies behave\nas a Purple Planet";
            case COLOR_YELLOW:
                return "Makes all enemies behave\nas a Yellow Planet";
            case KO:
                return "Chance for One-Hit KO "+powerAmt+"%";
            case FIRST_SLOT:
                return "Laser Boost when on a planet\nin 1st slot by "+powerAmt+"%";
            case SECOND_SLOT:
                return "Laser Boost when on a planet\nin 2nd slot by "+powerAmt+"%";
            case THIRD_SLOT:
                return "Laser Boost when on a planet\nin 3rd slot by "+powerAmt+"%";
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
