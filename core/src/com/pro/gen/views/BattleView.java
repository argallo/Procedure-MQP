package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleBar;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Range;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.MiniGame;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/21/2016.
 */
public class BattleView extends BaseView {


    private Background background;
    private TitleLabel titleBar;
    private Planet planetEnemy, playerPlanet;
    private MiniGame miniGame;
    //private KillerShip killerShip;
    //private WinningsPopup winningsPopup;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        planetEnemy = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.BATTLE_SLOT);
        playerPlanet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_1);//TODO: check to see which are habitable
        titleBar = new TitleLabel("Laser Battle");
        miniGame = new MiniGame(this);
        //winningsPopup = new WinningsPopup(this);
        //killerShip = new KillerShip(winningsPopup);
        //planetEnemy.burn();
    }

    @Override
    public void setSizes() {
        planetEnemy.setSize(350, 350);
        playerPlanet.setSize(350, 350);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(Constants.VIRTUAL_WIDTH/2 - titleBar.getWidth()/2, 600);
        planetEnemy.setPosition(30, Constants.VIRTUAL_HEIGHT / 2 - planetEnemy.getHeight() / 2);
        playerPlanet.setPosition(900, Constants.VIRTUAL_HEIGHT / 2 - planetEnemy.getHeight() / 2);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(planetEnemy);
        addActor(playerPlanet);
        addActor(titleBar);
        addActor(miniGame);
        //addActor(killerShip);
        //addActor(winningsPopup);
    }

    @Override
    public void handle(int outcome) {
        switch(outcome){
            case AbsPopup.YES:
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.SOLAR_SYSTEM, TransitionType.SLIDE_R_TRANSITION);
                        return true;
                    }
                }));
                break;
        }
    }

    public String getPlayerPlanetBaseColor(){
        return playerPlanet.getColorType();
    }

    /**
     *     public void activatePopup() {
     background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2),
     Actions.visible(true),
     Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
    @Override
    public boolean act(float delta) {
    question.setVisible(true);
    no.setVisible(true);
    yes.setVisible(true);
    return true;
    }
    }));
     }
     */


}
