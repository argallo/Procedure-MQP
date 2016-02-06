package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 10/24/2015.
 */
public class MiniPlanet extends Button {

    private SolarSystem solarSystem;
    public final static float radiusX = 400;
    public final static float radiusY = 120;

    private float miniLoop = 0;
    private float radiusAdd = 0;
    private float speed = 1;
    private boolean acting = true;

    public MiniPlanet(Color backgroundColor, SolarSystem solarSystem, float radiusAdd) {
        super(Pic.Solar_Planet, backgroundColor);
        this.solarSystem = solarSystem;
        this.radiusAdd = radiusAdd;
        miniLoop = MathUtils.random(0f, 3f);
        speed = MathUtils.random(0.3f, 1.1f);
        setSize(1, 1);
        setOrigin(0.5f, 0.5f);
        createAction();
    }

    public void createAction() {
        this.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                //draw Animation?
                solarSystem.planetClicked(MiniPlanet.this);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(acting) {
            miniLoop += delta * speed;
            setPosition((radiusX + radiusAdd) * MathUtils.cos(miniLoop) + 640, (radiusY + radiusAdd) * MathUtils.sin(miniLoop) + 360);
            setScale((MathUtils.sin(miniLoop) - 1.5f) * -30f, (MathUtils.sin(miniLoop) - 1.5f) * -30f);
        }
    }

    public void moveToCenter(){
        acting = false;
        addAction(Actions.parallel(Actions.moveTo(640, 360, 1f, Interpolation.exp5In), Actions.scaleTo(100, 100, 1f, Interpolation.exp5In)));
    }
}
