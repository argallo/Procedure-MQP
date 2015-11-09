package com.pro.gen.simplealien;

import com.badlogic.gdx.scenes.scene2d.Group;
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

    public FinalAlien(float width, float height, RandomAlien randomAlien){
        setSize(width, height);
        currentDirection = Animation.CALM;
        randomAlien.generateAlien(width, height);
        characterParts = randomAlien.getParts();
        for(CharacterPart part : characterParts){
            addActor(part);
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
}
