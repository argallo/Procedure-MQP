package com.pro.gen.managers;

import com.badlogic.gdx.utils.XmlWriter;
import com.pro.gen.worldcomponents.Hat;
import com.pro.gen.worldcomponents.Planet;
import com.pro.gen.worldcomponents.SolarSystem;

import java.io.StringWriter;

/**
 * Created by Gallo-Desktop on 3/17/2016.
 */
public class XmlManager {

    private XmlWriter xmlWriter;


    public XmlManager(){
        StringWriter writer = new StringWriter();
        xmlWriter = new XmlWriter(writer);
    }

    public void savePlanet(Planet planet){

    }

    public void updatePlanet(int slot, int size, int energy, int rank, int xp){

    }

    public void addHatToPlanet(Hat hat, int slot){
        removeHatFromList(hat);
    }

    public void removeHatFromPlanet(int slot){
        //addToHatsList();
    }

    public void deletePlanet(int slot){

    }

    public void saveSolarSystem(SolarSystem solarSystem){
        // saves full planets as well
    }

    public void saveFuelUnits(int units){

    }

    public void savePowerCrystals(int crystals){

    }

    public void saveMegaCrystals(int crystals){

    }

    public void addToHatsList(Hat hat){

    }

    public void removeHatFromList(Hat hat){

    }

    public void saveBossLevel(int level){

    }




}
