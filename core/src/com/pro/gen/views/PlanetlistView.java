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
import com.pro.gen.worldcomponents.HatsInventory;
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
    private int selectedPlanetIndex = 0;
    private GlobeRank globeRank;
    private TechPlanetStats techPlanetStats;
    private Button remove;
    private TextLabel todaysStepsLabel, habitableLabel;
    private ArrayList<TintedImage> miniPlanets;
    private HatsInventory hatsInventory;


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
        habitableLabel = new TextLabel("", Assets.getInstance().getSmallFont());
        hatsInventory = new HatsInventory(this);
        hatsInventory.setVisible(false);
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
        habitableLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - habitableLabel.getWidth() / 2, (Constants.VIRTUAL_HEIGHT / 2) + 20);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(rowDivider);
        addActor(shipUI);
        addActor(header);
        addActor(headerText);
        addActor(backBtn);
        addActor(habitableLabel);

        setupPlanets();

        addActor(hatsInventory);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {
        setSelectedPlanet(planets.get(outcome), outcome);
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
                miniPlanet.setPosition((110 * (i)+30), 400);
                button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "", Assets.getInstance().getSmallFont(), miniPlanet);
                button.setSize(100, 100);
                button.setPosition((110 * (i)+30), 400);
                addActor(button);
                slots.add(button);
                final int x = i;
                slots.get(i).setButtonAction(new ButtonAction() {
                    @Override
                    public void buttonPressed() {
                        handle(x);
                    }
                });
            }
            else {
                button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Empty", Assets.getInstance().getSmallFont());
                button.setSize(100, 100);
                button.setPosition((110 * (i)+30), 400);
                addActor(button);
                slots.add(button);
            }

        }

        for(Planet planet : planets){
            planet.setSize(250,250);
            planet.setPosition(510,100);
            planet.setVisible(false);
            if (planet.isInhabitable()){
                planet.instantBurn();
            }
            planet.setBackgroundTint(Tint.DARK_PURPLE);
            addActor(planet);
        }

        globeRank = new GlobeRank(1,0,100); // default init params
        globeRank.setPosition(50,50);
        addActor(globeRank);

        techPlanetStats = new TechPlanetStats(0,0,"Blue"); // default init params
        techPlanetStats.setPosition(930, 100);
        addActor(techPlanetStats);

        setSelectedPlanet(planets.get(0), 0);
        setupHatButton(planets.get(0));

    }

    public void setSelectedPlanet(Planet selectedPlanet, int index){
        selectedPlanetIndex = index;
        for(Planet planet : planets){
            planet.setVisible(false);
        }
        selectedPlanet.setVisible(true);
        globeRank.newPlanetRanks(selectedPlanet.getGlobeRank(),selectedPlanet.getCurrentXP(),selectedPlanet.getRankXP());
        techPlanetStats.setParams(selectedPlanet.getPlanetSize(), selectedPlanet.getPlanetEnergy(), selectedPlanet.getColorType());
        if(selectedPlanet.isInhabitable()){
            habitableLabel.setText("Inhabitable");
        } else {
            habitableLabel.setText("");
        }
    }

    public void setupHatButton(Planet selectedPlanet){
        if(selectedPlanet.getHat().getHatID() == 0){
            remove = new Button(Pic.Pixel, Tint.MED_PURPLE, "Select Hat", Assets.getInstance().getXSmallFont());
        } else {
            remove = new Button(Pic.Pixel, Tint.MED_PURPLE, "Remove Hat", Assets.getInstance().getXSmallFont());
        }
        remove.setSize(120, 40);
        remove.setPosition(780, 150);
        remove.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                if (planets.get(selectedPlanetIndex).getHat().getHatID() == 0) {
                    hatsInventory.setVisible(true);
                } else {
                    XmlManager.getInstance().removeHatFromPlanet(XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex));
                }
            }
        });
        addActor(remove);
    }


    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public int getSelectedPlanetIndex() {
        return selectedPlanetIndex;
    }
}
