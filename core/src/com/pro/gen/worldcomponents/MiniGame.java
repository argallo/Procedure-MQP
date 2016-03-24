package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
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
    private PowerBar powerBar;
    private int targetsAmt = 0;
    private float powerPercent = 0;
    private TextLabel countdownLabel;

    private TextLabel finalTargetLabel, finalOutputLabel;
    private Button beginBattleBtn;

    public MiniGame(BattleView battleView){
        this.battleView = battleView;
        background = new Background(Pic.Pixel, Tint.OPAQUE_PURPLE);
        techOverlay = new TintedImage(Pic.TechOverlay_HLarge);
        instructions = new TextLabel("Break Targets Matching Your Base Color: "+battleView.getPlayerPlanetBaseColor());
        targetSystem = new TargetSystem(battleView.getPlayerPlanetBaseColor(), this);
        powerBar = new PowerBar(this);
        countdownLabel = new TextLabel("3", Tint.STAR_WHITE, Assets.getInstance().getLargeFont());

        techOverlay.setSize(930, 480);
        targetSystem.setSize(930, 480);

        techOverlay.setPosition(Constants.VIRTUAL_WIDTH / 2 - techOverlay.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 - techOverlay.getHeight() / 2 - 75);
        targetSystem.setPosition(Constants.VIRTUAL_WIDTH / 2 - targetSystem.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 - targetSystem.getHeight() / 2 - 75);
        instructions.setPosition(Constants.VIRTUAL_WIDTH / 2 - instructions.getWidth() / 2, 520);
        countdownLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - countdownLabel.getWidth() / 2, techOverlay.getY() + (techOverlay.getHeight() / 2 - countdownLabel.getHeight() / 2));

        addActor(background);
        addActor(techOverlay);
        addActor(instructions);
        addActor(targetSystem);
        addActor(countdownLabel);


        this.setVisible(false);
        addAction(Actions.sequence(Actions.moveTo(Constants.VIRTUAL_WIDTH, 0), Actions.visible(true),
                Actions.moveTo(0, 0, 1f, Interpolation.exp10),
                Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        countdown();
                        return true;
                    }
                }));

    }

    public void countdown(){
        addAction(Actions.sequence(Actions.delay(0.6f), new Action() {
            @Override
            public boolean act(float delta) {
                countdownLabel.setText("2");
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        countdownLabel.setText("1");
                        addAction(Actions.sequence(Actions.delay(1f), new Action() {
                            @Override
                            public boolean act(float delta) {
                                countdownLabel.setText("GO!");
                                addAction(Actions.sequence(Actions.delay(0.4f), new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        removeActor(countdownLabel);
                                        targetSystem.begin();
                                        return true;
                                    }
                                }));
                                return true;
                            }
                        }));
                        return true;
                    }
                }));
                return true;
            }
        }));

    }


    public void targetsDestroyed(){
        targetsAmt = targetSystem.getTargetCounter();
        addAction(Actions.sequence(Actions.delay(0.5f), new Action() {
            @Override
            public boolean act(float delta) {
                removeActor(instructions);
                removeActor(targetSystem);
                addActor(powerBar);
                return true;
            }
        }));
    }

    public void finished(){
        powerPercent = powerBar.getOutputpercent();
        addAction(Actions.sequence(Actions.delay(1.2f), new Action() {
            @Override
            public boolean act(float delta) {
                removeActor(powerBar);
                addAction(Actions.sequence(Actions.delay(0.2f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        finalTargetLabel = new TextLabel("Targets Destroyed: "+targetsAmt);
                        finalOutputLabel = new TextLabel("Output Power Percent: "+(powerPercent*100));
                        beginBattleBtn = new Button(Pic.Pixel, Tint.BLAST_RED, "Start Battle", Assets.getInstance().getMidFont());
                        beginBattleBtn.setSize(300,70);
                        finalTargetLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - finalTargetLabel.getWidth() / 2, techOverlay.getY() + (techOverlay.getHeight() / 2 - finalTargetLabel.getHeight() / 2)+50);
                        finalOutputLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - finalOutputLabel.getWidth() / 2, techOverlay.getY() + (techOverlay.getHeight() / 2 - finalOutputLabel.getHeight() / 2)-50);
                        beginBattleBtn.setPosition(Constants.VIRTUAL_WIDTH / 2 - beginBattleBtn.getWidth() / 2, techOverlay.getY() + (techOverlay.getHeight() / 2 - beginBattleBtn.getHeight() / 2)-140);
                        beginBattleBtn.setButtonAction(new ButtonAction() {
                            @Override
                            public void buttonPressed() {
                                battleView.beginBattle(targetsAmt, powerPercent);
                            }
                        });
                        addActor(finalTargetLabel);
                        addActor(finalOutputLabel);
                        addActor(beginBattleBtn);
                        return true;
                    }
                }));
                return true;
            }
        }));
        }

    }
