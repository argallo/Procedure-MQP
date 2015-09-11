package com.pro.gen.alien.alienhead;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.alien.alienfactories.AlienHeadFactory;
import com.pro.gen.utils.PositionPair;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienHead extends Group implements AlienHeadPlan {

    private AlienEyes alienEyes;
    private AlienMouth alienMouth;
    private AlienHatorHair alienHatorHair;
    private AlienEars alienEars;
    private AlienHeadFactory alienHeadFactory;
    private AlienHeadShape alienHeadShape;

    /*
        These are the three connection points of the alien head.
        May want to combine the default positions with range class to give variablility
        if body is empty arm and leg default to connection to head. legConnection == bodyConnection
     */
    private PositionPair bodyConnection;
    private PositionPair armConnection;
    private PositionPair legConnection;


    public AlienHead(){
        alienHeadFactory = new AlienHeadFactory();
        alienHeadShape = (AlienHeadShape)alienHeadFactory.generateFactoryItem(MathUtils.random());
        this.addActor(alienHeadShape);
        createConnectionPoints();
    }


    @Override
    public void setAlienEyes(AlienEyes alienEyes) {
        this.alienEyes = alienEyes;
        alienEyes.connectTo(alienHeadShape.getEyeConnection());
        this.addActor(alienEyes);
    }

    @Override
    public void setAlienMouth(AlienMouth alienMouth) {
        this.alienMouth = alienMouth;
        alienMouth.connectTo(alienHeadShape.getMouthConnection());
        this.addActor(alienMouth);
    }

    @Override
    public void setAlienHatorHair(AlienHatorHair alienHatorHair) {
        this.alienHatorHair = alienHatorHair;
        alienHatorHair.connectTo(alienHeadShape.getHatorhairConnection());
        this.addActor(alienHatorHair);
    }

    @Override
    public void setAlienEars(AlienEars alienEars) {
        this.alienEars = alienEars;
        alienEars.connectTo(alienHeadShape.getEarsConnection());
        this.addActor(alienEars);
    }

    @Override
    public String save() {
        return getAlienEyes().save()+getAlienMouth().save()+getAlienHatorHair().save()+getAlienEars().save();
    }

    public AlienEars getAlienEars() { return alienEars; }

    public AlienEyes getAlienEyes() {
        return alienEyes;
    }

    public AlienHatorHair getAlienHatorHair() {
        return alienHatorHair;
    }

    public AlienMouth getAlienMouth() {
        return alienMouth;
    }

    private void createConnectionPoints(){
        bodyConnection = new PositionPair(alienHeadShape.getWidth()/2, alienHeadShape.getHeight()/10);
        legConnection = new PositionPair(bodyConnection);
        armConnection = new PositionPair(alienHeadShape.getWidth()/8, alienHeadShape.getHeight()/2);
    }

    public PositionPair getBodyConnection() {
        return bodyConnection;
    }

    public PositionPair getLegConnection() {
        return legConnection;
    }

    public PositionPair getArmConnection() {
        return armConnection;
    }
}
