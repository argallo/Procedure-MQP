package com.pro.gen.views;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.GlobeRank;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.TechPlanetStats;

/**
 * Created by Gallo on 9/10/2015.
 */
public class CreateStarterPlanetView extends BaseView{

    private static final int RANDOMIZE_ACTION = 2;
    private static final int SELECT_ACTION = 3;

    private static final String CHOOSE_PLANET = "CHOOSE HOME PLANET";
    private static final String SELECT_BTN = "Select";
    private static final String RANDOMIZE_BTN = "Randomize";

    private TitleLabel choosePlanet;
    private Button selectButton, randomizeButton;
    private Background background;
    private Planet planet;
    private GlobeRank globeRank;
    private TechPlanetStats techPlanetStats;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        choosePlanet = new TitleLabel(CHOOSE_PLANET);
        selectButton = new Button(Pic.Pixel, Tint.PINK, SELECT_BTN, Assets.getInstance().getMidFont());
        randomizeButton = new Button(Pic.Pixel, Tint.ORANGE, RANDOMIZE_BTN, Assets.getInstance().getMidFont());
        BitmapFont xsmallFont = Assets.getInstance().getXSmallFont();
        planet = new Planet(new RandomPlanet(1,1));
        globeRank = new GlobeRank(planet.getGlobeRank(), planet.getCurrentXP(), planet.getRankXP());
        //globeRank = new GlobeRank();
        techPlanetStats = new TechPlanetStats(planet.getPlanetSize(), planet.getPlanetEnergy(), planet.getBasePlanetColor().toString());
        selectButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(SELECT_ACTION);
            }
        });
        randomizeButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
             handle(RANDOMIZE_ACTION);
            }
        });

    }

    @Override
    public void setSizes() {
        selectButton.setSize(300, 150);
        randomizeButton.setSize(300, 150);
        planet.setSize(350, 350);

    }

    @Override
    public void setPositions() {
        choosePlanet.setPosition(Constants.VIRTUAL_WIDTH / 2 - choosePlanet.getWidth() / 2, Constants.VIRTUAL_HEIGHT - choosePlanet.getHeight());
        selectButton.setPosition(Constants.VIRTUAL_WIDTH-selectButton.getWidth()-25, 25);
        randomizeButton.setPosition(25, 25);
        planet.setPosition(640 - planet.getWidth() / 6, Constants.VIRTUAL_HEIGHT / 2 - planet.getHeight() / 2 + 20);
        globeRank.setPosition(Constants.VIRTUAL_WIDTH/2 - globeRank.getWidth()/2, 25);
        techPlanetStats.setPosition(950, 220);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(choosePlanet);
        addActor(planet);
        addActor(globeRank);
        addActor(techPlanetStats);
        addActor(selectButton);
        addActor(randomizeButton);
    }

    @Override
    public void handle(int outcome) {
        switch(outcome){
            case RANDOMIZE_ACTION:
                removeActor(planet);
                planet = new Planet(new RandomPlanet(1,1));
                planet.setSize(350, 350);
                planet.setPosition(640 - planet.getWidth() / 6, Constants.VIRTUAL_HEIGHT / 2 - planet.getHeight() / 2 + 20);
                addActorAt(2, planet);
                break;
            case SELECT_ACTION:
                XmlManager manager = new XmlManager();
                manager.savePlanet(planet);
                ViewManager.getInstance().transitionViewTo(ViewID.MAIN_MENU, TransitionType.SLIDE_R_TRANSITION);
                break;
        }
    }

}
