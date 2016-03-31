package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
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
    private Button backBtn;
    private TextLabel hatInventoryLabel;

    public HatsInventory(final PlanetlistView planetlistView){
        this.planetlistView = planetlistView;
        background = new TintedImage(Pic.Pixel, Tint.DARK_PURPLE);
        background.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        addActor(background);
        hatInventoryLabel = new TextLabel("Hat Inventory", Assets.getInstance().getSmallFont());
        hatInventoryLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - hatInventoryLabel.getWidth() / 2, 650);
        addActor(hatInventoryLabel);
        backBtn = new Button(Pic.Back_Button, Color.WHITE);
        backBtn.setSize(60, 60);
        backBtn.setPosition(20, 650);
        backBtn.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                setVisible(false);
            }
        });
        addActor(backBtn);


        setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);

        setupHatsScroll();

    }

    public void setupHatsScroll(){
        hatList = XmlManager.getInstance().getHatList();
        rows = new Group();
        int numRows = (int)Math.ceil(hatList.size() / 4.0);
        rows.setSize(Constants.VIRTUAL_WIDTH, (numRows * 300));


        for(int i = 0; i < numRows; i++) {
            Group row = new Group();
            row.setSize(Constants.VIRTUAL_WIDTH, 300);
            row.setPosition(0, ((numRows - 1) - i) * 300);

            for (int j = 0; j < 4; j++) {
                if ((4 * i) + j >= hatList.size())
                    break;
                Hat hat = hatList.get((4 * i) + j);
                hat.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j) + 40, 140);
                hat.setTouchable(Touchable.disabled);
                TextLabel textLabel = new TextLabel(hat.getEffectDescription(), Assets.getInstance().getXSmallFont(), Align.center);
                textLabel.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j) + 20, 80);
                textLabel.setTouchable(Touchable.disabled);
                Button button = new Button(Pic.Pixel, Tint.MEDIUM_GRAY, "Select", Assets.getInstance().getXSmallFont());
                button.setSize(120, 60);
                button.setPosition(((Constants.VIRTUAL_WIDTH / 4) * j) + 75, 0);
                final int x = (4 * i) + j;
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
        scrollPane.setSize(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT-70);
        //scrollPane.setFillParent(true);
        addActor(scrollPane);

    }


    public void addHat(int index){
        planetlistView.getPlanets().get(planetlistView.getSelectedPlanetIndex()).setHat(hatList.get(index));
        hatList.remove(index);
        XmlManager.getInstance().saveHatList(hatList);
        XmlManager.getInstance().savePlanet(planetlistView.getPlanets().get(planetlistView.getSelectedPlanetIndex()),
                XmlManager.getInstance().convertSlotintToString(planetlistView.getSelectedPlanetIndex()));
        this.setVisible(false);
        planetlistView.updateHatButton(planetlistView.getPlanets().get(planetlistView.getSelectedPlanetIndex()));
        updateInventory();
    }

    public void updateInventory(){
        rows.clear();
        scrollPane.clear();
        removeActor(scrollPane);
        setupHatsScroll();
    }



}
