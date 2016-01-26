package com.pro.gen.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pro.gen.aliens.Alien;
import com.pro.gen.aliens.SimpleAlienBuilder;
import com.pro.gen.components.BackgroundSky;
import com.pro.gen.components.Button;
import com.pro.gen.components.CameraUpdater;
import com.pro.gen.components.TintedImage;
import com.pro.gen.random.RandomTerrain;
import com.pro.gen.twod.levelpieces.LevelEnemies;
import com.pro.gen.twod.levelpieces.LevelEnemyStructures;
import com.pro.gen.twod.uibuttons.UIButtonGroup;
import com.pro.gen.utils.Assets;
import com.pro.gen.utils.ColorHelper;
import com.pro.gen.utils.Constants;
import com.pro.gen.weapons.CollisionChecker;
import com.pro.gen.weapons.SimplePistol;
import com.pro.gen.worldcomponents.ParallaxBackground;

/**
 * Created by Gallo on 10/27/2015.
 */
public class GroundView extends BaseView{

    CameraUpdater cameraUpdater;
    BackgroundSky background;
    ParallaxBackground terrain;
    TintedImage land, ship;
    Alien alien;
    LevelEnemies levelEnemies;
    LevelEnemyStructures levelEnemyStructures;
    CollisionChecker check;

    TintedImage image;

    public static final int WORLD_LENGTH = 10; //TODO: change to be based off planet level?

    Button leave;

    UIButtonGroup uiButtonGroup;


    @Override
    public void init() {
        check = new CollisionChecker(this);

        Color color = ColorHelper.generateDarkColor();
        Color backgroundColor = ColorHelper.changeHue(new Color(color), 0.3f);
        background = new BackgroundSky(Constants.PIXEL, new Color(backgroundColor));
        terrain = new ParallaxBackground(this, new RandomTerrain(15).getTextures(), color, 290, WORLD_LENGTH, 0.4f, 3);

        ship = new TintedImage(Constants.SMALL_SHIP, Color.WHITE);
        ship.setSize(250, 500);
        ship.setPosition(Constants.VIRTUAL_WIDTH / 2 - ship.getWidth() / 2, 100);

        leave = new Button(Constants.CURVERECT, Color.GREEN, "Leave", Assets.getInstance().getSmallFont());
        leave.setSize(150, 50);
        leave.setPosition(Constants.VIRTUAL_WIDTH / 2 - leave.getWidth() / 2, 620);

        land = new TintedImage(Constants.PIXEL, ColorHelper.generateGoodColor());
        land.setSize(Constants.VIRTUAL_WIDTH, 250);


        //Color cc = ColorHelper.generateGoodColor();
        //Texture head = PixmapColorize.genTexture(Constants.ALIEN_HEAD, cc);
        //Texture eye =PixmapColorize.genTexture(Constants.ALIEN_EYE, cc);
        //Texture leg =PixmapColorize.genTexture(Constants.ALIEN_LEG, cc);


        SimpleAlienBuilder alienBuilder = new SimpleAlienBuilder();
        Texture alienTexture = alienBuilder.build(80, 158, ColorHelper.generateGoodColor());
        alien = new Alien(new TextureRegion(alienTexture), check);
        alien.setWeapon(new SimplePistol(check));
        alien.setPosition(Constants.VIRTUAL_WIDTH / 2 - alien.getWidth() / 2, 250);
        alien.setSize(80, 158);


        cameraUpdater = new CameraUpdater(alien);

        uiButtonGroup = new UIButtonGroup(cameraUpdater, alien);

        cameraUpdater.register(background);
        cameraUpdater.register(land);
        cameraUpdater.register(terrain);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        cameraUpdater.updateCamera(delta);
    }

    @Override
    public void addActors() {
        addActorAt(0, background);
        addActor(ship);
        addActor(leave);
        addActor(land);
        addActor(alien);
        addActor(uiButtonGroup);

        levelEnemies = new LevelEnemies(this, 10, check);
    }


}
