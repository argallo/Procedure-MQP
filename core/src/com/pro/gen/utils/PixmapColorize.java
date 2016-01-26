package com.pro.gen.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.pro.gen.twod.levelpieces.LevelEnemies;

/**
 * Created by Gallo on 11/13/2015.
 */
public class PixmapColorize {
    static boolean test = false;

    public static Texture genTexture(String texturename, Color colortype) {
        Texture tex = Assets.getInstance().getTextureRegion(texturename).getTexture();

        TextureData textureData = tex.getTextureData();
        textureData.prepare();


        Pixmap pixmap = tex.getTextureData().consumePixmap();

        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++) {

                Color color = new Color();
                Color.rgba8888ToColor(color, pixmap.getPixel(x, y));
                if(color.r < 1 && color.g < 1 && color.b < 1 && color.a == 1){
                    pixmap.setColor(ColorHelper.colorize(color, colortype));
                    pixmap.fillRectangle(x, y, 1, 1);
                }
            }
        }

        tex = new Texture(pixmap);
        textureData.disposePixmap();
        pixmap.dispose();

        return tex;
    }

    public static void genTexture2(String texturename, String texturename2, final Color colortype, final LevelEnemies levelEnemies) {

        Texture tex = Assets.getInstance().getTextureRegion(texturename).getTexture();
        Texture tex2 = Assets.getInstance().getTextureRegion(texturename2).getTexture();

        final TextureData textureData = tex.getTextureData();
        textureData.prepare();

        final TextureData textureData2 = tex2.getTextureData();
        textureData2.prepare();

        final Pixmap pixmap = tex.getTextureData().consumePixmap();
        final Pixmap pixmap2 = tex2.getTextureData().consumePixmap();

        new Thread(new Runnable() {
            @Override
            public void run() {

                // do something important here, asynchronously to the rendering thread
                pixmap.drawPixmap(pixmap2,0,0);

                for (int y = 0; y < pixmap.getHeight(); y++) {
                    for (int x = 0; x < pixmap.getWidth(); x++) {

                        Color color = new Color();
                        Color.rgba8888ToColor(color, pixmap.getPixel(x, y));
                        if(color.r < 1 && color.g < 1 && color.b < 1 && color.a == 1){
                            pixmap.setColor(ColorHelper.colorize(color, colortype));
                            pixmap.fillRectangle(x, y, 1, 1);
                        }
                    }
                }
                LogUtils.Log();
                test = true;
                // post a Runnable to the rendering thread that processes the result
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // process the result, e.g. add it to an Array<Result> field of the ApplicationListener.
                        levelEnemies.addTexture(pixmap);
                        textureData.disposePixmap();
                        textureData2.disposePixmap();
                        pixmap.dispose();
                        pixmap2.dispose();
                    }
                });
            }
        }).start();


    }

}
