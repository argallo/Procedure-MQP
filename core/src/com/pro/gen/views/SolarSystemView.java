package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.popups.SolarSystemPopup;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.worldcomponents.SolarSystem;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/14/2016.
 */
public class SolarSystemView extends BaseView {

    private Background background;
    private TitleBar titleBar;
    private ShipDoor shipDoor;
    private StarMap stars;
    private TintedImage shipUI;
    private SolarSystem solarSystem;
    private SolarSystemPopup solarSystemPopup;
    private TintedImage fadeOutLayer;
    private TravelButton backBtn;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        stars = new StarMap(1, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f));
        stars.setStarSpeed(0.1f);
        shipDoor = new ShipDoor(false);
        solarSystemPopup = new SolarSystemPopup(this);
        shipUI = new TintedImage(Pic.UI_Open);
        titleBar = new TitleBar("System SuiNxc");
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        solarSystem = XmlManager.getInstance().getSolarSystem();
        solarSystem.setPopup(solarSystemPopup);
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);
    }


    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        backBtn.setSize(60, 60);
    }


    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        backBtn.setPosition(50,55);
    }


    @Override
    public void addActors() {
        addActor(background);
        addActor(stars);
        addActor(shipUI);
        addActor(titleBar);
        addActor(solarSystem);
        addActor(solarSystemPopup);
        addActor(backBtn);
        addActor(shipDoor);
        addActor(fadeOutLayer);

        openingAnimation();
    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case AbsPopup.YES:
                solarSystem.getSelectedMiniPlanet().setTouchable(Touchable.disabled);
                solarSystem.getSelectedMiniPlanet().moveToCenter();
                XmlManager.getInstance().savePlanet(solarSystem.getSelectedPlanet(), PreferenceManager.BATTLE_SLOT);
                removeActor(titleBar);
                solarSystem.fadeAway();
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(1.5f), Actions.fadeIn(2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.PLANET, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
        }
    }

    public void openingAnimation(){
        fadeOutLayer.addAction(Actions.fadeOut(2f));
    }


}
