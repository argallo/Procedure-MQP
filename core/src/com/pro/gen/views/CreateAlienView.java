package com.pro.gen.views;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.simplealien.FinalAlien;
import com.pro.gen.simplealien.RandomAlien;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
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
    private TextLabel name, nickname, height, earthWeight, bio, species, abilities, homePlanet;
    private TextBox nicknameTextbox;
    private Background background;
    TintedImage head;
    FinalAlien alien;

    Planet planet;
    int alienLeft = 0;

    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        chooseAlien = new TitleLabel(CHOOSE_ALIEN);
        selectButton = new Button(Constants.RECTANGLE, Constants.PINK, SELECT_BTN, Assets.getInstance().getMidFont());
        randomizeButton = new Button(Constants.RECTANGLE, Constants.ORANGE, RANDOMIZE_BTN, Assets.getInstance().getMidFont());
        BitmapFont smallFont = Assets.getInstance().getSmallFont();
        name = new TextLabel("Name: ", smallFont);
        nickname = new TextLabel("Nickname: ", smallFont);
        height = new TextLabel("Height: ", smallFont);
        earthWeight = new TextLabel("Earth Weight: ", smallFont);
        bio = new TextLabel("Bio: ", smallFont);
        species = new TextLabel("Species: ", smallFont);
        abilities = new TextLabel("Abilities: ", smallFont);
        homePlanet = new TextLabel("Home Planet", smallFont);
        nicknameTextbox = new TextBox(12, "", TextBox.CHARDIG);
        planet = new Planet(new RandomPlanet(), false);
        alien = new FinalAlien(150, 300, new RandomAlien());
        //head.addAction(Actions.forever(Actions.sequence(Actions.moveBy(0, 15f, 1f, Interpolation.pow2), Actions.moveBy(0, -15, 1f, Interpolation.pow2))));

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
        bio.setPosition(Constants.VIRTUAL_WIDTH / 2, 380);
        species.setPosition(Constants.VIRTUAL_WIDTH / 2, 350);
        abilities.setPosition(Constants.VIRTUAL_WIDTH / 2, 320);
        homePlanet.setPosition(Constants.VIRTUAL_WIDTH*0.68f, 290);
        planet.setPosition(Constants.VIRTUAL_WIDTH*0.65f, 80);
        alien.setPosition(100,250);

    }

    @Override
    public void addListeners() {
        selectButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                ViewManager.getInstance().transitionViewTo(ViewID.APP, TransitionType.SLIDE_R_TRANSITION);
            }
        });
        randomizeButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                removeActor(alien);
                alien = new FinalAlien(150, 300, new RandomAlien());
                alien.setPosition(100,250);
                addActor(alien);
                removeActor(planet);
                planet = new Planet(new RandomPlanet(), false);
                planet.setSize(200, 200);
                planet.setPosition(Constants.VIRTUAL_WIDTH * 0.65f, 80);
                addActorAt(2, planet);
            }
        });
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
        addActor(bio);
        addActor(species);
        addActor(abilities);
        addActor(homePlanet);
        addActor(alien);
        addActor(selectButton);
        addActor(randomizeButton);
    }

}
