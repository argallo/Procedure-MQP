package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TintedImage;
import com.pro.gen.popups.AbsPopup;
import com.pro.gen.popups.WinningsPopup;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.BattleView;

/**
 * Created by Gallo on 3/24/2016.
 */
public class LaserAnimation extends Group {

    public final static int PLAYER = 0;
    public final static int ENEMY = 1;

    private TintedImage playerBeamTip, enemyBeamTip;
    private TintedImage playerBeam, enemyBeam;
    private TintedImage blastBackgroundO, blastBackgroundR, blast;

    private BattleView battleView;
    private int winner;

    public LaserAnimation(int winner, BattleView battleView){
        this.battleView = battleView;
        this.winner = winner;

        playerBeamTip = new TintedImage(Pic.Circle_Large, Tint.STAR_BLUE);
        enemyBeamTip = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED);

        playerBeam = new TintedImage(Pic.Curve_rectangle, Tint.STAR_BLUE);
        enemyBeam = new TintedImage(Pic.Curve_rectangle, Tint.BLAST_RED);

        blastBackgroundO = new TintedImage(Pic.Circle_Large, Tint.BLAST_ORANGE_A);
        blastBackgroundR = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED_A);
        blast = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED);

        playerBeamTip.setSize(1, 1);
        playerBeamTip.setOrigin(Align.center);
        playerBeamTip.setPosition(898, 355);
        playerBeamTip.setVisible(false);
        addActor(playerBeamTip);

        enemyBeamTip.setSize(1, 1);
        enemyBeamTip.setOrigin(Align.center);
        enemyBeamTip.setPosition(378, 355);
        enemyBeamTip.setVisible(false);
        addActor(enemyBeamTip);

        playerBeam.setSize(10, 40);
        playerBeam.setPosition(903, 335);
        playerBeam.setVisible(false);
        addActor(playerBeam);

        enemyBeam.setSize(10, 40);
        enemyBeam.setPosition(367, 335);
        enemyBeam.setVisible(false);
        addActor(enemyBeam);


        blastBackgroundO.setSize(1, 1);
        blastBackgroundO.setOrigin(Align.center);
        blastBackgroundO.setPosition(Constants.VIRTUAL_WIDTH/2, 353);
        blastBackgroundO.setVisible(false);
        addActor(blastBackgroundO);


        blastBackgroundR.setSize(1, 1);
        blastBackgroundR.setOrigin(Align.center);
        blastBackgroundR.setPosition(Constants.VIRTUAL_WIDTH/2, 353);
        blastBackgroundR.setVisible(false);
        addActor(blastBackgroundR);


        blast.setSize(1, 1);
        blast.setOrigin(Align.center);
        blast.setPosition(Constants.VIRTUAL_WIDTH/2, 353);
        blast.setVisible(false);
        addActor(blast);


        if(winner == PLAYER) {
            activateLasersPlayer();
        } else {
            activateLasersEnemy();
        }

    }

    public void activateLasersPlayer(){

        playerBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        enemyBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        playerBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(-300, 0, 0.2f)), Actions.delay(0.6f),
                Actions.parallel(Actions.sizeBy(50, 0, 1.2f, Interpolation.swingOut), Actions.moveBy(-50, 0, 1.2f, Interpolation.swingOut)), Actions.delay(0.7f),
                Actions.parallel(Actions.sizeBy(-180, 0, 2f, Interpolation.swingOut), Actions.moveBy(180, 0, 2f, Interpolation.swingOut)), Actions.delay(0.6f),
                Actions.parallel(Actions.sizeBy(390, 0, 1f, Interpolation.exp5), Actions.moveBy(-390, 0, 1f,Interpolation.exp5))));
        enemyBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(0, 0, 0.2f)), Actions.delay(0.6f),
                Actions.sizeBy(-50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                Actions.sizeBy(180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                Actions.sizeBy(-390, 0, 1f, Interpolation.exp5)));

        blast.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(35, 35, 0.6f, Interpolation.swingOut),
                Actions.sequence(Actions.moveBy(-50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(-390, 0, 1f, Interpolation.exp5))));

        blastBackgroundR.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(50, 50, 0.6f, Interpolation.swingOut), Actions.parallel(
                Actions.repeat(10, Actions.sequence(Actions.scaleBy(30, 30, 0.6f, Interpolation.pow2), Actions.scaleBy(-30, -30, 0.8f, Interpolation.pow2))),
                Actions.sequence(Actions.moveBy(-50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(-390, 0, 1f, Interpolation.exp5)))));

        blastBackgroundO.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(80, 80, 0.6f, Interpolation.swingOut), new Action() {
            @Override
            public boolean act(float delta) {
                battleView.getEnemyPlanet().burn();
                return true;
            }
        },Actions.parallel(
                Actions.repeat(10, Actions.sequence(Actions.scaleBy(30, 30, 0.8f, Interpolation.pow2), Actions.scaleBy(-30, -30, 0.6f, Interpolation.pow2))),
                Actions.sequence(Actions.moveBy(-50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(-390, 0, 1f, Interpolation.exp5))), new Action() {
            @Override
            public boolean act(float delta) {
                deactivateLaser();
                return true;
            }
        }));

    }

    public void activateLasersEnemy(){

        playerBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        enemyBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        playerBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(-300, 0, 0.2f)), Actions.delay(0.6f),
                Actions.parallel(Actions.sizeBy(-50, 0, 1.2f, Interpolation.swingOut), Actions.moveBy(50, 0, 1.2f, Interpolation.swingOut)), Actions.delay(0.7f),
                Actions.parallel(Actions.sizeBy(180, 0, 2f, Interpolation.swingOut), Actions.moveBy(-180, 0, 2f, Interpolation.swingOut)), Actions.delay(0.6f),
                Actions.parallel(Actions.sizeBy(-390, 0, 1f, Interpolation.exp5), Actions.moveBy(390, 0, 1f,Interpolation.exp5))));
        enemyBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(0, 0, 0.2f)), Actions.delay(0.6f),
                Actions.sizeBy(50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                Actions.sizeBy(-180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                Actions.sizeBy(390, 0, 1f, Interpolation.exp5)));

        blast.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(35, 35, 0.6f, Interpolation.swingOut),
                Actions.sequence(Actions.moveBy(50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(-180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(390, 0, 1f, Interpolation.exp5))));

        blastBackgroundR.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(50, 50, 0.6f, Interpolation.swingOut), Actions.parallel(
                Actions.repeat(10, Actions.sequence(Actions.scaleBy(30, 30, 0.6f, Interpolation.pow2), Actions.scaleBy(-30, -30, 0.8f, Interpolation.pow2))),
                Actions.sequence(Actions.moveBy(50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(-180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(390, 0, 1f, Interpolation.exp5)))));

        blastBackgroundO.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(80, 80, 0.6f, Interpolation.swingOut), new Action() {
            @Override
            public boolean act(float delta) {
                battleView.getPlayerPlanet().burn();
                return true;
            }
        },Actions.parallel(
                Actions.repeat(10, Actions.sequence(Actions.scaleBy(30, 30, 0.8f, Interpolation.pow2), Actions.scaleBy(-30, -30, 0.6f, Interpolation.pow2))),
                Actions.sequence(Actions.moveBy(50, 0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                        Actions.moveBy(-180, 0, 2f, Interpolation.swingOut), Actions.delay(0.6f),
                        Actions.moveBy(390, 0, 1f, Interpolation.exp5))), new Action() {
            @Override
            public boolean act(float delta) {
                deactivateLaser();
                return true;
            }
        }));

    }



    public void deactivateLaser(){
        blastBackgroundO.addAction(Actions.sequence(Actions.scaleTo(0, 0, 0.3f), Actions.visible(false)));
        blastBackgroundR.addAction(Actions.sequence(Actions.scaleTo(0, 0, 0.3f), Actions.visible(false)));
        blast.addAction(Actions.sequence(Actions.scaleTo(0, 0, 0.3f), Actions.visible(false)));
        playerBeam.addAction(Actions.sequence(Actions.delay(0.1f),Actions.parallel(Actions.sizeTo(10, 40, 0.1f), Actions.moveTo(903, 335, 0.1f)), Actions.visible(false)));
        enemyBeam.addAction(Actions.sequence(Actions.delay(0f),Actions.parallel(Actions.sizeTo(10, 40, 0.1f), Actions.moveTo(367, 335, 0.1f)), Actions.visible(false)));
        enemyBeamTip.addAction(Actions.sequence(Actions.delay(0f), Actions.scaleTo(0, 0, 0.1f), Actions.visible(false)));
        playerBeamTip.addAction(Actions.sequence(Actions.delay(0f), Actions.scaleTo(0, 0, 0.1f), Actions.visible(false), new Action() {
            @Override
            public boolean act(float delta) {
                if(winner == PLAYER){
                    battleView.activatePopup(PLAYER);
                } else {
                    battleView.activatePopup(ENEMY);
                }
                return true;
            }
        }));
    }





}
