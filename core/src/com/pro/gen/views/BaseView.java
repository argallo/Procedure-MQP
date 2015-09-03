package com.pro.gen.views;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 8/11/2015.
 */
public abstract class BaseView extends Group {

    public BaseView() {
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        init();
        setSizes();
        setPositions();
        addListeners();
        addActors();
    }

    public abstract void init();

    public void setSizes(){

    }
    public void setPositions(){

    }
    public void addListeners(){

    }
    public abstract void addActors();


}
