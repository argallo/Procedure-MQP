package com.pro.gen.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.random.RandomGlobeMapInfo;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Tint;
import com.pro.gen.worldcomponents.EmptyHat;
import com.pro.gen.worldcomponents.FuelUnits;
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

    public void savePlanet(Planet planet){
        //TODO: potentially make this check for next open slot depending on what will call this.
        savePlanet(planet, PreferenceManager.SLOT_1);
    }

    //finds the next open slot and saves the planet xml here
    public void savePlanet(Planet planet, String slot){
        try {
        xmlWriter.element("planet")
                .attribute("colortype", planet.getColorType())
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
                    .attribute("whiteSize", planet.getPlanetEyes().getWhiteSize())
                    .attribute("whiteX", planet.getPlanetEyes().getWhiteX())
                    .attribute("whiteY", planet.getPlanetEyes().getWhiteY())
                    .attribute("eyeSize", planet.getPlanetEyes().getEyeSize())
                    .attribute("eyeX", planet.getPlanetEyes().getEyeX())
                    .attribute("eyeY", planet.getPlanetEyes().getEyeY())
                    .attribute("blackSize", planet.getPlanetEyes().getBlackSize())
                    .attribute("blackX", planet.getPlanetEyes().getBlackX())
                    .attribute("blackY", planet.getPlanetEyes().getBlackY())
                    .attribute("glareWidth", planet.getPlanetEyes().getGlareWidth())
                    .attribute("glareHeight", planet.getPlanetEyes().getGlareHeight())
                    .attribute("glareX", planet.getPlanetEyes().getGlareX())
                    .attribute("glareY", planet.getPlanetEyes().getGlareY())
                    .attribute("rwePosition", planet.getPlanetEyes().getRwePosition())
                    .attribute("image", planet.getPlanetEyes().getImage())
                    .attribute("eyeColor", planet.getPlanetEyes().getEyeColor())
                    .pop()
                .element("hat")
                    .attribute("image", "hat1")
                    .pop()
                .pop();
        //LogUtils.Log(writer.toString());
    } catch (IOException e){

    }

    PreferenceManager.getInstance().saveString(slot, writer.toString());
    }
    //comment

    //updates planet when it gains experience
    public void updatePlanet(String slot, int size, int energy, int rank, int xp){
        Planet planet = getPlanet(slot);
        planet.setPlanetSize(size);
        planet.setPlanetEnergy(energy);
        planet.setGlobeRank(rank);
        planet.setCurrentXP(xp);
        planet.setRankXP((int)Math.pow(rank, 1.2)*100);
        savePlanet(planet, slot);
    }

    //updates planet with hat given
    public void addHatToPlanet(Hat hat, String slot){
        removeHatFromList(hat);
        Planet planet = getPlanet(slot);
        planet.setHat(hat);
        savePlanet(planet, slot);
    }

    //removes hat from planet at given slot
    public void removeHatFromPlanet(String slot){
        Planet planet = getPlanet(slot);
        //addToHatsList(planet.getHat());
        planet.setHat(new EmptyHat());
    }

    //removes planet at given slot and shifts other slots down
    public void deletePlanet(String slot){
        PreferenceManager.getInstance().saveString(slot, "");
    }

    //Saves solar system and specific planets that can be traveled to
    public void saveSolarSystem(SolarSystem solarSystem){
        // saves full planets as well
    }

    //save/updates the fuel units amount
    // the new units amount if less than replenish max then set the fuel timer to current time
    public void saveFuelUnits(int units){
        PreferenceManager.getInstance().saveInt(PreferenceManager.FUEL_UNITS, units);
        if(units < FuelUnits.MAX_FUEL_REPLENISH){
            setFuelTimer();
        }
    }

    //gets the current amount of fuel units
    public int getFuelUnits(){
        return PreferenceManager.getInstance().getInt(PreferenceManager.FUEL_UNITS);
    }

    //sets the timer to the current time
    public void setFuelTimer(){
        PreferenceManager.getInstance().saveString(PreferenceManager.FUEL_TIMER, String.valueOf(TimeUtils.millis()));
    }

    //if fuel units is less than max replenish than check timer to see if time past since set is greater than replenish time then add unit
    public long checkFuelTimer(){
        long timeStart = 0;
        if(getFuelUnits() < FuelUnits.MAX_FUEL_REPLENISH) {
            timeStart = Long.parseLong(PreferenceManager.getInstance().getString(PreferenceManager.FUEL_TIMER));
            if(TimeUtils.timeSinceMillis(timeStart) > FuelUnits.FUEL_REPLENISH_TIME) {
                saveFuelUnits(getFuelUnits()+1);
            }
        }
        return timeStart;
    }

    //saves/updates power crystals
    public void savePowerCrystals(int crystals){
        PreferenceManager.getInstance().saveInt(PreferenceManager.POWER_CRYSTALS, crystals);
    }

    public int getPowerCrystals(){
        return PreferenceManager.getInstance().getInt(PreferenceManager.POWER_CRYSTALS);
    }

    //saves mega crystals (probably only ++ each time)
    public void saveMegaCrystals(int crystals){
        PreferenceManager.getInstance().saveInt(PreferenceManager.MEGA_CRYSTALS, crystals);
    }

    public int getMegaCrystals(){
        return PreferenceManager.getInstance().getInt(PreferenceManager.MEGA_CRYSTALS);
    }

    //gets list of hats and adds new hat to list
    public void addToHatsList(Hat hat){

    }

    //removes hat from the list
    public void removeHatFromList(Hat hat){

    }

    //saves/updates boss level
    public void saveBossLevel(int level){
        PreferenceManager.getInstance().saveInt(PreferenceManager.BOSS_LEVEL, level);
    }

    public int getBossLevel(){
        return PreferenceManager.getInstance().getInt(PreferenceManager.BOSS_LEVEL);
    }


    public Planet getPlanet(String slot){
        String planetString = PreferenceManager.getInstance().getString(slot);
        XmlReader.Element root = reader.parse(planetString);
        Color baseColor = Color.valueOf(root.getAttribute("basecolor"));
        String colorType = root.getAttribute("colortype");
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

        //Create Eyes
        XmlReader.Element eye = root.getChildByName("eyes");
        PlanetEyes eyes = new PlanetEyes(Float.parseFloat(eye.getAttribute("whiteSize")),Float.parseFloat(eye.getAttribute("whiteX")),Float.parseFloat(eye.getAttribute("whiteY")),Float.parseFloat(eye.getAttribute("eyeSize")),Float.parseFloat(eye.getAttribute("eyeX")),Float.parseFloat(eye.getAttribute("eyeY")),Float.parseFloat(eye.getAttribute("blackSize")),
                Float.parseFloat(eye.getAttribute("blackX")),Float.parseFloat(eye.getAttribute("blackY")),Float.parseFloat(eye.getAttribute("glareWidth")),Float.parseFloat(eye.getAttribute("glareHeight")),Float.parseFloat(eye.getAttribute("glareX")),Float.parseFloat(eye.getAttribute("glareY")),Float.parseFloat(eye.getAttribute("rwePosition")),
               eye.getAttribute("image"),Color.valueOf(eye.getAttribute("eyeColor")));


        Hat hat = new Hat();

        return new Planet(colorType, baseColor,landMap, cloudMap, eyes,size,energy,globeRank,currentXP,rankXP,inhabitable,timeStart,amtofTime,hat,Tint.UNIVERSE_BACKGROUND_COLOR);
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