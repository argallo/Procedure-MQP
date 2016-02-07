package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 2/6/2016.
 */
public class CapsuleSpinner extends Group {

    private TintedImage background;
    private TintedImage selector;
    private CapsuleItem[] capsuleItem;
    private float speed = 0;
    private float decrement = 0.2f;

    private boolean isSpinning = false;


    public CapsuleSpinner(){
        background = new TintedImage(Pic.Pixel, Tint.MEDIUM_GRAY);
        selector = new TintedImage(Pic.Pixel, Color.YELLOW);
        capsuleItem = new CapsuleItem[50];

        background.setSize(Constants.VIRTUAL_WIDTH, 180);
        selector.setSize(5, 180);
        selector.setPosition(Constants.VIRTUAL_WIDTH/2, 0);

        addActor(background);

        for(int i = 0; i < capsuleItem.length; i++){
            capsuleItem[i] = new CapsuleItem();
            capsuleItem[i].setSize(240,160);
            capsuleItem[i].setPosition(((i+1)*-240), 5);
            capsuleItem[i].setVisible(false);
            addActor(capsuleItem[i]);
        }

        addActor(selector);

    }

    public void spin(){
        speed = 1500;
        decrement = 0.2f;
        isSpinning = true;

    }

    @Override
    public void act(float delta) {
        if(isSpinning){
            for(CapsuleItem item : capsuleItem){
                if(item.getX() > Constants.VIRTUAL_WIDTH || item.getX()+item.getWidth() < 0){
                    item.setVisible(false);
                } else {
                    item.setVisible(true);
                }
                item.setX(item.getX()+(delta*speed));
            }
            decrement += delta/2;
            speed -= decrement;
            if(speed< 0.1){
                isSpinning = false;
                speed = 0;
                getResult();
            }
        }
    }

    public void getResult(){

    }
}
