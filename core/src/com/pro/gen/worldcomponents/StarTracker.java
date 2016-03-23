package com.pro.gen.worldcomponents;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.*;
import com.pro.gen.utils.Pic;

/**
 * Created by Gallo on 1/13/2016.
 */
public class StarTracker extends Group {

    private StarMap stars, rareStars;
    private TintedImage currentStar;

    private TintedImage selectedStar, techOverlay;
    private TextLabel rankLabel, rarityLabel;

    private int lowRank = 0;
    private int highRank = 0;
    private float timer = 0;
    private boolean pause = false;


    public StarTracker(StarMap stars, StarMap rareStars){
        this.stars = stars;
        this.rareStars = rareStars;
        setSize(200, 140);

        techOverlay = new TintedImage(Pic.TechOverlay_HSmall);
        techOverlay.setSize(200, 140);

        selectedStar = new TintedImage(Pic.Circle_Small);
        selectedStar.setPosition(getWidth()/2, getHeight()/2);

        rankLabel = new TextLabel("Rank 0-10", Tint.STAR_RED, Assets.getInstance().getSmallFont());
        rarityLabel = new TextLabel("Rare", Tint.RARE_YELLOW, Assets.getInstance().getSmallFont());

        rankLabel.setPosition(23, 90);
        rarityLabel.setPosition(65, 10);


        addActor(techOverlay);
        addActor(rarityLabel);
        addActor(rankLabel);
        addActor(selectedStar);
        findStar(getStarMap());
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(currentStar.getX() - getWidth() / 2 + currentStar.getWidth() / 2, currentStar.getY() - getHeight() / 2 + currentStar.getHeight() / 2);
        if(!pause) {
            timer += delta;
            if (timer > 1) {
                timer = 0;
                findStar(getStarMap());
            }
        }
    }

    public void findStar(StarMap stars){
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
        selectedStar.setImage(currentStar.getImageName());
        selectedStar.setColor(currentStar.getTint());
        selectedStar.setSize(currentStar.getWidth(), currentStar.getHeight());
        selectedStar.setPosition(getWidth() / 2 - selectedStar.getWidth() / 2, getHeight() / 2 - selectedStar.getHeight() / 2);

        setTextColors();
        setRarity();

    }

    public double distanceFromMiddle(TintedImage star){
        return Math.sqrt((star.getX() - Constants.VIRTUAL_WIDTH/2)*(star.getX() - Constants.VIRTUAL_WIDTH/2)
            + (star.getY() - Constants.VIRTUAL_HEIGHT/2)*(star.getY() - Constants.VIRTUAL_HEIGHT/2));
    }

    public StarMap getStarMap(){
        int rand = MathUtils.random(100);
        return rand <= 90?stars:rareStars;
    }

    public void setTextColors(){
        String colorHex = currentStar.getTint().toString();
        if(colorHex.equals(Tint.STAR_BLUE.toString())){
            rankLabel.setTint(Tint.STAR_BLUE);
            rankLabel.setText("Rank 30-50");
            lowRank = 30;
            highRank = 50;
        } else if (colorHex.equals(Tint.STAR_WHITE.toString())){
            rankLabel.setTint(Tint.STAR_WHITE);
            rankLabel.setText("Rank 15-30");
            lowRank = 15;
            highRank = 30;
        } else if (colorHex.equals(Tint.STAR_RED.toString())){
            rankLabel.setTint(Tint.STAR_RED);
            rankLabel.setText("Rank 1-15");
            lowRank = 1;
            highRank = 15;
        }
    }

    public void setRarity(){
        if (currentStar.getImageName().equals(Pic.Star_Twinkle)){
            rarityLabel.setText("Rare");
            rarityLabel.setTint(Tint.RARE_YELLOW);
        } else {
            rarityLabel.setText("Common");
            rarityLabel.setTint(Tint.MEDIUM_GRAY);
        }
    }

    public int getLowRank() {
        return lowRank;
    }

    public int getHighRank() {
        return highRank;
    }

    public void pause(){
        pause = true;
    }

    public void unpause(){
        pause = false;
    }
}
