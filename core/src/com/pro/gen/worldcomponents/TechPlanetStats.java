package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Pic;


/**
 * Created by Gallo-Desktop on 3/20/2016.
 */
public class TechPlanetStats extends Group {

    private TintedImage techOverlay;

    private TextLabel planetSizeLabel;
    private TextLabel energyOutputLabel;
    private TextLabel baseColorLabel;

    private int planetSize;
    private int energyOutput;
    private String baseColor;

    public TechPlanetStats(int planetSize, int energyOutput, String baseColor){
        techOverlay = new TintedImage(Pic.TechOverlay_VMed);
        this.planetSize = planetSize;
        this.energyOutput = energyOutput;
        this.baseColor = baseColor;

        planetSizeLabel = new TextLabel("Planet Size: "+planetSize+"km", Assets.getInstance().getXSmallFont());
        energyOutputLabel = new TextLabel("EnergyOutput: "+energyOutput+"kWh", Assets.getInstance().getXSmallFont());
        baseColorLabel = new TextLabel("Base Color: "+baseColor, Assets.getInstance().getXSmallFont());

        techOverlay.setSize(300,325);

        planetSizeLabel.setPosition(20, 270);
        energyOutputLabel.setPosition(20, 240);
        baseColorLabel.setPosition(20, 210);

        planetSizeLabel.setAlign(Align.left);
        energyOutputLabel.setAlign(Align.left);
        baseColorLabel.setAlign(Align.left);

        addActor(techOverlay);
        addActor(planetSizeLabel);
        addActor(energyOutputLabel);
        addActor(baseColorLabel);

    }

    public void setParams(int planetSize, int energyOutput, String baseColor){
        this.planetSize = planetSize;
        this.energyOutput = energyOutput;
        this.baseColor = baseColor;

        planetSizeLabel.setText("Planet Size: "+planetSize+"km");
        energyOutputLabel.setText("EnergyOutput: "+energyOutput+"kWh");
        baseColorLabel.setText("Base Color: "+baseColor);

    }





}
