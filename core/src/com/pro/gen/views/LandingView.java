package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.SkyDown;

/**
 * Created by Gallo on 10/27/2015.
 */
public class LandingView extends BaseView {


    SkyDown skyDown;
    TintedImage ship;


    public LandingView(){
        super();
        ship.addAction(Actions.sequence(Actions.moveTo(ship.getX(), Constants.VIRTUAL_HEIGHT / 2 - ship.getHeight() / 2, 2f, Interpolation.exp5),
                Actions.forever(Actions.sequence(Actions.moveBy(0, 50, 0.6f, Interpolation.fade), Actions.moveBy(0, -60, 0.6f, Interpolation.fade)))));

    }


    @Override
    public void init() {
        ship = new TintedImage(Constants.SMALL_SHIP, Color.WHITE);
        // make this color from the planets land Color?
        skyDown = new SkyDown(ColorHelper.generateGoodColor(), this);
    }

    @Override
    public void setSizes() {
        ship.setSize(300, 600);
    }

    @Override
    public void setPositions() {
        ship.setPosition(Constants.VIRTUAL_WIDTH / 2 - ship.getWidth() / 2, Constants.VIRTUAL_HEIGHT);
    }

    @Override
    public void addActors() {
        addActor(skyDown);
        addActor(ship);
    }

    public void finishLanding(){
        Background whiteFade = new Background(Constants.PIXEL, Color.WHITE);
        addActor(whiteFade);
        whiteFade.addAction(Actions.sequence(Actions.alpha(1, 1.5f, Interpolation.fade), new Action() {
            @Override
            public boolean act(float delta) {
                ViewManager.getInstance().transitionViewTo(ViewID.LAND, TransitionType.DEFAULT_TRANSITION);
                return true;
            }
        }));
    }

}
