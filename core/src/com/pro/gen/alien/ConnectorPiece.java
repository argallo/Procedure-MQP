package com.pro.gen.alien;

import com.pro.gen.utils.PositionPair;

/**
 * Created by Gallo on 9/11/2015.
 */
public interface ConnectorPiece {

    void connectTo(PositionPair positionPair);
    String save();

}
