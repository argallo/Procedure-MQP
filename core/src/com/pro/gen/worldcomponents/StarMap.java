package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.PixmapCombos;

/**
 * Created by Gallo on 8/15/2015.
 */
public class StarMap extends Group {

    private float emptySize;
    private float centerObjSize;
    private boolean animateStars;
    private TintedImage[] starCluster;
    private RandomPlacement rp;
    PixmapCombos p;

    public StarMap(float emptySize, float centerObjSize, boolean animateStars){
        this.emptySize = emptySize;
        this.centerObjSize = centerObjSize;
        this.animateStars = animateStars;
        this.setSize(emptySize, emptySize);
        p = new PixmapCombos();
        generateClusters();
        attachActors();
    }

    public void generateClusters(){
        int clusterAmount = MathUtils.round(emptySize/3);
        starCluster = new TintedImage[clusterAmount];
        Stars stars = new Stars(clusterAmount);
        rp = new RandomPlacement(stars.getMixedStars(), emptySize, emptySize, centerObjSize);
        for(int index = 0; index < clusterAmount; index++){
            generateTintedImage(index);
        }
    }

    /**
     * TODO: Actors need to be added in the correct order based on their image so that caching occurs and speeds up render times
     * seperate out starCluster array and then combine once all starpieces have been generated.
     * @param index
     */
    public void generateTintedImage(int index){
        TintedImage starPiece;
        if(rp.getMapItem(index).getItem().getObjectName().equals(Constants.TWINKLE) && MathUtils.random()>0.75){
            starPiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), Constants.STAR_PURPLE);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
        } else {
            starPiece = new TintedImage(p.getTexture(), Color.CLEAR);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
        }

        starCluster[index] = starPiece;
    }

    public void attachActors(){
        for(TintedImage image : starCluster){
           this.addActor(image);
        }
    }

    public void resizeStars(float width, float height){
        float sizeChangeW = width/this.getWidth();
        float sizeChangeH = height/this.getHeight();
        for(int i = 0; i < starCluster.length; i++){
            starCluster[i].setSize(starCluster[i].getWidth() * sizeChangeW, starCluster[i].getHeight() * sizeChangeH);
            starCluster[i].setPosition(starCluster[i].getX() * sizeChangeW, starCluster[i].getY() * sizeChangeH);
        }
        this.setSize(width, height);
    }
}


