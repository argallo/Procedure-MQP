package com.pro.gen.simplealien;

/**
 * Created by Gallo on 10/2/2015.
 */
public class FinalAlien {

    /*
    ArrayList<CharacterPart> characterParts;
    int currentDirection;
    Weapon weapon;
    boolean isRed = false;
    //TintedImage hitboxes;



    public FinalAlien(float width, float height, RandomAlien randomAlien){
        currentDirection = Animation.CALM;
        randomAlien.generateAlien(width, height);
        characterParts = randomAlien.getParts();


       // hitboxes = new TintedImage(Constants.RECTANGLE, Color.RED);
       // hitboxes.setSize(getWidth(), getHeight());
       // addActor(hitboxes);

    }

    public void switchDirections(){
        if(currentDirection == Animation.LEFT){
            currentDirection = Animation.RIGHT;
        } else if(currentDirection == Animation.RIGHT){
            currentDirection = Animation.LEFT;
        }
        directionChanged();
    }

    public void switchDirections(int direction){
        if(direction != currentDirection){
            currentDirection = direction;
            directionChanged();
        }
    }

    public void directionChanged(){
        for(CharacterPart part : characterParts){
            part.directionChanged(currentDirection);
        }
    }

    public void attachWeapon(Weapon weapon){
        this.weapon = weapon;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        for(CharacterPart part: characterParts){
            part.act(delta);
        }
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public Weapon getWeapon() {
        return weapon;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(CharacterPart part: characterParts){
            part.draw(batch, parentAlpha);
        }
    }

    public void red(int hitAmt){
        if(!isRed) {
            isRed = true;
            this.addAction(Actions.sequence(new Action() {
                @Override
                public boolean act(float delta) {
                    setTint(Color.RED);
                    //for (CharacterPart part : characterParts) {
                    //    part.red();
                   // }
                    return true;
                }
            }, Actions.delay(0.1f), new Action() {
                @Override
                public boolean act(float delta) {
                   setPreviousColor();
                   // for (CharacterPart part : characterParts) {
                   //      part.unRed();
                   // }
                    isRed = false;
                    return true;
                }
            }));
        }

    }

    public void ragdoll(){
        addAction(Actions.parallel(Actions.moveTo(-getY(), getX(), 1f, Interpolation.exp10In), Actions.rotateBy(90, 1f, Interpolation.exp10In)));
    }



    public void ragdoll(){
        for(CharacterPart part : characterParts){
            part.ragdoll();
        }
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        for(CharacterPart part : characterParts){
            part.setScale(scaleX, scaleY);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        for(CharacterPart part : characterParts){
            part.setPosition(x, y);
        }
    }

    */
}
