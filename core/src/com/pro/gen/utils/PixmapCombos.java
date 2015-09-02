package com.pro.gen.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.pro.gen.components.ComboImage;

/**
 * Created by Gallo on 8/31/2015.
 */
public class PixmapCombos{

    Texture comboTexture;
    Color baseColor;
    Color currentColor;
    Color newColor;
    Pixmap pixmap;
    Pixmap.Blending blending;


    public PixmapCombos(ComboImage comboImage) {
        Texture texture = comboImage.getBaseTexture();
        texture.getTextureData().prepare();
        pixmap = texture.getTextureData().consumePixmap();
        pixmap.setBlending(Pixmap.Blending.None);
        baseColor = new Color();
        newColor = comboImage.getBaseColor();

        for (int x = 0; x < pixmap.getWidth(); x++) {
            for (int y = 0; y < pixmap.getHeight(); y++) {
                Color.rgba8888ToColor(baseColor, pixmap.getPixel(x, y));
                newColor.a = baseColor.a;
                pixmap.setColor(newColor);
                pixmap.fillRectangle(x, y, 1, 1);
            }
        }

        for(int index = 0; index < comboImage.getLength(); index++) {
            Texture layerTexture = comboImage.getTexture(index);
            layerTexture.getTextureData().prepare();
            Pixmap tempPixmap = layerTexture.getTextureData().consumePixmap();
            Pixmap layerPixmap = new Pixmap(comboImage.getWidth(index), comboImage.getHeight(index), tempPixmap.getFormat());
            layerPixmap.drawPixmap(tempPixmap, 0, 0, tempPixmap.getWidth(), tempPixmap.getHeight(), 0, 0, layerPixmap.getWidth(), layerPixmap.getHeight());
            currentColor = new Color();
            newColor = comboImage.getColor(index);
            pixmap.setBlending(Pixmap.Blending.None);
            for (int x = comboImage.getX(index); x < comboImage.getX(index) + comboImage.getWidth(index); x++) {
                for (int y = comboImage.getY(index); y < comboImage.getY(index) + comboImage.getHeight(index); y++) {
                    Color.rgba8888ToColor(baseColor, pixmap.getPixel(x, y));
                    Color.rgba8888ToColor(currentColor, layerPixmap.getPixel(x - comboImage.getX(index), y - comboImage.getY(index)));
                    //might be able to get rid of this if phones texture filter is good enough
                    blending = (baseColor.a != 0f && currentColor.a < 1) ? Pixmap.Blending.SourceOver : Pixmap.Blending.None;
                    pixmap.setBlending(blending);
                    newColor.a = currentColor.a;
                    pixmap.setColor(newColor);
                    pixmap.fillRectangle(x, y, 1, 1);
                }
            }
            tempPixmap.dispose();
            layerPixmap.dispose();
        }

        comboTexture = new Texture(pixmap);
        comboTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public Texture getTexture(){
        return comboTexture;
    }
}
