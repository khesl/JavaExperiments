package src.game_objects;

import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import javaproj4.utils.TypeObject;

public abstract class Sprite {
    private BufferedImage image;
    private Vector2 position;
    private Rectangle bounds;
    private TypeObject typeObject;
    
    public Sprite() {
        super();
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

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }
}
