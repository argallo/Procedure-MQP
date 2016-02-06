package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TitleBar;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.popups.WinningsPopup;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.KillerShip;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/21/2016.
 */
public class DestroyView extends BaseView {


    private Background background;
    private StarMap stars;
    private TitleBar titleBar;
    private Planet planet;
    private KillerShip killerShip;
    private WinningsPopup winningsPopup;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        planet = new Planet(new RandomPlanet());
        stars = new StarMap(0, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f), 350);
        titleBar = new TitleBar("Destroying");
        winningsPopup = new WinningsPopup(this);
        killerShip = new KillerShip(winningsPopup);
        planet.burn();
    }

    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        planet.setSize(350, 350);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        planet.setPosition(640 - planet.getWidth() / 6, Constants.VIRTUAL_HEIGHT / 2 - planet.getHeight() / 2);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(planet);
        addActor(stars);
        addActor(killerShip);
        addActor(winningsPopup);
    }

    @Override
    public void handle(int outcome) {
        switch(outcome){
            case AbsPopup.YES:
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.SOLAR_SYSTEM, TransitionType.SLIDE_R_TRANSITION);
                        return true;
                    }
                }));
                break;
        }
    }


}
