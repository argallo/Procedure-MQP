package com.pro.gen.znewmqp;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.worldcomponents.StarMap;

/**
 * Created by Gallo on 1/13/2016.
 */
public class StarTracker extends Group {

    private StarMap stars;
    private TintedImage currentStar, rect, triangle;
    private Button flyToBtn;


    public StarTracker(StarMap stars, final AbsPopup popup){
        this.stars = stars;
        setSize(200, 140);

        flyToBtn = new Button(Pic.Curve_rectangle, Tint.DARK_PURPLE, "Fly Here?", Assets.getInstance().getSmallFont());
        flyToBtn.setSize(180, 80);
        flyToBtn.setPosition(10, 40);
        flyToBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                StarTracker.this.stars.pause();
                popup.activatePopup();
            }
        });

        rect = new TintedImage(Pic.Curve_square, Tint.PURPLE);
        rect.setSize(200, 100);
        rect.setPosition(0, 30);

        triangle = new TintedImage(Pic.Triangle, Tint.PURPLE);
        triangle.setSize(40, 40);
        triangle.setPosition(getWidth() / 2-triangle.getWidth()/2, 1);


        addActor(triangle);
        addActor(rect);
        addActor(flyToBtn);
        findStar();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(currentStar.getX() - getWidth() / 2 + currentStar.getWidth() / 2, currentStar.getY() + currentStar.getHeight() + 2);
        if(distanceFromMiddle(currentStar)>200){
            findStar();
        }
    }

    public void findStar(){
        currentStar = stars.getStars().get(0);
        double currentDistance = distanceFromMiddle(currentStar);
        double distance;
        for(int i = 1; i < stars.getStars().size(); i++){
            distance = distanceFromMiddle(stars.getStars().get(i));
            if(distance < currentDistance){
                currentStar = stars.getStars().get(i);
                currentDistance = distance;
            }
        }
    }

    public double distanceFromMiddle(TintedImage star){
        return Math.sqrt((star.getX() - Constants.VIRTUAL_WIDTH/2)*(star.getX() - Constants.VIRTUAL_WIDTH/2)
            + (star.getY() - Constants.VIRTUAL_HEIGHT/2)*(star.getY() - Constants.VIRTUAL_HEIGHT/2));
    }

}
