package com.pro.gen.weapons;

import com.badlogic.gdx.math.Vector2;
import com.pro.gen.simplealien.EnemyAlien;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/6/2015.
 */
public class CollisionChecker {

    private ArrayList<Bullet> bullets;

    public CollisionChecker(){
        bullets = new ArrayList<Bullet>();
    }
    
    public boolean collided(EnemyAlien enemyAlien){
        for(int i = 0; i < bullets.size(); i++) {
            if (enemyAlien.localToStageCoordinates(new Vector2(0, 0)).x < bullets.get(i).localToStageCoordinates(new Vector2(0, 0)).x + bullets.get(i).getWidth() &&
                    enemyAlien.localToStageCoordinates(new Vector2(0, 0)).x + enemyAlien.getWidth() > bullets.get(i).localToStageCoordinates(new Vector2(0, 0)).x) {
                //if(actor.getY() < collider.getY()+collider.getHeight() && actor.getY()+actor.getHeight() > collider.getY()){
                if(!enemyAlien.isdead()) {
                    enemyAlien.red(bullets.get(i).getDamage());
                    bullets.remove(i).remove();
                    return true;
                }
                // }
            }
            if(bullets.get(i).localToStageCoordinates(new Vector2(0, 0)).x > bullets.get(i).getStage().getCamera().position.x+ Constants.VIRTUAL_WIDTH/2){
                bullets.remove(i).remove();
            }
        }
        return false;
    }

    public void addCollider(Bullet bullet){
        bullets.add(bullet);
    }

}
