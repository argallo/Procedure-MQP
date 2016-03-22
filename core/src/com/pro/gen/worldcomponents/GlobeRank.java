package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
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
    private TextLabel rankXpLabel;
    private TextLabel currentRankLabel;

    private final static int rankDistance = 333;

    public GlobeRank(){
        backgroundPanel = new TintedImage(Pic.Globe_Rank);
        rankBar = new TintedImage(Pic.Pixel, Tint.GLOBE_RANK_ORANGE);
        currentRankLabel = new TextLabel("47", Tint.GLOBE_RANK_ORANGE);

        currentXp = 20000;
        rankXp = 49992;

        rankXpLabel = new TextLabel(currentXp+"/"+rankXp, Color.WHITE, Assets.getInstance().getXSmallFont());
        rankBar.setSize(1, 25);
        rankBar.setPosition(34, 29);
        rankBar.addAction(Actions.sizeBy(calculateRankWidth(), 0, 0.7f, Interpolation.linear));

        rankXpLabel.setPosition(34, 29);

        setSize(400, 140);
        backgroundPanel.setSize(400, 140);

        currentRankLabel.setPosition(290, 65);

        addActor(backgroundPanel);
        addActor(rankBar);
        addActor(rankXpLabel);
        addActor(currentRankLabel);


    }

    public GlobeRank(int rank, int currentXp, int rankXp){
        backgroundPanel = new TintedImage(Pic.Globe_Rank);
        rankBar = new TintedImage(Pic.Pixel, Tint.GLOBE_RANK_ORANGE);
        currentRankLabel = new TextLabel(String.valueOf(rank), Tint.GLOBE_RANK_ORANGE);

        this.currentXp = currentXp;
        this.rankXp = rankXp;

        rankXpLabel = new TextLabel(currentXp+"/"+rankXp, Color.WHITE, Assets.getInstance().getXSmallFont());
        rankBar.setSize(1, 25);
        rankBar.setPosition(34, 29);
        rankBar.addAction(Actions.sizeBy(calculateRankWidth(), 0, 0.7f, Interpolation.linear));

        rankXpLabel.setPosition(34, 29);

        setSize(400, 140);
        backgroundPanel.setSize(400, 140);

        currentRankLabel.setPosition(300, 65);

        addActor(backgroundPanel);
        addActor(rankBar);
        addActor(rankXpLabel);
        addActor(currentRankLabel);
    }

    private float calculateRankWidth(){
        return (currentXp*rankDistance)/rankXp;
    }




}
