package com.pro.gen.alien;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienEngineer {

    private AlienBuilder alienBuilder;

    public AlienEngineer(AlienBuilder alienBuilder){
        this.alienBuilder = alienBuilder;
    }


    public Alien getAlien(){
        return alienBuilder.getAlien();
    }

    public void makeAlien(){
        this.alienBuilder.buildAlienHead();
        this.alienBuilder.buildAlienBody();
        this.alienBuilder.buildAlienArms();
        this.alienBuilder.buildAlienLegs();
        this.alienBuilder.createConnections();
    }

}
