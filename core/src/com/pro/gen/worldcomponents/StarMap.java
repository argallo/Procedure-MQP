package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.ComboImage;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.PixmapCombos;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/15/2015.
 */
public class StarMap extends Group {

    private float emptySize;
    private float centerObjSize;
    private boolean animateStars;
    private ArrayList<TintedImage> subCluster1, subCluster2, subCluster3;
    private RandomPlacement rp;
    PixmapCombos p;

    public StarMap(float emptySize, float centerObjSize, boolean animateStars){
        this.emptySize = emptySize;
        this.centerObjSize = centerObjSize;
        this.animateStars = animateStars;
        this.setSize(emptySize, emptySize);

        ComboImage comboImage = new ComboImage();
        comboImage.addImage(Assets.getInstance().getTexture(Constants.TWINKLE).getTexture(),0,0,0,0, new Color(110/255f,36/255f,185/255f,1f));
        comboImage.addImage(Assets.getInstance().getTexture(Constants.CIRCLE_SMALL).getTexture(),16,16,32,32, new Color(1f, 0f, 1f, 1f));
        comboImage.addImage(Assets.getInstance().getTexture(Constants.CIRCLE_SMALL).getTexture(),24,24,16,16, new Color(1f, 1f, 1f, 1f));
        p = new PixmapCombos(comboImage);
        generateClusters();
        attachActors();
    }

    public void generateClusters(){
        int clusterAmount = MathUtils.round(emptySize/3);
       // starCluster = new TintedImage[clusterAmount];
        /**
         * Note: adding similar actors at the same time to a group will allow them to render
         * better because the texture will already be in memory and will not need to be switched
         */
        subCluster1 = new ArrayList<TintedImage>();
        subCluster2 = new ArrayList<TintedImage>();
        subCluster3 = new ArrayList<TintedImage>();

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
        if(rp.getMapItem(index).getItem().getObjectName().equals(Constants.TWINKLE) && MathUtils.random()>0.5){
            starPiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), Constants.STAR_PURPLE);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
            subCluster1.add(starPiece);
        } else if (rp.getMapItem(index).getItem().getObjectName().equals(Constants.CIRCLE_SMALL) && MathUtils.random()< 0.5){
            starPiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), Constants.STAR_WHITE);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
            subCluster3.add(starPiece);
        } else {
            starPiece = new TintedImage(p.getTexture(), Color.CLEAR);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
            subCluster2.add(starPiece);
        }


        //starCluster[index] = starPiece;
    }

    public void attachActors(){
        for(TintedImage image : subCluster1){
           this.addActor(image);
        }
        for(TintedImage image : subCluster2){
            this.addActor(image);
        }
        for(TintedImage image : subCluster3){
            this.addActor(image);
        }
    }

    public void resizeStars(float width, float height){
        float sizeChangeW = width/this.getWidth();
        float sizeChangeH = height/this.getHeight();
        for(int i = 0; i < subCluster1.size(); i++){
            subCluster1.get(i).setSize(subCluster1.get(i).getWidth() * sizeChangeW, subCluster1.get(i).getHeight() * sizeChangeH);
            subCluster1.get(i).setPosition(subCluster1.get(i).getX() * sizeChangeW, subCluster1.get(i).getY() * sizeChangeH);
        }
        for(int i = 0; i < subCluster2.size(); i++){
            subCluster2.get(i).setSize(subCluster2.get(i).getWidth() * sizeChangeW, subCluster2.get(i).getHeight() * sizeChangeH);
            subCluster2.get(i).setPosition(subCluster2.get(i).getX() * sizeChangeW, subCluster2.get(i).getY() * sizeChangeH);
        }
        for(int i = 0; i < subCluster3.size(); i++){
            subCluster3.get(i).setSize(subCluster3.get(i).getWidth() * sizeChangeW, subCluster3.get(i).getHeight() * sizeChangeH);
            subCluster3.get(i).setPosition(subCluster3.get(i).getX() * sizeChangeW, subCluster3.get(i).getY() * sizeChangeH);
        }
        this.setSize(width, height);
    }


    @Override
    public void act(float delta) {
       // for(int i = 0; i < subCluster1.size(); i++){
      //      subCluster1.get(i).setPosition(subCluster1.get(i).getX()+MathUtils.random(-50, 50)*delta,subCluster1.get(i).getY()+MathUtils.random(-30, 30)*delta);
     //   }
    }
}


