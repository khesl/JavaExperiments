package src.game_objects;

import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import javaproj4.utils.TypeObject;

/**
 * Sky platform
 */
public class Platform extends Sprite{
    private BufferedImage image;
    private Vector2 position;
    private Rectangle bounds;
    private boolean init = false;
    private TypeObject typeObject;

    /**
     * Constructor for initialize sprite, bounds, position
     */
    public Platform(Vector2 position) {
        this.position = position;
        //this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }    
    public Platform(Vector2 position, BufferedImage image) {
        this.typeObject = TypeObject.empty;
        this.image = image;
        this.position = position;
        this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        setInit(true);
    }
    public Platform(Vector2 position, BufferedImage image, TypeObject typeObject) {
        this.typeObject = typeObject;
        this.image = image;
        this.position = position;
        this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        setInit(true);
    }
    
    public void initBounds(){
        if (!image.equals(null)){
            this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
            setInit(true);
        }
    }

    /**
     * Get sprite
     * @return
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get current position
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Calculating game_objects overlaps
     * @param player player bounds
     * @return
     */
    public boolean collides(Rectangle player) {
        return bounds.overlaps(player);
    }

    /**
     * Unload resources
     */
    public void dispose() {
        //image.dispose();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isInit() {
        return init;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }
}
