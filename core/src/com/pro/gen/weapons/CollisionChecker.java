package com.pro.gen.weapons;

import com.badlogic.gdx.math.Vector2;
import com.pro.gen.simplealien.EnemyAlien;
import com.pro.gen.twod.levelpieces.LevelEnemies;

/**
 * Created by Gallo on 11/6/2015.
 */
public class CollisionChecker {

    private LevelEnemies levelEnemies;

    public CollisionChecker(LevelEnemies levelEnemies){
        this.levelEnemies = levelEnemies;
    }
    
    public boolean collided(Bullet collider){
        for(int i = 0; i < levelEnemies.getEnemyAliens().length; i++) {
            EnemyAlien[] enemyAliens = levelEnemies.getEnemyAliens();
            if (enemyAliens[i].localToStageCoordinates(new Vector2(0, 0)).x < collider.localToStageCoordinates(new Vector2(0, 0)).x + collider.getWidth() &&
                    enemyAliens[i].localToStageCoordinates(new Vector2(0, 0)).x + enemyAliens[i].getWidth() > collider.localToStageCoordinates(new Vector2(0, 0)).x) {
                //if(actor.getY() < collider.getY()+collider.getHeight() && actor.getY()+actor.getHeight() > collider.getY()){
                if(!enemyAliens[i].isdead()) {
                    enemyAliens[i].red(collider.getDamage());
                    return true;
                }
                // }
            }
        }
        return false;
    }

}
