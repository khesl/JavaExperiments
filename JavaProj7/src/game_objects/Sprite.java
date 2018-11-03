package src.game_objects;

import src.utils.TypeObjectEnum;
import src.math.Rectangle;
import src.math.Vector2;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Sprite {
    protected boolean visible;
    protected Image image;
    protected Vector2 position;
    protected TypeObjectEnum typeObject;

    public Sprite(){ super(); }

    /**
     * Get sprite
     * @return
     */
    public final Image getImage() {
        return image;
    }

    /**
     * Get current position
     * @return
     */
    public final Vector2 getPosition() {
        return position;
    }
    /**
     * Calculating game_objects overlaps
     * @param bounds entity bounds
     * @return
     */
    public final boolean collides(Rectangle bounds) {
        return getBounds().overlaps(bounds);
    }

    public final boolean isVisible() {
        return visible;
    }
    public final void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public final Rectangle getBounds() {
        return new Rectangle(position.x, position.y, image.getWidth(null), image.getHeight(null));
    }

    public final void setPosition(Vector2 position) {
        this.position = position;
    }
    public final void setImage(Image image){
        this.image = image;
    }

    public final void setTypeObject(TypeObjectEnum typeObject) {
        this.typeObject = typeObject;
    }
    public final TypeObjectEnum getTypeObject () {
        return typeObject;
    }
}
