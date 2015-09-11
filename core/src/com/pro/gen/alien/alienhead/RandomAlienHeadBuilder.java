package com.pro.gen.alien.alienhead;

/**
 * Created by Gallo on 9/11/2015.
 */
public class RandomAlienHeadBuilder implements AlienHeadBuilder {

    private AlienHead alienHead;

    public RandomAlienHeadBuilder(){
        //pass in an alien head shape/color?
        //or have an alienhead factory that then chooses the base and attr given random variables
        alienHead = new AlienHead();
    }

    @Override
    public void buildAlienEyes() {

    }

    @Override
    public void buildAlienMouth() {

    }

    @Override
    public void buildAlienHatorHair() {

    }

    @Override
    public void buildAlienEars() {

    }

    @Override
    public AlienHead getAlienHead() {
        return this.alienHead;
    }
}
