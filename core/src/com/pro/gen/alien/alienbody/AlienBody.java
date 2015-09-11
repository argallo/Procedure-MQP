package com.pro.gen.alien.alienbody;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.alien.ConnectorPiece;
import com.pro.gen.utils.PositionPair;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienBody extends Group implements ConnectorPiece{

    private PositionPair armConnection;
    private PositionPair legConnection;

    @Override
    public void connectTo(PositionPair positionPair) {

    }

    @Override
    public String save() {
        return "BodyType: BodyColor: BodySize: BodyPosition: ";
    }

    public PositionPair getArmConnection() {
        return armConnection;
    }

    public PositionPair getLegConnection() {
        return legConnection;
    }
}
