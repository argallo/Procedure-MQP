package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 1/21/2016.
 */
public class KillerShip extends Group {

    private TintedImage ship, shipGun, shipHolder, beamTip, beam, blastBackgroundO, blastBackgroundR, blast;

    private com.pro.gen.popups.AbsPopup popup;

    public KillerShip(com.pro.gen.popups.AbsPopup popup){
        this.popup = popup;
        setVisible(false);
        shipHolder = new TintedImage(com.pro.gen.utils.Pic.Pixel, com.pro.gen.utils.Tint.MEDIUM_GRAY);
        shipHolder.setSize(7,25);
        shipHolder.setPosition(1040, 410);
        addActor(shipHolder);
        shipGun = new TintedImage(com.pro.gen.utils.Pic.Ship_Gun);
        shipGun.setSize(80, 40);
        shipGun.setPosition(995, 430);
        addActor(shipGun);
        ship = new TintedImage(com.pro.gen.utils.Pic.Ship);
        ship.setSize(100, 50);
        ship.setPosition(1020, 370);
        addActor(ship);


        beam = new TintedImage(com.pro.gen.utils.Pic.Curve_rectangle, com.pro.gen.utils.Tint.BLAST_RED);
        beam.setSize(10, 20);
        beam.setPosition(988, 440);
        beam.setVisible(false);
        addActor(beam);

        beamTip = new TintedImage(com.pro.gen.utils.Pic.Circle_Large, com.pro.gen.utils.Tint.BLAST_RED);
        beamTip.setSize(1, 1);
        beamTip.setOrigin(Align.center);
        beamTip.setPosition(990, 450);
        beamTip.setVisible(false);
        addActor(beamTip);

        blastBackgroundO = new TintedImage(com.pro.gen.utils.Pic.Circle_Large, com.pro.gen.utils.Tint.BLAST_ORANGE_A);
        blastBackgroundO.setSize(1, 1);
        blastBackgroundO.setOrigin(Align.center);
        blastBackgroundO.setPosition(788, 450);
        blastBackgroundO.setVisible(false);
        addActor(blastBackgroundO);

        blastBackgroundR = new TintedImage(com.pro.gen.utils.Pic.Circle_Large, com.pro.gen.utils.Tint.BLAST_RED_A);
        blastBackgroundR.setSize(1, 1);
        blastBackgroundR.setOrigin(Align.center);
        blastBackgroundR.setPosition(788, 450);
        blastBackgroundR.setVisible(false);
        addActor(blastBackgroundR);

        blast = new TintedImage(com.pro.gen.utils.Pic.Circle_Large, com.pro.gen.utils.Tint.BLAST_RED);
        blast.setSize(1, 1);
        blast.setOrigin(Align.center);
        blast.setPosition(788, 450);
        blast.setVisible(false);
        addActor(blast);

        activateLaser();
    }

    public void activateLaser(){
        addAction(Actions.sequence(Actions.moveTo(300, 0), Actions.visible(true), Actions.moveTo(0, 0, 3f, Interpolation.pow2Out)));
        beamTip.addAction(Actions.sequence(Actions.delay(3f), Actions.visible(true), Actions.scaleBy(30, 30, 3.8f, Interpolation.bounceIn)));
        beam.addAction(Actions.sequence(Actions.delay(6.5f), Actions.visible(true), Actions.parallel(Actions.sizeTo(210, 20, 0.2f), Actions.moveBy(-210, 0, 0.2f))));


        blast.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(20, 20), Actions.scaleBy(10, 10, 0.6f, Interpolation.swingOut)));
        blastBackgroundR.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(20, 20), Actions.scaleBy(30, 30, 0.6f, Interpolation.swingOut),
                Actions.repeat(6, Actions.sequence(
                Actions.scaleBy(10, 10, 0.7f, Interpolation.pow2), Actions.scaleBy(-10, -10, 0.7f, Interpolation.pow2)))));

        blastBackgroundO.addAction(Actions.sequence(Actions.delay(6.7f), Actions.visible(true), Actions.scaleBy(20, 20), Actions.scaleBy(60, 60, 1f, Interpolation.swingOut),
                Actions.repeat(6, Actions.sequence(
                Actions.scaleBy(15, 15, 0.7f, Interpolation.pow2), Actions.scaleBy(-15, -15, 0.7f, Interpolation.pow2)
        )), new Action() {
            @Override
            public boolean act(float delta) {
                deactivateLaser();
                return true;
            }
        }));
    }

    public void deactivateLaser(){
        blastBackgroundO.addAction(Actions.sequence(Actions.scaleBy(-80, -80, 0.1f), Actions.visible(false)));
        blastBackgroundR.addAction(Actions.sequence(Actions.scaleBy(-60, -60, 0.1f), Actions.visible(false)));
        blast.addAction(Actions.sequence(Actions.scaleBy(-30, -30, 0.1f), Actions.visible(false)));
        beam.addAction(Actions.sequence(Actions.delay(0.1f),Actions.parallel(Actions.sizeTo(20, 20, 0.1f), Actions.moveBy(210, 0, 0.1f)), Actions.visible(false)));
        beamTip.addAction(Actions.sequence(Actions.delay(0.2f), Actions.scaleBy(-30, -30, 0.1f), Actions.visible(false), new Action() {
            @Override
            public boolean act(float delta) {
                popup.activatePopup();
                return true;
            }
        }));
    }


}
