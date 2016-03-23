package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 10/26/2015.
 */
public class MiniPlanetSystem extends Group {

    private MiniPlanet miniPlanet;
    private TintedImage orbit;
    private TextLabel rank;



    public MiniPlanetSystem(Color color, SolarSystem solarSystem, int radiusAdd, int rankNum){
        this.rank = new TextLabel("Rank "+rankNum, Tint.STAR_WHITE, Assets.getInstance().getXSmallFont());
        miniPlanet = new MiniPlanet(color, solarSystem, radiusAdd);
        orbit = new TintedImage(Pic.Ring, Color.WHITE);
        orbit.setSize((MiniPlanet.radiusX + radiusAdd)*2, (MiniPlanet.radiusY + radiusAdd)*2);
        orbit.setPosition((MiniPlanet.radiusY * 2) - radiusAdd, (MiniPlanet.radiusY * 2) - radiusAdd);
        orbit.setTouchable(Touchable.disabled);
        addActor(orbit);
        addActor(miniPlanet);
        addActor(rank);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rank.setPosition(miniPlanet.getX(), miniPlanet.getY()+35);
    }

    public MiniPlanet getMiniPlanet() {
        return miniPlanet;
    }

    public void fadeOut(MiniPlanet selectedPlanet){
        if (!miniPlanet.equals(selectedPlanet))
            addAction(Actions.sequence(Actions.sizeTo(1,1,0.5f, Interpolation.exp10), Actions.visible(false)));
        else
            orbit.addAction(Actions.sequence(Actions.sizeTo(1, 1, 0.5f, Interpolation.exp10), Actions.visible(false)));
            removeActor(rank);
    }
}
