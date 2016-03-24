package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 9/2/2015.
 */
public class SplashView extends BaseView{

    public static final int SPLASH_TIME = 3; //approx seconds

    private Image splashLogo;
    private boolean counting = true;
    private float counter = 0;

    /**
     * Construct the splash view and add the logo background to the stage
     */
    public SplashView(){

    }

    @Override
    public void init() {
        splashLogo = new Image(Assets.getInstance().getSplash());
        //for now just load texture atlas but as it becomes bigger we may need to modify the act method to load in increments
        Assets.getInstance().loadCommonAssets();
    }

    @Override
    public void setSizes() {

    }
    @Override
    public void setPositions() {

    }
    @Override
    public void handle(int outcome) {

    }

    public void addActors(){
        addActor(splashLogo);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateSplash(delta);
    }

    /**
     * Waits a certain amount of time before transitioning to the main application
     * @param delta the detla time passed
     */
    public void updateSplash(float delta){
        if(counting) {
            counter += delta;
            //If other preloading needs to be done we can wait until its finished before transitioning
            if(counter > SPLASH_TIME){
                completeSplashView();
            }
        }
    }

    /**
     * Make the transition to the next view
     */
    public void completeSplashView(){
        counting = false;
        if(PreferenceManager.getInstance().hasAccount()){
            //Users account can be found in local storage, go straight to Home Screen
            ViewManager.getInstance().transitionViewTo(ViewID.MAIN_MENU, TransitionType.DEFAULT_TRANSITION);
        } else {
            //User either does not have an account or is not logged in. Go to login screen
            ViewManager.getInstance().transitionViewTo(ViewID.DESTROY, TransitionType.DEFAULT_TRANSITION);
        }
    }

}
