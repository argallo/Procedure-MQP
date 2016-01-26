package com.pro.gen.managers;

import com.badlogic.gdx.graphics.Camera;
import com.pro.gen.components.AppStage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 8/11/2015.
 */
public class ViewManager {

    private Camera camera;
    private AppStage stage;
    private ViewBuilderFactory viewBuilder = new ViewBuilderFactory();
    private TransitionManager transitionManager = new TransitionManager();

    private static final ViewManager INSTANCE = new ViewManager();

    public static ViewManager getInstance() {
        return INSTANCE;
    }

    public void setStage(AppStage stage) {
        this.stage = stage;
    }

    /**
     * Called when view wants to transition to a new view
     * @param newViewID the ID of the new view
     * @param transitionType the type of transition to make when moving to new view
     */
    public void transitionViewTo(ViewID newViewID, TransitionType transitionType) {
        if(stage.getCurrentView().equals(ViewID.SPLASH)){
            Assets.getInstance().disposeSplash();
        }
        transitionManager.createTransition(stage.getCurrentView(), viewBuilder.build(newViewID), newViewID, stage, transitionType);
    }

    /**
     * Sets the initial view when the app starts up.
     * @param viewID
     */
    public void setInitialView(ViewID viewID) {
        stage.addInitialView(viewBuilder.build(viewID));
    }

    /**
     * Used for login/create account screen, quick way to unfocus all objects in a stage
     */
    public void unfocusAll(){
        stage.unfocusAll();
    }

}
