package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.pro.gen.App;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TravelButton;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.HealPlanetPopup;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
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
    private TravelButton backBtn;
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
    private Button deletePlanet, moveLeft, moveRight, healPlanet;
    private HealPlanetPopup healPlanetPopup;

    private int restartSlot = 0;

    @Override
    public void init() {
        restart();
        shipDoor = new ShipDoor(true);
        backBtn.setShipDoor(shipDoor);

    }

    public void restart() {
        background = new Background(Pic.Pixel, Tint.DARK_PURPLE);
        shipDoor = new ShipDoor(false);
        rowDivider = new TintedImage(Pic.Pixel, Tint.MED_PURPLE);
        shipUI = new TintedImage(Pic.UI_Open);
        shipUI.setTouchable(Touchable.disabled);
        header = new TintedImage(Pic.Header_Bar, Tint.PURPLE);
        headerText = new TextLabel("Manage Planets", Assets.getInstance().getSmallFont());
        backBtn = new TravelButton(Pic.Back_Button, ViewID.MAIN_MENU, shipDoor);
        healPlanetPopup = new HealPlanetPopup(this);
        planets = new ArrayList<Planet>();
        miniPlanets = new ArrayList<TintedImage>();
        slots = new ArrayList<Button>();
        habitableLabel = new TextLabel("", Assets.getInstance().getSmallFont());
        hatsInventory = new HatsInventory(this);
        hatsInventory.setVisible(false);
        deletePlanet = new Button(Pic.Curve_rectangle, Tint.BLAST_RED, "Release Planet", Assets.getInstance().getXSmallFont());
        deletePlanet.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                if(planets.size() > 1) {
                    XmlManager.getInstance().deletePlanet(XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex));
                    restartSlot = 0;
                    clear();
                    init();
                    setSizes();
                    setPositions();
                    addActors();
                }
            }
        });
        moveLeft = new Button(Pic.Pixel, Tint.BLAST_RED, "<", Assets.getInstance().getMidFont());
        moveRight = new Button(Pic.Pixel, Tint.BLAST_RED, ">", Assets.getInstance().getMidFont());

        moveLeft.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                if(selectedPlanetIndex > 0){
                    XmlManager.getInstance().swap(XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex), XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex - 1));
                    restartSlot = selectedPlanetIndex-1;
                    clear();
                    restart();
                    setSizes();
                    setPositions();
                    addActors();
                }
            }
        });

        moveRight.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                if(selectedPlanetIndex < 3 && XmlManager.getInstance().getPlanetFromSlot(XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex+1))!=null){
                    XmlManager.getInstance().swap(XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex), XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex+1));
                    restartSlot = selectedPlanetIndex+1;
                    clear();
                    restart();
                    setSizes();
                    setPositions();
                    addActors();
                }
            }
        });

        healPlanet = new Button(Pic.Pixel, Tint.GLOBE_RANK_GREEN, "Heal Planet", Assets.getInstance().getXSmallFont());
        healPlanet.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                healPlanetPopup.activatePopup();
            }
        });




    }

    @Override
    public void setSizes() {
        shipUI.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rowDivider.setSize(Constants.VIRTUAL_WIDTH, 25);
        header.setSize(400, 80);
        backBtn.setSize(60, 60);
        deletePlanet.setSize(200, 40);
        moveLeft.setSize(60, 60);
        moveRight.setSize(60, 60);
        healPlanet.setSize(120, 80);


    }

    @Override
    public void setPositions() {
        rowDivider.setPosition(0, 513);
        header.setPosition(Constants.VIRTUAL_WIDTH / 2 - header.getWidth() / 2, 620);
        headerText.setPosition(Constants.VIRTUAL_WIDTH / 2 - headerText.getWidth() / 2, 640);
        backBtn.setPosition(50, 590);
        habitableLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - habitableLabel.getWidth() / 2, (Constants.VIRTUAL_HEIGHT / 2) + 100);
        deletePlanet.setPosition(150, 220);
        moveLeft.setPosition(180, 330);
        moveRight.setPosition(250, 330);
        healPlanet.setPosition(780, 330);
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
        addActor(healPlanet);

        setupPlanets();

        addActor(deletePlanet);
        addActor(moveLeft);
        addActor(moveRight);

        addActor(healPlanetPopup);
        addActor(hatsInventory);
        addActor(shipDoor);
    }

    @Override
    public void handle(int outcome) {
        if(outcome == HealPlanetPopup.WALK){//TODO:save these through xml and load them back based on the planet.
            planets.get(selectedPlanetIndex).setTimeStart(TimeUtils.millis());
            planets.get(selectedPlanetIndex).setAmtofTime(150);
            XmlManager.getInstance().savePlanet(planets.get(selectedPlanetIndex), XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex));
            healPlanet.setText("150 Steps");
            healPlanet.setTouchable(Touchable.disabled);
        } else if(outcome == HealPlanetPopup.WAIT){
            planets.get(selectedPlanetIndex).setTimeStart(TimeUtils.millis());
            planets.get(selectedPlanetIndex).setAmtofTime(600000);
            XmlManager.getInstance().savePlanet(planets.get(selectedPlanetIndex), XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex));
            healPlanet.setText("10 mins");
            healPlanet.setTouchable(Touchable.disabled);
        } else {
            for (Button button : slots) {
                button.setTint(Tint.MEDIUM_GRAY);
            }
            slots.get(outcome).setTint(Tint.RARE_YELLOW);
            setSelectedPlanet(planets.get(outcome), outcome);
            updateHatButton(planets.get(outcome));
        }
    }


    public void setupPlanets(){
        Planet planet1 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_1);
        Planet planet2 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_2);
        Planet planet3 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_3);
        Planet planet4 = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_4);

        if(planet1 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet1.getShowColor()));
            planets.add(planet1);
        }

        if(planet2 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet2.getShowColor()));
            planets.add(planet2);
        }

        if(planet3 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet3.getShowColor()));
            planets.add(planet3);
        }

        if(planet4 !=null){
            miniPlanets.add(new TintedImage(Pic.Solar_Planet, planet4.getShowColor()));
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

        for(int i = 0; i < planets.size(); i++){
            planets.get(i).setSize(250, 250);
            planets.get(i).setPosition(510, 100);
            planets.get(i).setVisible(false);
            if (planets.get(i).isInhabitable()){
                if(planets.get(i).getAmtofTime() == 600000){
                    if(planets.get(i).getTimeStart()+600000 < TimeUtils.millis()){
                        planets.get(i).setInhabitable(false);
                        XmlManager.getInstance().savePlanet(planets.get(i), XmlManager.getInstance().convertSlotintToString(i));
                    } else {
                        planets.get(i).instantBurn();
                        healPlanet.setText("10 mins");
                    }
                } else if(planets.get(i).getAmtofTime() == 150){
                    int steps = App.stepCallback.getStepsSince(planets.get(i).getTimeStart());
                    if( steps >= 150){
                        planets.get(i).setInhabitable(false);
                        XmlManager.getInstance().savePlanet(planets.get(i), XmlManager.getInstance().convertSlotintToString(i));
                    } else {
                        planets.get(i).instantBurn();
                        healPlanet.setText("150 Steps");
                    }
                } else {
                    planets.get(i).instantBurn();
                }

            }
            planets.get(i).setBackgroundTint(Tint.DARK_PURPLE);
            addActor(planets.get(i));
        }

        globeRank = new GlobeRank(1,0,100); // default init params
        globeRank.setPosition(50, 50);
        addActor(globeRank);

        techPlanetStats = new TechPlanetStats(0,0,"Blue"); // default init params
        techPlanetStats.setPosition(930, 100);
        addActor(techPlanetStats);

        setSelectedPlanet(planets.get(restartSlot), restartSlot);
        setupHatButton(planets.get(restartSlot));
        slots.get(restartSlot).setTint(Tint.RARE_YELLOW);

    }

    public void setSelectedPlanet(Planet selectedPlanet, int index){
        selectedPlanetIndex = index;
        for(Planet planet : planets){
            planet.setVisible(false);
        }
        selectedPlanet.setVisible(true);
        globeRank.newPlanetRanks(selectedPlanet.getGlobeRank(), selectedPlanet.getCurrentXP(), selectedPlanet.getRankXP());
        techPlanetStats.setParams(selectedPlanet.getPlanetSize(), selectedPlanet.getPlanetEnergy(), selectedPlanet.getColorType());
        if(selectedPlanet.isInhabitable()){
            healPlanet.setVisible(true);
            if(planets.get(selectedPlanetIndex).getAmtofTime() == 0) {
                healPlanet.setTouchable(Touchable.enabled);
                healPlanet.setText("Heal Planet");
            } else if(planets.get(selectedPlanetIndex).getAmtofTime() == 600000){
                healPlanet.setText("10 mins");
                healPlanet.setTouchable(Touchable.disabled);
            } else {
                healPlanet.setText("150 steps");
                healPlanet.setTouchable(Touchable.disabled);
            }
            habitableLabel.setText("unInhabitable");
        } else {
            healPlanet.setVisible(false);
            healPlanet.setTouchable(Touchable.disabled);
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
                    LogUtils.Log("Remove Hat");
                    Hat hat = planets.get(selectedPlanetIndex).getHat();
                    LogUtils.Log(hat.getHatID());
                    planets.get(selectedPlanetIndex).setHat(new Hat(0));
                    XmlManager.getInstance().addToHatsList(hat);
                    XmlManager.getInstance().savePlanet(planets.get(selectedPlanetIndex), XmlManager.getInstance().convertSlotintToString(selectedPlanetIndex));
                    updateHatButton(planets.get(selectedPlanetIndex));
                    hatsInventory.updateInventory();
                }
            }
        });
        addActor(remove);
    }

    public void updateHatButton(Planet selectedPlanet){
        if(selectedPlanet.getHat().getHatID() == 0){
            remove.setText("Select Hat");
        } else {
            remove.setText("Remove Hat");
        }
    }


    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public int getSelectedPlanetIndex() {
        return selectedPlanetIndex;
    }
}
