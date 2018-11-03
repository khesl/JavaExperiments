package src.game_objects;

import java.awt.*;

import src.utils.TypeObjectEnum;

/**
 * Sky platform
 */
public class Platform extends Sprite {
    private boolean init = false;

    /**
     * Constructor for initialize sprite, bounds, position
     */
    public Platform() {
        this(new src.math.Vector2(0, 0));
    }
    public Platform(src.math.Vector2 position) {
        this(position, null);
    }
    public Platform(src.math.Vector2 position, Image image) {
        this(position, image, TypeObjectEnum.empty);
    }
    public Platform(src.math.Vector2 position, Image image, TypeObjectEnum typeObject) {
        this.position = position;
        this.image = image;
        this.typeObject = typeObject;
        visible = true;
        setInit(true);
    }

    /**
     * Unload resources
     */
    public void dispose() {
        //image.dispose();
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public boolean isInit() {
        return init;
    }


}
