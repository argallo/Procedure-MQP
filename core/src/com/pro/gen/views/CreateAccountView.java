package com.pro.gen.views;

import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.PreferenceManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

/**
 * Created by Gallo on 9/2/2015.
 */
public class CreateAccountView extends BaseView {

    private static final String TITLE = "WELCOME";
    private static final String USERNAME = "Create a Username Below";

    private Background background;
    private Button button;
    private TextBox textbox;
    private TitleLabel titleLabel;
    private TextLabel textLabel;

    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        button = new Button(Constants.RECTANGLE, Constants.PINK, "Submit", Assets.getInstance().getMidFont());
        textbox = new TextBox(12);
        titleLabel = new TitleLabel(TITLE);
        textLabel = new TextLabel(USERNAME);
    }

    @Override
    public void setSizes() {
        button.setSize(300, 150);
        textbox.setSize(500, 60);
    }

    @Override
    public void setPositions() {
        button.setPosition(Constants.VIRTUAL_WIDTH/2-button.getWidth()/2, 50);
        textbox.setPosition(Constants.VIRTUAL_WIDTH/2-textbox.getWidth()/2, 400);
        titleLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - titleLabel.getWidth() / 2, Constants.VIRTUAL_HEIGHT - titleLabel.getHeight());
        textLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - textLabel.getWidth() / 2, 400 + textLabel.getHeight() + 10);
    }

    @Override
    public void addListeners() {
        button.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                int response = DatabaseManager.getInstance().makeDBCall(DatabaseManager.USERNAME, new String[]{"3284893247", textbox.getText()});
                if (response == 400) {
                    PreferenceManager.getInstance().getPreferences().putBoolean(PreferenceManager.HAS_ACCOUNT, true);
                    PreferenceManager.getInstance().getPreferences().putString(PreferenceManager.ACCOUNT_NAME, textbox.getText());
                    PreferenceManager.getInstance().getPreferences().flush();
                    ViewManager.getInstance().transitionViewTo(ViewID.APP, TransitionType.SLIDE_R_TRANSITION);
                } else if (response == 300) {
                    usernameTaken();
                    ViewManager.getInstance().transitionViewTo(ViewID.APP, TransitionType.SLIDE_R_TRANSITION);
                } else {
                    error();
                }
            }
        });
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(button);
        addActor(textbox);
        addActor(titleLabel);
        addActor(textLabel);
    }

    public void usernameTaken(){

    }

    public void error(){

    }

}
