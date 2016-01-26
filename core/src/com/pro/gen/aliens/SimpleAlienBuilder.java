package com.pro.gen.aliens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.pro.gen.simplealien.Heads;
import com.pro.gen.simplealien.Legs;
import com.pro.gen.utils.TextureBuilder;

import java.util.ArrayList;

/**
 * Created by Gallo on 11/12/2015.
 */
public class SimpleAlienBuilder implements AlienBuilder {

    private ArrayList<AlienParts> alienParts;
    private TextureBuilder textureBuilder;

    public SimpleAlienBuilder(){
        alienParts = new ArrayList<AlienParts>();
        textureBuilder = new TextureBuilder();
    }

   public void buildHead(int width, int height, Color color){
        alienParts.add(Heads.simpleHead(width, height, color));
    }

    public void buildBody(int width, int height, Color color){
        //alienParts.add(Bodys.simpleBody(width, height));
    }

    public void buildLegs(int width, int height, Color color){
        alienParts.add(Legs.Legs(width, height, color));
    }

    public void buildArms(int width, int height, Color color){
        //alienParts.add(Arms.simpleArms(width, height));
    }

    @Override
    public Texture build(int width, int height, Color color) {
        buildLegs(width, height, color);
        buildBody(width, height, color);
        buildHead(width, height, color);
        buildArms(width, height, color);

        return textureBuilder.createTexture(alienParts, width, height);
    }


}
