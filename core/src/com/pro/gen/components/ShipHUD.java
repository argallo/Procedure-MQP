package com.pro.gen.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.screens.PlanetScreen;
import com.pro.gen.screens.ScreenFactory;
import com.pro.gen.screens.ShipScreen;
import com.pro.gen.screens.SolarSystemScreen;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 10/21/2015.
 */
public class ShipHUD extends Group{

    public enum ShipScreenTypes {
        INSIDE, EXPLORE, INVENTORY, HOME, SOLARSYSTEM, PLANET, LAND;
    }

    private ShipScreen shipScreen;
    private ShipScreenTypes shipScreenTypes;
    private Action slideIn, slideOut;
    private TintedImage slidingScreenLeft, slidingScreenRight;
    private ScreenFactory screenFactory;

    //Default to inside ship
    public ShipHUD(){
        this(ShipScreenTypes.INSIDE);
    }

    /**
     * Constructor for creating ships HUD screen.
     * @param shipScreenTypes
     */
    public ShipHUD(ShipScreenTypes shipScreenTypes) {
        screenFactory = new ScreenFactory(this);
        this.shipScreen = screenFactory.createScreen(shipScreenTypes);
        this.shipScreenTypes = shipScreenTypes;
        setupScreenSliders();
        buildActions();

    }


    /**
     * Creates two screen sliders that can be used during transitions of screens on the HUD
     */
    private void setupScreenSliders(){
        slidingScreenLeft = new TintedImage(Constants.RECTANGLE, Constants.ORANGE);
        slidingScreenRight = new TintedImage(Constants.RECTANGLE, Constants.ORANGE);
        slidingScreenLeft.setSize(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT);
        slidingScreenRight.setSize(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT);
        slidingScreenLeft.setPosition(-slidingScreenLeft.getWidth(), 0);
        slidingScreenRight.setPosition(Constants.VIRTUAL_WIDTH, 0);
        slidingScreenLeft.setVisible(false);
        slidingScreenRight.setVisible(false);
        this.addActor(slidingScreenLeft);
        this.addActor(slidingScreenRight);
    }

    /**
     * Build Actions that can be used as animations during screen transitions
     */
    private void buildActions(){
        //Slides the screen sliders over the screen
        slideIn = new Action() {
            @Override
            public boolean act(float delta) {
                slidingScreenLeft.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(-slidingScreenLeft.getWidth(), 0), Actions.moveBy(slidingScreenLeft.getWidth(), 0, 0.4f, Interpolation.exp10In)));
                slidingScreenRight.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(Constants.VIRTUAL_WIDTH, 0), Actions.moveBy(-slidingScreenRight.getWidth(), 0, 0.4f, Interpolation.exp10In)));
                return true;
            }
        };

        //Slides the screen sliders off the screen
        slideOut = new Action() {
            @Override
            public boolean act(float delta) {
                slidingScreenLeft.addAction(Actions.sequence(Actions.moveBy(-slidingScreenLeft.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                slidingScreenRight.addAction(Actions.sequence(Actions.moveBy(slidingScreenRight.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                return true;
            }
        };
    }


    public void transitionTo(ShipScreenTypes shipScreenTypes){
        ShipScreen shipScreen = screenFactory.createScreen(shipScreenTypes);

        if(this.shipScreenTypes == ShipScreenTypes.EXPLORE && shipScreenTypes == ShipScreenTypes.SOLARSYSTEM){
            hyperSpeedTransition(shipScreen, shipScreenTypes);
        } else if (this.shipScreenTypes == ShipScreenTypes.SOLARSYSTEM && shipScreenTypes == ShipScreenTypes.PLANET){
            zoomToPlanetTransition(shipScreen, shipScreenTypes);
        } else if (this.shipScreenTypes == ShipScreenTypes.PLANET && shipScreenTypes == ShipScreenTypes.LAND){
            fadeToLandTransition();
        } else {
            simpleTransition(shipScreen, shipScreenTypes);
        }
    }


    /*
    * TRANSITION METHODS
    * */

    /**
     * Simple Transition will animate ship screen doors, removing old actors and adding new ones.
     * Then setting the screentype to the new screen type.
     * @param shipScreen
     * @param shipScreenTypes
     */
    private void simpleTransition(final ShipScreen shipScreen, final ShipScreenTypes shipScreenTypes){
        this.addAction(Actions.sequence(slideIn, Actions.delay(0.5f), new Action() {
            @Override
            public boolean act(float delta) {
                removeScreen();
                setNewScreen(shipScreen, shipScreenTypes);
                addNewScreen();
                return true;
            }
        }, Actions.delay(0.5f), slideOut));
    }

    private void hyperSpeedTransition(final ShipScreen shipScreen, final ShipScreenTypes shipScreenTypes){
        this.addActorAt(0,shipScreen);
        shipScreen.setVisible(false);
        Action action = new Action() {
            @Override
            public boolean act(float delta) {
                getShipScreen().addAction(Actions.sequence(Actions.scaleBy(20, 20, 2f, Interpolation.exp10In), Actions.visible(false), new Action() {
                    @Override
                    public boolean act(float delta) {
                        removeScreen();
                        setNewScreen(shipScreen, shipScreenTypes);
                        return true;
                    }
                }));

                shipScreen.addAction(Actions.sequence(Actions.scaleTo(0.1f, 0.1f), Actions.alpha(0), Actions.visible(true), Actions.delay(1f),
                        Actions.parallel(Actions.scaleBy(1, 1, 2f, Interpolation.exp10In), Actions.alpha(1,1f))));
                return true;
            }
        };
        this.addAction(Actions.sequence(action));
    }

    private void zoomToPlanetTransition(final ShipScreen shipScreen, final ShipScreenTypes shipScreenTypes){
        ((PlanetScreen)shipScreen).createPlanetFromColor(((SolarSystemScreen) getShipScreen()).getSelectedPlanet().getTint());
        Action action = new Action() {
            @Override
            public boolean act(float delta) {
                ((SolarSystemScreen) getShipScreen()).getSelectedPlanet().addAction(Actions.alpha(0, 0.2f));
                shipScreen.addAction(Actions.sequence(Actions.scaleTo(0.25f, 0.25f), Actions.visible(true), Actions.scaleBy(0.75f, 0.75f, 0.7f, Interpolation.exp5In)));
                return true;
            }
        };
        this.addActorAt(0, shipScreen);
        this.addAction(Actions.sequence(action, new Action() {
            @Override
            public boolean act(float delta) {
                removeScreen();
                setNewScreen(shipScreen, shipScreenTypes);
                return true;
            }
        }));
    }

    public void fadeToLandTransition(){
        Background fadeout = new Background(Constants.PIXEL, Color.WHITE);
        fadeout.addAction(Actions.alpha(0));
        addActor(fadeout);
        this.getParent().swapActor(1, 2);
        fadeout.addAction(Actions.sequence(Actions.alpha(1, 1.5f, Interpolation.fade), new Action() {
            @Override
            public boolean act(float delta) {
                ViewManager.getInstance().transitionViewTo(ViewID.LANDING, TransitionType.DEFAULT_TRANSITION);
                return true;
            }
        }));

    }

    private void setNewScreen(ShipScreen shipScreen, ShipScreenTypes shipScreenTypes){
        this.shipScreen = shipScreen;
        this.shipScreenTypes = shipScreenTypes;
    }

    private void addNewScreen(){
        this.addActorAt(0, this.shipScreen);
    }

    private void removeScreen(){
        removeActor(this.shipScreen);
    }

    public ShipScreen getShipScreen() {
        return shipScreen;
    }
}