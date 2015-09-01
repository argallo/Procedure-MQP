package com.pro.gen.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Gallo on 8/31/2015.
 */
public class PixmapCombos{

    Texture texture1;

    /**
     * TODO: Make PixmapCombos usable through parameters
     */
    public PixmapCombos (){
        Texture texture = Assets.getInstance().getTexture(Constants.TWINKLE).getTexture();
        texture.getTextureData().prepare();
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        pixmap.setBlending(Pixmap.Blending.None);

        Color color  = new Color();
        for(int x = 0; x < pixmap.getWidth(); x++){
            for(int y = 0; y < pixmap.getHeight(); y++){
                Color.rgba8888ToColor(color, pixmap.getPixel(x, y));
                pixmap.setColor(new Color(110/255f,36/255f,185/255f, color.a));
                pixmap.fillRectangle(x, y, 1, 1);
            }
        }

        Texture texture2 = Assets.getInstance().getTexture(Constants.CIRCLE_SMALL).getTexture();
        texture2.getTextureData().prepare();
        Pixmap pixmap2 = texture2.getTextureData().consumePixmap();
        Pixmap temp = new Pixmap(pixmap2.getWidth() / 2, pixmap2.getHeight() / 2, pixmap2.getFormat());
        temp.drawPixmap(pixmap2, 0, 0, pixmap2.getWidth(), pixmap2.getHeight(), 0, 0, temp.getWidth(), temp.getHeight());
        Color color2 = new Color();

        pixmap.setBlending(Pixmap.Blending.None);
        for(int x = temp.getWidth()/2; x < temp.getWidth()*1.5; x++){
            for(int y = temp.getHeight()/2; y < temp.getHeight()*1.5; y++){
                Color.rgba8888ToColor(color, temp.getPixel(x - temp.getWidth() / 2, y - temp.getHeight() / 2));
                Color.rgba8888ToColor(color2, pixmap.getPixel(x, y));
                    //might be able to get rid of this if phones texture filter is good enough
                    if(color2.a != 0f && color.a < 1){
                        pixmap.setBlending(Pixmap.Blending.SourceOver);
                    }
                    else{
                        pixmap.setBlending(Pixmap.Blending.None);
                    }

                    pixmap.setColor(new Color(1f, 0f, 1f, color.a));
                    pixmap.fillRectangle(x, y, 1, 1);


            }
        }




        Texture texture3 = Assets.getInstance().getTexture(Constants.CIRCLE_SMALL).getTexture();
        texture3.getTextureData().prepare();
        Pixmap pixmap3 = texture3.getTextureData().consumePixmap();
        Pixmap temp2 = new Pixmap(pixmap3.getWidth() / 4, pixmap3.getHeight() / 4, pixmap3.getFormat());
        temp2.drawPixmap(pixmap3, 0, 0, pixmap3.getWidth(), pixmap3.getHeight(), 0, 0, temp2.getWidth(), temp2.getHeight());


        pixmap.setBlending(Pixmap.Blending.None);
        for(int x = (temp2.getWidth()/2)*3; x < (temp2.getWidth()/2)*5; x++){
            for(int y = (temp2.getHeight()/2)*3; y < (temp2.getHeight()/2)*5; y++){
                Color.rgba8888ToColor(color, temp2.getPixel(x - (temp2.getWidth() /2)*3, y - (temp2.getHeight() /2)*3));
                Color.rgba8888ToColor(color2, pixmap.getPixel(x, y));
                if(color2.a != 0f && color.a < 1){
                    pixmap.setBlending(Pixmap.Blending.SourceOver);
                }
                else{
                    pixmap.setBlending(Pixmap.Blending.None);
                }

                pixmap.setColor(new Color(1f, 1f, 1f, color.a));
                pixmap.fillRectangle(x, y, 1, 1);
            }
        }

        texture1 = new Texture(pixmap);
        texture1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pixmap.dispose();
        pixmap2.dispose();
    }

    public Texture getTexture(){
        return texture1;
    }
}
