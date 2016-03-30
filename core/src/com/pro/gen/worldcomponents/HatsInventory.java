package com.pro.gen.worldcomponents;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TintedImage;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.views.PlanetlistView;

import java.util.ArrayList;

/**
 * Created by Gallo-Desktop on 3/30/2016.
 */
public class HatsInventory extends Group {

    private ArrayList<Hat> hatList;
    private TintedImage background;
    private ScrollPane scrollPane;
    private Group rows;
    private PlanetlistView planetlistView;

    public HatsInventory(final PlanetlistView planetlistView){
        this.planetlistView = planetlistView;
        background = new TintedImage(Pic.Pixel, Tint.DARK_PURPLE);
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        addActor(background);

        hatList = XmlManager.getInstance().getHatList();

        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        rows = new Group();
        int numRows = (int)Math.ceil(hatList.size() / 4.0);
        rows.setSize(Constants.VIRTUAL_WIDTH, numRows * 280);

        for(int i = 0; i < numRows; i++){
            Group row = new Group();
            row.setSize(Constants.VIRTUAL_WIDTH, 280);
            row.setPosition(0, ((numRows-1) - i) * 280);

            for (int j = 0; j < 4; j++){
                if((4*i)+j >= hatList.size())
                    break;
                Hat hat = hatList.get((4*i)+j);
                hat.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j) + 40, 140);
                hat.setTouchable(Touchable.disabled);
                TextLabel textLabel = new TextLabel(hat.getEffectDescription(), Assets.getInstance().getXSmallFont(), Align.center);
                textLabel.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j)+20, 80);
                textLabel.setTouchable(Touchable.disabled);
                Button button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Select", Assets.getInstance().getXSmallFont());
                button.setSize(120,60);
                button.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j) + 75, 0);
                final int x = (4*i)+j;
                button.setButtonAction(new ButtonAction() {
                    @Override
                    public void buttonPressed() {
                        addHat(x);
                    }
                });
                row.addActor(hat);
                row.addActor(textLabel);
                row.addActor(button);

            }


            rows.addActor(row);
        }

        scrollPane = new ScrollPane(rows);
        scrollPane.setFillParent(true);
        addActor(scrollPane);

    }

    public void addHat(int index){
        planetlistView.getPlanets().get(planetlistView.getSelectedPlanetIndex()).setHat(hatList.get(index));
        XmlManager.getInstance().savePlanet(planetlistView.getPlanets().get(planetlistView.getSelectedPlanetIndex()),
                XmlManager.getInstance().convertSlotintToString(planetlistView.getSelectedPlanetIndex()));
        this.setVisible(false);
    }



}
