package com.pro.gen.popups;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo on 1/17/2016.
 */
public abstract class AbsPopup extends Group{

    public static final int YES = 1;
    public static final int NO = 0;

    protected TintedImage background;
    protected BaseView baseView;

    public AbsPopup(BaseView baseView){
        this.baseView = baseView;
        background = new TintedImage(Pic.Blank_Popup);
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        background.setVisible(false);
        addActor(background);
    }

    public abstract void activatePopup();

    public abstract void deactivatePopup();



}
