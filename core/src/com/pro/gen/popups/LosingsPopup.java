package com.pro.gen.popups;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.BaseView;

/**
 * Created by Gallo-Desktop on 3/25/2016.
 */
public class LosingsPopup extends AbsPopup {

    public final static int RETURN = 9;
    public final static int NEXT = 10;

    private TextLabel title, subtitle;
    private Button returnBtn, useNextBtn;

    public LosingsPopup(BaseView baseView) {
        super(baseView);

        title = new TextLabel("Your Planet was Destoryed!");
        title.setPosition(Constants.VIRTUAL_WIDTH / 2 - title.getWidth() / 2, 500);
        title.setVisible(false);
        addActor(title);

        subtitle = new TextLabel("Select what to do next", Assets.getInstance().getXSmallFont());
        subtitle.setPosition(Constants.VIRTUAL_WIDTH / 2 - subtitle.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 + 100);
        subtitle.setVisible(false);
        addActor(subtitle);


        returnBtn = new Button(Pic.Pixel, Tint.PURPLE, "Return Home", Assets.getInstance().getSmallFont());
        returnBtn.setSize(250, 100);
        returnBtn.setPosition(300, Constants.VIRTUAL_HEIGHT / 4 - 30);
        returnBtn.setVisible(false);
        returnBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                LosingsPopup.this.baseView.handle(RETURN);
            }
        });
        addActor(returnBtn);
        if(XmlManager.getInstance().hasHabitable()) {
            useNextBtn = new Button(Pic.Pixel, Tint.PURPLE, "Use Next Planet", Assets.getInstance().getSmallFont());
        } else {
            useNextBtn = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "No More\nHabitable Planets", Assets.getInstance().getXSmallFont());
            useNextBtn.setTouchable(Touchable.disabled);
        }
        useNextBtn.setSize(250, 100);
        useNextBtn.setPosition(730, Constants.VIRTUAL_HEIGHT / 4 - 30);
        useNextBtn.setVisible(false);
        useNextBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {

                        deactivatePopup();
                        LosingsPopup.this.baseView.handle(NEXT);
                        return true;
                    }
                }));
            }
        });
        addActor(useNextBtn);

    }

    @Override
    public void activatePopup() {
        background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2),
                Actions.visible(true),
                Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        title.setVisible(true);
                        subtitle.setVisible(true);
                        useNextBtn.setVisible(true);
                        returnBtn.setVisible(true);

                        return true;
                    }
                }));
    }

    @Override
    public void deactivatePopup() {
        background.addAction(Actions.sequence(new Action() {
                                                  @Override
                                                  public boolean act(float delta) {
                                                      title.setVisible(false);
                                                      subtitle.setVisible(false);
                                                      useNextBtn.setVisible(false);
                                                      returnBtn.setVisible(false);
                                                      return true;
                                                  }
                                              }, Actions.delay(0.1f),
                Actions.parallel(Actions.sizeTo(0, 0, 0.5f, Interpolation.exp10), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2, 0.5f, Interpolation.exp10)), Actions.visible(false)));
    }
}
