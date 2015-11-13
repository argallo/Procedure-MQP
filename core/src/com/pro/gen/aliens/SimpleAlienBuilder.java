package com.pro.gen.aliens;

import com.pro.gen.simplealien.Arms;
import com.pro.gen.simplealien.Bodys;
import com.pro.gen.simplealien.Heads;
import com.pro.gen.simplealien.Legs;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/12/2015.
 */
public class SimpleAlienBuilder implements AlienBuilder {

    private ArrayList<AlienParts> alienParts;

    public SimpleAlienBuilder(){
        alienParts = new ArrayList<AlienParts>();
    }

    public void buildHead(float width, float height){
        alienParts.add(Heads.simpleHead(width, height));
    }

    public void buildBody(float width, float height){
        alienParts.add(Bodys.simpleBody(width, height));
    }

    public void buildLegs(float width, float height){
        alienParts.add(Legs.simpleLegs(width, height));
    }

    public void buildArms(float width, float height){
        alienParts.add(Arms.simpleArms(width, height));
    }

    @Override
    public ArrayList<AlienParts> build(float width, float height) {
        buildLegs(width, height);
        buildBody(width, height);
        buildHead(width, height);
        buildArms(width, height);
        return alienParts;
    }


}
