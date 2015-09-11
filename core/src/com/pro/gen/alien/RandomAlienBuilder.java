package com.pro.gen.alien;

import com.pro.gen.alien.alienhead.AlienHeadEngineer;
import com.pro.gen.alien.alienhead.RandomAlienHeadBuilder;

/**
 * Created by Gallo on 9/11/2015.
 */
public class RandomAlienBuilder implements AlienBuilder {
    private Alien alien;

    public RandomAlienBuilder(){
        alien = new Alien();
    }

    @Override
    public void buildAlienHead() {
        AlienHeadEngineer alienHeadEngineer = new AlienHeadEngineer(new RandomAlienHeadBuilder());
        alienHeadEngineer.makeAlienHead();
        //something like that?
        alien.addActor(alien.getAlienHead());
        alien.setAlienHead(alienHeadEngineer.getAlienHead());
    }

    @Override
    public void buildAlienBody() {

    }

    @Override
    public void buildAlienArms() {

    }

    @Override
    public void buildAlienLegs() {

    }

    @Override
    public Alien getAlien() {
        return this.alien;
    }
}
