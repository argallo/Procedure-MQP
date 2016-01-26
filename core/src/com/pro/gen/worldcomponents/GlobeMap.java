package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomGlobeMapInfo;

import java.util.ArrayList;

/**
 * Created by Gallo on 8/14/2015.
 */
public class GlobeMap extends Group {

    private ArrayList<TintedImage> globePieces;
    private float speed;

    public GlobeMap(RandomGlobeMapInfo randomGlobeMapInfo){
        this(randomGlobeMapInfo.getGlobePieces(), randomGlobeMapInfo.getSpeed());
    }

    public GlobeMap(ArrayList<TintedImage> globePieces, float speed){
        this.globePieces = globePieces;
        this.speed = speed;
        attachActors();
    }

    private void attachActors(){
        for(TintedImage piece: globePieces){
            addActor(piece);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for(TintedImage piece: globePieces){
            piece.setX(piece.getX() - (speed * getWidth() / 1500));
            if(piece.getX()+piece.getWidth() < getWidth()/4){
                piece.setX(getWidth()-piece.getWidth());
            }
        }

    }

    public void resizeGlobe(float width, float height){
        this.setSize(width, height);
        for(TintedImage piece: globePieces){
            piece.setSize(piece.getWidth()*width/1500, piece.getHeight()*height/500);
            piece.setPosition(piece.getX() * width / 1500, piece.getY() * height / 500);
        }
    }

    public void burn(Color color){
        for(TintedImage piece: globePieces){
            piece.addAction(Actions.color(color, 8f));
        }
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if(speed > 0){
                    speed -=delta/2.2f;
                    return false;
                }
                return true;
            }
        });
    }

}


    /*private float speed;
    private float repeatDistance = -((Constants.GLOBE_REC_WIDTH*5)/12);


    public GlobeMap(RandomGlobeMapInfo randomGlobeMapInfo){
        globePieces = randomGlobeMapInfo.getMapActors();
        this.setSize(Constants.GLOBE_REC_WIDTH, Constants.GLOBE_REC_HEIGHT);
        speed = (getWidth()*randomGlobeMapInfo.getSpeed())/Constants.VIRTUAL_HEIGHT;
    }

    public void addActors(){
        for(TintedImage image : globePieces){
            addActor(image);
        }
    }


     * resize land information based on groups new width and height
     * @param width width of the planet. Land is twice this size to account for back of planet
     * @param height height of the planet

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
*/