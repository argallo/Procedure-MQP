package com.pro.gen.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pro.gen.simplealien.FinalAlien;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/6/2015.
 */
public class CameraUpdater {

    FinalAlien player;
    private final static float CAMERA_SPEED = 300;
    private final static float MOVEMENT_GAP = 50;
    ArrayList<Actor> cameraActors;
    ArrayList<Float> speeds;

    public CameraUpdater(FinalAlien player){
        this.player = player;
        cameraActors = new ArrayList<Actor>();
        speeds = new ArrayList<Float>();
    }

    /**
     * register actors to move with camera at certain percentage speeds compared to camera
     * @param actor actor to register
     * @param speedPercent the speed percent of the actor compared to camera
     */
    public void register(Actor actor, float speedPercent){
        cameraActors.add(actor);
        speeds.add(speedPercent);
    }

    /**
     * register actors to move with camera
     * @param actor actor to register
     */
    public void register(Actor actor){
        register(actor, 1);
    }

    /**
     * Updates the cameras movement using the player as the anchor
     * @param delta detla time
     */
    public void updateCamera(float delta){
        if(player.getCurrentDirection() == Animation.RIGHT){
            moveCameraComponents(delta, 1);
        } else if(player.getCurrentDirection() == Animation.LEFT){
            moveCameraComponents(delta, -1);
        }
    }

    /**
     * Moves the camera and its attached components left and right
     * @param delta detla time
     * @param sign whether the movement is positive (right) or negative (left)
     */
    public void moveCameraComponents(float delta, int sign){
        player.setX(player.getX() + (sign*(CAMERA_SPEED * delta)));
        if(player.getX() > player.getStage().getCamera().position.x+MOVEMENT_GAP || player.getX() < player.getStage().getCamera().position.x-MOVEMENT_GAP-player.getWidth()/2) {
            player.getStage().getCamera().translate((sign*CAMERA_SPEED) * delta, 0, 0);
            for(int i = 0; i < cameraActors.size(); i++){
                cameraActors.get(i).setX(cameraActors.get(i).getX() + (sign*((CAMERA_SPEED * speeds.get(i))* delta)));
            }
        }
    }

}
