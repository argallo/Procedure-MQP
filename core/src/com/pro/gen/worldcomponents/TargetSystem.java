package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.Target;

import java.util.ArrayList;

/**
 * Created by Gallo-Desktop on 3/23/2016.
 */
public class TargetSystem extends Group {

    private final static int MAX_AVA_TARGETS = 15;

    private ArrayList<Target> targets;
    private ArrayList<Target> removalList;
    private int targetCounter = 0;
    private Color playerTargetColor;
    private TintedImage myTarget;
    private TextLabel counterLabel;
    private int avaliableTargetCount = 0;
    private MiniGame miniGame;

    private boolean beginGame = false;
    private boolean finishUp = false;

    public TargetSystem(String playerTargetColor, MiniGame miniGame){
        this.miniGame = miniGame;
        this.playerTargetColor = getPlayerColor(playerTargetColor);
        targets = new ArrayList<Target>();
        removalList = new ArrayList<Target>();
        myTarget = new TintedImage(Pic.Target, getPlayerColor(playerTargetColor));
        myTarget.setSize(50,50);
        myTarget.setPosition(940,0);
        counterLabel = new TextLabel("x"+targetCounter);
        counterLabel.setPosition(1000,0);

        addActor(myTarget);
        addActor(counterLabel);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(beginGame&&!finishUp){
            if(targets.size() < 6) {
                generateTarget();
            }
        }
        for(Target target:targets){
            target.updateLife(delta);
        }
        removeFromList();
        if(finishUp&&targets.size() == 0){
            miniGame.targetsDestroyed();
        }
    }

    public void begin() {
        beginGame = true;
    }

    private void generateTarget(){
        Color targetColor = getRandomTargetColor();
        if(targetColor.equals(playerTargetColor)){
            avaliableTargetCount++;
            LogUtils.Log(avaliableTargetCount);
            if(avaliableTargetCount == MAX_AVA_TARGETS){
                finishUp = true;
            }
        }
        final Target target = new Target(Pic.Target, targetColor, this);
        float size = MathUtils.random(60,100);
        target.setSize(size, size);
        float[] xy = getRandomPosition(size);
        target.setPosition(xy[0],xy[1]);
        target.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                if (target.getTint().equals(playerTargetColor)) {
                    targetCounter++;
                } else {
                    if (targetCounter < 1) {
                        targetCounter = 0;
                    } else {
                        targetCounter--;
                    }
                }
                removeSelf(target);
                updateLabel();
                begin();
            }
        });
        addActor(target);
        targets.add(target);

    }

    private float[] getRandomPosition(float size){
        float x = MathUtils.random(20, getWidth() - size - 20);
        float y = MathUtils.random(20, getHeight() - size - 20);
        if(checkCollision(x,y,size)){
            return getRandomPosition(size);
        } else{
            return new float[]{x,y};
        }
    }

    private boolean checkCollision(float x, float y, float size){
        for(Target target:targets){
            if(x+size > target.getX() && x < target.getX()+target.getWidth()){
                if(y+size > target.getY() && y < target.getY()+target.getHeight()){
                    return true;
                }
            }
        }
        return false;
    }

    private Color getPlayerColor(String color) {
        if(color.equals("Blue")){
            return Tint.TARGET_BLUE;
        } else if(color.equals("Green")){
            return Tint.TARGET_GREEN;
        }else if(color.equals("Orange")){
            return Tint.TARGET_ORANGE;
        }else if(color.equals("Purple")){
            return Tint.TARGET_PURPLE;
        }else if(color.equals("Red")){
            return Tint.TARGET_RED;
        }else if(color.equals("Yellow")){
            return Tint.TARGET_YELLOW;
        }
        return null;
    }

    private Color getRandomTargetColor(){
        switch (MathUtils.random(5)){
            case 0:
                return Tint.TARGET_BLUE;
            case 1:
                return Tint.TARGET_GREEN;
            case 2:
                return Tint.TARGET_ORANGE;
            case 3:
                return Tint.TARGET_PURPLE;
            case 4:
                return Tint.TARGET_RED;
            case 5:
                return Tint.TARGET_YELLOW;
            default:
                return null;
        }
    }

    private void updateLabel(){
        counterLabel.setText("x"+targetCounter);
    }

    public void removeSelf(Target target){
        removeActor(target);
        listRemoval(target);
    }

    public void listRemoval(Target target){
        removalList.add(target);
    }

    public void removeFromList(){
        for(Target target:removalList){
            targets.remove(target);
        }
        removalList.clear();
    }

    public int getTargetCounter() {
        return targetCounter;
    }
}
