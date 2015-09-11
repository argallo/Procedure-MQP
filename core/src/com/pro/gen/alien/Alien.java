package com.pro.gen.alien;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.alien.alienarms.AlienArms;
import com.pro.gen.alien.alienbody.AlienBody;
import com.pro.gen.alien.alienhead.AlienHead;
import com.pro.gen.alien.alienlegs.AlienLegs;

/**
 * Created by Gallo on 9/11/2015.
 */
public class Alien extends Group implements AlienPlan {

    private AlienHead alienHead;
    private AlienBody alienBody;
    private AlienArms alienArms;
    private AlienLegs alienLegs;

    @Override
    public void setAlienHead(AlienHead alienHead) {
        this.alienHead = alienHead;
    }

    @Override
    public void setAlienBody(AlienBody alienBody) {
        this.alienBody = alienBody;
    }

    @Override
    public void setAlienArms(AlienArms alienArms) {
        this.alienArms = alienArms;
    }

    @Override
    public void setAlienLegs(AlienLegs alienLegs) {
        this.alienLegs = alienLegs;
    }

    public AlienArms getAlienArms() {
        return alienArms;
    }

    public AlienBody getAlienBody() {
        return alienBody;
    }

    public AlienHead getAlienHead() {
        return alienHead;
    }

    public AlienLegs getAlienLegs() {
        return alienLegs;
    }

}
