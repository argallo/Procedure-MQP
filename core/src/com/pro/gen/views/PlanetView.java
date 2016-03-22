package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.PlanetPopup;
import com.pro.gen.random.RandomPlanet;

import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.PlanetFatePanel;
import com.pro.gen.worldcomponents.StarMap;
import com.pro.gen.worldcomponents.TechPlanetStats;

/**
 * Created by Gallo on 1/17/2016.
 */
public class PlanetView extends BaseView {

    private Background background;
    private StarMap stars;
    private TitleBar titleBar;
    private TintedImage shipUI;
    private Planet planet;
    private PlanetPopup planetPopup;
    private TintedImage fadeOutLayer;
    private TextLabel globeRank;
    private TextLabel globeRankNumber;
    private TechPlanetStats techPlanetStats;
    private Button laserBattleBtn;
    private Button backBtn;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        planet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.BATTLE_SLOT);
        stars = new StarMap(0, 120, new Item(Pic.Circle_Small, new Range(1f,1f),3f, 12f), 400);
        planetPopup = new PlanetPopup(this);
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        techPlanetStats = new TechPlanetStats(planet.getPlanetSize(), planet.getPlanetEnergy(), planet.getColorType());
        titleBar = new TitleBar("Planet "+generateRandomName());
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);
        globeRank = new TextLabel("Globe Rank", Tint.GLOBE_RANK_GREEN);
        globeRankNumber = new TextLabel(planet.getGlobeRank()+"", Tint.GLOBE_RANK_ORANGE);
        TintedImage laserIcon = new TintedImage(Pic.Laser_Icon);
        laserIcon.setSize(187, 137);
        laserIcon.setPosition(100, Constants.VIRTUAL_HEIGHT/2 - 75);
        laserBattleBtn = new Button(Pic.Pixel, Tint.DARK_PURPLE, "Laser Battle", Assets.getInstance().getMidFont(), laserIcon);
        backBtn = new Button(Pic.Back_Button, Color.WHITE);

        laserBattleBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(0);
            }
        });
        backBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(1);
            }
        });


    }

    @Override
    public void setSizes() {
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        planet.setSize(400, 400);
        backBtn.setSize(60, 60);
        laserBattleBtn.setSize(300,150);

    }


    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        planet.setPosition(640 - planet.getWidth() / 6, Constants.VIRTUAL_HEIGHT / 2 - planet.getHeight() / 2);
        globeRank.setPosition(900, 30);
        globeRankNumber.setPosition(1180, 30);
        techPlanetStats.setPosition(930, 220);
        backBtn.setPosition(50,55);
        laserBattleBtn.setPosition(50, Constants.VIRTUAL_HEIGHT/2 - laserBattleBtn.getHeight()/2);
    }




    @Override
    public void addActors() {
        addActor(background);
        addActor(planet);
        addActor(stars);
        addActor(shipUI);
        addActor(titleBar);
        addActor(globeRank);
        addActor(globeRankNumber);
        addActor(techPlanetStats);
        addActor(laserBattleBtn);
        addActor(backBtn);
        addActor(planetPopup);
        addActor(fadeOutLayer);

        openingAnimation();
    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case 0:
                removeActor(titleBar);
                removeActor(shipUI);
                removeActor(techPlanetStats);
                removeActor(backBtn);
                removeActor(laserBattleBtn);
                removeActor(globeRank);
                removeActor(globeRankNumber);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.4f),Actions.fadeIn(2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.DESTROY, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
            case 1:
                removeActor(titleBar);
                removeActor(shipUI);
                removeActor(techPlanetStats);
                removeActor(backBtn);
                removeActor(laserBattleBtn);
                removeActor(globeRank);
                removeActor(globeRankNumber);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.2f),Actions.fadeIn(0.5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.SOLAR_SYSTEM, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
        }
    }

    public void openingAnimation(){
        fadeOutLayer.addAction(Actions.fadeOut(2f));
    }

    public String generateRandomName(){
        String alphabet = "ABCDEFGHIJKLMNOPQURTUVWXYZ1234567890-_";
        int length = MathUtils.random(5,7);
        String name = "";
        for(int i = 0; i < length; i++){
            name += alphabet.charAt(MathUtils.random(alphabet.length()-1));
        }
        return name;
    }

}
