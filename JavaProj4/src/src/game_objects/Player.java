package src.game_objects;
/* В общем, использовать libGDX (https://github.com/libgdx/libgdx/blob/master/)
 * я всегда смогу использовать позже, но нужно будет подключать слишком много
 * файлов, поэтому сейчас надо попробовать сделать это с минимальным подключением
 * и максиматьной эффективностью!
 *
 * и блин для удобного подключения и использования Vector2 типа пришлось добавить до десятка
 *          файлов...
 *
 * */


import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import javaproj4.MyTestPlayState;


public class Player extends Entity{
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
    
    private Vector2 velocity;
    private Vector2 position;
    private BufferedImage image; // хз что лучше это или просто Image...
    private Rectangle bounds;
    private boolean stayOnObject;
    private volatile ArrayList<Sprite> spriteColl =  new ArrayList<Sprite>();
    
    /**
     * Constructor for initialized position, velocity and texture
     */
    public Player(){ // пока что без отлова ошибки
    //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        try {
            image = ImageIO.read(new File("src/res/assets/player.png"));
        } catch (IOException e) {
            System.out.println("Possible wrong path.");
        }
        System.out.println("basic  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                    "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        setPosition(new Vector2(30, 50));
        // Initial velocity for player down on ground
        velocity = new Vector2(0, 0);
        setBounds(new Rectangle(position.x, position.y, image.getWidth(), image.getHeight()));
        stayOnObject = false;
    }
    public Player(BufferedImage image){ // пока что без отлова ошибки
        //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        this.image = image;
        System.out.println("basic  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        setPosition(new Vector2(30, 50));
        // Initial velocity for player down on ground
        velocity = new Vector2(0, 0);
        setBounds(new Rectangle(position.x, position.y, image.getWidth(), image.getHeight()));
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
        // AnimTest1
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
    
    public void updateBorders(){
        Rectangle tempBounds = bounds;
        if (debugMode) System.out.println("  new pos " + position.x + " " + position.y);
        tempBounds.setPosition(getNewInvertPosition(position));
        if (debugMode) System.out.println(" x " + bounds.x + " y " + bounds.y + " w "+ bounds.width + " h " + bounds.height);
        boolean good = true;
        boolean inair = true;
        int i = 0;
        //Vector2 tempPosition = getNewInvertPosition(new Vector2(tempBounds.x,  tempBounds.y));
        for (Sprite spr : spriteColl)
            if (!spr.getTypeObject().isMoveThrough()){
                if (debugMode) System.out.println("type object [" + spr.getTypeObject().name() + "]");
                inair = false;
                //Vector2 tempPosition = getNewInvertPosition(new Vector2(tempBounds.x, tempBounds.y));
                if (debugMode) System.out.println("step [" + i + "]");
                if (debugMode) System.out.println("  old pos " + tempBounds.x + " " + tempBounds.y);
                if (debugMode) System.out.println("  old spr " + spr.getPosition().x + " " + spr.getPosition().y);
            
                //if (spr.collides(new Rectangle(tempPosition.x, tempPosition.y, image.getWidth(), image.getHeight()))) {
                if (spr.collides(tempBounds)){
                    good = false;
                    if (debugMode) System.out.println("collide [" + i + "]");
                    if (debugMode) System.out.println(" old border  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                                "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
                    Rectangle tempRec = getGoodRect(spr, new Vector2(tempBounds.x, tempBounds.y));
                    //tempPosition.x = tempRec.x;
                    //tempPosition.y = tempRec.y;
                    if (debugMode) System.out.println(" new border  localMaxX (" + localMaxX + "), localMinX (" + localMinX +
                                "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
                    //resetVelocity();
                
                    tempBounds.setPosition(new Vector2(tempRec.x, tempRec.y));
                    if (debugMode) System.out.println(" x " + tempBounds.x + " y " + tempBounds.y + 
                                    " w " + tempBounds.width + " h " + tempBounds.height);
                } else if (debugMode) System.out.println("not collide [" + i + "]");
                
                i++;
            }
        if (debugMode) System.out.println(" new border  localMaxX (" + localMaxX + "), localMinX (" + localMinX + "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        if (debugMode) System.out.println("beforeFinal: bounds   x " + bounds.x + " y " + bounds.y + " w "+ bounds.width + " h " + bounds.height);
        if (debugMode) System.out.println("beforeFinal: position x " + position.x + " y " + position.y + " w "+ image.getWidth() + " h " + image.getHeight());
        position = getNewInvertPosition(new Vector2(tempBounds.x, tempBounds.y));
        bounds = tempBounds;
        if (debugMode) System.out.println("commitFinal: bounds   x " + bounds.x + " y " + bounds.y + " w "+ bounds.width + " h " + bounds.height);
        if (debugMode) System.out.println("commitFinal: position x " + position.x + " y " + position.y + " w "+ image.getWidth() + " h " + image.getHeight());
        //Addition Checking overlaps with game sprite bounds
        //if (position.y <= localMinY) { position.y = localMinY; resetVelocity(); }
        //if (position.x <= localMinX) { position.x = localMinX; resetVelocity(); }
        //Addition Checking overlaps with game sprite bounds
        //if (position.y + image.getHeight() >= localMaxY - 1) { position.y = localMaxY; resetVelocity(); setStayOnObject(true);}
        //if (position.x + image.getWidth() >= localMaxX - 1) { position.x = localMaxX; resetVelocity(); }
        setStayOnObject(false);
        if ((bounds.y + image.getHeight()) >= localMaxY){ resetVelocity(); setStayOnObject(true);}
        spriteColl.clear();
        
        
        //position = new Vector2(tempBounds.x, tempBounds.y);
        if (debugMode) System.out.println("> new pos 2 x " + position.x + " y " + position.y + 
                           " > invert x = " + position.x + " y = " + getNewInvertPosition(position).y);
        
        if (debugMode) System.out.println(" new border final localMaxX (" + localMaxX + "), localMinX (" + localMinX + "), localMaxY (" + localMaxY + "), localMinY (" + localMinY + ")");
        
        if (debugMode) System.out.println("final: x " + bounds.x + " y " + bounds.y + " w "+ bounds.width + " h " + bounds.height);
        if (debugMode) System.out.println("\tafterFinal y " + (position.y + image.getHeight()) + " == "+ localMaxY);
        //if ((position.y + image.getHeight()) >= localMaxY - 1){ resetVelocity(); setStayOnObject(true);}
        //if ((position.y) <= localMinY + 1) resetVelocity();
        
        //if ((position.x + image.getWidth()) >= localMaxX - 1) resetVelocity();
        //if ((position.x) <= localMaxX + 1) resetVelocity();
        //if (inair) setStayOnObject(false);
        
        shareCollidesBorder();
        //resetCollidesBorder();
    }
    
    /**
     * Проверяем наличие коллизий
     */
    public Rectangle getGoodRect(Sprite spr, Vector2 position) {
        boolean xf = false, notxf = false, yf = false, notyf = false;
        if ((image.getWidth() > spr.getImage().getWidth()) &&
            (image.getHeight() > spr.getImage().getHeight())) {
            if (debugMode) System.out.println("--= x=[" + position.x + ", " + position.y + "] x'=[" + spr.getPosition().x + ", " + spr.getPosition().y + "]");
            
            if (debugMode) System.out.print("---noty> " + ((int)position.y + image.getHeight()) + " >= " + (int)(spr.getPosition().y));
            if (debugMode) System.out.println(" and " + (int)(position.y + image.getHeight()) + " <=  " + (int)(spr.getPosition().y + spr.getImage().getHeight()));
            // игрок залетает на стену сверху, но уже в ней
            if (!isStayOnObject()) if ((position.y + image.getHeight() >= spr.getPosition().y) &&
                ((position.y + image.getHeight() <= spr.getPosition().y + spr.getImage().getHeight()))) {
                if (debugMode) System.out.print("-+----noty> " + (localMaxY) + " > " + (int)(spr.getPosition().y));
                if (localMaxY > (int)spr.getPosition().y) {
                    localMaxY = (int)spr.getPosition().y;
                    position.y = localMaxY - image.getHeight();
                    notyf = true;
                    System.out.print(" -- +noty");
                }
                if (debugMode) System.out.println(";  localMaxY = " + localMaxY);
            }
            if (debugMode) System.out.print("---y> " + (int)(position.y) + " <= " + (int)(spr.getPosition().y + spr.getImage().getHeight()));
            if (debugMode) System.out.println(" and " + (int)(position.y) + " >=  " + (int)(spr.getPosition().y));
            // игрок залетает на стену снизу, но уже в ней
            if (!isStayOnObject()) if ((position.y <= spr.getPosition().y + spr.getImage().getHeight()) &&
                ((position.y >= spr.getPosition().y))) {
                if (debugMode) System.out.print("-+----y> " + (localMinY) + " < " + (int)(spr.getPosition().y + spr.getImage().getHeight()));
                if (localMinY < (int)spr.getPosition().y + spr.getImage().getHeight()) {
                    localMinY = (int)spr.getPosition().y + spr.getImage().getHeight();
                    position.y = localMinY;
                    yf = true;
                    System.out.println(" -- +y");
                }
                if (debugMode) System.out.println(";  localMinY = " + localMinY);
            }
            if (debugMode) System.out.print("---notx> " + (position.x + image.getWidth()) + " >= " + spr.getPosition().x);
            if (debugMode) System.out.println(" and " + (position.x + image.getWidth()) + " <  " + (spr.getPosition().x + spr.getImage().getWidth()));
            // игрок залетает на стену слева, но уже провалился в неё
            /*if ((position.x + image.getWidth() >= spr.getPosition().x) &&
                (position.x + image.getWidth() <= spr.getPosition().x + spr.getImage().getWidth())) {
                System.out.print("-+----notx> " + (localMaxX) + " > " + (int)spr.getPosition().x);
                if (localMaxX > (int)spr.getPosition().x) {
                    localMaxX = (int)spr.getPosition().x;
                    position.x = localMaxX - image.getWidth()-1;
                    notxf = true;
                    System.out.print(" --  +notx");
                }
                System.out.println(";  localMaxX = " + localMaxX);
            }*/
            if (debugMode) System.out.print("---x> " + ((int)position.x) + " <= " + (int)(spr.getPosition().x + spr.getImage().getWidth()));
            if (debugMode) System.out.println(" and " + ((int)position.x) + " >=  " + ((int)spr.getPosition().x));
            // игрок залетает на стену справа, но уже провалился в неё
            /*if (((int)position.x <= (int)spr.getPosition().x + (int)spr.getImage().getWidth()) &&
                ((int)position.x >= (int)spr.getPosition().x)) {
                System.out.print("-+----x> " + (localMinX) + " < " + (int)(spr.getPosition().x + spr.getImage().getWidth()));
                if (localMinX < (int)(spr.getPosition().x + spr.getImage().getWidth())) {
                    localMinX = (int)spr.getPosition().x + spr.getImage().getWidth();
                    position.x = localMinX+1;
                    xf = true;
                    System.out.print(" -- +x");
                }
                System.out.println(";  localMinX = " + localMinX);
            }*/
        } else
            if (debugMode) System.out.println(" too small your player entity ");
        Vector2 tempPosition = position;
        if (debugMode) System.out.println("X " + xf + ", Y " + yf + ", notX " + notxf + ", notY " +notyf);
        if (xf && yf) { tempPosition = new Vector2(localMaxX, localMaxY); System.out.println("XY"); }
        if (xf && notyf) { tempPosition = new Vector2(localMinX, localMaxY); System.out.println("XnotY"); }
        if (notxf && yf) { tempPosition = new Vector2(localMaxX, localMinY); System.out.println("notXY"); }
        if (notxf && notyf) { tempPosition = new Vector2(localMinX, localMinY); System.out.println("notXnotY"); }

        if (debugMode) System.out.println(" optimal new coord - localMaxX = " + localMaxX + " localMinX = " + localMinX + " localMaxY = " +
                           localMaxY + " localMinY = " + localMinY + " | ");
        return new Rectangle(tempPosition.x, tempPosition.y, image.getWidth(), image.getHeight());
    }
    
    public void resetCollidesBorder(){
        /*localMaxX = (int)(bounds.x + image.getWidth());
        localMinX = (int)bounds.x;
        localMaxY = (int)(bounds.y + image.getHeight());
        localMinY = (int)bounds.y;*/
        localMaxX = MyTestPlayState.WIDTH_FIELD;
        localMinX = 0;
        localMaxY = MyTestPlayState.HEIGHT_FIELD;
        localMinY = 0;
    }
    
    private void shareCollidesBorder(){
        localMaxX += 16;
        localMinX -= 16;
        localMaxY += 16;
        localMinY -= 16;
    }
    
    /**
     * Добавляем новую коллизию
     * @param spr
     */
    public void addCollides(Sprite spr){
        spriteColl.add(spr);
    }
    
    /**
     * Сбрасываем наши колизии
     * @param spr
     * */
    public void delCollides(Sprite spr){
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
}
