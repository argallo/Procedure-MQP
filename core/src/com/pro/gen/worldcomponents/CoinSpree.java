package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;

import java.util.ArrayList;

/**
 * Created by Gallo on 1/27/2016.
 */
public class CoinSpree extends Group {

    ArrayList<TintedImage> coins;



    public CoinSpree(){
        coins = new ArrayList<TintedImage>();

        addAction(Actions.forever(Actions.sequence(Actions.delay(0.1f), new Action() {
            @Override
            public boolean act(float delta) {
                generateCoins();
                return true;
            }
        })));
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        for(TintedImage coin : coins){
            coin.setPosition(coin.getX()-1, coin.getY()-1);
            coin.setSize(coin.getWidth()+delta*10, coin.getHeight()+delta*10);
        }
    }

    public void generateCoins(){
        //int amount = MathUtils.random(5, 40);
       // for(int i = 0; i < amount; i++){
            TintedImage coin = new TintedImage(getCoinType());
            coin.setSize(20,20);
            coin.setPosition(MathUtils.random(760, 810), MathUtils.random(120, 150));
            coins.add(coin);
            addActor(coin);
        //}
    }

    public String getCoinType(){
        int type = MathUtils.random(1,3);
        switch (type){
            case 1:
                return com.pro.gen.utils.Pic.R_Crystal;
            case 2:
                return com.pro.gen.utils.Pic.G_Crystal;
            case 3:
                return com.pro.gen.utils.Pic.B_Crystal;
            default:
                return null;
        }
    }


}
