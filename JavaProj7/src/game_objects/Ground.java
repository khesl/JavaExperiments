package src.game_objects;

import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javaproj4.Platformer;

import javaproj4.utils.TypeObject;

import javax.imageio.ImageIO;

/**
 * Ground sprite
 */
public class Ground extends Sprite {
    private BufferedImage image;
    private Vector2 position;
    private Rectangle bounds;
    private static int height = 20;
    private TypeObject typeObject;

    /**
     * Constructor for initialize sprite, bounds, position
     */
    public Ground() {
        try {
            image = ImageIO.read(new File("src/res/assets/platform.png"));
        } catch (IOException e) {
        }
        this.height = height;
        position = new Vector2(0, 0);
        bounds = new Rectangle(position.x, position.y, Platformer.WIDTH, this.height);
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

}
