package com.pro.gen.simplealien;

/**
 * Created by Gallo on 11/10/2015.
 */
public class EnemyAlien extends FinalAlien {

    public EnemyAlien(float width, float height, RandomAlien randomAlien) {
        super(width, height, randomAlien);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        //setX(getX()*100*delta);
    }
}
