package com.pro.gen.simplealien;

/**
 * Created by Gallo on 11/10/2015.
 */
public class EnemyAlien extends FinalAlien {

    HealthBar healthBar;
    private boolean isdead = false;

    public EnemyAlien(float width, float height, RandomAlien randomAlien) {
        super(width, height, randomAlien);
        healthBar = new HealthBar(200);
        healthBar.addToActor(this);
        healthBar.setPosition(5, getHeight()+50);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(!isdead) {
            setX(getX() - 50 * delta);
        }
    }

    @Override
    public void red(int hitAmt) {
        super.red(hitAmt);
        healthBar.lowerHealth(hitAmt);
        if(healthBar.depleated()){
            isdead = true;
            ragdoll();
        }
    }

    public boolean isdead() {
        return isdead;
    }
}
