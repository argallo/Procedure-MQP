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
import com.pro.gen.utils.Pic;

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
        ship = new TintedImage(Pic.Ship_V, Color.WHITE);
        //TODO: make this color from the planets land Color?
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

    @Override
    public void handle(int outcome) {

    }

    public void finishLanding(){
        Background whiteFade = new Background(Pic.Pixel, new Color(Color.WHITE));
        whiteFade.setVisible(false);
        addActor(whiteFade);
        whiteFade.addAction(Actions.sequence(Actions.fadeOut(0f),Actions.visible(true),Actions.fadeIn(2f), new Action() {
            @Override
            public boolean act(float delta) {
                ViewManager.getInstance().transitionViewTo(ViewID.MINE, TransitionType.DEFAULT_TRANSITION);
                return true;
            }
        }));
    }

}
