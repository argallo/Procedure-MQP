package com.pro.gen.worldcomponents;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.pro.gen.components.TintedImage;
import com.pro.gen.planet.PlanetEyes;
import com.pro.gen.random.RandomPlanet;
import com.pro.gen.utils.Pic;
import com.pro.gen.utils.Tint;

/**
 * Created by Gallo on 8/12/2015.
 */
public class Planet extends Group {

    private String colorType;
    private TintedImage basePlanet; //base planet includes the base color for the planet
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
    private String planetName;
    private Color originalColor;

    public Planet(RandomPlanet randomPlanet){
       this(randomPlanet, Tint.UNIVERSE_BACKGROUND_COLOR);
    }

    public Planet(RandomPlanet randomPlanet, Color background){
        this(randomPlanet.getColorType(), randomPlanet.getPlanetColor(), randomPlanet.getLands(), randomPlanet.getClouds(), randomPlanet.getPlanetEyes(), randomPlanet.getPlanetSize(),
                randomPlanet.getPlanetEnergy(), randomPlanet.getGlobeRank(), randomPlanet.getCurrentXP(), randomPlanet.getRankXP(), randomPlanet.isInhabitable(),
                randomPlanet.getTimeStart(), randomPlanet.getAmtofTime(), randomPlanet.getHat(), background, randomPlanet.getPlanetName());
    }

    /**
     * Default with Universe background color
     */
    public Planet (Color planetColor, GlobeMap lands, GlobeMap clouds, PlanetEyes planetEyes) {
       //this(planetColor, lands, clouds, planetEyes, Tint.UNIVERSE_BACKGROUND_COLOR);
    }

    /**
     * Planet base constructor. Normally called directly only for testing or possibly for reseting saved planet.
     * @param planetColor the color of the base planet
     * @param lands the land that will move around the planet
     * @param clouds the clouds that will float around the planet
     */
    public Planet (String colorType, Color planetColor, GlobeMap lands, GlobeMap clouds, PlanetEyes planetEyes, int planetSize, int planetEnergy, int globeRank, int currentXP, int rankXP,
                   boolean inhabitable, long timeStart, long amtofTime, Hat hat, Color background, String planetName){

        this.colorType = colorType;
        originalColor = new Color(planetColor);
        basePlanet = new TintedImage(Pic.Circle_Large, planetColor);
        this.lands = lands;
        this.clouds = clouds;
        this.planetEyes = planetEyes;
        this.planetSize = planetSize;
        this.planetEnergy = planetEnergy;
        this.globeRank = globeRank;
        this.currentXP = currentXP;
        this.rankXP = rankXP;
        this.inhabitable = inhabitable;
        this.timeStart = timeStart;
        this.amtofTime = amtofTime;
        this.hat = hat;
        this.planetName = planetName;


        circleHole_Right = new TintedImage(Pic.Circle_Hole_Right, background);
        circleHole_left = new TintedImage(Pic.Circle_Hole, background);
        attachActors();
    }

    public void setBackgroundTint(Color color){
        circleHole_left.setTint(color);
        circleHole_Right.setTint(color);
    }

    /**
     * Attach four actors that make up a planet
     */
    public void attachActors() {
        addActor(basePlanet);
        addActor(lands);
        addActor(planetEyes);
        addActor(clouds);
        addActor(circleHole_left);
        addActor(circleHole_Right);
        addActor(hat);



    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width * 3, height);
        circleHole_left.setSize(width * 1.5f, height);
        circleHole_Right.setSize(width * 1.5f, height);
        basePlanet.setSize(width, height);

        lands.resizeGlobe(width * 3, height);
        clouds.resizeGlobe(width * 3, height);
        planetEyes.resizeEyes(width, height);

        circleHole_Right.setX(width * 1.5f);
        basePlanet.setX(width * 0.5f + basePlanet.getWidth() / 2);
        hat.resizeHat(width,height);


    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x - getWidth() / 3, y);
    }

    public void burn(){
        setInhabitable(true);
        setAmtofTime(0);
        addAction(Actions.sequence(Actions.delay(6.5f), new Action() {
            @Override
            public boolean act(float delta) {
                basePlanet.addAction(Actions.color(Tint.DEAD_PLANET_RED, 6f));
                lands.burn(Tint.DEAD_PLANET_LAND);
                clouds.burn(Tint.DEAD_PLANET_CLOUD);
                return true;
            }
        }));
    }

    public void instantBurn(){
        basePlanet.addAction(Actions.color(Tint.DEAD_PLANET_RED));
        lands.instantBurn(Tint.DEAD_PLANET_LAND);
        clouds.instantBurn(Tint.DEAD_PLANET_CLOUD);
    }

    public int gainXp(int xp){
        int rankUps = 0;
        currentXP += xp;
        while(currentXP >= rankXP){
            currentXP -= rankXP;
            globeRank++;
            rankXP = (int) (Math.pow(globeRank, 1.2) * 100);
            setPlanetSize((((int)(Math.pow(globeRank+1, 1.3)*105+300))*getPlanetSize())/((int)(Math.pow(globeRank, 1.3)*105+300)));
            setPlanetEnergy((((int) (Math.pow(globeRank+1, 1.3) * 105 + 800)) * getPlanetEnergy()) / ((int) (Math.pow(globeRank, 1.3) * 105 + 800)));
            rankUps++;
        }
        return rankUps;
    }


    /**
     * Getters and setters for saving planet to xml
     *
     */

    public String getColorType() {
        return colorType;
    }

    public int getPlanetSize() {
        return planetSize;
    }

    public int getPlanetEnergy() {
        return planetEnergy;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public int getGlobeRank() {
        return globeRank;
    }

    public int getRankXP() {
        return rankXP;
    }

    public Hat getHat() {
        return hat;
    }

    public PlanetEyes getPlanetEyes() {
        return planetEyes;
    }

    public GlobeMap getClouds() {
        return clouds;
    }

    public GlobeMap getLands() {
        return lands;
    }

    public Color getShowColor(){
        return basePlanet.getTint();
    }

    public Color getBasePlanetColor() {
        return originalColor;
    }

    public boolean isInhabitable() {
        return inhabitable;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public long getAmtofTime() {
        return amtofTime;
    }

    public void setAmtofTime(long amtofTime) {
        this.amtofTime = amtofTime;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public void setRankXP(int rankXP) {
        this.rankXP = rankXP;
    }

    public void setGlobeRank(int globeRank) {
        this.globeRank = globeRank;
        setRankXP((int)Math.pow(globeRank, 1.2)*100);
    }

    public void setHat(Hat hat) {
        removeActor(this.hat);
        this.hat = hat;
        hat.setPosition(0,0);
        hat.setHatSize();
        hat.resizeHat(getWidth()/3, getHeight());
        addActor(hat);
    }

    public void setInhabitable(boolean inhabitable) {
        this.inhabitable = inhabitable;
    }

    public void setPlanetEnergy(int planetEnergy) {
        this.planetEnergy = planetEnergy;
    }

    public void setPlanetSize(int planetSize) {
        this.planetSize = planetSize;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public String getPlanetName() {
        return planetName;
    }
}
