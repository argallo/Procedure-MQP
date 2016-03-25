package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.popups.ExplorePopup;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.FuelUnits;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.worldcomponents.SolarSystem;
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
    private StarMap rareStars;
    private FuelUnits fuelUnits;
    private StarTracker starTracker;
    private ExplorePopup popup;
    private Button flyToStar;
    private Button currentStar;
    private TravelButton backBtn;
    private TintedImage fadeOutLayer;

    private final static int FLY = 100;
    private final static int CURRENT = 200;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        stars = new StarMap(1, 120, new Item(Pic.Circle_Small, new Range(1f,1f), 3f, 12f));
        rareStars = new StarMap(1, 10, new Item(Pic.Star_Twinkle, new Range(1f,1f), 15f, 25f));
        popup = new ExplorePopup(this);
        starTracker = new StarTracker(stars, rareStars);
        shipUI = new TintedImage(Pic.UI_Open);
        titleBar = new TitleBar("Select a Star");
        shipDoor = new ShipDoor(true);
        fuelUnits = new FuelUnits();
        flyToStar = new Button(Pic.Pixel, Tint.MED_PURPLE, "Fly To Star", Assets.getInstance().getSmallFont());
        if(XmlManager.getInstance().hasSolarSystem()) {
            currentStar = new Button(Pic.Pixel, Tint.MED_PURPLE, "Current Star", Assets.getInstance().getSmallFont());
        } else {
            currentStar = new Button(Pic.Pixel, Tint.DARK_PURPLE, "Current Star", Assets.getInstance().getSmallFont());
            currentStar.setTouchable(Touchable.disabled);
        }
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);

        flyToStar.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(FLY);
            }
        });
        currentStar.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(CURRENT);
            }
        });
    }

    @Override
    public void setSizes() {
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rareStars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        backBtn.setSize(60, 60);
        flyToStar.setSize(250, 80);
        currentStar.setSize(250, 80);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        backBtn.setPosition(50, 55);
        flyToStar.setPosition(Constants.VIRTUAL_WIDTH / 2 + 10, 13);
        currentStar.setPosition(Constants.VIRTUAL_WIDTH / 2 - currentStar.getWidth() - 10, 13);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(stars);
        addActor(rareStars);
        addActor(fuelUnits);
        addActor(shipUI);
        addActor(titleBar);
        addActor(starTracker);
        addActor(backBtn);
        addActor(flyToStar);
        addActor(currentStar);
        addActor(shipDoor);
        addActor(popup);

        openingAnimation();
        addActor(fadeOutLayer);

    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case AbsPopup.YES:
                flyToSolarSystem(true);
                break;
            case FLY:
                stars.pause();
                rareStars.pause();
                starTracker.pause();
                popup.activatePopup();
                break;
            case CURRENT:
                flyToSolarSystem(false);
            default:
                stars.unpause();
                rareStars.unpause();
                starTracker.unpause();
        }
    }

    public void flyToSolarSystem(final boolean newSystem){
        addAction(Actions.sequence(new Action() {
            @Override
            public boolean act(float delta) {
                removeActor(starTracker);
                removeActor(fuelUnits);
                removeActor(shipUI);
                removeActor(currentStar);
                removeActor(flyToStar);
                removeActor(backBtn);
                removeActor(titleBar);
                stars.setStarSpeed(0);
                stars.unpause();
                stars.increaseSpeed(100);
                rareStars.setStarSpeed(0);
                rareStars.unpause();
                rareStars.increaseSpeed(100);
                return true;
            }
        }, Actions.delay(5f), new Action() {
            @Override
            public boolean act(float delta) {
                fadeOutLayer.setVisible(true);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(1.5f), Actions.fadeIn(2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        if(newSystem) {
                            XmlManager.getInstance().saveFuelUnits(XmlManager.getInstance().getFuelUnits()-1);
                            SolarSystem solarSystem = new SolarSystem(getLowRank(), getHighRank());
                        }
                        ViewManager.getInstance().transitionViewTo(ViewID.SOLAR_SYSTEM, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                return true;
            }
        }));
    }

    public void openingAnimation(){
        fadeOutLayer.setVisible(false);
        fadeOutLayer.addAction(Actions.fadeOut(0));
    }

    public int getLowRank(){
        return starTracker.getLowRank();
    }

    public int getHighRank(){
        return starTracker.getHighRank();
    }

}
