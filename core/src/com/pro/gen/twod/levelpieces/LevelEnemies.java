package com.pro.gen.twod.levelpieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;
import com.pro.gen.components.TintedImage;
import com.pro.gen.simplealien.EnemyAlien;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.GroundView;
import com.pro.gen.weapons.CollisionChecker;

/**
 * Created by Gallo on 11/10/2015.
 */
public class LevelEnemies {

    //TODO: create enemies based off planet level.

    private int worldLength;
    private GroundView groundView;
    private EnemyAlien[] enemyAliens;
    int enemies;

    public LevelEnemies(GroundView groundView, int worldLength, CollisionChecker checker){
        this.groundView = groundView;
        this.worldLength = worldLength;
        enemies = MathUtils.random(50, 55);
        enemyAliens = new EnemyAlien[enemies];
        Color cc = ColorHelper.generateGoodColor();
       // Texture head = PixmapColorize.genTexture(Constants.ALIEN_HEAD, cc);
       // Texture eye =PixmapColorize.genTexture(Constants.ALIEN_EYE, cc);
       // Texture leg =PixmapColorize.genTexture(Constants.ALIEN_LEG, cc);
       // PixmapColorize.genTexture2(Constants.ALIEN_LEG,Constants.ALIEN_HEAD, ColorHelper.generateGoodColor(), this);
        //Texture t2 = PixmapColorize.genTexture(Constants.ALIEN_HEAD, ColorHelper.generateGoodColor());


        for(int i = 0; i < enemies; i++){

            //EnemyAlien enemyAlien = new EnemyAlien(ColorHelper.generateDarkColor(), new SimpleAlienBuilder(head, eye, leg), checker);
            EnemyAlien enemyAlien = new EnemyAlien(checker, groundView);
            enemyAlien.setPosition(MathUtils.random(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_WIDTH*(worldLength-1)), 250);
            //enemyAlien.setPosition(MathUtils.random(400, 900), 250);
            enemyAlien.setSize(60, 125);
            enemyAliens[i] = enemyAlien;
            groundView.addActor(enemyAlien);
        }


    }

    public TintedImage[] getEnemyAliens(){
        return enemyAliens;
    }

    public void addTexture(Pixmap pixmap){
  /*      Texture t = new Texture(pixmap);
        TintedImage enemyAlien;
        for(int i = 0; i < enemies*2; i++){


            EnemyAlien enemyAlien = new EnemyAlien(), checker);
            if(i%2 ==0) {
                enemyAlien = new EnemyAlien(t, Color.WHITE);

            } else {
                enemyAlien = new TintedImage(t, Color.WHITE);
            }
            //enemyAlien.setPosition(MathUtils.random(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_WIDTH*(worldLength-1)), 250);
            enemyAlien.setPosition(MathUtils.random(400, 900), 250);
            enemyAlien.setSize(60, 125);
            //enemyAlien.setScale(0.5f, 0.5f);
            enemyAliens[i] = enemyAlien;
            groundView.addActor(enemyAlien);
        }

        */
    }

}
