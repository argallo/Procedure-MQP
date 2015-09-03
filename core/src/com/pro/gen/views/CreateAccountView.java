package com.pro.gen.views;

import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TitleText;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;

/**
 * Created by Gallo on 9/2/2015.
 */
public class CreateAccountView extends BaseView {

    private Background background;
    private Button button;
    private TextBox textbox;
    private TitleText titleText;

    public CreateAccountView(){

    }

    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        button = new Button(Constants.RECTANGLE, Constants.PINK, "Submit", Assets.getInstance().getMidFont());
        textbox = new TextBox();
        titleText = new TitleText("WELCOME");
    }

    @Override
    public void setSizes() {
        button.setSize(300, 150);
        textbox.setSize(300, 60);
    }

    @Override
    public void setPositions() {
        button.setPosition(Constants.VIRTUAL_WIDTH/2-button.getWidth()/2, 50);
        textbox.setPosition(Constants.VIRTUAL_WIDTH/2-textbox.getWidth()/2, 300);
        titleText.setPosition(Constants.VIRTUAL_WIDTH/2-titleText.getWidth()/2, Constants.VIRTUAL_HEIGHT-titleText.getHeight());
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(button);
        addActor(textbox.getActor());
        addActor(titleText);
    }
}
