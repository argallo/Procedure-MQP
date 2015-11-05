package com.pro.gen.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Gallo on 8/14/2015.
 */
public class ColorHelper {


    /**
     * Generates a color at random that isnt too dark or too light
     * @return a random color
     */
    public static Color generateDarkColor(){
        float h = MathUtils.random();
        float s = 1;
        float b = 1;
        int color = HSBtoRGB(h, s, b);
        return new Color(getRed(color)/255f,getGreen(color)/255f,getBlue(color)/255f,1f);
    }

    /**
     * Generates a color at random that isnt too dark or too light
     * @return a random color
     */
    public static Color generateGoodColor(){
        float h = MathUtils.random();
        float s = MathUtils.random(0.5f)+0.5f;
        float b = MathUtils.random(0.5f)+0.5f;
        int color = HSBtoRGB(h, s, b);
        return new Color(getRed(color)/255f,getGreen(color)/255f,getBlue(color)/255f,1f);
    }

    /**
     * Generates a color at random that is light
     * @return a random color
     */
    public static Color generateLightColor(){
        float h = MathUtils.random();
        float s = MathUtils.random(0.3f);
        float b = 1;
        int color = HSBtoRGB(h, s, b);
        return new Color(getRed(color)/255f,getGreen(color)/255f,getBlue(color)/255f,1f);
    }

    public static Color lighten(Color color, float lightAmt){
        float[] hsb = new float[3];
        RGBtoHSB((int)(color.r*255), (int)(color.g*255), (int)(color.b*255), hsb);
        hsb[1] = Math.max(hsb[1]-lightAmt, 0);
        hsb[2] = 1;
        int c = HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        color.r = getRed(c)/255f;
        color.g = getGreen(c)/255f;
        color.b = getBlue(c)/255f;
        return color;
    }

    public static Color darken(Color color, float darkAmt){
        float[] hsb = new float[3];
        RGBtoHSB((int)(color.r*255), (int)(color.g*255), (int)(color.b*255), hsb);
        hsb[2] = Math.max(hsb[2]-darkAmt, 0);
        int c = HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        color.r = getRed(c)/255f;
        color.g = getGreen(c)/255f;
        color.b = getBlue(c)/255f;
        return color;
    }


    public static Color changeHue(Color color, float hueAmt){
        float[] hsb = new float[3];
        RGBtoHSB((int)(color.r*255), (int)(color.g*255), (int)(color.b*255), hsb);
        if(hsb[0]+hueAmt > 1){
            hsb[0] = 1 - (hsb[0] + 1);
        } else {
            hsb[0] = Math.max(hsb[0] + hueAmt, 0);
        }
        int c = HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        color.r = getRed(c)/255f;
        color.g = getGreen(c)/255f;
        color.b = getBlue(c)/255f;
        return color;
    }


    /**
     * Converts the components of a color, as specified by the HSB
     * model, to an equivalent set of values for the default RGB model.
     * @param     hue   the hue component of the color
     * @param     saturation   the saturation of the color
     * @param     brightness   the brightness of the color
     * @return    the RGB value of the color with the indicated hue,
     *                            saturation, and brightness.
     */
    public static int HSBtoRGB(float hue, float saturation, float brightness) {
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        } else {
            float h = (hue - (float)Math.floor(hue)) * 6.0f;
            float f = h - (float)java.lang.Math.floor(h);
            float p = brightness * (1.0f - saturation);
            float q = brightness * (1.0f - saturation * f);
            float t = brightness * (1.0f - (saturation * (1.0f - f)));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
    }


    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) {
        float hue, saturation, brightness;
        if (hsbvals == null) {
            hsbvals = new float[3];
        }
        int cmax = (r > g) ? r : g;
        if (b > cmax) cmax = b;
        int cmin = (r < g) ? r : g;
        if (b < cmin) cmin = b;

        brightness = ((float) cmax) / 255.0f;
        if (cmax != 0)
            saturation = ((float) (cmax - cmin)) / ((float) cmax);
        else
            saturation = 0;
        if (saturation == 0)
            hue = 0;
        else {
            float redc = ((float) (cmax - r)) / ((float) (cmax - cmin));
            float greenc = ((float) (cmax - g)) / ((float) (cmax - cmin));
            float bluec = ((float) (cmax - b)) / ((float) (cmax - cmin));
            if (r == cmax)
                hue = bluec - greenc;
            else if (g == cmax)
                hue = 2.0f + redc - bluec;
            else
                hue = 4.0f + greenc - redc;
            hue = hue / 6.0f;
            if (hue < 0)
                hue = hue + 1.0f;
        }
        hsbvals[0] = hue;
        hsbvals[1] = saturation;
        hsbvals[2] = brightness;
        return hsbvals;
    }

    /**
     * Returns the red component in the range 0-255 in the default sRGB
     * space.
     * @return the red component.
     */
    public static int getRed(int value) {
        return (value >> 16) & 0xFF;
    }

    /**
     * Returns the green component in the range 0-255 in the default sRGB
     * space.
     * @return the green component.
     */
    public static int getGreen(int value) {
        return (value >> 8) & 0xFF;
    }

    /**
     * Returns the blue component in the range 0-255 in the default sRGB
     * space.
     * @return the blue component.
     */
    public static int getBlue(int value) {
        return (value >> 0) & 0xFF;
    }

    public static Color shadowOf(Color originalColor){
        return new Color(originalColor.r/4, originalColor.g/4, originalColor.b/4, 1f);
    }

}
