package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.pro.gen.components.TextLabel;

/**
 * Created by Gallo on 1/22/2016.
 */
public class DynamicCounter extends TextLabel {

    private int currentAmount = 0;

    public DynamicCounter(int amount){
        super(String.valueOf(amount));
        currentAmount = amount;
    }

    public void updateAmount(final int newAmount){
        int amountDiff = newAmount - currentAmount;

        final int countMultiplier = MathUtils.ceil(amountDiff/60f);


        addAction(Actions.sequence(Actions.repeat(amountDiff / countMultiplier, Actions.sequence(new Action() {
            @Override
            public boolean act(float delta) {
                currentAmount += countMultiplier;
                setText(String.valueOf(currentAmount));
                return true;
            }
        }, Actions.delay(1/(countMultiplier*60f)))), new Action() {
            @Override
            public boolean act(float delta) {
                currentAmount = newAmount;
                setText(String.valueOf(currentAmount));
                return true;
            }
        }));
    }




}
