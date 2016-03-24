package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 3/24/2016.
 */
public class LaserAnimation extends Group {

    private TintedImage playerBeamTip, enemyBeamTip;
    private TintedImage playerBeam, enemyBeam;
    private TintedImage blastBackgroundO, blastBackgroundR, blast;

    public LaserAnimation(){
        playerBeamTip = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED);
        enemyBeamTip = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED);

        playerBeam = new TintedImage(Pic.Curve_rectangle, Tint.BLAST_RED);
        enemyBeam = new TintedImage(Pic.Curve_rectangle, Tint.BLAST_RED);

        blastBackgroundO = new TintedImage(Pic.Circle_Large, Tint.BLAST_ORANGE_A);
        blastBackgroundR = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED_A);
        blast = new TintedImage(Pic.Circle_Large, Tint.BLAST_RED);

        playerBeamTip.setSize(1, 1);
        playerBeamTip.setOrigin(Align.center);
        playerBeamTip.setPosition(895, 355);
        playerBeamTip.setVisible(false);
        addActor(playerBeamTip);

        enemyBeamTip.setSize(1, 1);
        enemyBeamTip.setOrigin(Align.center);
        enemyBeamTip.setPosition(375, 355);
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



        activateLasers();


    }

    public void activateLasers(){

        playerBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        enemyBeamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(50, 50, 3.8f, Interpolation.bounceIn)));
        playerBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(-300, 0, 0.2f))));
        enemyBeam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(300, 40, 0.2f), Actions.moveBy(0, 0, 0.2f))));


        blast.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(30, 30, 0.6f, Interpolation.swingOut)));
        blastBackgroundR.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(50, 50, 0.6f, Interpolation.swingOut), Actions.parallel(
                Actions.repeat(6, Actions.sequence(Actions.scaleBy(30, 30, 0.7f, Interpolation.pow2), Actions.scaleBy(-30, -30, 0.7f, Interpolation.pow2))),
                Actions.sequence(Actions.moveBy(-50,0, 1.2f, Interpolation.swingOut), Actions.delay(0.7f),
                                 Actions.moveBy(180,0, 2f,Interpolation.swingOut), Actions.delay(0.9f),
                                 Actions.moveBy(-360, 0, 3f, Interpolation.swingOut)))));

        blastBackgroundO.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(40, 40), Actions.scaleBy(80, 80, 1f, Interpolation.swingOut), Actions.repeat(6, Actions.sequence(
                Actions.scaleBy(35, 35, 0.7f, Interpolation.pow2), Actions.scaleBy(-35, -35, 0.7f, Interpolation.pow2)
        )), new Action() {
            @Override
            public boolean act(float delta) {
                //deactivateLaser();
                return true;
            }
        }));

    }





}
