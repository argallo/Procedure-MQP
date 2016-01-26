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
 * Created by Gallo on 1/22/2016.
 */
public class WinningsPopup extends AbsPopup {

    private TextLabel title, prizes;
    private Button ok;
    private CurrencyPanel currencyPanel;

    public WinningsPopup(final PopupHandler popupHandler) {
        super(popupHandler);


        title = new TextLabel("Planet Destroyed!");
        title.setPosition(Constants.VIRTUAL_WIDTH / 2 - title.getWidth() / 2, 500);
        title.setVisible(false);
        addActor(title);

        prizes = new TextLabel("You Received", Assets.getInstance().getXSmallFont());
        prizes.setPosition(Constants.VIRTUAL_WIDTH/2-prizes.getWidth()/2, Constants.VIRTUAL_HEIGHT/2+100);
        prizes.setVisible(false);
        addActor(prizes);

        currencyPanel = new CurrencyPanel();
        currencyPanel.setSize(Constants.VIRTUAL_WIDTH*0.6f, Constants.VIRTUAL_HEIGHT*0.6f);
        currencyPanel.setPosition(Constants.VIRTUAL_WIDTH / 5 - 20, Constants.VIRTUAL_HEIGHT / 2 - 100);
        currencyPanel.init();
        currencyPanel.setVisible(false);
        addActor(currencyPanel);

        ok = new Button(Pic.Pixel, Tint.PURPLE, "Back to System", Assets.getInstance().getXSmallFont());
        ok.setSize(250, 100);
        ok.setPosition(Constants.VIRTUAL_WIDTH / 2 - ok.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 4 - 30);
        ok.setVisible(false);
        ok.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                popupHandler.handle(AbsPopup.YES);
            }
        });
        addActor(ok);


    }

    @Override
    public void activatePopup() {
        background.addAction(Actions.sequence(Actions.sizeTo(0, 0), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2),
                Actions.visible(true),
                Actions.parallel(Actions.sizeTo(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, 0.5f, Interpolation.exp10), Actions.moveTo(0, 0, 0.5f, Interpolation.exp10)), Actions.delay(0.1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        title.setVisible(true);
                        prizes.setVisible(true);
                        ok.setVisible(true);
                        currencyPanel.setVisible(true);
                        currencyPanel.updateAmounts(24,45,10,3,1);
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
                                                      prizes.setVisible(false);
                                                      ok.setVisible(false);
                                                      currencyPanel.setVisible(false);
                                                      return true;
                                                  }
                                              }, Actions.delay(0.1f),
                Actions.parallel(Actions.sizeTo(0, 0, 0.5f, Interpolation.exp10), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2, 0.5f, Interpolation.exp10)), Actions.visible(false)));
    }
}
