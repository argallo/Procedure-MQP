package com.pro.gen.simplealien;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Animation;
import com.pro.gen.components.AnimationList;
import com.pro.gen.components.TintedImage;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/2/2015.
 */
public class CharacterPart extends Group {

    TintedImage image;
    ArrayList<CharacterPart> subParts;
    int currentDirection;
    Animation animation;


    public CharacterPart(TintedImage image, float width, float height, float x, float y, ArrayList<CharacterPart> subParts, Animation animation){
        this.image = image;
        this.animation = animation;
        setSize(width, height);
        setPosition(x, y);
        this.image.setSize(getWidth(), getHeight());
        this.addActor(this.image);
        this.subParts = subParts;
        attachSubparts();
        currentDirection = Animation.CALM;
    }

    public CharacterPart(TintedImage image, float width, float height, float x, float y, ArrayList<CharacterPart> subParts){
        this(image, width, height, x, y, subParts, AnimationList.None());
    }

    public CharacterPart(TintedImage image, float width, float height, float x, float y){
        this(image, width, height, x, y, new ArrayList<CharacterPart>(), AnimationList.None());
    }

    private void attachSubparts(){
        for(CharacterPart part : subParts){
            this.addActor(part);
        }
    }

    public void directionChanged(int direction){
        currentDirection = direction;
        //change to other animations? YUP
        for(CharacterPart part : subParts){
            part.directionChanged(direction);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animation.act(delta, this);
    }

    public static CharacterPart Clone(CharacterPart characterPart){
        ArrayList<CharacterPart> newList = new ArrayList<CharacterPart>();
        for(CharacterPart part : characterPart.subParts){
            newList.add(CharacterPart.Clone(part));
        }

        return new CharacterPart(TintedImage.Clone(characterPart.image), characterPart.getWidth(),
                characterPart.getHeight(), characterPart.getX(), characterPart.getY(), newList, characterPart.animation);
    }

    public void red(){
        image.setTint(Color.RED);
        for(CharacterPart part : subParts){
            part.red();
        }
    }

    public void unRed(){
        image.setPreviousColor();
        for(CharacterPart part : subParts){
            part.unRed();
        }
    }

    /**
     * This will need to list all variables in json format with recursive call to all subparts.
     * @return
     */
    @Override
    public String toString() {
        return "";
    }
}
