package com.pro.gen.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.random.RandomGlobeMapInfo;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.SolarSystem;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gallo-Desktop on 3/17/2016.
 */
public class XmlManager {

    private XmlWriter xmlWriter;
    private StringWriter writer;
    private XmlReader reader;

    public XmlManager(){
        writer = new StringWriter();
        xmlWriter = new XmlWriter(writer);
        reader = new XmlReader();
    }

    //finds the next open slot and saves the planet xml here
    public void savePlanet(Planet planet){
        try {
        xmlWriter.element("planet")
                .attribute("basecolor", planet.getBasePlanetColor())
                .attribute("size", planet.getPlanetSize())
                .attribute("energy", planet.getPlanetEnergy())
                .attribute("globerank", planet.getGlobeRank())
                .attribute("currentxp", planet.getCurrentXP())
                .attribute("rankxp", planet.getRankXP())
                .element("habitability")
                    .attribute("inhabitable",planet.isInhabitable())
                    .attribute("timestart", planet.getTimeStart())
                    .attribute("amtoftime",planet.getAmtofTime())
                    .pop()
                .element("land")
                    .attribute("image", planet.getLands().getRandomGlobeMapInfo().getRp().getName())
                    .attribute("color", planet.getLands().getRandomGlobeMapInfo().getColor())
                    .attribute("widths", planet.getLands().getRandomGlobeMapInfo().getRp().getWidths())
                    .attribute("heights", planet.getLands().getRandomGlobeMapInfo().getRp().getHeights())
                    .attribute("xs", planet.getLands().getRandomGlobeMapInfo().getRp().getXs())
                    .attribute("ys", planet.getLands().getRandomGlobeMapInfo().getRp().getYs())
                    .pop()
                .element("clouds")
                    .attribute("image", planet.getClouds().getRandomGlobeMapInfo().getRp().getName())
                    .attribute("color", planet.getClouds().getRandomGlobeMapInfo().getColor())
                    .attribute("widths", planet.getClouds().getRandomGlobeMapInfo().getRp().getWidths())
                    .attribute("heights", planet.getClouds().getRandomGlobeMapInfo().getRp().getHeights())
                    .attribute("xs", planet.getClouds().getRandomGlobeMapInfo().getRp().getXs())
                    .attribute("ys", planet.getClouds().getRandomGlobeMapInfo().getRp().getYs())
                    .pop()
                .element("eyes")
                    .attribute("image", Pic.Circle_Small)
                    .pop()
                .element("hat")
                    .attribute("image", "hat1")
                    .pop()
                .pop();
        LogUtils.Log(writer.toString());
    } catch (IOException e){

    }

    PreferenceManager.getInstance().saveString(PreferenceManager.SLOT_1, writer.toString());

    }

    //updates planet when it gains experience
    public void updatePlanet(int slot, int size, int energy, int rank, int xp){

    }

    //updates planet with hat given
    public void addHatToPlanet(Hat hat, int slot){
        removeHatFromList(hat);
    }

    //removes hat from planet at given slot
    public void removeHatFromPlanet(int slot){
        //addToHatsList();
    }

    //removes planet at given slot and shifts other slots down
    public void deletePlanet(int slot){

    }

    //Saves solar system and specific planets that can be traveled to
    public void saveSolarSystem(SolarSystem solarSystem){
        // saves full planets as well
    }

    //save/updates the fuel units amount
    //if param == 0 saves current datetime and amount of time until restock
    public void saveFuelUnits(int units){

    }

    //checks how much time is left before a fuel restock.
    //precondition: only call when fuelUnits is at 0
    //if time is <= 0 then add one fuel unit to saveFuelUnits(1).
    public long checkFuelTimer(){

        return 0;
    }

    //saves/updates power crystals
    public void savePowerCrystals(int crystals){

    }

    //saves mega crystals (probably only ++ each time)
    public void saveMegaCrystals(int crystals){

    }

    //gets list of hats and adds new hat to list
    public void addToHatsList(Hat hat){

    }

    //removes hat from the list
    public void removeHatFromList(Hat hat){

    }

    //saves/updates boss level
    public void saveBossLevel(int level){

    }



    public Planet getPlanet(String slot){
        String planetString = PreferenceManager.getInstance().getString(slot);
        XmlReader.Element root = reader.parse(planetString);
        Color baseColor = Color.valueOf(root.getAttribute("basecolor"));
        int size = root.getIntAttribute("size");
        int energy = root.getIntAttribute("energy");
        int globeRank = root.getIntAttribute("globerank");
        int currentXP = root.getIntAttribute("currentxp");
        int rankXP = root.getIntAttribute("rankxp");

        XmlReader.Element habitability = root.getChildByName("habitability");
        boolean inhabitable = Boolean.parseBoolean(habitability.getAttribute("inhabitable"));
        long timeStart = Long.parseLong(habitability.getAttribute("timestart"));
        long amtofTime = Long.parseLong(habitability.getAttribute("amtoftime"));

        //Create Land Globemap
        XmlReader.Element land = root.getChildByName("land");
        RandomPlacement rpLand = new RandomPlacement(land.getAttribute("image"),createFloatArray(land.getAttribute("xs")),
                createFloatArray(land.getAttribute("ys")),createFloatArray(land.getAttribute("widths")),createFloatArray(land.getAttribute("heights")));
        RandomGlobeMapInfo landInfo = new RandomGlobeMapInfo(Color.valueOf(land.getAttribute("color")), rpLand, Constants.VIRTUAL_WIDTH/300);
        GlobeMap landMap = new GlobeMap(landInfo);

        //Create Cloud Globemap
        XmlReader.Element clouds = root.getChildByName("clouds");
        RandomPlacement rpClouds = new RandomPlacement(clouds.getAttribute("image"),createFloatArray(clouds.getAttribute("xs")),
                createFloatArray(clouds.getAttribute("ys")),createFloatArray(clouds.getAttribute("widths")),createFloatArray(clouds.getAttribute("heights")));
        RandomGlobeMapInfo cloudInfo = new RandomGlobeMapInfo(Color.valueOf(clouds.getAttribute("color")), rpClouds, Constants.VIRTUAL_WIDTH/300);
        GlobeMap cloudMap = new GlobeMap(cloudInfo);

        PlanetEyes eyes = new PlanetEyes();
        Hat hat = new Hat();

        return new Planet(baseColor,landMap, cloudMap, eyes,size,energy,globeRank,currentXP,rankXP,inhabitable,timeStart,amtofTime,hat,Tint.UNIVERSE_BACKGROUND_COLOR);
    }


    private float[] createFloatArray(String stringArray){
        List<String> list = Arrays.asList(stringArray.substring(1, stringArray.length() - 1).split(", "));
        float[] floats = new float[list.size()];
        for(int i = 0; i < list.size(); i++){
            floats[i] = Float.parseFloat(list.get(i));
        }
        return floats;
    }





}




/* xmlWriter.element("planet")
                    .attribute("basecolor", Tint.BLAST_ORANGE_A)
                    .attribute("size", 600)
                    .attribute("energy", 300)
                    .attribute("globerank",1)
                    .attribute("currentxp", 30)
                    .attribute("rankxp", 100)
                    .element("habitability")
                        .attribute("inhabitable",true)
                        .attribute("timestart", 150)
                        .attribute("amtoftime",5000)
                        .pop()
                    .element("land")
                        .attribute("image", Pic.Circle_Small)
                        .attribute("color", Color.GREEN)
                        .attribute("widths", "10, 15, 20, 34")
                        .attribute("heights", "33, 4, 23, 63")
                        .attribute("xs", "180, 800, 400, 200")
                        .attribute("ys", "200, 250, 600, 450")
                        .pop()
                    .element("clouds")
                        .attribute("image", Pic.Circle_Small)
                        .attribute("color", Color.RED)
                        .attribute("widths", "10, 15, 20, 34")
                        .attribute("heights", "33, 4, 23, 63")
                        .attribute("xs", "180, 800, 400, 200")
                        .attribute("ys", "200, 250, 600, 450")
                        .pop()
                    .element("eyes")
                        .attribute("image", Pic.Circle_Small)
                        .pop()
                    .element("hat")
                        .attribute("image", "hat1")
                        .pop()
                    .pop();
            LogUtils.Log(writer.toString());
        } catch (IOException e){

        }*/