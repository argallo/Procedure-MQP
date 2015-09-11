package com.pro.gen.alien;

/**
 * Created by Gallo on 9/11/2015.
 */
public interface AlienBuilder {

    void buildAlienHead();
    void buildAlienBody();
    void buildAlienArms();
    void buildAlienLegs();
    void createConnections();
    Alien getAlien();


}
