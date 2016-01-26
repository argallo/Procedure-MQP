package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo on 10/29/2015.
 */
public class ParallaxBackground extends Actor {

    TintedImage[][] terrainChunks;
    private final static int TERRAIN_OFFSET = 25;
    private final static float PEAK_OFFSET = 0.1f;
    private final static float LIGHTING_OFFSET = 0.4f;


    public ParallaxBackground(BaseView baseView, Texture[] textures, Color color, float height, int chuckSize, float peaks, int terrains){
        this.terrainChunks = new TintedImage[terrains][chuckSize];
        for(int j = 0; j < terrainChunks.length; j++){
            for(int i = 0; i < terrainChunks[j].length; i++){
                terrainChunks[j][i] = new TintedImage(new TextureRegion(textures[MathUtils.random(j*(textures.length/terrains), (((j+1)*(textures.length/terrains))-1))]), new Color(color));
                terrainChunks[j][i].setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT * (peaks-(j*PEAK_OFFSET)));
                terrainChunks[j][i].setPosition(i * Constants.VIRTUAL_WIDTH, height-(j*TERRAIN_OFFSET));
                baseView.addActor(terrainChunks[j][i]);
            }
            color = ColorHelper.lighten(color, LIGHTING_OFFSET);
        }
    }

    @Override
    public void setX(float amount){  //TODO: make this more generic
        for(TintedImage chunck : terrainChunks[1]){
            chunck.setX(chunck.getX() + (amount*1/6));
        }
        for(TintedImage chunck : terrainChunks[0]){
            chunck.setX(chunck.getX() + (amount*1/3));
        }
    }

    public void setTint(Color color){
        for (TintedImage[] terrains : terrainChunks) {
            for (TintedImage chunck : terrains) {
                chunck.setTint(color);
            }
        }
     }

}
