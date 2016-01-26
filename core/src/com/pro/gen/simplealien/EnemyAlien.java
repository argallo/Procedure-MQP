package com.pro.gen.simplealien;

import com.pro.gen.aliens.Alien;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.GroundView;
import com.pro.gen.weapons.CollisionChecker;

/**
 * Created by Gallo on 11/10/2015.
 */
public class EnemyAlien extends Alien {

    HealthBar healthBar;
    CollisionChecker checker;
    private boolean isdead = false;


    public EnemyAlien(CollisionChecker checker, GroundView groundView) {
        super(Assets.getInstance().getTextureRegion(Constants.ALIEN), checker);
        this.checker = checker;
        healthBar = new HealthBar(200);
        healthBar.addToActor(groundView);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(this.getStage().getCamera().frustum.boundsInFrustum(getX(), getY(), 0 ,getWidth(),getHeight(), 0)){
            checker.collided(this); // probably should move effects of this being true to out here
        }
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
            healthBar.remove();
            ragdoll();
        }
    }

    public boolean isdead() {
        return isdead;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        healthBar.setPosition(x+getWidth()/2-healthBar.backgroundBar.getWidth()/2, y+getHeight()+50);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        healthBar.setX(x+getWidth()/2-healthBar.backgroundBar.getWidth()/2);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if(healthBar!=null)
            healthBar.setPosition(getX()+getWidth()/2-healthBar.backgroundBar.getWidth()/2, getY()+height+5);
    }
}
