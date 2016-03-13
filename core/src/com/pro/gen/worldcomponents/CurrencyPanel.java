package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TintedImage;

/**
 * Created by Gallo on 1/22/2016.
 */
public class CurrencyPanel extends Group {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    private TintedImage r_crystal, g_crystal, b_crystal, power_crystal, mega_crystal;
    private DynamicCounter r_counter, g_counter, b_counter, power_counter, mega_counter;
    private int orientation;


    public CurrencyPanel(int orientation){
        this.orientation = orientation;
    }

    public void init(){
        r_crystal = new TintedImage(com.pro.gen.utils.Pic.R_Crystal);
        g_crystal = new TintedImage(com.pro.gen.utils.Pic.G_Crystal);
        b_crystal = new TintedImage(com.pro.gen.utils.Pic.B_Crystal);
        power_crystal = new TintedImage(com.pro.gen.utils.Pic.Power_Crystal);
        mega_crystal = new TintedImage(com.pro.gen.utils.Pic.Mega_Crystal);

        r_counter = new DynamicCounter(0);
        g_counter = new DynamicCounter(0);
        b_counter = new DynamicCounter(0);
        power_counter = new DynamicCounter(0);
        mega_counter = new DynamicCounter(0);


        r_crystal.setSize(60,50);
        g_crystal.setSize(60,50);
        b_crystal.setSize(60,50);
        power_crystal.setSize(25,50);
        mega_crystal.setSize(60,60);

        if(orientation == HORIZONTAL) {

            r_crystal.setPosition(getWidth() * 0.1f - r_crystal.getWidth() / 4, 40);
            g_crystal.setPosition(getWidth() * 0.3f - g_crystal.getWidth() / 4, 40);
            b_crystal.setPosition(getWidth() * 0.5f - b_crystal.getWidth() / 4, 40);
            power_crystal.setPosition(getWidth() * 0.7f - power_crystal.getWidth() / 8, 40);
            mega_crystal.setPosition(getWidth() * 0.9f - mega_crystal.getWidth() / 4, 40);

            r_counter.setPosition(getWidth() * 0.1f, 100);
            g_counter.setPosition(getWidth() * 0.3f, 100);
            b_counter.setPosition(getWidth() * 0.5f, 100);
            power_counter.setPosition(getWidth() * 0.7f, 100);
            mega_counter.setPosition(getWidth() * 0.9f, 100);
        } else if(orientation == VERTICAL){
            r_counter.setAlign(Align.left);
            g_counter.setAlign(Align.left);
            b_counter.setAlign(Align.left);
            power_counter.setAlign(Align.left);
            mega_counter.setAlign(Align.left);

            r_crystal.setPosition(0, getHeight());
            g_crystal.setPosition(0, getHeight()*0.75f);
            b_crystal.setPosition(0, getHeight()*0.50f);
            power_crystal.setPosition(15, getHeight()*0.25f);
            mega_crystal.setPosition(0, 0);

            r_counter.setPosition(65, getHeight());
            g_counter.setPosition(65, getHeight()*0.75f);
            b_counter.setPosition(65, getHeight()*0.50f);
            power_counter.setPosition(65, getHeight()*0.25f);
            mega_counter.setPosition(65, 0);
        }

        addActor(r_crystal);
        addActor(g_crystal);
        addActor(b_crystal);
        addActor(power_crystal);
        addActor(mega_crystal);

        addActor(r_counter);
        addActor(g_counter);
        addActor(b_counter);
        addActor(power_counter);
        addActor(mega_counter);
    }

    public void updateAmounts(int r, int g, int b, int pow, int mega){
        r_counter.updateAmount(r);
        g_counter.updateAmount(g);
        b_counter.updateAmount(b);
        power_counter.updateAmount(pow);
        mega_counter.updateAmount(mega);
    }


}
