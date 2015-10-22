package com.pro.gen.components;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.NewStarMap;
import com.pro.gen.worldcomponents.WarpToPopup;

/**
 * Created by Gallo on 10/21/2015.
 */
public class ShipScreen extends Group {

    private Action slideIn, slideOut;
    private NewStarMap map;
    private WarpToPopup warpToPopup;

    public enum ShipScreenTypes {
        DEFAULT, EXPLORE, INVENTORY, HOME;
    }

    private TintedImage slidingScreenLeft, slidingScreenRight;
    private ShipScreenTypes currentScreen;

    public ShipScreen(){
        currentScreen = ShipScreenTypes.DEFAULT;
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

        this.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-100);
        this.setPosition(0, 100);

        slideIn = new Action() {
            @Override
            public boolean act(float delta) {
                slidingScreenLeft.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(-slidingScreenLeft.getWidth(),0), Actions.moveBy(slidingScreenLeft.getWidth(),0,0.4f, Interpolation.exp10In)));
                slidingScreenRight.addAction(Actions.sequence(Actions.visible(true), Actions.moveTo(Constants.VIRTUAL_WIDTH, 0), Actions.moveBy(-slidingScreenRight.getWidth(), 0, 0.4f, Interpolation.exp10In)));
                return true;
            }
        };

        slideOut = new Action() {
            @Override
            public boolean act(float delta) {
                slidingScreenLeft.addAction(Actions.sequence(Actions.moveBy(-slidingScreenLeft.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                slidingScreenRight.addAction(Actions.sequence(Actions.moveBy(slidingScreenRight.getWidth(),0,0.4f, Interpolation.exp10Out), Actions.visible(false)));
                return true;
            }
        };
    }

    public void viewScreen(final ShipScreenTypes type){
        switch (type){
            case EXPLORE:
                this.addAction(Actions.sequence(slideIn, Actions.delay(0.4f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        removeOldScreen(type);
                        createExploreScreen();
                        return true;
                    }
                }, Actions.delay(0.6f), slideOut));
                break;
            case INVENTORY:
                this.addAction(Actions.sequence(slideIn, Actions.delay(0.4f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        removeOldScreen(type);
                        createInventoryScreen();
                        return true;
                    }
                }, Actions.delay(0.6f), slideOut));
                break;
            case HOME:

                break;
            case DEFAULT:

                break;
        }
    }

    private void removeOldScreen(ShipScreenTypes type){
        switch (currentScreen){
            case EXPLORE:
                disposeExplore();
                break;
            case INVENTORY:
                disposeInventory();
                break;
            case HOME:

                break;
            case DEFAULT:

                break;
        }
        currentScreen = type;
    }


    private void createExploreScreen(){
        map = new NewStarMap(200, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-100, this);
        addActorAt(0, map);
    }

    private void disposeExplore(){
        removeActor(map);
        //might have to dispose actors in map
    }

    private void createInventoryScreen(){

    }

    private void disposeInventory(){
        removeActor(map);
        //might have to dispose actors in map
    }

    public void popUp(){
        warpToPopup = new WarpToPopup(this);
        warpToPopup.setName("popup");
        map.setTouchable(Touchable.disabled);
        addActor(warpToPopup);
    }

    public void popUpFly(){
        //fly somewhere
        //hyper speed to solar system
        //animate selecting solar system planets
        //zoom into planet.
        //give details about planet.
        //screen transition to landing into 2d scroller.
        warpToPopup.remove();
    }

    public void popUpCancel(){
        map.setTouchable(Touchable.enabled);
        warpToPopup.remove();
    }
}
