package src.game_objects;
/** В общем, использовать libGDX (https://github.com/libgdx/libgdx/blob/master/)
 * я всегда смогу использовать позже, но нужно будет подключать слишком много
 * файлов, поэтому сейчас надо попробовать сделать это с минимальным подключением
 * и максиматьной эффективностью!
 *
 * и блин для удобного подключения и использования Vector2 типа пришлось добавить до десятка
 *          файлов...
 *
 * */

import src.math.Rectangle;
import src.math.Vector2;
import src.control.MyPlayState_Modificate;
import src.utils.Anim_TypeObjectEnum;
import src.utils.Utils;
import src.utils._BindObjectEnum;

import java.awt.image.BufferedImage;


public class Player extends Entity {
    // Movement on X axis
    public static final int MOVEMENT_ON_X = 50;
    // Resistance movement
    public static final int RESISTANCE = -10;
    // Strength of jump
    public static final int JUMP_RATE = 50;
    // Gravity takes away from the Y axis
    public static final int GRAVITY = -10;
    private static boolean isInverse = true;
    private Anim_TypeObjectEnum curMove;

    private static boolean debugMode = false;

    private Vector2 velocity;
    private Vector2 position;
    private BufferedImage image; // хз что лучше это или просто Image...
    private boolean isImageLoad = false;
    private Animator_controller animator_controller;
    private boolean stayOnObject;
    //private volatile ArrayList<Sprite> spriteColl =  new ArrayList<Sprite>();

    /**
     * Constructor for initialized position, velocity and texture
     */
    public Player(){ // пока что без отлова ошибки
        //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        this.setBindType(_BindObjectEnum.player);
        if (!isImageLoad)
            System.out.println("Image is empty");
        stayOnObject = false;
    }
    public Player(Vector2 position, Vector2 velocity, BufferedImage image, Animator_controller animator_controller){ // пока что без отлова ошибки
        //public Player() throws IOException {
        //new Texture("src\\assets\\player.png");
        // Initial player position
        this.setBindType(_BindObjectEnum.player);
        this.image = image;
        this.position = position;
        // Initial velocity for player down on ground
        this.velocity = velocity;
        this.animator_controller = animator_controller;
        curMove = Anim_TypeObjectEnum.idle;
        setStayOnObject(false); //stayOnObject = false;
        setInit(true);
    }

    public Vector2 getPosition() {
        return isInverse?position:new Vector2(position.x, MyPlayState_Modificate.HEIGHT_FIELD - position.y);
    }

    /*public BufferedImage getImage() {
        return image;
    }*/
    public BufferedImage getImage(){
        return Utils.toBufferedImage(animator_controller.getCurrentNode().getItem().getCurSprite());
    }

    public void setImage (BufferedImage image) {
        this.image = image;
        isImageLoad = true;
    }

    public Rectangle getBounds() { return new Rectangle(position.x, position.y, image.getWidth(), image.getHeight()); }

    public Animator_controller getAnimator_controller(){return animator_controller;}


    public Anim_TypeObjectEnum getCurMove() { return curMove; }
    public void setCurMove(Anim_TypeObjectEnum curMove) { this.curMove = curMove; }

    public boolean isStayOnObject() {
        return stayOnObject;
    }

    public void setStayOnObject(boolean stayOnObject) {
        this.stayOnObject = stayOnObject;
        if (stayOnObject) {
            resetVelocityY();
        }
    }

    public void jump() {
        if (isStayOnObject()){
            //System.out.println("try jump?");
            velocity.y = -JUMP_RATE;
            setStayOnObject(false);
        }
    }

    public void right() {
        velocity.x += MOVEMENT_ON_X/3;
        if (velocity.x >= MOVEMENT_ON_X) velocity.x = MOVEMENT_ON_X;
        //new Resources_manager().getInstance().getScreen_manager().fillCurrentScreenMap(new Vector2(4, 0));

    }

    public void left() {
        velocity.x += -MOVEMENT_ON_X/3;
        if (velocity.x <= -MOVEMENT_ON_X) velocity.x = -MOVEMENT_ON_X;
        //new Resources_manager().getInstance().getScreen_manager().fillCurrentScreenMap(new Vector2(-4, 0));
    }

    /**
     * Stop move object
     */
    public void resetVelocity() {
        velocity.x = 0;
        velocity.y = 0;
    }
    public void resetVelocityX() { velocity.x = 0; }
    public void resetVelocityY() { velocity.y = 0; }

    /**
     * Calculating new position
     * @param delta time after last call method render
     */
    public void update(float delta) {
        // расчет нового спрайта из анимации
        getAnimator_controller().getCurrentNode().getItem().getNewSprite();

     //   System.out.print("old position:" + position.x + ":" + position.y + ";");
        Vector2 old_position = new Vector2(position.x, position.y);
        if (position.y < MyPlayState_Modificate.HEIGHT_FIELD - image.getHeight()) {//0 position.y > 0
            // Add velocity vector and gravity vector  (0, -15)
            // For additional information see: https://goo.gl/lD9I77
            velocity.add(0, -GRAVITY);
        }

        // Calculation new position
        if (velocity.x != 0) {
            position.add(velocity.x * delta, 0);
            velocity.add(- velocity.x * delta, 0);
            if (Math.abs(velocity.x) < 2) velocity.set(0, velocity.y);
        }
     //   System.out.print("||" + "stayON: " + stayOnObject + "; Veloc: " + velocity.y + "||");
        if (!stayOnObject || velocity.y > 0) {
            position.add(0, velocity.y * delta);
        }

        //Checking overlaps with game world bounds
        if (position.y < 0) { position.y = 0; resetVelocityY(); }
        if (position.x < 0) { position.x = 0; resetVelocityX(); }
        if (position.y > MyPlayState_Modificate.HEIGHT_FIELD - image.getHeight()) {
            position.y = MyPlayState_Modificate.HEIGHT_FIELD - image.getHeight();
            resetVelocityY();
        }
        if (position.x > MyPlayState_Modificate.WIDTH_FIELD - image.getWidth()) {
            position.x = MyPlayState_Modificate.WIDTH_FIELD - image.getWidth();
            resetVelocityX();
        }

        //setPosition(position);
        setPosition(getNewInvertPosition(position));// - так как у  меня инвертирован Y...
        //        System.out.println("v = " + velocity + "; p = " + position + "; d = " + delta);
        //System.out.println("compare: " + old_position.y + ":" + position.y);

        //if (old_position.y == position.y) setStayOnObject(true);
        if (velocity.y == 0) setStayOnObject(true);
        else setStayOnObject(false);
      //  System.out.println(" new position:" + position.x + ":" + position.y + "; stay: " + isStayOnObject());
    }

    /**
     * Добавляем новую коллизию
     * @param spr
     */
    /*public void addCollides(Sprite spr){
        spriteColl.add(spr);
    }*/

    /**
     * Сбрасываем наши колизии
     * @param spr
     * */
    /*public void delCollides(Sprite spr){
        if (spriteColl.contains(spr))
            spriteColl.remove(spriteColl.indexOf(spr));
    }*/

    /**
     * Unload resources
     */
    public void dispose() {
        // метод для возврата ресурсов используемых в рендере
        // изменить после добавления текстур
        //image.dispose();
    }

    public Vector2 getNewInvertPosition(Vector2 position){
        return new Vector2(position.x, MyPlayState_Modificate.HEIGHT_FIELD - position.y - image.getHeight());
    }

    public void move(Anim_TypeObjectEnum action) {
        switch (action) {
            case left: {
                left();
                break;
            }
            case right: {
                right();
                break;
            }
            case jump: {
                jump();
                break;
            }
            case idle: {
                //resetVelocity();
                break;
            }
            case left_release: {
                //resetVelocity();
                break;
            }
            case right_release: {
                //resetVelocity();
                break;
            }
            default: {
                System.out.println("Object (" + getBindType() + ") has not that move action.");
                return;
            }
        }
        if (debugMode) System.out.println(getAnimator_controller().getCurrentNode().toString());
        if (action != getCurMove()){
            getAnimator_controller().goNextNode(action); // переходим на новую анимацию
            getAnimator_controller().getCurrentNode().getItem().getNewSprite(); // сдвиг кадра на 1, чтобы пропустить начальный кадр, для плавности.
        }
        setCurMove(action);
    }

}
