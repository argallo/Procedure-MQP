package com.pro.gen.views;

import com.pro.gen.alien.Alien;
import com.pro.gen.alien.AlienEngineer;
import com.pro.gen.alien.RandomAlienBuilder;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

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
    private Alien alien;

    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        chooseAlien = new TitleLabel(CHOOSE_ALIEN);
        selectButton = new Button(Constants.RECTANGLE, Constants.PINK, SELECT_BTN, Assets.getInstance().getMidFont());
        randomizeButton = new Button(Constants.RECTANGLE, Constants.ORANGE, RANDOMIZE_BTN, Assets.getInstance().getMidFont());
        name = new TextLabel("Name: ");
        nickname = new TextLabel("Nickname: ");
        height = new TextLabel("Height: ");
        earthWeight = new TextLabel("Earth Weight: ");
        bio = new TextLabel("Bio: ");
        species = new TextLabel("Species: ");
        abilities = new TextLabel("Abilities: ");
        homePlanet = new TextLabel("Home Planet: ");
        nicknameTextbox = new TextBox(12, "", TextBox.CHARDIG);
        alien = createAlien();

    }

    @Override
    public void setSizes() {
        selectButton.setSize(300,150);
        randomizeButton.setSize(300, 150);
    }

    @Override
    public void setPositions() {
        chooseAlien.setPosition(Constants.VIRTUAL_WIDTH/2 - chooseAlien.getWidth()/2, Constants.VIRTUAL_HEIGHT - chooseAlien.getHeight());
        selectButton.setPosition(350,25);
        randomizeButton.setPosition(25, 25);
        name.setPosition(Constants.VIRTUAL_WIDTH / 2, 500);
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(chooseAlien);
        addActor(name);
        addActor(selectButton);
        addActor(randomizeButton);
    }

    public Alien createAlien(){
        AlienEngineer alienEngineer = new AlienEngineer(new RandomAlienBuilder());
        alienEngineer.makeAlien();
        return alienEngineer.getAlien();
    }
}
