package com.pro.gen.simplealien;

/**
 * Created by Gallo on 10/20/2015.
 */
public class Mouth {

    /*
    public static AlienParts getRandomMouth(float width, float height){
        switch (MathUtils.random(1,3)){
            case 1:
                return simpleCircleMouth(width, height);
            case 2:
                return simpleCurveTeethMouth(width, height);
            case 3:
                return simpleCurvRectMouth(width, height);
            default: return null;
        }
    }

    public static AlienParts simpleCurvRectMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        float mouthWidth = MathUtils.random(width * 0.35f, width * 0.45f);
        float mouthHeight = MathUtils.random(height * 0.05f, height * 0.10f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.05f, height * 0.20f)-mouthHeight/2;

        AlienParts mouth = new AlienParts(Constants.CURVERECT, color);
        mouth.setSize(mouthWidth, mouthHeight);
        mouth.setPosition(mouthX, mouthY);

        return mouth;
    }

    public static AlienParts simpleCircleMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        float mouthWidth = MathUtils.random(width * 0.15f, width * 0.35f);
        float mouthHeight = MathUtils.random(width * 0.05f, width * 0.40f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.02f, height * 0.20f)-mouthHeight/2;

        AlienParts mouth = new AlienParts(Constants.CIRCLE_SMALL, color);
        mouth.setSize(mouthWidth, mouthHeight);
        mouth.setPosition(mouthX, mouthY);

        return mouth;
    }

    public static AlienParts simpleCurveTeethMouth(float width, float height){
        Color color = ColorHelper.generateGoodColor();
        Color teethColor = ColorHelper.generateLightColor();
        float mouthWidth = MathUtils.random(width * 0.35f, width * 0.45f);
        float mouthHeight = MathUtils.random(height * 0.02f, height * 0.08f);
        float mouthX = width/2-mouthWidth/2;
        float mouthY = MathUtils.random(height * 0.06f, height * 0.20f)-mouthHeight/2;

        float teethHeight = MathUtils.random(mouthHeight * 0.85f, mouthHeight * 1.2f);
        float teethWidth = MathUtils.random(teethHeight * 0.85f, teethHeight * 1.2f);
        float teethX =  MathUtils.random(mouthWidth/2 * 0.35f, mouthWidth/2 * 0.85f);



        AlienParts mouth = new AlienParts(Constants.CURVERECT, color);
        mouth.setSize(mouthWidth, mouthHeight);
        mouth.setPosition(mouthX, mouthY);

        AlienParts leftTooth = new AlienParts(Constants.TRIANGLE, teethColor);
        leftTooth.setSize(teethWidth, teethHeight);
        leftTooth.setPosition((mouthWidth/2) - teethX, 0);

        AlienParts rightTooth = new AlienParts(Constants.TRIANGLE, teethColor);
        rightTooth.setSize(teethWidth, teethHeight);
        rightTooth.setPosition((mouthWidth/2) + teethX - teethWidth, 0);

        mouth.addSubPart(leftTooth);
        mouth.addSubPart(rightTooth);

        return mouth;
    }

*/
}
