package com.pro.gen.znewmqp;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 1/17/2016.
 */
public class PlanetPopup extends AbsPopup {

    private int type;
    public final static int ARMY = 1;
    public final static int DESTROY = 2;
    public final static int MINE = 3;

    private TextLabel question;
    private Button yes, no;

    public PlanetPopup(PopupHandler popupHandler) {
        super(popupHandler);

        question = new TextLabel("Building an Army will Cost §700 g crystals", Assets.getInstance().getSmallFont());
        question.setPosition(300, 500);


        no = new Button(Pic.Curve_square, Tint.PURPLE, "CANCEL", Assets.getInstance().getSmallFont());
        yes = new Button(Pic.Curve_square, Tint.PURPLE, "PURCHASE", Assets.getInstance().getSmallFont());

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

    public void setupType(int type){
        switch (type){
            case ARMY:
                question.setText("Building an Army will Cost §1 Power Crystal");
                this.type = type;
                break;
            case DESTROY:
                question.setText("Destroying Planet will Cost §1 Power Crystal");
                this.type = type;
                break;
            case MINE:
                question.setText("Mining the Planet will Cost §1 Power Crystal");
                this.type = type;
                break;
        }
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
                popupHandler.handle(type);
            }
        });
        no.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                popupHandler.handle(AbsPopup.NO);
            }
        });
    }
}
