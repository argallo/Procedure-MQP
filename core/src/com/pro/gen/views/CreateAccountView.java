package com.pro.gen.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.managers.XmlManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

import java.util.HashMap;
import java.util.Map;

import sun.rmi.runtime.Log;

/**
 * Created by Gallo on 9/2/2015.
 */
public class CreateAccountView extends BaseView {

    private static final int LOGIN_ACTION = 2;

    private static final String TITLE = "WELCOME";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Player ID";


    private static final String CREATE_ACCOUNT = "Create an Account Below";
    private static final String CREATE = "Create";
    private static final String USER_FAIL = "Username has already been taken, try again";
    private static final String ERROR_FAIL = "Error couldn't connect, try again";

    private Background background;
    private Button loginCreate;
    private TextBox usernameTBox, passwordTBox;
    private TitleLabel titleLabel;
    private TextLabel informationalHeaderLabel, errorLabel;

    @Override
    public void init() {
        background = new Background(Pic.Pixel, Tint.UNIVERSE_BACKGROUND_COLOR);
        loginCreate = new Button(Pic.Pixel, Tint.PINK, CREATE, Assets.getInstance().getMidFont());

        usernameTBox = new TextBox(9, USERNAME, TextBox.CHARDIG);
        passwordTBox = new TextBox(15, PASSWORD, TextBox.CHARDIG);
        titleLabel = new TitleLabel(TITLE);
        informationalHeaderLabel = new TextLabel(CREATE_ACCOUNT);
        //Use error label to also address other issues that might come up like not enough chars for username or pw
        errorLabel = new TextLabel("", Color.RED);
        loginCreate.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                handle(LOGIN_ACTION);
            }
        });

        /**
         * background listener:
         * Allows user to remove keyboard when finished typing by
         * clicking on the background of the view.
         */
        background.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        });


    }

    @Override
    public void setSizes() {
        loginCreate.setSize(300, 150);
        usernameTBox.setSize(500, 60);
        passwordTBox.setSize(500, 60);

    }

    @Override
    public void setPositions() {
        loginCreate.setPosition(Constants.VIRTUAL_WIDTH/2 - loginCreate.getWidth()/2, 50);
        usernameTBox.setPosition(120, 400);
        passwordTBox.setPosition(650, 400);
        titleLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - titleLabel.getWidth() / 2, Constants.VIRTUAL_HEIGHT - titleLabel.getHeight());
        informationalHeaderLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - informationalHeaderLabel.getWidth() / 2, 400 + informationalHeaderLabel.getHeight() + 10);
        errorLabel.setPosition(Constants.VIRTUAL_WIDTH/2 - errorLabel.getWidth()/2, 250);
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(usernameTBox);
        addActor(passwordTBox);
        addActor(titleLabel);
        addActor(informationalHeaderLabel);
        addActor(errorLabel);
        addActor(loginCreate);
    }

    @Override
    public void handle(int outcome) {
        login();
    }

    /**
     * LoginCreate Listener:
     * if state is login, check database for correct password and save response information into preferences
     * if state is create, check database for similar email or username, saves info to database and to preferences
     *      if username similar call usernameTaken()
     *      if email similar call emailUsed()
     * Result: move to create Character view, move to home view, throw correct error method.
     */
    public void login(){
        errorLabel.setText("");

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", usernameTBox.getText());
        params.put("id", passwordTBox.getText());
        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                LogUtils.Log(response);
                if (response.equals("username")) {
                    usernameTaken();
                } else if (response.startsWith("error")) {
                    error();
                } else if (response.equals("success")) {
                    Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    XmlManager.getInstance().setAccount();
                                    XmlManager.getInstance().setUsername(usernameTBox.getText());
                                    XmlManager.getInstance().setID(passwordTBox.getText());
                                    ViewManager.getInstance().transitionViewTo(ViewID.CREATE_ALIEN, TransitionType.SLIDE_R_TRANSITION);
                                }
                            });
                        }
                    }

                    @Override
                    public void failed(Throwable t) {

                    }

                    @Override
                    public void cancelled() {

                    }
                };
                DatabaseManager.getInstance().makeDBCall(DatabaseManager.CREATE, params, listener);
    }


    /**
     * Called when username has already been taken by another user
     */
    public void usernameTaken(){
        errorLabel.setText(USER_FAIL);
    }

    /**
     * Called when something when wrong with connection
     */
    public void error(){
        errorLabel.setText(ERROR_FAIL);
    }

}
