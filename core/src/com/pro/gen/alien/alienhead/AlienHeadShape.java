package com.pro.gen.alien.alienhead;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.utils.PositionPair;

/**
 * Created by Gallo on 9/11/2015.
 */
public class AlienHeadShape extends Group {

    private PositionPair eyeConnection;
    private PositionPair mouthConnection;
    private PositionPair hatorhairConnection;
    private PositionPair earsConnection;

    public AlienHeadShape(){
        
    }

    public PositionPair getEyeConnection() {
        return eyeConnection;
    }

    public PositionPair getMouthConnection() {
        return mouthConnection;
    }

    public PositionPair getHatorhairConnection() {
        return hatorhairConnection;
    }

    public PositionPair getEarsConnection() {
        return earsConnection;
    }
}
