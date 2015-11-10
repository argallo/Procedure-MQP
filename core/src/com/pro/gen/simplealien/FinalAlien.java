package com.pro.gen.simplealien;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Animation;
import com.pro.gen.weapons.Weapon;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/2/2015.
 */
public class FinalAlien extends Group {

    ArrayList<CharacterPart> characterParts;
    int currentDirection;
    Weapon weapon;
    boolean isRed = false;
    //TintedImage hitboxes;

    public FinalAlien(float width, float height, RandomAlien randomAlien){
        setSize(width, height);
        currentDirection = Animation.CALM;
        randomAlien.generateAlien(width, height);
        characterParts = randomAlien.getParts();
        for(CharacterPart part : characterParts){
            addActor(part);
        }
       // hitboxes = new TintedImage(Constants.RECTANGLE, Color.RED);
       // hitboxes.setSize(getWidth(), getHeight());
       // addActor(hitboxes);
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
        for(CharacterPart part : characterParts){
            part.directionChanged(currentDirection);
        }
    }

    public void attachWeapon(Weapon weapon){
        this.weapon = weapon;
        addActor(weapon);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void red(){
        if(!isRed) {
            isRed = true;
            this.addAction(Actions.sequence(new Action() {
                @Override
                public boolean act(float delta) {
                    for (CharacterPart part : characterParts) {
                        part.red();
                    }
                    return true;
                }
            }, Actions.delay(0.1f), new Action() {
                @Override
                public boolean act(float delta) {
                    for (CharacterPart part : characterParts) {
                        part.unRed();
                    }
                    isRed = false;
                    return true;
                }
            }));
        }

    }

}
