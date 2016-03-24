package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.LaserAnimation;
import com.pro.gen.worldcomponents.MiniGame;
import com.pro.gen.worldcomponents.Planet;

/**
 * Created by Gallo on 1/21/2016.
 */
public class BattleView extends BaseView {


    private Background background;
    private TitleLabel titleBar;
    private Planet enemyPlanet, playerPlanet;
    private MiniGame miniGame;
    LaserAnimation laserAnimation;
    //private KillerShip killerShip;
    //private WinningsPopup winningsPopup;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        enemyPlanet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.BATTLE_SLOT);
        playerPlanet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.SLOT_1);//TODO: check to see which are habitable
        titleBar = new TitleLabel("Laser Battle");
        miniGame = new MiniGame(this);


        laserAnimation = new LaserAnimation();
        //winningsPopup = new WinningsPopup(this);

    }

    @Override
    public void setSizes() {
        enemyPlanet.setSize(350, 350);
        playerPlanet.setSize(350, 350);
    }

    @Override
    public void setPositions() {
        titleBar.setPosition(Constants.VIRTUAL_WIDTH / 2 - titleBar.getWidth() / 2, 600);
        enemyPlanet.setPosition(30, Constants.VIRTUAL_HEIGHT / 2 - enemyPlanet.getHeight() / 2);
        playerPlanet.setPosition(900, Constants.VIRTUAL_HEIGHT / 2 - enemyPlanet.getHeight() / 2);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(enemyPlanet);
        addActor(playerPlanet);
        addActor(titleBar);
        //addActor(miniGame);
        addActor(laserAnimation);



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

    public void beginBattle(int targetsAmt, float powerPercent){
        removeActor(miniGame);
        int winner = findWinner(targetsAmt, powerPercent);
        playWinningAnimation(winner);
    }

    public int findWinner(int targetsAmt, float powerPercent){
        float playerTotal = playerPlanet.getPlanetSize()*(targetsAmt/10) + playerPlanet.getPlanetEnergy()*powerPercent;
        float enemyTotal = enemyPlanet.getPlanetSize()*(targetsAmt/15) + enemyPlanet.getPlanetEnergy()*powerPercent;
        int baseColorBonus = getBaseColorBonus();
        switch (baseColorBonus){
            case 1:
                playerTotal*=1.3;
                break;
            case 2:
                playerTotal*=1.1;
                break;
            case 5:
                enemyTotal*=1.28;
                break;
            case 4:
                enemyTotal*=1.09;
                break;
        }
        if(playerTotal > enemyTotal){
            return 0;
        } else {
            return 1;
        }
    }

    public int getBaseColorBonus(){
        String playerColor = playerPlanet.getColorType();
        String enemyColor = enemyPlanet.getColorType();
        int playerColorID = getColorID(playerColor);
        int enemyColorID = getColorID(enemyColor);
        return (playerColorID - enemyColorID) % 6;
    }

    public int getColorID(String color){
        if(color.equals("Blue")){
            return 0;
        } else if(color.equals("Green")){
            return 1;
        }else if(color.equals("Yellow")){
            return 2;
        }else if(color.equals("Orange")){
            return 3;
        }else if(color.equals("Red")){
            return 4;
        }else if(color.equals("Purple")){
            return 5;
        }
        return -1;
    }

    public void playWinningAnimation(int winner){

    }


}
