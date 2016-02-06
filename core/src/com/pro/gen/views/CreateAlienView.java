package com.pro.gen.views;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 9/10/2015.
 */
public class CreateAlienView extends BaseView{

    private static final String CHOOSE_ALIEN = "CHOOSE ALIEN";
    private static final String SELECT_BTN = "Select";
    private static final String RANDOMIZE_BTN = "Randomize";

    private TitleLabel chooseAlien;
    private Button selectButton, randomizeButton;
    private TextLabel name, nickname, height, earthWeight, species, homePlanet;
    private Background background;
    private Planet planet;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        chooseAlien = new TitleLabel(CHOOSE_ALIEN);
        selectButton = new Button(Pic.Pixel, Tint.PINK, SELECT_BTN, Assets.getInstance().getMidFont());
        randomizeButton = new Button(Pic.Pixel, Tint.ORANGE, RANDOMIZE_BTN, Assets.getInstance().getMidFont());
        BitmapFont xsmallFont = Assets.getInstance().getXSmallFont();
        name = new TextLabel("Name: ", xsmallFont);
        nickname = new TextLabel("Nickname: ", xsmallFont);
        height = new TextLabel("Height: ", xsmallFont);
        earthWeight = new TextLabel("Earth Weight: ", xsmallFont);
        species = new TextLabel("Species: ", xsmallFont);
        homePlanet = new TextLabel("Home Planet", xsmallFont);
        planet = new Planet(new RandomPlanet());

        selectButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                ViewManager.getInstance().transitionViewTo(ViewID.MAIN_MENU, TransitionType.SLIDE_R_TRANSITION);
            }
        });
        randomizeButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                removeActor(planet);
                planet = new Planet(new RandomPlanet());
                planet.setSize(200, 200);
                planet.setPosition(Constants.VIRTUAL_WIDTH * 0.65f, 80);
                addActorAt(2, planet);

            }
        });

    }

    @Override
    public void setSizes() {
        selectButton.setSize(300, 150);
        randomizeButton.setSize(300, 150);
        planet.setSize(200, 200);
    }

    @Override
    public void setPositions() {
        chooseAlien.setPosition(Constants.VIRTUAL_WIDTH/2 - chooseAlien.getWidth()/2, Constants.VIRTUAL_HEIGHT - chooseAlien.getHeight());
        selectButton.setPosition(350, 25);
        randomizeButton.setPosition(25, 25);
        name.setPosition(Constants.VIRTUAL_WIDTH / 2, 500);
        nickname.setPosition(Constants.VIRTUAL_WIDTH / 2, 470);
        height.setPosition(Constants.VIRTUAL_WIDTH / 2, 440);
        earthWeight.setPosition(Constants.VIRTUAL_WIDTH / 2, 410);
        species.setPosition(Constants.VIRTUAL_WIDTH / 2, 350);
        homePlanet.setPosition(Constants.VIRTUAL_WIDTH*0.68f, 290);
        planet.setPosition(Constants.VIRTUAL_WIDTH*0.65f, 80);


    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(chooseAlien);
        addActor(planet);
        addActor(name);
        addActor(nickname);
        addActor(height);
        addActor(earthWeight);
        addActor(species);
        addActor(homePlanet);
        addActor(selectButton);
        addActor(randomizeButton);
    }

    @Override
    public void handle(int outcome) {

    }

}
