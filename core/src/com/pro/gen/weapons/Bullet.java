package com.pro.gen.weapons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.pro.gen.components.Animation;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 11/6/2015.
 */
public class Bullet extends TintedImage {

    CollisionChecker collisionChecker;
    private int direction;
    private int damage;
    private final static float SPEED = 950;
    float bx = Integer.MIN_VALUE;

    public Bullet(String imageName, Color tint, int direction, float x, float y, CollisionChecker collisionChecker, int damage) {
        super(imageName, tint);
        this.collisionChecker = collisionChecker;
        this.direction = direction;
        this.damage = damage;
        setSize(10, 6);
        setPosition(x, y);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if(!collisionChecker.collided(this)){
            if(bx-getCoordsX() == 0 || bx == Integer.MIN_VALUE) {
                setX(getX() + (direction == Animation.LEFT ? (-1 * SPEED * delta) : (SPEED * delta)));
            } else {
                setX(getX() + (2*(bx-getCoordsX())) + (direction == Animation.LEFT ? (-1 * SPEED * delta) : (SPEED * delta)));
            }
        } else {
            remove();
        }
        if(getX() > 1500){
            remove();
        }
        bx = getCoordsX();
    }


    public float getCoordsX(){
        return localToStageCoordinates(parentToLocalCoordinates(new Vector2(0,0))).x;
    }

    public int getDamage() {
        return damage;
    }
}
