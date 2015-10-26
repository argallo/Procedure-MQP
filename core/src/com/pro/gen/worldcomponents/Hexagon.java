package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/25/2015.
 */
public class Hexagon extends Group {

    TintedImage hexTop, hexBottom, hexBottomLeft, hexBottomRight, hexTopLeft, hexTopRight;
    float thickness;
    boolean animating = false;

    public Hexagon(Color color, float thickness){
        this.thickness = thickness;
        hexTop = new TintedImage(Constants.LINE, color);
        hexBottom = new TintedImage(Constants.LINE, color);
        hexBottomLeft = new TintedImage(Constants.LINE, color);
        hexBottomRight = new TintedImage(Constants.LINE, color);
        hexTopLeft = new TintedImage(Constants.LINE, color);
        hexTopRight = new TintedImage(Constants.LINE, color);

        setSizes();
        setOrigins();
        setAngles();
        setPositions();
        addActors();
        this.setOrigin(getWidth()/2, getHeight()/2);
        this.setRotation(90);
    }

    public void setOrigins(){
        hexTop.setOrigin(0, thickness / 2);
        hexBottom.setOrigin(0, thickness / 2);
        hexBottomLeft.setOrigin(0, thickness / 2);
        hexBottomRight.setOrigin(0, thickness / 2);
        hexTopLeft.setOrigin(0, thickness / 2);
        hexTopRight.setOrigin(0, thickness / 2);
    }


    public void setSizes(){
        if(!animating) {
            hexTop.setSize(this.getWidth() / 2, thickness);
            hexBottom.setSize(this.getWidth() / 2, thickness);
            hexBottomLeft.setSize(this.getWidth() / 2, thickness);
            hexBottomRight.setSize(this.getWidth() / 2, thickness);
            hexTopLeft.setSize(this.getWidth() / 2, thickness);
            hexTopRight.setSize(this.getWidth() / 2, thickness);
        }
    }

    public void setAngles(){
        hexTop.setRotation(0);
        hexBottom.setRotation(0);
        hexBottomLeft.setRotation(-60);
        hexBottomRight.setRotation(-120);
        hexTopLeft.setRotation(60);
        hexTopRight.setRotation(120);
    }

    public void setPositions(){
        //left side
        hexTopLeft.setPosition(0, getHeight() / 2);
        hexBottomLeft.setPosition(0, getHeight() / 2);

        //right side
        hexTopRight.setPosition(getWidth(), getHeight() / 2);
        hexBottomRight.setPosition(getWidth(), getHeight() / 2);

        //top-bottom
        hexTop.setPosition(getWidth() / 4, getHeight() / 2 + (float) Math.sqrt(3) * (getWidth() / 4));
        hexBottom.setPosition(getWidth() / 4, getHeight() / 2 - (float) Math.sqrt(3) * (getWidth() / 4));
    }

    public void addActors(){
        addActor(hexTop);
        addActor(hexBottom);
        addActor(hexBottomLeft);
        addActor(hexBottomRight);
        addActor(hexTopLeft);
        addActor(hexTopRight);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setSizes();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setPositions();
    }

    public void formHex(){
        Action action = new Action() {
            @Override
            public boolean act(float delta) {
                animating = true;

                hexTop.addAction(Actions.sequence(Actions.moveTo(hexTop.getX()+hexTop.getWidth(),hexTop.getY()+hexTop.getHeight()),Actions.sizeTo(0, thickness), Actions.moveTo(hexTop.getX(),hexTop.getY(), 0.5f),Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                hexTopLeft.addAction(Actions.sequence(Actions.sizeTo(0, thickness), Actions.delay(0.5f), Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                hexBottomLeft.addAction(Actions.sequence(Actions.sizeTo(0, thickness), Actions.delay(1f), Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                hexBottom.addAction(Actions.sequence(Actions.sizeTo(0, thickness), Actions.delay(1.5f), Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                hexBottomRight.addAction(Actions.sequence(Actions.sizeTo(0, thickness), Actions.delay(2f), Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                hexTopRight.addAction(Actions.sequence(Actions.sizeTo(0, thickness), Actions.delay(2.5f), Actions.sizeBy(Hexagon.this.getWidth() / 2, thickness, 0.5f)));
                return true;
            }
        };

        this.addAction(Actions.sequence(Actions.visible(true), action, Actions.delay(3f), new Action() {
            @Override
            public boolean act(float delta) {
                animating = false;
                return true;
            }
        }));
    }


}
