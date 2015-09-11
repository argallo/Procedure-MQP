package com.pro.gen.alien.alienhead;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.alien.alienfactories.AlienEarsFactory;
import com.pro.gen.alien.alienfactories.AlienEyesFactory;
import com.pro.gen.alien.alienfactories.AlienFactory;
import com.pro.gen.alien.alienfactories.AlienHatorHairFactory;
import com.pro.gen.alien.alienfactories.AlienMouthFactory;

/**
 * Created by Gallo on 9/11/2015.
 */
public class RandomAlienHeadBuilder implements AlienHeadBuilder {

    private AlienHead alienHead;

    public RandomAlienHeadBuilder(){
        alienHead = new AlienHead();
    }

    @Override
    public void buildAlienEyes() {
        AlienFactory alienEyesFactory = new AlienEyesFactory();
        AlienEyes alienEyes = (AlienEyes)alienEyesFactory.generateFactoryItem(MathUtils.random());
        alienHead.addActor(alienEyes);
        alienHead.setAlienEyes(alienEyes);
    }

    @Override
    public void buildAlienMouth() {
        AlienFactory alienMouthFactory = new AlienMouthFactory();
        AlienMouth alienMouth = (AlienMouth)alienMouthFactory.generateFactoryItem(MathUtils.random());
        alienHead.addActor(alienMouth);
        alienHead.setAlienMouth(alienMouth);
    }

    @Override
    public void buildAlienHatorHair() {
        AlienFactory alienHatorHairFactory = new AlienHatorHairFactory();
        AlienHatorHair alienHatorHair = (AlienHatorHair)alienHatorHairFactory.generateFactoryItem(MathUtils.random());
        alienHead.addActor(alienHatorHair);
        alienHead.setAlienHatorHair(alienHatorHair);
    }

    @Override
    public void buildAlienEars() {
        AlienFactory alienEarsFactory = new AlienEarsFactory();
        AlienEars alienEars = (AlienEars)alienEarsFactory.generateFactoryItem(MathUtils.random());
        alienHead.addActor(alienEars);
        alienHead.setAlienEars(alienEars);
    }

    @Override
    public AlienHead getAlienHead() {
        return this.alienHead;
    }
}
