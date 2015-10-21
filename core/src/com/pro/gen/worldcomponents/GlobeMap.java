package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomGlobeMapInfo;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 8/14/2015.
 */
public class GlobeMap extends Group{

    private RandomGlobeMapInfo randomGlobeMapInfo;
    private TintedImage[] globePieces;
    private float speed;
    private float repeatDistance = -((Constants.GLOBE_REC_WIDTH*5)/12);


    public GlobeMap(GlobeObjectType globeObjectType){
        randomGlobeMapInfo = new RandomGlobeMapInfo(globeObjectType);
        globePieces = randomGlobeMapInfo.getMapActors();
        addActors();
        this.setSize(Constants.GLOBE_REC_WIDTH, Constants.GLOBE_REC_HEIGHT);
        speed = (getWidth()*randomGlobeMapInfo.getSpeed())/Constants.VIRTUAL_HEIGHT;
    }

    public void addActors(){
        for(TintedImage image : globePieces){
            addActor(image);
        }
    }

    /**
     * resize land information based on groups new width and height
     * @param width width of the planet. Land is twice this size to account for back of planet
     * @param height height of the planet
     */
    public void resizeLands(float width, float height){
        float sizeChangeW = width*2/this.getWidth();
        float sizeChangeH = height/this.getHeight();
        for(int i = 0; i < globePieces.length; i++){
            globePieces[i].setSize(globePieces[i].getWidth() * sizeChangeW, globePieces[i].getHeight() * sizeChangeH);
            globePieces[i].setPosition(globePieces[i].getX() * sizeChangeW, globePieces[i].getY() * sizeChangeH);
        }
        this.setSize(width * 2, height);
        repeatDistance = -((width*5)/6);
        setSpeed();
        this.setPosition(this.getX() + width, this.getY() + height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for(int i = 0; i < globePieces.length; i++){
            globePieces[i].setX(globePieces[i].getX()-speed);
            if(globePieces[i].getX() < repeatDistance){
                globePieces[i].setX(this.getWidth()/2);
            }
        }
    }

    public void setSpeed(){
        speed = (getWidth()*randomGlobeMapInfo.getSpeed())/Constants.VIRTUAL_HEIGHT;
    }

}
