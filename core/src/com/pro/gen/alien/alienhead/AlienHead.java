package com.pro.gen.alien.alienhead;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienHead extends Group implements AlienHeadPlan {

    private AlienEyes alienEyes;
    private AlienMouth alienMouth;
    private AlienHatorHair alienHatorHair;
    private AlienEars alienEars;

    public AlienHead(){
        //build alienhead shape
    }


    @Override
    public void setAlienEyes(AlienEyes alienEyes) {
        this.alienEyes = alienEyes;
    }

    @Override
    public void setAlienMouth(AlienMouth alienMouth) {
        this.alienMouth = alienMouth;
    }

    @Override
    public void setAlienHatorHair(AlienHatorHair alienHatorHair) {
        this.alienHatorHair = alienHatorHair;
    }

    @Override
    public void setAlienEars(AlienEars alienEars) {
        this.alienEars = alienEars;
    }

    public AlienEars getAlienEars() {
        return alienEars;
    }

    public AlienEyes getAlienEyes() {
        return alienEyes;
    }

    public AlienHatorHair getAlienHatorHair() {
        return alienHatorHair;
    }

    public AlienMouth getAlienMouth() {
        return alienMouth;
    }
}
