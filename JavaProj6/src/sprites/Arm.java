package sprites;

/* В общем, использовать libGDX (https://github.com/libgdx/libgdx/blob/master/)
 * я всегда смогу использовать позже, но нужно будет подключать слишком много
 * файлов, поэтому сейчас надо попробовать сделать это с минимальным подключением
 * и максиматьной эффективностью!
 *
 * и блин для удобного подключения и использования Vector2 типа пришлось добавить до десятка
 *          файлов...
 *
 * */

import com.mylogic.graphics.g2d.Sprite;
import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javaProj6.MyTestPlayState;

import javax.imageio.ImageIO;

public class Arm extends Entity{
    // Movement on X axis
    public static final int MOVEMENT_ON_X = 50;
    // Resistance movement
    public static final int RESISTANCE = -10;
    // Strength of jump
    public static final int JUMP_RATE = 50;
    // Gravity takes away from the Y axis
    public static final int GRAVITY = -10;
    
    private static volatile int localMaxX = MyTestPlayState.WIDTH_FIELD;
    private static volatile int localMinX = 0;
    private static volatile int localMaxY = MyTestPlayState.HEIGHT_FIELD;
    private static volatile int localMinY = 0;
    private static boolean debugMode = MyTestPlayState.debugMode;
    
    private volatile int score = 0;
    private Vector2 velocity;
    private Vector2 position;
    private BufferedImage image; // хз что лучше это или просто Image...
    private Rectangle bounds;
    private boolean stayOnObject;
    private volatile ArrayList<Sprite> spriteColl =  new ArrayList<Sprite>();
    private volatile ArrayList<Entity> entityColl =  new ArrayList<Entity>();
    
    /**
     * Constructor for initialized position, velocity and texture
     */
    public Arm(){ // пока что без отлова ошибки
    //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        try {
            image = ImageIO.read(new File("src/assets/arm.png"));
        } catch (IOException e) {
            System.out.println("Possible wrong path.");
        }
        System.out.println("basic  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                    "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        setPosition(new Vector2(30, 50));
        // Initial velocity for player down on ground
        velocity = new Vector2(0, 0);
        setBounds(new Rectangle(position.x, position.y, image.getWidth(), image.getHeight()/2));
        stayOnObject = false;
    }
    public Arm(BufferedImage image){ // пока что без отлова ошибки
        //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        this.image = image;

        System.out.println("basic  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        setPosition(new Vector2(30, 50));
        // Initial velocity for player down on ground
        velocity = new Vector2(0, 0);
        setBounds(new Rectangle(position.x, position.y, image.getWidth(), image.getHeight()/2));
        stayOnObject = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    // my
 /*   public void setImage (Texture image) {
        this.image = image;
    }*/
    public void setImage (BufferedImage image) {
        this.image = image;
    }
    
    public Rectangle getBounds() {
        return bounds;
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

    public void jump() {
        if (isStayOnObject()){
            velocity.y = JUMP_RATE;
            setStayOnObject(false);
        }
    }

    public void right() {
        System.out.println("right velocity.x " + velocity.x + ", MOVEMENT_ON_X" + MOVEMENT_ON_X);
        velocity.x = MOVEMENT_ON_X;        
    }

    public void left() {
        velocity.x = -MOVEMENT_ON_X;
    }

    /**
     * Stop move object
     */
    public void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }
    
    /**
     * Calculating new position
     * @param delta time after last call method render
     */
    public void update(float delta) {
        if (position.y > 0) {//0
            // Add velocity vector and gravity vector  (0, -15)
            // For additional information see: https://goo.gl/lD9I77
            velocity.add(0, GRAVITY);
        }

        // Calculation new position
        if (velocity.x != 0) {
            position.add(velocity.x * delta, 0);
            //if (Math.abs(velocity.x) > 10)
                velocity.add(- velocity.x * delta, 0);
            if (Math.abs(velocity.x) < 2) velocity.set(0, velocity.y);
        }
        if (!stayOnObject || velocity.y > 0) {
            position.add(0, velocity.y * delta);
        }

        //Checking overlaps with game world bounds
        if (position.y < 0) { position.y = 0; resetVelocity(); }
        if (position.x < 0) { position.x = 0; resetVelocity(); }

        if (position.y > MyTestPlayState.HEIGHT_FIELD - image.getHeight()) {
             position.y = MyTestPlayState.HEIGHT_FIELD - image.getHeight();
             resetVelocity();
        }
        if (position.x > MyTestPlayState.WIDTH_FIELD - image.getWidth()) {
             position.x = MyTestPlayState.WIDTH_FIELD - image.getWidth();
             resetVelocity();
        }
        
        bounds.setPosition(getNewInvertPosition(position));// - так как у  меня инвертирован Y...
    //        System.out.println("v = " + velocity + "; p = " + position + "; d = " + delta);
    } 
    
    public void updateScore(){
        System.out.println("candy count " + entityColl.size());
        
        if (entityColl.size() > 0)
            for (Entity ent : entityColl)
            if (ent.getClass().equals(Candy.class)){
                System.out.println("candy score " + ent.getScore());
                if (ent.collides(getBounds()))
                    addScore(ent.getScore());
            } 
        
        System.out.println("score: " + getScore());
        entityColl.clear();        
    }
    
    /**
     * Добавляем новую коллизию
     * @param ent
     */
    public void addCollides(Entity ent){
        entityColl.add(ent);
    }
    public void addCollides(Sprite spr) {
        spriteColl.add(spr);
    }
    
    /**
     * Сбрасываем наши колизии
     * @param ent
     * */
    public void delCollides(Entity ent){
        if (entityColl.contains(ent))
            entityColl.remove(entityColl.indexOf(ent));
    }

    public void delCollides(Sprite spr) {
        if (spriteColl.contains(spr))
            spriteColl.remove(spriteColl.indexOf(spr));
    }
    
    /**
     * Unload resources
     */
    public void dispose() {
        // метод для возврата ресурсов используемых в рендере
        // изменить после добавления текстур
        //image.dispose();
    }
    
    public Vector2 getNewInvertPosition(Vector2 position){
         return new Vector2(position.x, MyTestPlayState.HEIGHT_FIELD - position.y - image.getHeight());
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    
    private void addScore(int score){
        setScore(getScore() + score);
    }
}
