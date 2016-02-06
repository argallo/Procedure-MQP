package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 1/27/2016.
 */
public class OilRig extends Group {

    TintedImage rock, top, stand, rope;

    public OilRig(){
        rock = new TintedImage(com.pro.gen.utils.Pic.Oil_Rock);
        top = new TintedImage(com.pro.gen.utils.Pic.Oil_Top);
        stand = new TintedImage(com.pro.gen.utils.Pic.Oil_Stand);
        rope = new TintedImage(com.pro.gen.utils.Pic.Oil_Rope);

        rock.setSize(184, 197);
        top.setSize(517, 312);
        stand.setSize(234, 320);
        rope.setSize(18, 353);

        top.setPosition(300, 270);
        rock.setPosition(230, 110);
        stand.setPosition(440, 120);
        rope.setPosition(795, 100);

        addActor(stand);
        addActor(rope);
        addActor(top);
        addActor(rock);


        top.setOrigin(top.getWidth()/2, top.getHeight()/2);
        top.addAction(Actions.forever(Actions.sequence(Actions.rotateTo(10f, 2f), Actions.rotateTo(-30f, 2f))));
        rock.addAction(Actions.forever(Actions.sequence(Actions.moveTo(250,70, 2f), Actions.moveTo(190, 245, 2f))));
        rope.addAction(Actions.forever(Actions.sequence(Actions.moveTo(795, 128, 2f), Actions.moveTo(780, -10, 2f))));


    }



}
