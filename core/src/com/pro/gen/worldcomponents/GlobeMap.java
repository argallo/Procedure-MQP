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
    private RandomGlobeMapInfo randomGlobeMapInfo;


    public GlobeMap(RandomGlobeMapInfo randomGlobeMapInfo){
        this.randomGlobeMapInfo = randomGlobeMapInfo;
        this.globePieces = randomGlobeMapInfo.getGlobePieces();
        this.speed = randomGlobeMapInfo.getSpeed();
        attachActors();
    }

    /*
    protected GlobeMap(ArrayList<TintedImage> globePieces, float speed){
        this.globePieces = globePieces;
        this.speed = speed;
        attachActors();
    }
*/

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
                piece.setX(getWidth()-piece.getWidth() - ((getWidth()/4) - (piece.getX()+piece.getWidth())));
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

    public RandomGlobeMapInfo getRandomGlobeMapInfo() {
        return randomGlobeMapInfo;
    }
}