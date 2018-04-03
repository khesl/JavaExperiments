package sprites;

import com.mylogic.graphics.g2d.Sprite;
import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;


import java.io.File;
import java.io.IOException;

import javaProj6.MyTestPlayState;

import javaProj6.utils.TypeEntityObject;

import javax.imageio.ImageIO;

public class Candy extends Entity{
    // Gravity takes away from the Y axis
    public static final int GRAVITY = -5;
    private static boolean goodCandy;
    private static final boolean debugMode = MyTestPlayState.debugMode;
    
    //private static boolean debugMode = MyTestPlayState.debugMode;
    
    private Vector2 velocity;
    private Vector2 position;
    private BufferedImage image; // хз что лучше это или просто Image...
    private Rectangle bounds;
    private TypeEntityObject typeObject; 
    private int score = 10;  
    private boolean stayOnObject = false;
    
    public Candy(){
        super();
    }
    public Candy(Entity ent){
        this.position = ent.getPosition();
        this.image = ent.getImage();
        this.bounds = ent.getBounds();
        this.typeObject = ent.getTypeObject();
        stayOnObject = false;
    }
    public Candy(Vector2 position, BufferedImage image, TypeEntityObject typeObject){
        System.out.println("image " + image.getWidth()+ ", " + image.getHeight());
        this.image = image;
        this.typeObject = typeObject;        
        setPosition(new Vector2(position.x, position.y));
        setVelocity(new Vector2(0, 0));
        setBounds(new Rectangle(getPosition().x, getPosition().y, image.getWidth(), image.getHeight()));
        stayOnObject = false;
    }
    
    /**
     * Calculating new position
     * @param delta time after last call method render
     */
    public void update(float delta) {
        if (debugMode) System.out.println("update " + getPosition().x + ", " + getPosition().y + ", vel " + velocity.x + ", " + velocity.y + "; ");
        if (getPosition().y > -40) {//0
            // Add velocity vector and gravity vector  (0, -15)
            // For additional information see: https://goo.gl/lD9I77
            velocity.add(0, GRAVITY);
        }

        // Calculation new position
        /*if (velocity.x != 0) {
            position.add(velocity.x * delta, 0);
            //if (Math.abs(velocity.x) > 10)
                velocity.add(- velocity.x * delta, 0);
            if (Math.abs(velocity.x) < 2) velocity.set(0, velocity.y);
        }*/
        if (!stayOnObject || velocity.y > 0)
            getPosition().add(0, velocity.y * delta);
        

        //Checking overlaps with game world bounds
        if (getPosition().y < 0) { getPosition().y = 0; resetVelocity(); }
        if (getPosition().x < 0) { getPosition().x = 0; resetVelocity(); }
        // AnimTest1
        if (getPosition().y > MyTestPlayState.HEIGHT_FIELD - getImage().getHeight()) {
             getPosition().y = MyTestPlayState.HEIGHT_FIELD - getImage().getHeight();
             resetVelocity();
        }
        if (getPosition().x > MyTestPlayState.WIDTH_FIELD - getImage().getWidth()) {
             getPosition().x = MyTestPlayState.WIDTH_FIELD - getImage().getWidth();
             resetVelocity();
        }
        getBounds().setPosition(getNewInvertPosition(getPosition()));
        if (getBounds().y == 0) setStayOnObject(true);
    }

    public void jump() {
    }

    public void right() {
    }

    public void left() {
    }

    public void resetVelocity() {
    }

    public void dispose() {
    }

    public void updateScore() {
    }
    
    public boolean isStayOnObject() {
        return stayOnObject;
    }
    
    public void setStayOnObject(boolean stayOnObject) {
        this.stayOnObject = stayOnObject;
        if (stayOnObject) {
            resetVelocity();
        }
    }
    
    public Vector2 getNewInvertPosition(Vector2 position){
         return new Vector2(position.x, MyTestPlayState.HEIGHT_FIELD - position.y - getImage().getHeight());
    }

    public void addCollides(Sprite spr) {
    }
    public void addCollides(Entity spr) {
    }
    public void delCollides(Sprite spr) {
    }
    public void delCollides(Entity spr) {
    }
    public static void setGoodCandy(boolean goodCandy) {
        Candy.goodCandy = goodCandy;
    }
    
    public void setPosition(float x, float y){
        setPosition(new Vector2(x, y));
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
