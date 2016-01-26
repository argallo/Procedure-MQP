package com.pro.gen.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.pro.gen.aliens.AlienParts;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/22/2015.
 */
public class TextureBuilder {


    private Pixmap.Blending blending;

    public TextureBuilder(){

    }

    public Texture createTexture(ArrayList<AlienParts> alienParts, int width, int height){
        Pixmap textureFrame = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        for(AlienParts part: alienParts){
            Texture partImage = part.getImage().getTexture();
            final TextureData textureData = partImage.getTextureData();
            textureData.prepare();
            Pixmap pixmapImage = partImage.getTextureData().consumePixmap();
            if(part.getTint() != Color.WHITE)
                colorizePixmap(textureFrame, pixmapImage, part.getTint());
            textureFrame.drawPixmap(pixmapImage, 0, 0, partImage.getWidth(), partImage.getHeight(), part.getX(), part.getY(), part.getWidth(), part.getHeight());
            textureData.disposePixmap();
            partImage.dispose();
            if(!part.getSubParts().isEmpty())
                drawSubparts(textureFrame, part);
        }
        return new Texture(textureFrame);
    }

    public void drawSubparts(Pixmap textureFrame, AlienParts parentPart){
        for(AlienParts part: parentPart.getSubParts()) {
            part.setPosition(part.getX()+parentPart.getX(), part.getY()+parentPart.getY());
            Texture partImage = part.getImage().getTexture();
            final TextureData textureData = partImage.getTextureData();
            textureData.prepare();
            Pixmap pixmapImage = partImage.getTextureData().consumePixmap();
            if (part.getTint() != Color.WHITE)
                colorizePixmap(textureFrame, pixmapImage, part.getTint());
            textureFrame.drawPixmap(pixmapImage, 0, 0, partImage.getWidth(), partImage.getHeight(), part.getX(), part.getY(), part.getWidth(), part.getHeight());
            textureData.disposePixmap();
            partImage.dispose();
            if(!parentPart.getSubParts().isEmpty())
                drawSubparts(textureFrame, part);
        }
    }


    public void colorizePixmap(Pixmap textureFrame, Pixmap pixmap, Color tint){
        Color currentColor = new Color();
        Color baseColor = new Color();
        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++) {

                Color.rgba8888ToColor(currentColor, pixmap.getPixel(x, y));
                Color.rgba8888ToColor(baseColor, textureFrame.getPixel(x, y));

                if(currentColor.r < 1 && currentColor.g < 1 && currentColor.b < 1 && currentColor.a > 0.7){
                    blending = (baseColor.a != 0f && currentColor.a > 0.7) ? Pixmap.Blending.SourceOver : Pixmap.Blending.None;
                    pixmap.setBlending(blending);
                    pixmap.setColor(ColorHelper.colorize(currentColor, tint));
                    pixmap.fillRectangle(x, y, 1, 1);
                }
            }
        }
    }


}
