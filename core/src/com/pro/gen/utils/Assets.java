package com.pro.gen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by Gallo on 8/11/2015.
 */
public class Assets extends AssetManager {


    private TextureAtlas textureAtlas;

    //Font vars
    private BitmapFont xsmallFont;
    private BitmapFont smallFont;
    private BitmapFont midFont;
    private BitmapFont largeFont;
    private FreeTypeFontGenerator.FreeTypeFontParameter font;
    private FreeTypeFontGenerator generator;

    private TextureLoader.TextureParameter textureParam;


    private static final Assets INSTANCE = new Assets();

    public static Assets getInstance() {
        return INSTANCE;
    }

    public Assets() {
        //maybe someday ill find a use for anything but linear filters...
        textureParam = new TextureLoader.TextureParameter();
        textureParam.minFilter = Texture.TextureFilter.Linear;
        textureParam.magFilter = Texture.TextureFilter.Linear;
        createFonts();

    }

    /**
     * Creates the different fonts that will be used in the application
     */
    public void createFonts(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/arial.ttf"));
        font = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font.size = 20;
        xsmallFont = generator.generateFont(font);
        font.size = 35;
        smallFont = generator.generateFont(font);
        font.size = 50;
        midFont = generator.generateFont(font);
        font.size = 100;
        largeFont = generator.generateFont(font);
    }

    /**
     * Splash background image will not be in the texture atlas that way we can show this image
     * while we load the texture atlas images.
     * @return the texture region containing the splash texture
     */
    public TextureRegion getSplash(){
        load(Pic.Splash, Texture.class, textureParam);
        finishLoading();
        return new TextureRegion(get(Pic.Splash,Texture.class));
    }

    /**
     * Splash image is only needed during the splash loading screen and will not be used again
     * so we can dispose of it to free up space.
     */
    public void disposeSplash(){
        unload(Pic.Splash);
    }

    /**
     * loads assets that will stay loaded for all screens
     */
    public void loadCommonAssets() {
        load("Appimages/textureatlas.atlas", TextureAtlas.class);
        load(Pic.UI_Full, Texture.class, textureParam);
        load(Pic.UI_Open, Texture.class, textureParam);
        load(Pic.Ship, Texture.class, textureParam);
        load(Pic.Ship_V, Texture.class, textureParam);
        load(Pic.Blank_Popup, Texture.class, textureParam);
        load(Pic.Bg1, Texture.class, textureParam);
        load(Pic.Bg2, Texture.class, textureParam);
        load(Pic.Bg3, Texture.class, textureParam);
        load(Pic.Bg4, Texture.class, textureParam);
        load(Pic.Bg5, Texture.class, textureParam);
        load(Pic.Globe_Rank, Texture.class, textureParam);
        load(Pic.TechOverlay_VMed, Texture.class, textureParam);
        load(Pic.TechOverlay_HSmall, Texture.class, textureParam);
        load(Pic.TechOverlay_HLarge, Texture.class, textureParam);
        load(Pic.Exlopre_Icon, Texture.class, textureParam);
        load(Pic.ManagePlanets_Icon, Texture.class, textureParam);
        load(Pic.BossBattle_Icon, Texture.class, textureParam);
        load(Pic.Leaderboard_Icon, Texture.class, textureParam);
        load(Pic.Shop_Icon, Texture.class, textureParam);
        load(Pic.Laser_Icon, Texture.class, textureParam);
        load(Pic.Star_Twinkle, Texture.class, textureParam);
        load(Pic.Target, Texture.class, textureParam);
        load(Pic.Power_Bar, Texture.class, textureParam);
        load(Pic.Boss_Plat, Texture.class, textureParam);
        load(Pic.Triangle_Eye, Texture.class, textureParam);
        load(Pic.DuRag_Hat, Texture.class, textureParam);
        load(Pic.Top_Hat, Texture.class, textureParam);

        finishLoading();
    }

    /**
     * getTextureRegion: uses the textures file path as the id so that it can
     * create a texture region of the image to return
     *
     * @param textureID The id of the texture
     * @return TextureRegion the texture found in assets
     */
    public TextureRegion getTextureRegion(String textureID) {
        if(textureID.endsWith(".png")){
            return new TextureRegion(get(textureID, Texture.class));
        } else {
            return get("Appimages/textureatlas.atlas", TextureAtlas.class).findRegion(textureID);
        }
    }

    public BitmapFont getXSmallFont() {
        return xsmallFont;
    }
    public BitmapFont getSmallFont() {
        return smallFont;
    }
    public BitmapFont getMidFont() {
        return midFont;
    }
    public BitmapFont getLargeFont() {
        return largeFont;
    }














    /**
     * disposeAssets: uses the current screenID to dispose of all loaded assets
     *
     * @param viewID The id of the current screen

    public void unloadAssets(ViewID viewID) {
        if(viewID != ViewID.UNKNOWN) {
            String[] assets = getAssetList(viewID);
            for (String asset : assets) {
                unload(asset);
            }
        }
    }


   /* public void setCurrentID(ViewID currentID) {
        this.currentID = currentID;
    }

    public ViewID getCurrentID() {
        return currentID;
    }


    /**
     * getAssetList: gets the list of assets file paths that are associated with the current screenID
     *
     * @param viewID The id of the current screen
     * @return the string array containing the current screens image file paths

    public String[] getAssetList(ViewID viewID){
        switch(viewID){
            case SPLASH:
                return Constants.SPLASH_IMAGES;
            case CREATE_ACCOUNT:
                return Constants.CREATE_ACCOUNT_IMAGES;
            case CREATE_ALIEN:
                return Constants.CREATE_ALIEN_IMAGES;
            case MAIN_MENU:
                return Constants.CREATE_ALIEN_IMAGES;
            case APP:
                return Constants.APP_IMAGES;
            case LANDING:
                return Constants.LAND_IMAGES;
            case LAND:
                return Constants.GROUND_IMAGES;
            case UNKNOWN:
                return Constants.COMMON_IMAGES;
            default:
                return null;
        }
    }
    */

    /**
     * loadAssets: loads all assets whose filepaths are associated with the screenID
     *
     * @param viewID The id of the current screen
     * @param isfinishLoading whether the function should block until all images are loaded

    public void loadAssets(ViewID viewID, boolean isfinishLoading) {
    String[] assets = getAssetList(viewID);
    for(String asset : assets) {
    load(asset, Texture.class, textureParam);
    }
    if(isfinishLoading) {
    finishLoading();
    }
    }
     */

}
