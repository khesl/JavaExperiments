package sprites;

import com.mylogic.graphics.g2d.Sprite;
import com.mylogic.math.Rectangle;
import com.mylogic.math.Vector2;

import java.awt.image.BufferedImage;

import javaProj6.utils.TypeEntityObject;

public abstract class Entity {
    private Vector2 position;
    private Rectangle bounds;   
    private TypeEntityObject typeObject; // потом сделать новый родительский класс для typeObject, чтобы их разделить, а тут возвращать базовый
    private BufferedImage image;
    private int score;
    private boolean stayOnObject;
    
    public Entity() {
        super();
    }
    
    public abstract void jump();

    public abstract void right();

    public abstract void left();
    
    public abstract void resetVelocity();
    
    public abstract void update(float delta);
    
    public abstract void dispose();
    
    public abstract void updateScore();
    
    /**
     * Добавляем новую коллизию
     * @param spr
     */
    public abstract void addCollides(Sprite spr);
    public abstract void addCollides(Entity spr);
    
    /**
     * Сбрасываем наши колизии
     * @param spr
     * */
    public abstract void delCollides(Sprite spr);
    public abstract void delCollides(Entity spr);
    
    /**
     * Calculating sprites overlaps
     * @param player player bounds
     * @return
     */
    public boolean collides(Rectangle player) {
        return bounds.overlaps(player);
    }
    
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
    
    public TypeEntityObject getTypeObject(){
        return typeObject;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setTypeObject(TypeEntityObject typeObject) {
        this.typeObject = typeObject;
    }
    
    public boolean isStayOnObject() {
        return stayOnObject;
    }
    
    public void setStayOnObject(boolean stayOnObject) {
        this.stayOnObject = stayOnObject;
        if (stayOnObject) {
            resetVelocity();
        }
    }
}
