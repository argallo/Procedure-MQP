package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.popups.BossWinningsPopup;
import com.pro.gen.popups.LosingsPopup;
import com.pro.gen.popups.WinningsPopup;
import com.pro.gen.random.RareRandomPlanet;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;
import com.pro.gen.worldcomponents.GlobeRank;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.LaserAnimation;
import com.pro.gen.worldcomponents.MiniGame;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.TechPlanetStats;

/**
 * Created by Gallo on 3/27/2016.
 */
public class BossBattleView extends BattleView{


    private Background background;
    private TitleLabel titleBar;
    private Planet enemyPlanet, playerPlanet;
    private MiniGame miniGame;
    LaserAnimation laserAnimation;
    private BossWinningsPopup winningsPopup;
    private LosingsPopup losingsPopup;

    private GlobeRank globeRank;
    private TechPlanetStats techPlanetStats;

    private TintedImage fadeOutLayer;
    private Button backBtn;
    private String currentSlot;


    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        enemyPlanet = XmlManager.getInstance().getPlanetFromSlot(PreferenceManager.BOSS_SLOT);
        playerPlanet = XmlManager.getInstance().getFirstHabitablePlanet();
        currentSlot = XmlManager.getInstance().getCurrentSlot();
        titleBar = new TitleLabel("Boss Battle");
        miniGame = new MiniGame(this);
        fadeOutLayer = new TintedImage(Pic.Pixel, new Color(Tint.STAR_WHITE));
        fadeOutLayer.setTouchable(Touchable.disabled);
        backBtn = new Button(Pic.Back_Button, Color.WHITE);
        backBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(1);
            }
        });

    }

    @Override
    public void setSizes() {
        fadeOutLayer.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        backBtn.setSize(60, 60);
        enemyPlanet.setSize(350, 350);
        playerPlanet.setSize(350, 350);
    }

    @Override
    public void setPositions() {
        backBtn.setPosition(50, 55);
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
        addActor(miniGame);

    }

    @Override
    public void handle(int outcome) {
        switch(outcome){
            case WinningsPopup.ABSORB:
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        globeRank = new GlobeRank(playerPlanet.getGlobeRank(), playerPlanet.getCurrentXP(), playerPlanet.getRankXP());
                        int rankups = playerPlanet.gainXp(enemyPlanet.getRankXP() / 3);
                        XmlManager.getInstance().savePlanet(playerPlanet, XmlManager.getInstance().getCurrentSlot());
                        XmlManager.getInstance().saveBossLevel(XmlManager.getInstance().getBossLevel()+1);
                        XmlManager.getInstance().saveBossPlanet(new Planet(new RareRandomPlanet((XmlManager.getInstance().getBossLevel()+1)*2,(XmlManager.getInstance().getBossLevel()+1)*2,true,false)));
                        globeRank.setPosition(450, 50);
                        globeRank.rankUp(rankups, playerPlanet.getCurrentXP());

                        techPlanetStats = new TechPlanetStats(playerPlanet.getPlanetSize(), playerPlanet.getPlanetEnergy(), playerPlanet.getColorType());
                        techPlanetStats.setPosition(500, 230);
                        addActor(techPlanetStats);
                        addActor(globeRank);
                        addActor(backBtn);
                        return true;
                    }
                }));
                break;
            case WinningsPopup.MONEY:
                XmlManager.getInstance().saveBossLevel(XmlManager.getInstance().getBossLevel()+1);
                XmlManager.getInstance().saveBossPlanet(new Planet(new RareRandomPlanet((XmlManager.getInstance().getBossLevel()+1)*2,(XmlManager.getInstance().getBossLevel()+1)*2,(MathUtils.random(0, 100) > 70) ? false : true, (MathUtils.random(0,100) > 22) ? false : true)));
                addActor(backBtn);
                break;
            case WinningsPopup.KEEP:
                XmlManager.getInstance().savePlanet(enemyPlanet);
                XmlManager.getInstance().saveBossLevel(XmlManager.getInstance().getBossLevel()+1);
                XmlManager.getInstance().saveBossPlanet(new Planet(new RareRandomPlanet((XmlManager.getInstance().getBossLevel()+1)*2,(XmlManager.getInstance().getBossLevel()+1)*2,(MathUtils.random(0,100) > 70) ? false : true, (MathUtils.random(0,100) > 22) ? false : true)));
                addActor(backBtn);
                break;
            case LosingsPopup.NEXT:
                openingAnimation();
                addActor(fadeOutLayer);
                fadeOutLayer.setVisible(true);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.2f), Actions.fadeIn(0.5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.BOSS_BATTLE, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
            case LosingsPopup.RETURN:
                openingAnimation();
                addActor(fadeOutLayer);
                fadeOutLayer.setVisible(true);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.2f), Actions.fadeIn(0.5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.MAIN_MENU, TransitionType.DEFAULT_TRANSITION);
                        return true;
                    }
                }));
                break;
            case 1:
                openingAnimation();
                addActor(fadeOutLayer);
                fadeOutLayer.setVisible(true);
                fadeOutLayer.addAction(Actions.sequence(Actions.delay(0.2f), Actions.fadeIn(0.5f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        ViewManager.getInstance().transitionViewTo(ViewID.MAIN_MENU, TransitionType.DEFAULT_TRANSITION);
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

        //CHECK FOR KO
        if(playerPlanet.getHat().getEffect() == Hat.KO){
            int chance = MathUtils.random(100);
            if(chance < playerPlanet.getHat().getPowerAmt()){
               return 0;
            }
        }
        if(enemyPlanet.getHat().getEffect() == Hat.KO){
            int chance = MathUtils.random(100);
            if(chance < enemyPlanet.getHat().getPowerAmt()){
                return 1;
            }
        }
        if(playerTotal > enemyTotal){
            return 0;
        } else {
            return 1;
        }
    }

    public int getBaseColorBonus(){
        String playerColor = "";
        String enemyColor = "";
        if(playerPlanet.getHat().getEffect() > 0 && playerPlanet.getHat().getEffect() < 7){
            enemyColor = colorHatEffect(playerPlanet.getHat());
        } else {
            enemyColor = enemyPlanet.getColorType();
        }

        if(enemyPlanet.getHat().getEffect() > 0 && enemyPlanet.getHat().getEffect() < 7){
            playerColor = colorHatEffect(enemyPlanet.getHat());
        } else {
            playerColor = playerPlanet.getColorType();
        }

        int playerColorID = getColorID(playerColor);
        int enemyColorID = getColorID(enemyColor);
        return (playerColorID - enemyColorID) % 6;
    }

    public String colorHatEffect(Hat hat){
        switch (hat.getEffect()){
            case Hat.COLOR_BLUE:
                return "Blue";
            case Hat.COLOR_GREEN:
                return "Green";
            case Hat.COLOR_ORANGE:
                return "Orange";
            case Hat.COLOR_PURPLE:
                return "Purple";
            case Hat.COLOR_RED:
                return "Red";
            case Hat.COLOR_YELLOW:
                return "Yellow";
            default:
                return "";
        }
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
        laserAnimation = new LaserAnimation(winner, this);
        addActor(laserAnimation);
    }

    public void activatePopup(int popup){
        if(popup == 0){
            winningsPopup = new BossWinningsPopup(this);
            winningsPopup.activatePopup();
            addActor(winningsPopup);
        } else {
            XmlManager.getInstance().savePlanet(playerPlanet, currentSlot);
            losingsPopup = new LosingsPopup(this);
            addActor(losingsPopup);
            losingsPopup.activatePopup();
        }
    }

    public Planet getEnemyPlanet() {
        return enemyPlanet;
    }

    public Planet getPlayerPlanet() {
        return playerPlanet;
    }

    public void openingAnimation(){
        fadeOutLayer.setVisible(false);
        fadeOutLayer.addAction(Actions.fadeOut(0));
    }

}
