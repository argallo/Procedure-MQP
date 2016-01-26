package com.pro.gen.znewmqp;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.views.BaseView;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/17/2016.
 */
public class PlanetView extends BaseView {

    private Background background;
    private StarMap stars;
    private TitleBar titleBar;
    private TintedImage shipUI;
    private Planet planet;
    private PlanetFatePanel planetFatePanel;
    private PlanetPopup planetPopup;
    private PopupHandler popupHandler;
    private TintedImage fadeOutLayer;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        planet = new Planet(new RandomPlanet());
        stars = new StarMap(0, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f), 400);
        popupHandler = new PopupHandler() {
            @Override
            public void handle(int outcome) {
               switch (outcome){
                   case PlanetPopup.ARMY:
                       removeActor(titleBar);
                       removeActor(shipUI);
                       removeActor(planetFatePanel);
                       fadeOutLayer.addAction(Actions.sequence(Actions.delay(1f),Actions.fadeIn(2f), new Action() {
                           @Override
                           public boolean act(float delta) {
                               ViewManager.getInstance().transitionViewTo(ViewID.LANDING, TransitionType.DEFAULT_TRANSITION);
                               return true;
                           }
                       }));
                       break;
                   case PlanetPopup.DESTROY:
                       removeActor(titleBar);
                       removeActor(shipUI);
                       removeActor(planetFatePanel);
                       fadeOutLayer.addAction(Actions.sequence(Actions.delay(1f),Actions.fadeIn(2f), new Action() {
                           @Override
                           public boolean act(float delta) {
                               ViewManager.getInstance().transitionViewTo(ViewID.DESTROY, TransitionType.DEFAULT_TRANSITION);
                               return true;
                           }
                       }));
                       break;
                   case PlanetPopup.MINE:
                       removeActor(titleBar);
                       removeActor(shipUI);
                       removeActor(planetFatePanel);
                       fadeOutLayer.addAction(Actions.sequence(Actions.delay(1f),Actions.fadeIn(2f), new Action() {
                           @Override
                           public boolean act(float delta) {
                               ViewManager.getInstance().transitionViewTo(ViewID.LANDING, TransitionType.DEFAULT_TRANSITION);
                               return true;
                           }
                       }));
                       break;
               }
            }
        };
        planetPopup = new PlanetPopup(popupHandler);
        planetFatePanel = new PlanetFatePanel(planetPopup);
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        titleBar = new TitleBar("Planet 34-Sdj");
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);

    }

    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        planet.setSize(400, 400);

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
        addActor(planetFatePanel);
        addActor(shipUI);
        addActor(titleBar);
        addActor(planetPopup);
        addActor(fadeOutLayer);

        openingAnimation();
    }

    public void openingAnimation(){
        fadeOutLayer.addAction(Actions.fadeOut(2f));
    }

}
