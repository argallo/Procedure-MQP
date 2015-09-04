package com.pro.gen.views;

import com.pro.gen.components.Background;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 9/2/2015.
 */
public class SplashView extends BaseView{

    public static final int SPLASH_TIME = 3; //approx seconds

    private Background splashLogo;
    private boolean counting = true;
    private float counter = 0;

    /**
     * Construct the splash view and add the logo background to the stage
     */
    public SplashView(){

    }

    @Override
    public void init() {
        splashLogo = new Background(Constants.SPLASH);
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
        if(PreferenceManager.getInstance().getPreferences().getBoolean(PreferenceManager.HAS_ACCOUNT, false)){
            ViewManager.getInstance().transitionViewTo(ViewID.APP, TransitionType.DEFAULT_TRANSITION);
        } else {
            ViewManager.getInstance().transitionViewTo(ViewID.CREATE_ACCOUNT, TransitionType.DEFAULT_TRANSITION);
        }
    }

}
