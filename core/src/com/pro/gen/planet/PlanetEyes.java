package com.pro.gen.planet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo-Desktop on 3/12/2016.
 */
public class PlanetEyes extends Group {

    private float planetWidth = 400;
    private float planetHeight = 400;

    private TintedImage leftWhiteEye;
    private TintedImage leftEye;
    private TintedImage leftBlackImage;
    private TintedImage leftGlare;
    private  TintedImage rightWhiteEye;
    private TintedImage rightEye;
    private TintedImage rightBlackImage;
    private TintedImage rightGlare;

    private float whiteSize;
    private float whiteX;
    private float whiteY;
    private float eyeSize;
    private float eyeX;
    private float eyeY;
    private float blackSize;
    private float blackX;
    private float blackY;
    private float glareWidth;
    private float glareHeight;
    private float glareX;
    private float glareY;
    private float rwePosition;
    private String image;
    private Color eyeColor;

    private float blinkDelay;

    //When loaded from preferences
    public PlanetEyes(float whiteSize, float whiteX, float whiteY, float eyeSize, float eyeX, float eyeY, float blackSize, float blackX, float blackY, float glareWidth, float glareHeight,
                      float glareX, float glareY, float rwePosition, String image, Color eyeColor){
        this.whiteSize = whiteSize;
        this.whiteX = whiteX;
        this.whiteY = whiteY;
        this.eyeSize = eyeSize;
        this.eyeX = eyeX;
        this.eyeY = eyeY;
        this.blackSize = blackSize;
        this.blackX = blackX;
        this.blackY = blackY;
        this.glareHeight = glareHeight;
        this.glareWidth = glareWidth;
        this.glareX = glareX;
        this.glareY = glareY;
        this.rwePosition = rwePosition;
        this.image = image;
        this.eyeColor = eyeColor;
        init();

    }

    //constructor for generating new eyes
    public PlanetEyes(){
        whiteSize = MathUtils.random((planetWidth/2) * 0.45f, (planetWidth/2) * 0.70f);
        whiteX = MathUtils.random((planetWidth/2)*0.0f, (planetWidth/2)*0.2f);;
        whiteY = MathUtils.random((planetHeight /2)*0.65f, (planetHeight /2)*0.85f);;

        eyeSize = MathUtils.random(whiteSize*0.6f, whiteSize*0.7f);
        eyeX = whiteSize/2-eyeSize/2;
        eyeY = whiteSize/2-eyeSize/2;

        blackSize = MathUtils.random(eyeSize*0.6f, eyeSize*0.7f);
        blackX = MathUtils.random(eyeSize*0.4f-blackSize*0.4f, eyeSize*0.6f-blackSize*0.6f);
        blackY = MathUtils.random(eyeSize*0.4f-blackSize*0.4f, eyeSize*0.6f-blackSize*0.6f);

        glareWidth = MathUtils.random(blackSize*0.16f, blackSize*0.7f);
        glareHeight = MathUtils.random(blackSize*0.16f, blackSize*0.7f);
        glareX = MathUtils.random(glareWidth*0.2f, blackSize*0.9f);
        glareY = MathUtils.random(blackSize * 0.05f, blackSize*0.8f);

        eyeColor = ColorHelper.generateGoodColor();
        rwePosition = MathUtils.random(130, 180);

        image = Pic.Circle_Large;
        //Circle, cylinder, solar_planet, triangle, sun, title bar, healthbar,

        init();
    }

    public void init(){
        leftWhiteEye = new TintedImage(image, Color.WHITE);
        leftWhiteEye.setSize(whiteSize, whiteSize);
        leftWhiteEye.setPosition(0, 0);

        leftEye = new TintedImage(image, eyeColor);
        leftEye.setSize(eyeSize, eyeSize);
        leftEye.setPosition(leftWhiteEye.getX() + eyeX, leftWhiteEye.getY() + eyeY);

        leftBlackImage = new TintedImage(image, Color.BLACK);
        leftBlackImage.setSize(blackSize, blackSize);
        leftBlackImage.setPosition(leftEye.getX() + blackX, leftEye.getY() + blackY);

        leftGlare = new TintedImage(image, Color.WHITE);
        leftGlare.setSize(glareWidth, glareHeight);
        leftGlare.setPosition(leftBlackImage.getX() + glareX, leftBlackImage.getY() + glareY);

        rightWhiteEye = new TintedImage(image, Color.WHITE);
        rightWhiteEye.setSize(whiteSize, whiteSize);
        rightWhiteEye.setPosition(rwePosition, 0);

        rightEye = new TintedImage(image, eyeColor);
        rightEye.setSize(eyeSize, eyeSize);
        rightEye.setPosition(rightWhiteEye.getX() + eyeX, rightWhiteEye.getY() + eyeY);

        rightBlackImage = new TintedImage(image, Color.BLACK);
        rightBlackImage.setSize(blackSize, blackSize);
        rightBlackImage.setPosition(rightEye.getX() + blackX, rightEye.getY() + blackY);

        rightGlare = new TintedImage(image, Color.WHITE);
        rightGlare.setSize(glareWidth, glareHeight);
        rightGlare.setPosition(rightBlackImage.getX() + glareX, rightBlackImage.getY() + glareY);


        addActor(leftWhiteEye);
        addActor(leftEye);
        addActor(leftBlackImage);
        addActor(leftGlare);
        addActor(rightWhiteEye);
        addActor(rightEye);
        addActor(rightBlackImage);
        addActor(rightGlare);

        setEyePosition();

        leftWhiteEye.setOrigin(leftWhiteEye.getWidth()/2, leftWhiteEye.getHeight()/2);
        leftEye.setOrigin(leftEye.getWidth()/2, leftEye.getHeight()/2);
        leftBlackImage.setOrigin(leftBlackImage.getWidth()/2, leftBlackImage.getHeight()/2);
        leftGlare.setOrigin(leftGlare.getWidth()/2, leftGlare.getHeight()/2);
        rightWhiteEye.setOrigin(rightWhiteEye.getWidth()/2, rightWhiteEye.getHeight()/2);
        rightEye.setOrigin(rightEye.getWidth()/2, rightEye.getHeight()/2);
        rightBlackImage.setOrigin(rightBlackImage.getWidth()/2, rightBlackImage.getHeight()/2);
        rightGlare.setOrigin(rightGlare.getWidth()/2, rightGlare.getHeight()/2);

        animate();

    }
    
    public void animate(){
        blinkDelay = MathUtils.random(0.5f,4f);
        leftWhiteEye.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        leftEye.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        leftBlackImage.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        leftGlare.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        rightWhiteEye.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        rightEye.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        rightBlackImage.addAction(Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out)));
        rightGlare.addAction( Actions.sequence(Actions.delay(blinkDelay),Actions.scaleBy(0, -1, 0.2f, Interpolation.exp5In), Actions.scaleBy(0, 1, 0.3f, Interpolation.exp5Out), new Action() {
            @Override
            public boolean act(float delta) {
                animate();
                return true;
            }
        }));

    }
    
    


    public void resizeEyes(float width, float height){
        leftWhiteEye.setSize(leftWhiteEye.getWidth()*width/planetWidth, leftWhiteEye.getHeight()*height/planetHeight);
        leftEye.setSize(leftEye.getWidth()*width/planetWidth, leftEye.getHeight()*height/planetHeight);
        leftBlackImage.setSize(leftBlackImage.getWidth()*width/planetWidth, leftBlackImage.getHeight()*height/planetHeight);
        leftGlare.setSize(leftGlare.getWidth()*width/planetWidth, leftGlare.getHeight()*height/planetHeight);
        rightWhiteEye.setSize(rightWhiteEye.getWidth()*width/planetWidth, rightWhiteEye.getHeight()*height/planetHeight);
        rightEye.setSize(rightEye.getWidth()*width/planetWidth, rightEye.getHeight()*height/planetHeight);
        rightBlackImage.setSize(rightBlackImage.getWidth()*width/planetWidth, rightBlackImage.getHeight()*height/planetHeight);
        rightGlare.setSize(rightGlare.getWidth()*width/planetWidth, rightGlare.getHeight()*height/planetHeight);

        leftWhiteEye.setPosition(leftWhiteEye.getX() * width / planetWidth, leftWhiteEye.getY() * height / planetHeight);
        leftEye.setPosition(leftEye.getX() * width / planetWidth, leftEye.getY() * height / planetHeight);
        leftBlackImage.setPosition(leftBlackImage.getX() * width / planetWidth, leftBlackImage.getY() * height / planetHeight);
        leftGlare.setPosition(leftGlare.getX() * width / planetWidth, leftGlare.getY() * height / planetHeight);
        rightWhiteEye.setPosition(rightWhiteEye.getX() * width / planetWidth, rightWhiteEye.getY() * height / planetHeight);
        rightEye.setPosition(rightEye.getX() * width / planetWidth, rightEye.getY() * height / planetHeight);
        rightBlackImage.setPosition(rightBlackImage.getX() * width / planetWidth, rightBlackImage.getY() * height / planetHeight);
        rightGlare.setPosition(rightGlare.getX() * width / planetWidth, rightGlare.getY() * height / planetHeight);

        planetWidth = width;
        planetHeight = height;

        setEyePosition();
    }

    public void setEyePosition(){
        setPosition(planetWidth*3/2-leftWhiteEye.getWidth()-(rightWhiteEye.getX()-leftWhiteEye.getWidth())/2, planetHeight/2);
    }


    public float getWhiteSize() {
        return whiteSize;
    }

    public float getWhiteX() {
        return whiteX;
    }

    public float getWhiteY() {
        return whiteY;
    }

    public float getEyeSize() {
        return eyeSize;
    }

    public float getEyeX() {
        return eyeX;
    }

    public float getEyeY() {
        return eyeY;
    }

    public float getBlackSize() {
        return blackSize;
    }

    public float getBlackX() {
        return blackX;
    }

    public float getBlackY() {
        return blackY;
    }

    public float getGlareWidth() {
        return glareWidth;
    }

    public float getGlareHeight() {
        return glareHeight;
    }

    public float getGlareX() {
        return glareX;
    }

    public float getGlareY() {
        return glareY;
    }

    public float getRwePosition() {
        return rwePosition;
    }

    public String getImage() {
        return image;
    }

    public Color getEyeColor() {
        return eyeColor;
    }
}
