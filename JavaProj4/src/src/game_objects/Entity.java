package src.game_objects;

import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

public abstract class Entity {
    private Vector2 position;
    private Rectangle bounds;    
    
    public Entity() {
        super();
    }
    
    public abstract void jump();

    public abstract void right();

    public abstract void left();
    
    public abstract void resetVelocity();
    
    public abstract void update(float delta);
    
    public abstract void dispose();
    
    public abstract void updateBorders();
    
    /**
     * Добавляем новую коллизию
     * @param spr
     */
    public abstract void addCollides(Sprite spr);
    
    /**
     * Сбрасываем наши колизии
     * @param spr
     * */
    public abstract void delCollides(Sprite spr);
    
    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
