package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.GlobeRank;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.ShipDoor;
import com.pro.gen.worldcomponents.TechPlanetStats;

import java.util.ArrayList;

/**
 * Created by Gallo on 2/5/2016.
 */
public class PlanetlistView extends BaseView {


    private Background background;
    private TintedImage shipUI;
    private TintedImage rowDivider;
    private Button backBtn;
    private TintedImage header;
    private TextLabel headerText;
    private ShipDoor shipDoor;

    private ArrayList<Button> slots;
    private ArrayList<Planet> planets;
    private GlobeRank globeRank;
    private TechPlanetStats techPlanetStats;
    private Button remove, selectHat;
    private TextLabel todaysStepsLabel, habitableLabel;
    private ArrayList<TintedImage> miniPlanets;

    private int currentSelectedSlot = 1;
    private Hat hat;//TODO:IMPLEMENT HATS!


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.DARK_PURPLE);
        shipDoor = new ShipDoor(true);
        rowDivider = new TintedImage(Pic.Pixel, Tint.MED_PURPLE);
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        header = new TintedImage(Pic.Header_Bar, Tint.PURPLE);
        headerText = new TextLabel("Manage Planets", Assets.getInstance().getSmallFont());
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);

        planets = new ArrayList<Planet>();
        miniPlanets = new ArrayList<TintedImage>();
        slots = new ArrayList<Button>();

        setupPlanets();
    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        header.setSize(400, 80);
        backBtn.setSize(60, 60);
    }

    @Override
    public void setPositions() {
        rowDivider.setPosition(0, 513);
        header.setPosition(Constants.VIRTUAL_WIDTH / 2 - header.getWidth() / 2, 620);
        headerText.setPosition(Constants.VIRTUAL_WIDTH / 2 - headerText.getWidth() / 2, 640);
        backBtn.setPosition(50, 590);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(rowDivider);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);
        addActor(backBtn);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {

    }


    public void setupPlanets(){
        Planet planet1 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_1);
        Planet planet2 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_2);
        Planet planet3 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_3);
        Planet planet4 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_4);

        if(planet1 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet1.getBasePlanetColor()));
            planets.add(planet1);
        }

        if(planet2 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet2.getBasePlanetColor()));
            planets.add(planet2);
        }

        if(planet3 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet3.getBasePlanetColor()));
            planets.add(planet3);
        }

        if(planet4 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet4.getBasePlanetColor()));
            planets.add(planet4);
        }

        for(int i = 0; i < 4; i++){
            Button button;
            if(i < miniPlanets.size()) {
                TintedImage miniPlanet = miniPlanets.get(i);
                miniPlanet.setSize(100, 100);
                miniPlanet.setPosition((120 * (i + 1) + 20), 600);
                button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "", Assets.getInstance().getMidFont(), miniPlanet);
            }
            else {
                button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Empty", Assets.getInstance().getMidFont());
            }
            button.setSize(100, 100);
            button.setPosition((120 * (i + 1) + 20), 600);
            addActor(button);
            slots.add(button);
        }

        slots.get(0).setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(5);
            }
        });
        slots.get(1).setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(6);
            }
        });
        slots.get(2).setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(7);
            }
        });
        slots.get(3).setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(8);
            }
        });

    }


}
