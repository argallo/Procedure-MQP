package com.pro.gen.znewmqp;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 1/17/2016.
 */
public abstract class AbsPopup extends Group{

    static final int YES = 1;
    static final int NO = 0;

    protected TintedImage background;
    protected PopupHandler popupHandler;

    public AbsPopup(PopupHandler popupHandler){
        this.popupHandler = popupHandler;
        background = new TintedImage(Pic.Blank_Popup);
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        background.setVisible(false);
        addActor(background);
    }

    public abstract void activatePopup();

    public abstract void deactivatePopup();



}
