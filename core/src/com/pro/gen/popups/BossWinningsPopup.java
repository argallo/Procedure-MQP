package com.pro.gen.popups;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.BossBattleView;
import com.pro.gen.worldcomponents.DynamicCounter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gallo on 3/27/2016.
 */
public class BossWinningsPopup extends AbsPopup{

    public final static int KEEP = 6;
    public final static int ABSORB = 7;
    public final static int MONEY = 8;

    private TextLabel title, prizes;
    private Button absorbBtn, moneyBtn, keepBtn;
    private TintedImage powerCrystal;
    private DynamicCounter powerCounter;

    private TintedImage megaCrystal;
    private DynamicCounter megaCounter;

    public BossWinningsPopup(BossBattleView baseView) {
        super(baseView);

        title = new TextLabel("Boss Planet Destroyed!");
        title.setPosition(Constants.VIRTUAL_WIDTH / 2 - title.getWidth() / 2, 500);
        title.setVisible(false);
        addActor(title);

        prizes = new TextLabel("Select Your Reward", Assets.getInstance().getXSmallFont());
        prizes.setPosition(Constants.VIRTUAL_WIDTH / 2 - prizes.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 2 + 100);
        prizes.setVisible(false);
        addActor(prizes);

        powerCrystal = new TintedImage(Pic.Power_Crystal);
        powerCrystal.setSize(25, 50);
        powerCrystal.setPosition(Constants.VIRTUAL_WIDTH / 2 - powerCrystal.getWidth() / 2-30, Constants.VIRTUAL_HEIGHT / 2 - powerCrystal.getHeight() / 2);
        powerCrystal.setVisible(false);

        powerCounter = new DynamicCounter(XmlManager.getInstance().getPowerCrystals());
        powerCounter.setPosition(Constants.VIRTUAL_WIDTH / 2 - powerCrystal.getWidth() / 2 + 10, Constants.VIRTUAL_HEIGHT / 2 - powerCrystal.getHeight() / 2 - 5);
        powerCounter.setVisible(false);

        addActor(powerCounter);
        addActor(powerCrystal);


        megaCrystal = new TintedImage(Pic.Mega_Crystal);
        megaCrystal.setSize(56, 40);
        megaCrystal.setPosition(Constants.VIRTUAL_WIDTH / 2 - megaCrystal.getWidth() / 2 - 45, Constants.VIRTUAL_HEIGHT / 2 - megaCrystal.getHeight() / 2 + 70);
        megaCrystal.setVisible(false);

        megaCounter = new DynamicCounter(XmlManager.getInstance().getMegaCrystals());
        megaCounter.setPosition(Constants.VIRTUAL_WIDTH / 2 - megaCrystal.getWidth() / 2 + 25, Constants.VIRTUAL_HEIGHT / 2 - megaCounter.getHeight() / 2 + 70);
        megaCounter.setVisible(false);

        addActor(megaCrystal);
        addActor(megaCounter);


        absorbBtn = new Button(Pic.Pixel, Tint.PURPLE, "Absorb Planet", Assets.getInstance().getSmallFont());
        absorbBtn.setSize(250, 100);
        absorbBtn.setPosition(250, Constants.VIRTUAL_HEIGHT / 4 - 30);
        absorbBtn.setVisible(false);
        absorbBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                deactivatePopup();
                BossWinningsPopup.this.baseView.handle(ABSORB);
            }
        });
        addActor(absorbBtn);

        moneyBtn = new Button(Pic.Pixel, Tint.PURPLE, "Take Resources", Assets.getInstance().getSmallFont());
        moneyBtn.setSize(250, 100);
        moneyBtn.setPosition(Constants.VIRTUAL_WIDTH / 2 - moneyBtn.getWidth() / 2, Constants.VIRTUAL_HEIGHT / 4 - 30);
        moneyBtn.setVisible(false);
        moneyBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                powerCounter.updateAmount(powerCounter.getCurrentAmount() + MathUtils.random(5, 10));
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        XmlManager.getInstance().savePowerCrystals(powerCounter.getCurrentAmount());
                        deactivatePopup();
                        BossWinningsPopup.this.baseView.handle(MONEY);
                        return true;
                    }
                }));
            }
        });
        addActor(moneyBtn);
       // if(XmlManager.getInstance().hasEmptySlot()){
           // keepBtn = new Button(Pic.Pixel, Tint.PURPLE, "Keep Planet", Assets.getInstance().getSmallFont());
       // } else {
            keepBtn = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Can't Keep\nBoss Planets", Assets.getInstance().getXSmallFont());
            keepBtn.setTouchable(Touchable.disabled);
       // }
        keepBtn.setSize(250, 100);
        keepBtn.setPosition(780, Constants.VIRTUAL_HEIGHT / 4 - 30);
        keepBtn.setVisible(false);
        keepBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                title.setTint(Tint.GLOBE_RANK_GREEN);
                title.setText("Planet Saved to Slot!");
                addAction(Actions.sequence(Actions.delay(1f), new Action() {
                    @Override
                    public boolean act(float delta) {
                        deactivatePopup();
                        BossWinningsPopup.this.baseView.handle(KEEP);
                        return true;
                    }
                }));
            }
        });
        addActor(keepBtn);


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
                        absorbBtn.setVisible(true);
                        moneyBtn.setVisible(true);
                        keepBtn.setVisible(true);
                        powerCrystal.setVisible(true);
                        powerCounter.setVisible(true);
                        megaCounter.setVisible(true);
                        megaCrystal.setVisible(true);
                        XmlManager.getInstance().saveMegaCrystals(megaCounter.getCurrentAmount() + ((BossBattleView) baseView).getEnemyPlanet().getGlobeRank());
                        megaCounter.updateAmount(megaCounter.getCurrentAmount() + ((BossBattleView) baseView).getEnemyPlanet().getGlobeRank());
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", XmlManager.getInstance().getUsername());
                        params.put("score", String.valueOf(XmlManager.getInstance().getMegaCrystals()));
                        DatabaseManager.getInstance().makeDBCall(DatabaseManager.SCORE, params, null);
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
                                                      moneyBtn.setVisible(false);
                                                      absorbBtn.setVisible(false);
                                                      keepBtn.setVisible(false);
                                                      powerCrystal.setVisible(false);
                                                      powerCounter.setVisible(false);
                                                      megaCounter.setVisible(false);
                                                      megaCrystal.setVisible(false);
                                                      return true;
                                                  }
                                              }, Actions.delay(0.1f),
                Actions.parallel(Actions.sizeTo(0, 0, 0.5f, Interpolation.exp10), Actions.moveTo(Constants.VIRTUAL_WIDTH / 2, Constants.VIRTUAL_HEIGHT / 2, 0.5f, Interpolation.exp10)), Actions.visible(false)));
    }

}
