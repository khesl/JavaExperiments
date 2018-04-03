package src.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Sky platform
 */
public class Platform {
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    /**
     * Constructor for initialize sprite, bounds, position
     */
    public Platform(Vector2 position) {
        this.image = new Texture("sky-platform.png");
        this.position = position;
        this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
    }

    /**
     * Get sprite
     * @return
     */
    public Texture getImage() {
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
     * Calculating sprites overlaps
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
        image.dispose();
    }
}
