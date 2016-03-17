package com.pro.gen.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.XmlWriter;
import com.pro.gen.utils.LogUtils;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.SolarSystem;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Gallo-Desktop on 3/17/2016.
 */
public class XmlManager {

    private XmlWriter xmlWriter;
    private StringWriter writer;


    /**
     *     private TintedImage basePlanet; //base planet includes the base color for the planet
     private GlobeMap lands; //land is the land images with their specific color sizes and positions
     private GlobeMap clouds; //cloud is the land images with their specific color sizes and positions
     private TintedImage circleHole_left, circleHole_Right; // Images that blend with background to give revolving effect
     private PlanetEyes planetEyes; //the eyes that were selected for the planet
     private Hat hat; // the hat that the planet is currently wearing

     private int planetSize; // The size stat of the planet
     private int planetEnergy; // The Energy Output of the planet
     private int globeRank; // The Globe Rank of the planet
     private int currentXP; // The Current XP that will be used to increase globe rank
     private int rankXP; // The overall amount of XP needed to increase globe rank
     private boolean inhabitable; //whether the planet is inhabitable
     private long timeStart; // the time at which it became inhabitable
     private long amtofTime; // the amount of time from timeStart to become habitable
     */
    public XmlManager(){
        writer = new StringWriter();
        xmlWriter = new XmlWriter(writer);
    }

    //finds the next open slot and saves the planet xml here
    public void savePlanet(Planet planet){
        try {
            xmlWriter.element("planet")
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

        }
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




}
