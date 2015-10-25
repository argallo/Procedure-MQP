package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.screens.ExploreScreen;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 10/22/2015.
 */
public class NewStarMap extends Group {

    private int amountOfStars;
    private ExploreScreen screen;

    public NewStarMap(int amountOfStars, float width, float height, ExploreScreen screen){
        this.amountOfStars = amountOfStars;
        this.screen = screen;
        setSize(width, height);
        generateStars(width, height);
    }

    public void generateStars(float width, float height){
        Stars stars = new Stars(amountOfStars);
        RandomPlacement randomPlacement = new RandomPlacement(stars.getCircleStars(), width, height);
        for(int i = 0; i < amountOfStars; i++) {
            Button starPiece = new Button(randomPlacement.getMapItem(i).getItem().getObjectName(), Constants.STAR_WHITE);
            starPiece.setSize(randomPlacement.getWidth(i), randomPlacement.getHeight(i));
            starPiece.setPosition(randomPlacement.getX(i), randomPlacement.getY(i));
            starPiece.setButtonAction(new ButtonAction() {
                @Override
                public void buttonPressed() {
                    screen.popUp();
                }
            });
            addActor(starPiece);
        }
    }

}
