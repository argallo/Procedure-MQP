package com.pro.gen.managers;

import com.pro.gen.components.AppStage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 8/11/2015.
 */
public class ViewManager {

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

    public void transitionViewTo(ViewID newViewID, TransitionType transitionType) {
        Assets.getInstance().loadAssets(newViewID, true);
        transitionManager.createTransition(stage.getCurrentView(), viewBuilder.build(newViewID), newViewID, stage, transitionType);
    }

    public void setInitialView(ViewID viewID) {
        Assets.getInstance().loadAssets(viewID, true);
        Assets.getInstance().setCurrentID(viewID);
        stage.addInitialView(viewBuilder.build(viewID));
    }

    public void unfocusAll(){
        stage.unfocusAll();
    }


}
