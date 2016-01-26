package com.pro.gen.znewmqp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.views.BaseView;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/14/2016.
 */
public class SolarSystemView extends BaseView {

    private Background background;
    private TitleBar titleBar;
    private StarMap stars;
    private TintedImage shipUI;
    private SolarSystem solarSystem;
    private SolarSystemPopup solarSystemPopup;
    private PopupHandler popupHandler;
    private TintedImage fadeOutLayer;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        stars = new StarMap(1, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f));
        stars.setStarSpeed(0.1f);

        popupHandler = new PopupHandler() {
            @Override
            public void handle(int outcome) {
                if(outcome == AbsPopup.YES){
                    solarSystem.selectedPlanet.setTouchable(Touchable.disabled);
                    solarSystem.selectedPlanet.moveToCenter();
                    removeActor(titleBar);
                    solarSystem.fadeAway();
                    fadeOutLayer.addAction(Actions.sequence(Actions.delay(1.5f), Actions.fadeIn(2f), new Action() {
                        @Override
                        public boolean act(float delta) {
                            ViewManager.getInstance().transitionViewTo(ViewID.PLANET, TransitionType.DEFAULT_TRANSITION);
                            return true;
                        }
                    }));
                }
            }
        };
        solarSystemPopup = new SolarSystemPopup(popupHandler);
        shipUI = new TintedImage(Pic.UI_Open);
        titleBar = new TitleBar("System SuiNxc");
        solarSystem = new SolarSystem(solarSystemPopup);
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);
    }


    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }


    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(stars);
        addActor(shipUI);
        addActor(titleBar);
        addActor(solarSystem);
        addActor(solarSystemPopup);
        addActor(fadeOutLayer);

        openingAnimation();
    }

    public void openingAnimation(){
        fadeOutLayer.addAction(Actions.fadeOut(2f));
    }


}
