package com.pro.gen.aliens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Animation;
import com.pro.gen.components.TintedImage;
import com.pro.gen.weapons.Weapon;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/12/2015.
 */
public class Alien extends TintedImage{

    private final static float WIDTH = 100;
    private final static float HEIGHT = 250;
    int currentDirection;
    boolean isRed = false;

    ArrayList<AlienParts> alienParts;
    Weapon weapon;
    //Shield shield;

    public Alien(Color tint, AlienBuilder alienBuilder) {
        super( tint);
        setSize(WIDTH, HEIGHT);
        //alienParts = new ArrayList<AlienParts>();
        alienParts = alienBuilder.build(WIDTH, HEIGHT);
    }

    public void attachWeapon(Weapon weapon){
        this.weapon = weapon;
    }


    public void red(int hitAmt){
        if(!isRed) {
            isRed = true;
            this.addAction(Actions.sequence(new Action() {
                @Override
                public boolean act(float delta) {
                    setTint(Color.RED);
                    //for (CharacterPart part : characterParts) {
                    //    part.red();
                    // }
                    return true;
                }
            }, Actions.delay(0.1f), new Action() {
                @Override
                public boolean act(float delta) {
                    setPreviousColor();
                    // for (CharacterPart part : characterParts) {
                    //      part.unRed();
                    // }
                    isRed = false;
                    return true;
                }
            }));
        }

    }

    public void ragdoll(){
        //addAction(Actions.parallel(Actions.moveTo(-getY(), getX(), 1f, Interpolation.exp10In), Actions.rotateBy(90, 1f, Interpolation.exp10In)));
    }



    @Override
    public void act(float delta) {
        super.act(delta);
        for(AlienParts part : alienParts){
            part.act(delta);
        }
        if(weapon != null){
            weapon.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       // long startTime = TimeUtils.nanoTime();
        super.draw(batch, parentAlpha);
        for(AlienParts part : alienParts){
            part.draw(batch, parentAlpha, getX(), getY(), getScaleX(), getScaleY());
        }
        if(weapon != null){
            weapon.draw(batch, parentAlpha);
        }
        //long elapsedTime = TimeUtils.timeSinceNanos(startTime);
       // Gdx.app.log("", String.valueOf(elapsedTime));
    }

    @Override
    public void setTint(Color tint) {
        super.setTint(tint);
        for(AlienParts part : alienParts){
            part.setTint(tint);
        }
    }


    public void switchDirections(){
        if(currentDirection == Animation.LEFT){
            currentDirection = Animation.RIGHT;
        } else if(currentDirection == Animation.RIGHT){
            currentDirection = Animation.LEFT;
        }
        directionChanged();
    }

    public void switchDirections(int direction){
        if(direction != currentDirection){
            currentDirection = direction;
            directionChanged();
        }
    }

    public void directionChanged(){
        for(AlienParts part : alienParts){
            part.directionChanged(currentDirection);
        }
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
