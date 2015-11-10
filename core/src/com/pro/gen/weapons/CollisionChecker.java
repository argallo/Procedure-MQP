package com.pro.gen.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.simplealien.FinalAlien;

/**
 * Created by Gallo on 11/6/2015.
 */
public class CollisionChecker {

    Group actor;

    public CollisionChecker(Group actor){
        this.actor = actor;
    }
    
    public boolean collided(Actor collider){
        if(actor.localToStageCoordinates(new Vector2(0,0)).x < collider.localToStageCoordinates(new Vector2(0,0)).x+collider.getWidth() && actor.localToStageCoordinates(new Vector2(0,0)).x+actor.getWidth()> collider.localToStageCoordinates(new Vector2(0,0)).x){
            //if(actor.getY() < collider.getY()+collider.getHeight() && actor.getY()+actor.getHeight() > collider.getY()){
            ((FinalAlien)actor).red();
                return true;
           // }
        }
        return false;
    }

}
