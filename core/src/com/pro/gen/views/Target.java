package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.Button;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.worldcomponents.TargetSystem;

/**
 * Created by Gallo-Desktop on 3/23/2016.
 */
public class Target extends Button {

    private float lifeSpan;
    private float lifeCounter;
    private TargetSystem targetSystem;

    public Target(String mainImage, Color backgroundColor, TargetSystem targetSystem) {
        super(mainImage, backgroundColor);
        this.targetSystem = targetSystem;
        lifeSpan = MathUtils.random(0.65f,1.0f);
    }

    public void updateLife(float delta){
        lifeCounter+=delta;
        if(lifeCounter>lifeSpan){
            targetSystem.removeSelf(this);
        }
    }
}
