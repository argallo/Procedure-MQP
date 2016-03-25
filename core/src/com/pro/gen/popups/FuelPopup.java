package com.pro.gen.popups;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo on 3/25/2016.
 */
public class FuelPopup extends AbsPopup {
    public final static int WALK = 22;
    public final static int WAIT = 23;

    private TextLabel question;
    private Button walk, wait;
    private int numberOfSteps = 0;

    public FuelPopup(BaseView baseView) {
        super(baseView);

        question = new TextLabel("How would you like to replenish Fuel?", Assets.getInstance().getSmallFont());
        question.setPosition(300, 500);


        wait = new Button(Pic.Curve_square, Tint.PURPLE, "Wait 10 Minutes", Assets.getInstance().getSmallFont());

        switch (MathUtils.random(2)){
            case 0:
                walk = new Button(Pic.Curve_square, Tint.PURPLE, "Walk 700 Steps", Assets.getInstance().getSmallFont());
                numberOfSteps = 700;
                break;
            case 1:
                walk = new Button(Pic.Curve_square, Tint.PURPLE, "Walk 1000 Steps", Assets.getInstance().getSmallFont());
                numberOfSteps = 1000;
                break;
            case 2:
                walk = new Button(Pic.Curve_square, Tint.PURPLE, "Walk 1300 Steps", Assets.getInstance().getSmallFont());
                numberOfSteps = 1300;
                break;
        }

        setupButtons();

        wait.setSize(280, 100);
        walk.setSize(280, 100);

        wait.setPosition(280, 200);
        walk.setPosition(740, 200);

        question.setVisible(false);

        wait.setVisible(false);
        walk.setVisible(false);


        addActor(question);
        addActor(wait);
        addActor(walk);

    }


    @Override
    public void activatePopup() {
        background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2),
                Actions.visible(true),
                Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        question.setVisible(true);
                        wait.setVisible(true);
                        walk.setVisible(true);
                        return true;
                    }
                }));
    }

    @Override
    public void deactivatePopup() {
        background.addAction(Actions.sequence(new Action() {
                                                  @Override
                                                  public boolean act(float delta) {
                                                      question.setVisible(false);
                                                      wait.setVisible(false);
                                                      walk.setVisible(false);
                                                      return true;
                                                  }
                                              }, Actions.delay(0.1f),
                Actions.parallel(Actions.sizeTo(0, 0, 0.5f, Interpolation.exp10), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2, 0.5f, Interpolation.exp10)), Actions.visible(false)));

    }

    public void setupButtons(){
        walk.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                baseView.handle(WALK);
            }
        });
        wait.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                baseView.handle(WAIT);
            }
        });
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }
}
