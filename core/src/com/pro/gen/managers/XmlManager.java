package com.pro.gen.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.random.RandomGlobeMapInfo;
import com.pro.gen.random.RandomPlacement;
import com.pro.gen.utils.Constants;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Tint;
import com.pro.gen.worldcomponents.FuelUnits;
import com.pro.gen.worldcomponents.GlobeMap;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.SolarSystem;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gallo-Desktop on 3/17/2016.
 */
public class XmlManager {

    private static final XmlManager INSTANCE = new XmlManager();

    public static XmlManager getInstance() {
        return INSTANCE;
    }

    private XmlManager(){

    }

    public void savePlanet(Planet planet){
        savePlanet(planet, checkOpenSlot());
    }

    public String checkOpenSlot(){
        if(PreferenceManager.getInstance().getString(PreferenceManager.SLOT_1).equals("")){
            return PreferenceManager.SLOT_1;
        } else if(PreferenceManager.getInstance().getString(PreferenceManager.SLOT_2).equals("")){
            return PreferenceManager.SLOT_2;
        } else if(PreferenceManager.getInstance().getString(PreferenceManager.SLOT_3).equals("")){
            return PreferenceManager.SLOT_3;
        } else if(PreferenceManager.getInstance().getString(PreferenceManager.SLOT_4).equals("")){
            return PreferenceManager.SLOT_4;
        } else {
            return null;
        }
    }

    //finds the next open slot and saves the planet xml here
    public void savePlanet(Planet planet, String slot){
        PreferenceManager.getInstance().saveString(slot, xmlPlanet(planet));
    }
    //comment

    //updates planet when it gains experience
    public void updatePlanet(String slot, int size, int energy, int rank, int xp){
        Planet planet = getPlanetFromSlot(slot);
        planet.setPlanetSize(size);
        planet.setPlanetEnergy(energy);
        planet.setGlobeRank(rank);
        planet.setCurrentXP(xp);
        planet.setRankXP((int) Math.pow(rank, 1.2) * 100);
        savePlanet(planet, slot);
    }

    //removes planet at given slot and shifts other slots down
    public void deletePlanet(String slot){
        PreferenceManager.getInstance().saveString(slot, "");
        if(slot.equals(PreferenceManager.SLOT_1)){
            slide(PreferenceManager.getInstance().SLOT_2, PreferenceManager.SLOT_1);
            slide(PreferenceManager.getInstance().SLOT_3, PreferenceManager.SLOT_2);
            slide(PreferenceManager.getInstance().SLOT_4, PreferenceManager.SLOT_3);
            PreferenceManager.getInstance().saveString(PreferenceManager.getInstance().SLOT_4, "");
        } else if(slot.equals(PreferenceManager.SLOT_2)){
            slide(PreferenceManager.getInstance().SLOT_3, PreferenceManager.SLOT_2);
            slide(PreferenceManager.getInstance().SLOT_4, PreferenceManager.SLOT_3);
            PreferenceManager.getInstance().saveString(PreferenceManager.getInstance().SLOT_4, "");
        } else if (slot.equals(PreferenceManager.SLOT_3)){
            slide(PreferenceManager.getInstance().SLOT_4, PreferenceManager.SLOT_3);
            PreferenceManager.getInstance().saveString(PreferenceManager.getInstance().SLOT_4, "");
        }
    }

    public void slide(String slotFrom, String slotTo){
        String planetString = PreferenceManager.getInstance().getString(slotFrom);
        PreferenceManager.getInstance().saveString(slotTo, planetString);
    }

    public void swap(String slotFrom, String slotTo){
        String planetStringFrom = PreferenceManager.getInstance().getString(slotFrom);
        String planetStringTo = PreferenceManager.getInstance().getString(slotTo);
        PreferenceManager.getInstance().saveString(slotTo, planetStringFrom);
        PreferenceManager.getInstance().saveString(slotFrom, planetStringTo);
    }

    public boolean hasSolarSystem(){
        if(PreferenceManager.getInstance().getString(PreferenceManager.SOLAR_SYSTEM).equals("")){
            return false;
        }
        return true;
    }

    //Saves solar system and specific planets that can be traveled to
    public void saveSolarSystem(SolarSystem solarSystem){
        // saves full planets as well
        ArrayList<Planet> planets = solarSystem.getPlanets();
        String solarSystemString = "";
        StringWriter writer = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(writer);
        try {
            xmlWriter.element("solarsystem");
            for(int i = 0; i < planets.size(); i++){
                xmlWriter.element("planets",xmlPlanet(planets.get(i)));
            }
            xmlWriter.pop();
            solarSystemString = writer.toString();
            xmlWriter.flush();
            xmlWriter.close();
        }catch (IOException e){}

        PreferenceManager.getInstance().saveString(PreferenceManager.SOLAR_SYSTEM, solarSystemString);
    }

    public void removePlanetFromSolarSystem(Planet planet){
        LogUtils.Log(planet.getPlanetName());
        ArrayList<Planet> planets = getSolarSystemPlanets();
        for(Planet splanets: planets){
            if(splanets.getPlanetName().equals(planet.getPlanetName())){
                planets.remove(splanets);
                break;
            }
        }
        saveSolarSystem(new SolarSystem(planets));
    }

    public ArrayList<Planet> getSolarSystemPlanets(){
        String solarSystemString = PreferenceManager.getInstance().getString(PreferenceManager.SOLAR_SYSTEM);
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(solarSystemString);
        ArrayList<Planet> planets = new ArrayList<Planet>();
        Array<XmlReader.Element> planetArray = root.getChildrenByName("planets");
        for(XmlReader.Element planet :planetArray){
            planets.add(getPlanet(planet.getChildByName("planet").toString()));
        }
        return planets;
    }

    public SolarSystem getSolarSystem(){
        return new SolarSystem(getSolarSystemPlanets());
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
        LogUtils.Log(crystals);
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
        ArrayList<Hat> hatList = getHatList();
        hatList.add(hat);
        saveHatList(hatList);
    }

    public ArrayList<Hat> getHatList(){
        String hatString = PreferenceManager.getInstance().getString(PreferenceManager.HAT_LIST);
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(hatString);
        ArrayList<Hat> hatList = new ArrayList<Hat>();
        Array<XmlReader.Element> hatArray = root.getChildrenByName("hat");
        for(XmlReader.Element hat :hatArray){
            hatList.add(new Hat(hat.getAttribute("image"), Integer.parseInt(hat.getAttribute("hatID")),Color.valueOf(hat.getAttribute("hatColor")), Integer.parseInt(hat.getAttribute("effect")),
                    Integer.parseInt(hat.getAttribute("powerAmt"))));
        }
        return hatList;
    }

    public void saveHatList(ArrayList<Hat> hats){

        String hatString = "";
        StringWriter writer = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(writer);
        try {
            xmlWriter.element("hatlist");
            for(int i = 0; i < hats.size(); i++){
                xmlWriter.element("hat")
                        .attribute("image", hats.get(i).getImage())
                        .attribute("hatID", hats.get(i).getHatID())
                        .attribute("hatColor", hats.get(i).getHatColor().toString())
                        .attribute("effect", hats.get(i).getEffect())
                        .attribute("powerAmt", hats.get(i).getPowerAmt())
                        .pop();
            }
            xmlWriter.pop();
            hatString = writer.toString();
           // LogUtils.Log(hatString);
            xmlWriter.flush();
            xmlWriter.close();
        }catch (IOException e){}

        PreferenceManager.getInstance().saveString(PreferenceManager.HAT_LIST, hatString);
    }

    //saves/updates boss level
    public void saveBossLevel(int level){
        PreferenceManager.getInstance().saveInt(PreferenceManager.BOSS_LEVEL, level);
    }

    public int getBossLevel(){
        return PreferenceManager.getInstance().getInt(PreferenceManager.BOSS_LEVEL);
    }

    public void saveBossPlanet(Planet planet){
        PreferenceManager.getInstance().saveString(PreferenceManager.BOSS_SLOT, xmlPlanet(planet));
    }

    public Planet getBossPlanet(){
        return getPlanet(PreferenceManager.getInstance().getString(PreferenceManager.BOSS_SLOT));
    }

    public boolean hasEmptySlot(){
        if(checkOpenSlot()!= null){
            return true;
        }
        return false;
    }

    public String getCurrentSlot(){
        Planet planet;
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_1).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_1);
            if(!planet.isInhabitable()){
                return PreferenceManager.SLOT_1;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_2).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_2);
            if(!planet.isInhabitable()){
                return PreferenceManager.SLOT_2;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_3).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_3);
            if(!planet.isInhabitable()){
                return PreferenceManager.SLOT_3;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_4).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_4);
            if(!planet.isInhabitable()){
                return PreferenceManager.SLOT_4;
            }
        }
        return null;
    }

    public Planet getFirstHabitablePlanet(){
        Planet planet;
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_1).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_1);
            if(!planet.isInhabitable()){
                return planet;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_2).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_2);
            if(!planet.isInhabitable()){
                return planet;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_3).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_3);
            if(!planet.isInhabitable()){
                return planet;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_4).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_4);
            if(!planet.isInhabitable()){
                return planet;
            }
        }
        return null;
    }

    public boolean hasHabitable(){
        Planet planet;
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_1).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_1);
            if(!planet.isInhabitable()){
                return true;
            }
        }

        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_2).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_2);
            if(!planet.isInhabitable()){
                return true;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_3).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_3);
            if(!planet.isInhabitable()){
                return true;
            }
        }
        if(!PreferenceManager.getInstance().getString(PreferenceManager.SLOT_4).equals("")){
            planet = getPlanetFromSlot(PreferenceManager.SLOT_4);
            if(!planet.isInhabitable()){
                return true;
            }
        }
        return false;
    }

    public Planet getPlanetFromSlot(String slot){
        String planetString = PreferenceManager.getInstance().getString(slot);
        if(!planetString.equals("")){
            return getPlanet(planetString);
        }
        return null;
    }

    public ArrayList<String> getUsernames(String xmlString){
        ArrayList<String> usernames = new ArrayList<String>();
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(xmlString);
        Array<XmlReader.Element> userArray = root.getChildrenByName("username");
        LogUtils.Log("root name "+root.getName());
        LogUtils.Log(root.getChildByName("username").getText());
        for(XmlReader.Element user :userArray){
            LogUtils.Log("Username="+user.getText());
            usernames.add(user.getText());
        }
        return usernames;
    }

    public ArrayList<String> getScores(String xmlString){
        ArrayList<String> scores = new ArrayList<String>();
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(xmlString);
        Array<XmlReader.Element> scoreArray = root.getChildrenByName("score");
        for(XmlReader.Element score :scoreArray){
            LogUtils.Log("Score="+score.getText());
            scores.add(score.getText());
        }
        return scores;
    }

    public Planet getPlanet(String planetString){
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(planetString);
        Color baseColor = Color.valueOf(root.getAttribute("basecolor"));
        String colorType = root.getAttribute("colortype");
        String planetName = root.getAttribute("planetname");
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

        XmlReader.Element hat = root.getChildByName("hat");
        Hat hats = new Hat(hat.getAttribute("image"), Integer.parseInt(hat.getAttribute("hatID")),Color.valueOf(hat.getAttribute("hatColor")), Integer.parseInt(hat.getAttribute("effect")),
                Integer.parseInt(hat.getAttribute("powerAmt")));
        return new Planet(colorType, baseColor,landMap, cloudMap, eyes,size,energy,globeRank,currentXP,rankXP,inhabitable,timeStart,amtofTime,hats,Tint.UNIVERSE_BACKGROUND_COLOR, planetName);
    }

    private float[] createFloatArray(String stringArray){
        List<String> list = Arrays.asList(stringArray.substring(1, stringArray.length() - 1).split(", "));
        float[] floats = new float[list.size()];
        for(int i = 0; i < list.size(); i++){
            floats[i] = Float.parseFloat(list.get(i));
        }
        return floats;
    }

    public String xmlPlanet(Planet planet){
        String planetString = "";
        StringWriter writer = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(writer);
        try {
            xmlWriter.element("planet")
                    .attribute("colortype", planet.getColorType())
                    .attribute("basecolor", planet.getBasePlanetColor())
                    .attribute("size", planet.getPlanetSize())
                    .attribute("energy", planet.getPlanetEnergy())
                    .attribute("globerank", planet.getGlobeRank())
                    .attribute("currentxp", planet.getCurrentXP())
                    .attribute("rankxp", planet.getRankXP())
                    .attribute("planetname", planet.getPlanetName())
                    .element("habitability")
                    .attribute("inhabitable", planet.isInhabitable())
                    .attribute("timestart", planet.getTimeStart())
                    .attribute("amtoftime", planet.getAmtofTime())
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
                    .attribute("image", planet.getHat().getImage())
                    .attribute("hatID", planet.getHat().getHatID())
                    .attribute("hatColor", planet.getHat().getHatColor())
                    .attribute("effect", planet.getHat().getEffect())
                    .attribute("powerAmt", planet.getHat().getPowerAmt())
                    .pop()
                    .pop();
            //LogUtils.Log(writer.toString());
            planetString = writer.toString();
            xmlWriter.flush();
            xmlWriter.close();
        } catch (IOException e){

        }
        return planetString;
    }


    public String convertSlotintToString(int slot){
        switch (slot){
            case 0:
                return PreferenceManager.SLOT_1;
            case 1:
                return PreferenceManager.SLOT_2;
            case 2:
                return PreferenceManager.SLOT_3;
            case 3:
                return PreferenceManager.SLOT_4;
            default:
                return PreferenceManager.SLOT_1;
        }
    }


}