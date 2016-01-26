package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Item;
import com.pro.gen.znewmqp.Tint;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/15/2015.
 */
public class StarMap extends Group {

    private boolean pause = false;
    private boolean speedBoost = false;
    private float endSpeed = 0.5f;
    private float StarSpeed = 0.5f;

    private int animation;
    private ArrayList<TintedImage> stars;


    public StarMap(int animation,int starSize, Item item){
        this(animation, starSize, item, 0);
    }

    public StarMap(int animation,int starSize, Item item, float avoidDiameter){
        this.animation = animation;
        RandomPlacement rp = new RandomPlacement(item, Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, starSize, avoidDiameter);
        initStarMap(rp);
    }


    private void initStarMap(RandomPlacement rp){
        stars = new ArrayList<TintedImage>();
        for(int i = 0; i < rp.getSize(); i++) {
            TintedImage star = new TintedImage(rp.getName(), Tint.STAR_WHITE);
            star.setSize(rp.getWidth(i), rp.getHeight(i));
            star.setPosition(rp.getX(i), rp.getY(i));
            stars.add(star);
            addActor(star);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(speedBoost){
            StarSpeed+=0.1f;
            if(StarSpeed>endSpeed)
                speedBoost = false;
        }
        if(!pause) {
            if (animation == 1) {
                throughSpace();
            } else if (animation == 2) {
                leftToRight();
            }
        }
    }

    private void leftToRight(){
        for(TintedImage star: stars) {
            float speed = StarSpeed+ star.getWidth()/4;
            star.setPosition(star.getX()+speed,star.getY());
            if(star.getX()>Constants.VIRTUAL_WIDTH){
                randomStar(star, -100, 100, 0, Constants.VIRTUAL_HEIGHT);
            }
        }
    }

    private void throughSpace(){
        for(TintedImage star: stars) {
            float rise = (star.getY() - Constants.VIRTUAL_HEIGHT/2);
            float run =  (star.getX() - Constants.VIRTUAL_WIDTH/2);
            float mag = (float)Math.sqrt((rise*rise) + (run*run));
            float speed = StarSpeed* star.getWidth()/15;
            star.setPosition(star.getX()+(run/mag)*speed,star.getY()+(rise/mag)*speed);
            if(outOfBounds(star)){
                randomStar(star, Constants.VIRTUAL_WIDTH/2-200, Constants.VIRTUAL_WIDTH/2+200,
                        Constants.VIRTUAL_HEIGHT/2 -200, Constants.VIRTUAL_HEIGHT/2+200);
            }
        }
    }

    private boolean outOfBounds(TintedImage star){
        if(star.getX() < -12 || star.getX() > Constants.VIRTUAL_WIDTH ||
                star.getY() < -12 || star.getY() > Constants.VIRTUAL_HEIGHT)
            return true;
        return false;
    }

    private void randomStar(TintedImage star, float minX, float maxX, float minY, float maxY){
        star.setPosition(MathUtils.random(minX, maxX), MathUtils.random(minY,maxY));
    }

    public void pause(){
        pause = true;
    }

    public void unpause(){
        pause = false;
    }

    public void setStarSpeed(float starSpeed) {
        StarSpeed = starSpeed;
    }

    public float getStarSpeed() {
        return StarSpeed;
    }

    public ArrayList<TintedImage> getStars() {
        return stars;
    }

    public void increaseSpeed(float endSpeed){
        speedBoost = true;
        this.endSpeed = endSpeed;
    }

    /*
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
        comboImage.addImage(Assets.getInstance().getTextureRegion(Constants.TWINKLE).getTexture(),0,0,0,0, new Color(110/255f,36/255f,185/255f,1f));
        comboImage.addImage(Assets.getInstance().getTextureRegion(Constants.CIRCLE_SMALL).getTexture(),16,16,32,32, new Color(1f, 0f, 1f, 1f));
        comboImage.addImage(Assets.getInstance().getTextureRegion(Constants.CIRCLE_SMALL).getTexture(),24,24,16,16, new Color(1f, 1f, 1f, 1f));
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

    public void generateTintedImage(int index){
        TintedImage starPiece;
        if(rp.getMapItem(index).getItem().getObjectName().equals(Constants.TWINKLE) && MathUtils.random()>0.5){
            starPiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), Tint.STAR_PURPLE);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
            subCluster1.add(starPiece);
        } else if (rp.getMapItem(index).getItem().getObjectName().equals(Constants.CIRCLE_SMALL) && MathUtils.random()< 0.5){
            starPiece = new TintedImage(rp.getMapItem(index).getItem().getObjectName(), Tint.STAR_WHITE);
            starPiece.setSize(rp.getWidth(index), rp.getHeight(index));
            starPiece.setPosition(rp.getX(index), rp.getY(index));
            subCluster3.add(starPiece);
        } else {
            starPiece = new TintedImage(new TextureRegion(p.getTexture()), Color.CLEAR);
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

  */
}


