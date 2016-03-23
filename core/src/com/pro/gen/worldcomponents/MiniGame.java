package com.pro.gen.worldcomponents;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.pro.gen.components.Background;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.BattleView;

/**
 * Created by Gallo-Desktop on 3/23/2016.
 */
public class MiniGame extends Group {

    private BattleView battleView;
    private TextLabel instructions;
    private TintedImage techOverlay;
    private Background background;
    private TargetSystem targetSystem;

    public MiniGame(BattleView battleView){
        this.battleView = battleView;
        background = new Background(Pic.Pixel, Tint.OPAQUE_PURPLE);
        techOverlay = new TintedImage(Pic.TechOverlay_HLarge);
        instructions = new TextLabel("Break Targets Matching Your Base Color: "+battleView.getPlayerPlanetBaseColor());
        targetSystem = new TargetSystem(battleView.getPlayerPlanetBaseColor());

        techOverlay.setSize(930, 480);
        targetSystem.setSize(930, 480);

        techOverlay.setPosition(Constants.VIRTUAL_WIDTH / 2 - techOverlay.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 - techOverlay.getHeight() / 2 - 75);
        targetSystem.setPosition(Constants.VIRTUAL_WIDTH / 2 - targetSystem.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 - targetSystem.getHeight() / 2 - 75);
        instructions.setPosition(Constants.VIRTUAL_WIDTH / 2 - instructions.getWidth() / 2, 520);

        addActor(background);
        addActor(techOverlay);
        addActor(instructions);
        addActor(targetSystem);

        targetSystem.begin();

    }

}
