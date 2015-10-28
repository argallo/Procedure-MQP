package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.views.LandingView;

/**
 * Created by Gallo on 10/27/2015.
 */
public class SkyDown extends Group {

    private final static float LIGHT_AMT = 0.05f;
    private final static float SPEED = 500f;

    TintedImage[] stars;

    TintedImage sky1, sky2, sky3, sky4;
    Color initialColor;
    LandingView landView;
    int resets = 0;

    public SkyDown(Color initialColor, LandingView landView){
        this.initialColor = initialColor;
        this.landView = landView;
        sky1 = new TintedImage(Constants.RECTANGLE, new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
        sky1.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/2);
        sky1.setPosition(0, Constants.VIRTUAL_HEIGHT / 2);
        lighten();
        sky2 = new TintedImage(Constants.RECTANGLE, new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
        sky2.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/2);
        sky2.setPosition(0, 0);
        lighten();
        sky3 = new TintedImage(Constants.RECTANGLE, new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
        sky3.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/2);
        sky3.setPosition(0, -Constants.VIRTUAL_HEIGHT / 2);
        lighten();
        sky4 = new TintedImage(Constants.RECTANGLE, new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
        sky4.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT/2);
        sky4.setPosition(0, -Constants.VIRTUAL_HEIGHT);
        lighten();

        stars = new TintedImage[50];

        addActor(sky1);
        addActor(sky2);
        addActor(sky3);
        addActor(sky4);

        for(int i = 0; i < stars.length; i++){
            stars[i] = new TintedImage(Constants.CIRCLE_SMALL, Color.WHITE);
            float size = MathUtils.random(1.1f, 3f);
            stars[i].setSize(size, size);
            stars[i].setPosition(10+MathUtils.random(0, Constants.VIRTUAL_WIDTH-10), 10 + MathUtils.random(0, Constants.VIRTUAL_HEIGHT-10));
            addActor(stars[i]);
        }
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        sky1.setY(sky1.getY() + (SPEED * delta));
        sky2.setY(sky2.getY() + (SPEED * delta));
        sky3.setY(sky3.getY() + (SPEED * delta));
        sky4.setY(sky4.getY() + (SPEED * delta));
        if(sky1.getY() >= Constants.VIRTUAL_HEIGHT){
            sky1.setY(sky4.getY()-Constants.VIRTUAL_HEIGHT/2);
            sky1.setTint(new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
            lighten();
            resets++;
        }
        if(sky2.getY() >= Constants.VIRTUAL_HEIGHT){
            sky2.setY(sky1.getY()-Constants.VIRTUAL_HEIGHT/2);
            sky2.setTint(new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
            lighten();
        }
        if(sky3.getY() >= Constants.VIRTUAL_HEIGHT){
            sky3.setY(sky2.getY()-Constants.VIRTUAL_HEIGHT/2);
            sky3.setTint(new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
            lighten();
        }
        if(sky4.getY() >= Constants.VIRTUAL_HEIGHT){
            sky4.setY(sky3.getY()-Constants.VIRTUAL_HEIGHT/2);
            sky4.setTint(new Color(initialColor.r, initialColor.g, initialColor.b, initialColor.a));
            lighten();
        }

        for(int i = 0; i < stars.length; i++){
            stars[i].setY(stars[i].getY()+(SPEED*delta));
            if(stars[i].getY()>=Constants.VIRTUAL_HEIGHT){
                if(MathUtils.random(0,5)>= 3){
                    stars[i].setPosition(10+MathUtils.random(0, Constants.VIRTUAL_WIDTH-10), -Constants.VIRTUAL_HEIGHT + (10 + MathUtils.random(0, Constants.VIRTUAL_HEIGHT-10)));
                } else {
                    removeActor(stars[i]);
                }
            }
        }

        if(resets > 4){
            landView.finishLanding();
        }
    }

    public void lighten(){
        initialColor = ColorHelper.lighten(initialColor, LIGHT_AMT);
    }

}
