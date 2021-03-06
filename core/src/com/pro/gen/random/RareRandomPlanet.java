package com.pro.gen.random;

import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.worldcomponents.Hat;

/**
 * Created by Gallo on 3/27/2016.
 */
public class RareRandomPlanet extends RandomPlanet {

    private boolean rareEyes, hasHat;
    int highRank;

    public RareRandomPlanet(int lowRank, int highRank, boolean rareEyes, boolean hasHat) {
        super(lowRank, highRank);
        this.hasHat = hasHat;
        this.rareEyes = rareEyes;
        this.highRank = highRank;
    }

    @Override
    public PlanetEyes getPlanetEyes() {
        return new PlanetEyes(rareEyes);
    }

    @Override
    public Hat getHat() {
        if(hasHat) {
            return new Hat(highRank);
        } else {
            return new Hat(0);
        }
    }
}
