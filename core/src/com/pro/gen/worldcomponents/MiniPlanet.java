package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.screens.SolarSystemScreen;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/24/2015.
 */
public class MiniPlanet extends Button {

    SolarSystemScreen solarSystemScreen;
    public final static float radiusX = 400;
    public final static float radiusY = 120;

    private float miniLoop = 0;
    private float radiusAdd = 0;
    private float speed = 1;
    TintedImage highlightSmall, highlightLarge;
    float small = 1.2f;
    float large = 1.5f;
    private boolean selected = false;

    public MiniPlanet(Color backgroundColor, SolarSystemScreen solarSystemScreen, float radiusAdd) {
        super(Constants.ABS_PLANET, backgroundColor);
        this.solarSystemScreen = solarSystemScreen;
        this.radiusAdd = radiusAdd;
        miniLoop = MathUtils.random(0f, 3f);
        speed = MathUtils.random(0.3f, 1.1f);
        setSize(1, 1);
        setOrigin(0.5f, 0.5f);
        createAction();
        highlightSmall = new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE);
        highlightLarge = new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE);
        highlightSmall.setAlpha(0.7f);
        highlightLarge.setAlpha(0.2f);
    }

    public void createAction() {
        this.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                //draw Animation?
                solarSystemScreen.planetClicked(MiniPlanet.this);
                setSelected(true);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        miniLoop += delta * speed;
        setPosition((radiusX + radiusAdd) * MathUtils.cos(miniLoop) + 640, (radiusY + radiusAdd) * MathUtils.sin(miniLoop) + 360);
        setScale((MathUtils.sin(miniLoop) - 1.5f) * -30f, (MathUtils.sin(miniLoop) - 1.5f) * -30f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(selected) {
            highlightSmall.setSize(getScaleX() * small, getScaleY() * small);
            highlightSmall.setPosition(getX() - highlightSmall.getWidth() / 2, getY() - highlightSmall.getHeight() / 2);
            highlightSmall.draw(batch, parentAlpha);

            highlightLarge.setSize(getScaleX() * large, getScaleY() * large);
            highlightLarge.setPosition(getX() - highlightLarge.getWidth() / 2, getY() - highlightLarge.getHeight() / 2);
            highlightLarge.draw(batch, parentAlpha);
        }
        super.draw(batch, parentAlpha);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
