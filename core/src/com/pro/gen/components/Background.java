package com.pro.gen.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 8/11/2015.
 */
public class Background extends Actor {

    private TextureRegion backgroundImage;

    public Background(String background) {
        backgroundImage = Assets.getInstance().getTexture(background);
        setPosition(0,0);
        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundImage, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        super.draw(batch, parentAlpha);
    }
}
