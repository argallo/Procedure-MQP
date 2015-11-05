package com.pro.gen.components;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.utils.Constants;

import java.util.ArrayList;

/**
 * Created by Gallo on 10/30/2015.
 */
public class ButtonGroup extends Group {

    ArrayList<AbsButtonItem> uiItems= new ArrayList<AbsButtonItem>();

    public ButtonGroup(ArrayList<AbsButtonItem> uiItems){
        this.uiItems = uiItems;
        this.setSize(Constants.VIRTUAL_WIDTH, 150);
        initItems();
    }

    public void initItems(){
        for(int i = 0; i < uiItems.size(); i++){
            uiItems.get(i).setSize(Constants.VIRTUAL_WIDTH/uiItems.size(), Constants.VIRTUAL_WIDTH/uiItems.size()); // squares for now
            uiItems.get(i).setPosition(i*(Constants.VIRTUAL_WIDTH/uiItems.size()), 0);

        }
    }

}
