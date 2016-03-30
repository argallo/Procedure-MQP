package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.worldcomponents.StarMap;
import com.pro.gen.worldcomponents.TechPlanetStats;

/**
 * Created by Gallo on 3/25/2016.
 */
public class BossView extends BaseView {

    private Background background;
    private ShipDoor shipDoor;
    private TintedImage shipUI;
    private TitleBar titleBar;
    private StarMap stars;
    private StarMap rareStars;
    private TravelButton backBtn;
    private TintedImage fadeOutLayer;

    private TintedImage boss;
    private Planet bossPlanet;
    private TechPlanetStats techPlanetStats;
    private Button battleBtn;
    private TextLabel globeRank;
    private TextLabel globeRankNumber;
    private TextLabel reward;
    private TintedImage megaCrystal;



    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        stars = new StarMap(0, 120, new Item(Pic.Circle_Small, new Range(1f,1f), 3f, 12f), 400);
        rareStars = new StarMap(0, 10, new Item(Pic.Star_Twinkle, new Range(1f,1f), 15f, 25f), 400);
        stars.setStarSpeed(0.1f);
        rareStars.setStarSpeed(0.1f);
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        titleBar = new TitleBar("Boss Battle");
        shipDoor = new ShipDoor(true);

        boss = new TintedImage(Pic.Boss_Plat);
        bossPlanet = XmlManager.getInstance().getBossPlanet();
        techPlanetStats = new TechPlanetStats(bossPlanet.getPlanetSize(), bossPlanet.getPlanetEnergy(), bossPlanet.getColorType());
        if(!XmlManager.getInstance().hasHabitable()) {
            battleBtn = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Need Habitable Planet", Assets.getInstance().getXSmallFont());
            battleBtn.setTouchable(Touchable.disabled);
        } else {
            battleBtn = new Button(Pic.Pixel, Tint.MED_PURPLE, "Battle Boss Jim", Assets.getInstance().getSmallFont());
            battleBtn.setButtonAction(new ButtonAction() {
                @Override
                public void buttonPressed() {
                    handle(7);
                }
            });
        }

        globeRank = new TextLabel("Globe Rank", Tint.GLOBE_RANK_GREEN);
        globeRankNumber = new TextLabel(bossPlanet.getGlobeRank()+"", Tint.GLOBE_RANK_ORANGE);

        reward = new TextLabel("Reward: "+bossPlanet.getGlobeRank(), Tint.GLOBE_RANK_GREEN, Assets.getInstance().getMidFont(), Align.right);
        megaCrystal = new TintedImage(Pic.Mega_Crystal);


        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);

    }

    @Override
    public void setSizes() {
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        stars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rareStars.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        backBtn.setSize(60, 60);
        boss.setSize(278,435);
        bossPlanet.setSize(350,350);
        battleBtn.setSize(280,90);
        megaCrystal.setSize(70,50);

    }

    @Override
    public void setPositions() {
        titleBar.setPosition(80, 600);
        backBtn.setPosition(50, 55);
        boss.setPosition(80, 120);
        bossPlanet.setPosition(640 - bossPlanet.getWidth() / 6, Constants.VIRTUAL_HEIGHT / 2 - bossPlanet.getHeight() / 2-40);
        techPlanetStats.setPosition(940, 200);
        battleBtn.setPosition(950, 80);
        globeRank.setPosition(480, 620);
        globeRankNumber.setPosition(760, 620);
        reward.setPosition(480,40);
        megaCrystal.setPosition(reward.getWidth()+490,45);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(bossPlanet);

        addActor(stars);
        addActor(rareStars);
        addActor(boss);
        addActor(techPlanetStats);
        addActor(shipUI);
        addActor(titleBar);
        addActor(globeRank);
        addActor(globeRankNumber);
        addActor(reward);
        addActor(megaCrystal);
        addActor(battleBtn);
        addActor(backBtn);
        addActor(shipDoor);
        openingAnimation();
        addActor(fadeOutLayer);

    }

    @Override
    public void handle(int outcome) {
        switch (outcome){
            case 7:
                removeActor(titleBar);
                removeActor(shipUI);
                removeActor(techPlanetStats);
                removeActor(backBtn);
                removeActor(battleBtn);
                removeActor(megaCrystal);
                removeActor(reward);
                removeActor(globeRank);
                removeActor(globeRankNumber);
                fadeOutLayer.setVisible(true);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.4f),Actions.fadeIn(2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.BOSS_BATTLE, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
            default:
                stars.unpause();
                rareStars.unpause();
        }
    }

    public void openingAnimation(){
        fadeOutLayer.setVisible(false);
        fadeOutLayer.addAction(Actions.fadeOut(0));
    }


}
