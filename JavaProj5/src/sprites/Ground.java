package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import base.Platformer;

/**
 * Ground sprite
 */
public class Ground {
    private Texture image;
    private Vector2 position;
    private Rectangle bounds;

    /**
     * Constructor for initialize sprite, bounds, position
     */
    public Ground() {
        image = new Texture("platform.png");
        position = new Vector2(0, 0);
        bounds = new Rectangle(position.x, position.y, Platformer.WIDTH, image.getHeight());
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
        image.dispose();
    }
}
