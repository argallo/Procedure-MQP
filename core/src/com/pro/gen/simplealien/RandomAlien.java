package com.pro.gen.simplealien;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/3/2015.
 */
public class RandomAlien {

    float width, height;
    ArrayList<CharacterPart> character;

    String[] bodyList = new String[]{Constants.CIRCLE};
    String[] headList = new String[]{Constants.CIRCLE};
    String[] legsList = new String[]{Constants.CYLINDER};


    public void generateAlien(float width, float height){
        this.width = width;
        this.height = height;
        character = new ArrayList<CharacterPart>();
        buildBody();
    }

    public ArrayList<CharacterPart> getParts(){
        return character;
    }

    private void buildBody(){
        int selected = MathUtils.random(bodyList.length-1);
        character.add(Bodys.simpleBody(width, height));
        buildHead();
    }

    private void buildHead(){
        int selected = MathUtils.random(headList.length-1);
        character.add(Heads.simpleHead(width, height));
        buildLegs();
    }

    private void buildLegs(){
        int selected = MathUtils.random(legsList.length-1);
        character.add(0,Legs.simpleLegs(width, height));
        buildArms();
    }

    private void buildArms(){
        character.add(Arms.simpleArms(width, height));
    }



}
