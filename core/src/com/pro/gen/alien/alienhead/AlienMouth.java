package com.pro.gen.alien.alienhead;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.alien.ConnectorPiece;
import com.pro.gen.utils.PositionPair;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienMouth extends Group implements ConnectorPiece{



    @Override
    public void connectTo(PositionPair positionPair) {

    }

    @Override
    public String save() {
        return "MouthType: MouthColor: MouthSize: MouthPosition: ";
    }
}
