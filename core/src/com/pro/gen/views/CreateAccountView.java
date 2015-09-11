package com.pro.gen.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pro.gen.components.Background;
import com.pro.gen.components.Button;
import com.pro.gen.components.ButtonAction;
import com.pro.gen.components.TextBox;
import com.pro.gen.components.TextLabel;
import com.pro.gen.components.TitleLabel;
import com.pro.gen.managers.DatabaseManager;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.TransitionType;
import com.pro.gen.utils.ViewID;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gallo on 9/2/2015.
 */
public class CreateAccountView extends BaseView {

    private static final String TITLE = "WELCOME";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String EMAIL = "Email";
    private static final String CONFIRMPW = "Confirm-Password";

    private static final String CREATE_ACCOUNT = "Create an Account Below";
    private static final String LOGIN_ACCOUNT = "Login to Your Account Below";
    private static final String LOGIN = "Login";
    private static final String CREATE = "Create";
    private static final String SUBMIT = "Submit";
    private static final String EMAIL_FAIL = "Email already active with another account";
    private static final String USER_FAIL = "Username has already been taken, try again";
    private static final String ERROR_FAIL = "Error couldn't connect, try again";
    private static final float ANIMATION_DURATION = 0.5f;
    private static final Interpolation INTERPOLATION = Interpolation.swing;



    private Background background;
    private Button loginCreate, optionButton;
    private TextBox usernameTBox, emailTBox, passwordTBox, confirmTBox;
    private TitleLabel titleLabel;
    private TextLabel informationalHeaderLabel, errorLabel;
    private boolean isLogin = false;

    @Override
    public void init() {
        background = new Background(Constants.RECTANGLE, Constants.UNIVERSE_BACKGROUND_COLOR);
        loginCreate = new Button(Constants.RECTANGLE, Constants.PINK, SUBMIT, Assets.getInstance().getMidFont());
        optionButton = new Button(Constants.RECTANGLE, Constants.PINK, LOGIN, Assets.getInstance().getMidFont());
        usernameTBox = new TextBox(12, USERNAME, TextBox.CHARDIG);
        emailTBox = new TextBox(35, EMAIL, TextBox.EMAIL);
        passwordTBox = new TextBox(35, PASSWORD, TextBox.ALL, true);
        confirmTBox = new TextBox(35, CONFIRMPW, TextBox.ALL, true);
        titleLabel = new TitleLabel(TITLE);
        informationalHeaderLabel = new TextLabel(CREATE_ACCOUNT);
        errorLabel = new TextLabel("", Color.RED);
    }

    @Override
    public void setSizes() {
        loginCreate.setSize(300, 150);
        optionButton.setSize(300, 150);
        usernameTBox.setSize(500, 60);
        emailTBox.setSize(500, 60);
        passwordTBox.setSize(500, 60);
        confirmTBox.setSize(500, 60);
    }

    @Override
    public void setPositions() {
        loginCreate.setPosition(Constants.VIRTUAL_WIDTH/2 + loginCreate.getWidth()/4, 50);
        optionButton.setPosition(Constants.VIRTUAL_WIDTH/2 - (optionButton.getWidth()*1.25f), 50);
        usernameTBox.setPosition(120, 400);
        emailTBox.setPosition(120, 335);
        passwordTBox.setPosition(650, 400);
        confirmTBox.setPosition(650, 335);
        titleLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - titleLabel.getWidth() / 2, Constants.VIRTUAL_HEIGHT - titleLabel.getHeight());
        informationalHeaderLabel.setPosition(Constants.VIRTUAL_WIDTH / 2 - informationalHeaderLabel.getWidth() / 2, 400 + informationalHeaderLabel.getHeight() + 10);
        errorLabel.setPosition(Constants.VIRTUAL_WIDTH/2 - errorLabel.getWidth()/2, 250);
    }

    @Override
    public void addListeners() {
        /**
         * LoginCreate Listener:
         * if state is login, check database for correct password and save response information into preferences
         * if state is create, check database for similar email or username, saves info to database and to preferences
         *      if username similar call usernameTaken()
         *      if email similar call emailUsed()
         * Result: move to create Character view, move to home view, throw correct error method.
         */
        loginCreate.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                errorLabel.setText("");
                if (validateFields()) {
                    if (isLogin) {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", usernameTBox.getText());
                        params.put("password", passwordTBox.getSecureString());
                        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
                            @Override
                            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                                //if(response is correct){ save infor to preferences, transition to home view }
                                //else { incorrectLogin() }
                            }

                            @Override
                            public void failed(Throwable t) {

                            }

                            @Override
                            public void cancelled() {

                            }
                        };
                        DatabaseManager.getInstance().makeDBCall(DatabaseManager.LOGIN, params, listener);
                    } else {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", emailTBox.getText());
                        params.put("username", usernameTBox.getText());
                        params.put("password", passwordTBox.getSecureString());
                        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
                            @Override
                            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                                String response = httpResponse.getResultAsString();
                                LogUtils.Log(response);
                                if (response.equals("email")) {
                                    emailTaken();
                                } else if (response.equals("username")) {
                                    usernameTaken();
                                } else if (response.startsWith("error")) {
                                    error();
                                } else if (response.equals("success")) {
                                    Gdx.app.postRunnable(new Runnable() {
                                        @Override
                                        public void run() {
                                            // PreferenceManager.getInstance().getPreferences().putBoolean(PreferenceManager.HAS_ACCOUNT, true);
                                            // PreferenceManager.getInstance().getPreferences().putString(PreferenceManager.ACCOUNT_NAME, usernameTBox.getText());
                                            // PreferenceManager.getInstance().getPreferences().flush();
                                            ViewManager.getInstance().transitionViewTo(ViewID.APP, TransitionType.SLIDE_R_TRANSITION);
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
                }
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

        /**
         * Option Listener:
         * Switches the state between loging into and account
         * and creating a new account.
         */
        optionButton.setButtonAction(new ButtonAction() {
            @Override
            public void buttonPressed() {
                errorLabel.setText("");
                if (optionButton.getText().equals(LOGIN)) {
                    optionButton.setText(CREATE);
                    informationalHeaderLabel.setText(LOGIN_ACCOUNT);
                    emailTBox.addAction(Actions.moveBy(-800, 0, ANIMATION_DURATION, INTERPOLATION));
                    confirmTBox.addAction(Actions.moveBy(800, 0, ANIMATION_DURATION, INTERPOLATION));
                    isLogin = true;
                } else {
                    optionButton.setText(LOGIN);
                    informationalHeaderLabel.setText(CREATE_ACCOUNT);
                    emailTBox.addAction(Actions.moveBy(800, 0, ANIMATION_DURATION, INTERPOLATION));
                    confirmTBox.addAction(Actions.moveBy(-800, 0, ANIMATION_DURATION, INTERPOLATION));
                    isLogin = false;
                }
            }
        });
    }

    @Override
    public void addActors() {
        addActor(background);
        addActor(usernameTBox);
        addActor(emailTBox);
        addActor(passwordTBox);
        addActor(confirmTBox);
        addActor(titleLabel);
        addActor(informationalHeaderLabel);
        addActor(errorLabel);
        addActor(loginCreate);
        addActor(optionButton);
    }

    /**
     * Validates all fields currently on screen checking they are not empty, passwords match, and they are of email regex
     * @return true if all fields are valid
     */
    public boolean validateFields(){
        ViewManager.getInstance().unfocusAll();
        boolean isValid = true;
        if(!usernameTBox.isValid()){
            usernameTBox.addAction(Actions.sequence(Actions.moveBy(30,0,0.1f,Interpolation.pow2), Actions.moveBy(-60,0,0.1f,Interpolation.pow2), Actions.moveBy(30,0,0.1f,Interpolation.pow2)));
            isValid = false;
        }
        if(!passwordTBox.isValid()){
            passwordTBox.addAction(Actions.sequence(Actions.moveBy(30,0,0.1f,Interpolation.pow2), Actions.moveBy(-60,0,0.1f,Interpolation.pow2), Actions.moveBy(30,0,0.1f,Interpolation.pow2)));
            isValid = false;
        }
        if(!isLogin){
            if(!emailTBox.isValid()){
                emailTBox.addAction(Actions.sequence(Actions.moveBy(30,0,0.1f,Interpolation.pow2), Actions.moveBy(-60,0,0.1f,Interpolation.pow2), Actions.moveBy(30,0,0.1f,Interpolation.pow2)));
                isValid = false;
            }
            if(!confirmTBox.isValid() || !confirmTBox.getText().equals(passwordTBox.getText())){
                confirmTBox.addAction(Actions.sequence(Actions.moveBy(30,0,0.1f,Interpolation.pow2), Actions.moveBy(-60,0,0.1f,Interpolation.pow2), Actions.moveBy(30,0,0.1f,Interpolation.pow2)));
                isValid = false;
            }
        }
        return isValid;
    }


    //TODO: CREATE THESE METHODS THEN MAKE LOGINING IN WORK AND SAVE PREFERENCES
    public void emailTaken(){
        errorLabel.setText(EMAIL_FAIL);
    }

    public void usernameTaken(){
        errorLabel.setText(USER_FAIL);
    }

    public void error(){
        errorLabel.setText(ERROR_FAIL);
    }

}
