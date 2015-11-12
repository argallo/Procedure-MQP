package com.pro.gen.simplealien;

import com.pro.gen.weapons.CollisionChecker;

/**
 * Created by Gallo on 11/10/2015.
 */
public class EnemyAlien extends FinalAlien {

    HealthBar healthBar;
    CollisionChecker checker;
    private boolean isdead = false;

    public EnemyAlien(float width, float height, RandomAlien randomAlien, CollisionChecker checker) {
        super(width, height, randomAlien);
        this.checker = checker;
        healthBar = new HealthBar(200);
        //healthBar.addToActor(this);
        healthBar.setPosition(5, getHeight()+50);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(this.getStage().getCamera().frustum.boundsInFrustum(getX(), getY(), 0 ,getWidth(),getHeight(), 0)){
            checker.collided(this); // probably should move effects of this being true to out here
        }
        if(!isdead) {
            //setX(getX() - 50 * delta);
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
