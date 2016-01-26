package com.pro.gen.aliens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Animation;
import com.pro.gen.components.TintedImage;
import com.pro.gen.weapons.CollisionChecker;
import com.pro.gen.weapons.Weapon;

/**
 * Created by Gallo on 11/12/2015.
 */
public class Alien extends TintedImage{

    int currentDirection;
    boolean isRed = false;
    Weapon weapon;

    public Alien(TextureRegion alienImage, CollisionChecker checker) {
        super(alienImage, Color.WHITE);
    }


    public void red(int hitAmt){
        if(!isRed) {
            isRed = true;
            this.addAction(Actions.sequence(new Action() {
                @Override
                public boolean act(float delta) {
                    setTint(Color.RED);
                    return true;
                }
            }, Actions.delay(0.1f), new Action() {
                @Override
                public boolean act(float delta) {
                    setPreviousColor();
                    isRed = false;
                    return true;
                }
            }));
        }

    }

    public void ragdoll(){
        addAction(Actions.rotateBy(90, 1f, Interpolation.exp10In));
    }

    public void switchDirections(){
        if(currentDirection == Animation.LEFT){
            currentDirection = Animation.RIGHT;
        } else if(currentDirection == Animation.RIGHT){
            currentDirection = Animation.LEFT;
        }
    }

    public void switchDirections(int direction){
            currentDirection = direction;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }


    @Override
    public void act(float delta) {
        if(weapon!=null) {
            weapon.setPosition(this.getX()+70, 300);
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(weapon!=null)
            weapon.draw(batch, parentAlpha);
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon(){
        return weapon;
    }

}
