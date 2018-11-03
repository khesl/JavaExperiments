package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Star
 */
public class Star {
    // Gravity takes away from the Y axis
    public static final int GRAVITY = -1;

    private Vector2 velocity;
    private Vector2 position;
    private Texture image;
    private Rectangle bounds;
    private boolean visible;

    /**
     * Constructor for initialized position, velocity and texture
     */
    public Star(Vector2 position) {
        this.image = new Texture("star.png");
        // Initial player position
        this.position = position;
        // Initial velocity for player down on ground
        this.velocity = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
        this.visible = true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
     * Calculating new position
     * @param delta time after last call method render
     */
    public void update(float delta) {
        if (position.y > 0) {
            // Add velocity vector and gravity vector  (0, -15)
            // For additional information see: https://goo.gl/lD9I77
            velocity.add(0, GRAVITY);
        }

        // Calculation new position
        position.add(0, velocity.y * delta);

        bounds.setPosition(position);
    }

    /**
     * Unload resources
     */
    public void dispose() {
        image.dispose();
    }
}
