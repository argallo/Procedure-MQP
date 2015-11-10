package com.pro.gen.twod.levelpieces;

import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.simplealien.EnemyAlien;
import com.pro.gen.simplealien.RandomAlien;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.GroundView;

/**
 * Created by Gallo on 11/10/2015.
 */
public class LevelEnemies {

    //TODO: create enemies based off planet level.

    private int worldLength;
    private GroundView groundView;

    public LevelEnemies(GroundView groundView, int worldLength){
        this.groundView = groundView;
        this.worldLength = worldLength;
        int enemies = MathUtils.random(5, 10);
        for(int i = 0; i < enemies; i++){
            EnemyAlien enemyAlien = new EnemyAlien(100,250, new RandomAlien());
            enemyAlien.setPosition(MathUtils.random(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_WIDTH*(worldLength-1)), 250);
            enemyAlien.setScale(0.5f, 0.5f);
            groundView.addActor(enemyAlien);
        }

    }

}
