package com.pro.gen.alien;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.alien.alienarms.AlienArms;
import com.pro.gen.alien.alienbody.AlienBody;
import com.pro.gen.alien.alienfactories.AlienArmsFactory;
import com.pro.gen.alien.alienfactories.AlienBodyFactory;
import com.pro.gen.alien.alienfactories.AlienFactory;
import com.pro.gen.alien.alienfactories.AlienLegsFactory;
import com.pro.gen.alien.alienhead.AlienHeadEngineer;
import com.pro.gen.alien.alienhead.RandomAlienHeadBuilder;
import com.pro.gen.alien.alienlegs.AlienLegs;

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
        alien.setAlienHead(alienHeadEngineer.getAlienHead());
    }

    @Override
    public void buildAlienBody() {
        AlienFactory alienBodyFactory = new AlienBodyFactory();
        AlienBody alienBody = (AlienBody)alienBodyFactory.generateFactoryItem(MathUtils.random());
        alien.setAlienBody(alienBody);
    }

    @Override
    public void buildAlienArms() {
        AlienFactory alienArmsFactory = new AlienArmsFactory();
        AlienArms alienArms = (AlienArms)alienArmsFactory.generateFactoryItem(MathUtils.random());
        alien.setAlienArms(alienArms);
    }

    @Override
    public void buildAlienLegs() {
        AlienFactory alienLegsFactory = new AlienLegsFactory();
        AlienLegs alienLegs = (AlienLegs)alienLegsFactory.generateFactoryItem(MathUtils.random());
        alien.setAlienLegs(alienLegs);
    }

    @Override
    public void createConnections() {
        alien.getAlienLegs().connectTo(alien.getAlienBody().getLegConnection());
        alien.getAlienArms().connectTo(alien.getAlienBody().getArmConnection());
        alien.getAlienBody().connectTo(alien.getAlienHead().getBodyConnection());
    }

    @Override
    public Alien getAlien() {
        return this.alien;
    }
}
