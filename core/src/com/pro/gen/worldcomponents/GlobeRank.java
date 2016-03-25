package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo-Desktop on 3/20/2016.
 */
public class GlobeRank extends Group {

    private TintedImage backgroundPanel;
    private TintedImage rankBar;
    private int currentXp;
    private int rankXp;
    private int rank;
    private TextLabel rankXpLabel;
    private TextLabel currentRankLabel;
    private int rankUps;

    private final static int rankDistance = 333;



    public GlobeRank(int rank, int currentXp, int rankXp){
        backgroundPanel = new TintedImage(Pic.Globe_Rank);
        rankBar = new TintedImage(Pic.Pixel, Tint.GLOBE_RANK_ORANGE);
        currentRankLabel = new TextLabel(String.valueOf(rank), Tint.GLOBE_RANK_ORANGE);

        this.currentXp = currentXp;
        this.rankXp = rankXp;
        this.rank = rank;

        rankXpLabel = new TextLabel(currentXp+"/"+rankXp, Color.WHITE, Assets.getInstance().getXSmallFont());
        rankBar.setSize(1, 25);
        rankBar.setPosition(34, 29);

        rankXpLabel.setPosition(35, 29);

        setSize(400, 140);
        backgroundPanel.setSize(400, 140);

        currentRankLabel.setPosition(300, 65);

        addActor(backgroundPanel);
        addActor(rankBar);
        addActor(rankXpLabel);
        addActor(currentRankLabel);
    }

    public void progress(){
        rankBar.addAction(Actions.sizeTo(calculateRankWidth(), 25, 0.7f, Interpolation.linear));
    }

    public void rankUp(int rankUps, final int cXp){
        if(rankUps == 0){
            currentXp = cXp;
            rankXpLabel.setText(currentXp + "/" + rankXp);
            progress();
        } else {
            setRankUps(rankUps);
            rankBar.addAction(Actions.sequence(Actions.sizeTo(rankDistance, 25, 0.7f, Interpolation.linear), new Action() {
                @Override
                public boolean act(float delta) {
                    rankBar.setWidth(0);
                    rank++;
                    rankXp = (int) (Math.pow(rank, 1.2) * 100);
                    currentXp = cXp;
                    currentRankLabel.setText(String.valueOf(rank));
                    setRankUps(getRankUps() - 1);
                    rankXpLabel.setText(rankXp + "/" + rankXp);
                    rankUp(getRankUps(), cXp);
                    return true;
                }
            }));
        }
    }

    public void setRankUps(int rankUps){
        this.rankUps = rankUps;
    }

    public int getRankUps() {
        return rankUps;
    }


    private float calculateRankWidth(){
        return (currentXp*rankDistance)/rankXp;
    }




}
