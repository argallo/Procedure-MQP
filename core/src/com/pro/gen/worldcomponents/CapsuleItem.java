package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 2/6/2016.
 */
public class CapsuleItem extends Group {

    private TintedImage background;
    private TintedImage r_crystal, g_crystal, b_crystal, power_crystal;
    private TextLabel text;

    public CapsuleItem(){
        construct();
    }

    public void construct(){
        int type = MathUtils.random(1,3);
        background = new TintedImage(Pic.Curve_square, Tint.STAR_WHITE);
        background.setSize(240, 160);
        background.setPosition(0, 5);
        addActor(background);
        switch(type){
            case 1:
                r_crystal = new TintedImage(Pic.R_Crystal);
                text = new TextLabel(String.valueOf("+"+ MathUtils.random(50, 5000)), Color.BLACK, Assets.getInstance().getXSmallFont());

                r_crystal.setSize(80, 80);
                r_crystal.setPosition(80,60);

                text.setPosition(120-text.getWidth()/2, 30);

                addActor(r_crystal);
                addActor(text);
                break;
            case 2:
                g_crystal = new TintedImage(Pic.G_Crystal);
                text = new TextLabel(String.valueOf("+"+MathUtils.random(50, 5000)), Color.BLACK, Assets.getInstance().getXSmallFont());

                g_crystal.setSize(80, 80);
                g_crystal.setPosition(80,60);

                text.setPosition(120-text.getWidth()/2, 30);

                addActor(g_crystal);
                addActor(text);
                break;
            case 3:
                b_crystal = new TintedImage(Pic.B_Crystal);
                text = new TextLabel(String.valueOf("+"+MathUtils.random(50, 5000)), Color.BLACK, Assets.getInstance().getXSmallFont());

                b_crystal.setSize(80, 80);
                b_crystal.setPosition(80,60);

                text.setPosition(120-text.getWidth()/2, 30);

                addActor(b_crystal);
                addActor(text);
                break;
        }
    }

    public void reset(){
        clear();
        construct();
    }


}
