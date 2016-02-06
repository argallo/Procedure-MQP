package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.popups.ExplorePopup;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.FuelUnits;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.worldcomponents.StarMap;
import com.pro.gen.worldcomponents.StarTracker;

/**
 * Created by Gallo on 1/13/2016.
 */
public class ExploreView extends BaseView {

    private Background background;
    private ShipDoor shipDoor;
    private TintedImage shipUI;
    private TitleBar titleBar;
    private StarMap stars;
    private FuelUnits fuelUnits;
    private StarTracker starTracker;
    private ExplorePopup popup;



    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        stars = new StarMap(1, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f));
        popup = new ExplorePopup(this);
        starTracker = new StarTracker(stars, popup);
        shipUI = new TintedImage(Pic.UI_Open);
        titleBar = new TitleBar("Select a Star");
        shipDoor = new ShipDoor(true);
        fuelUnits = new FuelUnits();

    }

    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(stars);
        addActor(fuelUnits);
        addActor(shipUI);
        addActor(titleBar);
        addActor(starTracker);
        addActor(shipDoor);
        addActor(popup);

    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case AbsPopup.YES:
                addAction(Actions.sequence(new Action() {
                    @Override
                    public boolean act(float delta) {
                        removeActor(starTracker);
                        removeActor(fuelUnits);
                        removeActor(shipUI);
                        removeActor(titleBar);
                        stars.setStarSpeed(0);
                        stars.unpause();
                        stars.increaseSpeed(100);
                        return true;
                    }
                }, Actions.delay(5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        background.addAction(Actions.sequence(Actions.color(Tint.STAR_WHITE, 2f), new Action() {
                            @Override
                            public boolean act(float delta) {
                                ViewManager.getInstance().transitionViewTo(ViewID.SOLAR_SYSTEM, TransitionType.DEFAULT_TRANSITION);
                                return true;
                            }
                        }));
                        return true;
                    }
                }));
                break;
            default:
                stars.unpause();
        }
    }

}
