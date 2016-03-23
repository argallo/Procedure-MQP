package com.pro.gen.popups;

import com.badlogic.gdx.math.Interpolation;
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
 * Created by Gallo on 1/17/2016.
 */
public class SolarSystemPopup extends com.pro.gen.popups.AbsPopup {

    private TextLabel question;
    private Button yes, no;

    public SolarSystemPopup(BaseView baseView) {
        super(baseView);
        question = new TextLabel("Would you like to visit the Planet?", Assets.getInstance().getSmallFont());
        question.setPosition(310, 500);

        no = new Button(Pic.Curve_square, Tint.PURPLE, "CANCEL", Assets.getInstance().getSmallFont());
        yes = new Button(Pic.Curve_square, Tint.PURPLE, "YES", Assets.getInstance().getSmallFont());

        setupButtons();

        no.setSize(200, 100);
        yes.setSize(200, 100);

        no.setPosition(280, 200);
        yes.setPosition(780, 200);

        question.setVisible(false);

        no.setVisible(false);
        yes.setVisible(false);


        addActor(question);
        addActor(no);
        addActor(yes);

    }

    @Override
    public void activatePopup() {
        background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2),
                Actions.visible(true),
                Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        question.setVisible(true);
                        no.setVisible(true);
                        yes.setVisible(true);
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
                                                      no.setVisible(false);
                                                      yes.setVisible(false);
                                                      return true;
                                                  }
                                              }, Actions.delay(0.1f),
                Actions.parallel(Actions.sizeTo(0, 0, 0.5f, Interpolation.exp10), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2, 0.5f, Interpolation.exp10)), Actions.visible(false)));

    }

    public void setupButtons(){
        yes.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                baseView.handle(AbsPopup.YES);
            }
        });
        no.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                baseView.handle(AbsPopup.NO);
            }
        });
    }


}
