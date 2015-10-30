package com.pro.gen.views;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.pro.gen.utils.Constants;


/**
 * Created by Gallo on 8/11/2015.
 */
public abstract class BaseView extends Group implements Cullable{

    private Rectangle rectangle;

    public BaseView() {
        rectangle = new Rectangle(0,0,Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
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

    @Override
    public void act(float delta) {
        super.act(delta);
        if(getStage()!= null) {
            //LogUtils.Log(getStage().getCamera().position.x-getStage().getCamera().viewportWidth/2, getStage().getCamera().position.y-getStage().getCamera().viewportHeight/2, getStage().getCamera().viewportWidth, getStage().getCamera().viewportHeight);
            //TODO: Extend camera to boolean has position changed?
            rectangle.set(getStage().getCamera().position.x-getStage().getCamera().viewportWidth/2, getStage().getCamera().position.y-getStage().getCamera().viewportHeight/2, getStage().getCamera().viewportWidth, getStage().getCamera().viewportHeight);
            setCullingArea(rectangle);
        }
    }
}
