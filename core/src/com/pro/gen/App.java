package com.pro.gen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pro.gen.components.AppStage;
import com.pro.gen.managers.ViewManager;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.ViewID;

public class App implements ApplicationListener {

    private static final ViewID INITIAL_SCREEN = ViewID.APP;
    private AppStage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Viewport viewport;
    private FPSLogger fpsLogger;

    @Override
    public void create () {
        initCamera();
        //Sets the initial spritebatch that will be used to draw the stage
        batch = new SpriteBatch();
        //Initializes the app stage passing the viewport and batch to render the stage
        stage = new AppStage(viewport, batch);
        //Will be used in switching between views
        ViewManager.getInstance().setStage(stage);
        //Load common screens assets
        Assets.getInstance().loadCommonAssets();
        //Sets the initial screen to be rendered
        ViewManager.getInstance().setInitialView(INITIAL_SCREEN);
        //sets the input to actors that are found in the stage
        Gdx.input.setInputProcessor(stage);

        fpsLogger = new FPSLogger();
    }

    /**
     * initCamera: creates the camera and viewport to be used in the application.
     */
    public void initCamera() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // stage.pause();
    }

    @Override
    public void resume() {
        // stage.resume();
    }

    @Override
    public void dispose() {
        //Always dispose Audio/Assets here
        //AudioUtils.dispose();
        stage.dispose();
        Assets.getInstance().dispose();
    }




}
