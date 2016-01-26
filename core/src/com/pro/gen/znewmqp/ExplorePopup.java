package com.pro.gen.znewmqp;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 1/13/2016.
 */
public class ExplorePopup extends AbsPopup {

    private TextLabel question, units;
    private TintedImage fuelUnits;
    private Button yes, no;

    public ExplorePopup(PopupHandler popupHandler){
        super(popupHandler);

        question = new TextLabel("Are you sure you want to use a Fuel Unit?", Assets.getInstance().getSmallFont());
        question.setPosition(310, 500);

        units = new TextLabel("3 Fuel Units", Assets.getInstance().getSmallFont());
        units.setPosition(570, 360);

        fuelUnits = new TintedImage(Pic.Fuel_Unit_Icon);
        fuelUnits.setSize(50, 54);
        fuelUnits.setPosition(490, 360);


        no = new Button(Pic.Curve_square, Tint.PURPLE, "CANCEL", Assets.getInstance().getSmallFont());
        yes = new Button(Pic.Curve_square, Tint.PURPLE, "YES", Assets.getInstance().getSmallFont());

        setupButtons();

        no.setSize(200, 100);
        yes.setSize(200, 100);

        no.setPosition(280, 200);
        yes.setPosition(780, 200);

        question.setVisible(false);
        units.setVisible(false);
        fuelUnits.setVisible(false);
        no.setVisible(false);
        yes.setVisible(false);


        addActor(question);
        addActor(no);
        addActor(yes);
        addActor(fuelUnits);
        addActor(units);
    }


    public void activatePopup(){
        background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT/2),
                Actions.visible(true),
                Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        question.setVisible(true);
                        units.setVisible(true);
                        fuelUnits.setVisible(true);
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
                                                      units.setVisible(false);
                                                      fuelUnits.setVisible(false);
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
                popupHandler.handle(1);
            }
        });
        no.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                popupHandler.handle(0);
            }
        });
    }

}
