package com.pro.gen.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Gallo on 8/11/2015.
 */
public class Assets extends AssetManager {

    /**
     * texturesMap
     *
     */
    private HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();
    private TextureAtlas textureAtlas;
    private BitmapFont smallFont;
    private BitmapFont midFont;
    private BitmapFont largeFont;
    private TextureLoader.TextureParameter textureParam;
    private ViewID currentID = ViewID.UNKNOWN;

    private static final Assets INSTANCE = new Assets();

    public static Assets getInstance() {
        return INSTANCE;
    }

    public Assets() {
        //maybe someday ill find a use for anything but linear filters...
        textureParam = new TextureLoader.TextureParameter();
        textureParam.minFilter = Texture.TextureFilter.Linear;
        textureParam.magFilter = Texture.TextureFilter.Linear;

    }


    /**
     * loadAssets: loads all assets whose filepaths are associated with the screenID
     *
     * @param viewID The id of the current screen
     * @param isfinishLoading whether the function should block until all images are loaded
     */
    public void loadAssets(ViewID viewID, boolean isfinishLoading) {
        String[] assets = getAssetList(viewID);
        for(String asset : assets) {
            load(asset, Texture.class, textureParam);
        }
        if(isfinishLoading) {
            finishLoading();
        }
    }

    /**
     * loads assets that will stay loaded for all screens
     */
    public void loadCommonAssets() {
        loadAssets(ViewID.UNKNOWN, true);
    }

    /**
     * getTexture: uses the textures file path as the id so that it can
     * create a texture region of the image to return
     *
     * @param textureID The id of the texture
     * @return TextureRegion the texture found in assets
     */
    public TextureRegion getTexture (String textureID) {
        return new TextureRegion(this.get(textureID, Texture.class));
    }

    /**
     * disposeAssets: uses the current screenID to dispose of all loaded assets
     *
     * @param viewID The id of the current screen
     */
    public void unloadAssets(ViewID viewID) {
        if(viewID != ViewID.UNKNOWN) {
            String[] assets = getAssetList(viewID);
            for (String asset : assets) {
                unload(asset);
            }
        }
    }


    public void setCurrentID(ViewID currentID) {
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
     */
    public String[] getAssetList(ViewID viewID){
        switch(viewID){
            case APP:
                return Constants.APP_IMAGES;
            case UNKNOWN:
                return Constants.COMMON_IMAGES;
            default:
                return null;
        }
    }
}
