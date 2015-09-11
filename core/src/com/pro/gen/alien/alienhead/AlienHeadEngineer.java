package com.pro.gen.alien.alienhead;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienHeadEngineer {

    private AlienHeadBuilder alienHeadBuilder;

    public AlienHeadEngineer(AlienHeadBuilder alienHeadBuilder){
        this.alienHeadBuilder = alienHeadBuilder;
    }

    public AlienHead getAlienHead(){
        return this.alienHeadBuilder.getAlienHead();
    }

    public void makeAlienHead(){
        this.alienHeadBuilder.buildAlienEyes();
        this.alienHeadBuilder.buildAlienMouth();
        this.alienHeadBuilder.buildAlienHatorHair();
        this.alienHeadBuilder.buildAlienEars();
    }

}
