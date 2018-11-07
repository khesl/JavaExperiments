package src.game_objects;

import src.math.Rectangle;
import src.math.Vector2;
import src.utils.Anim_TypeObjectEnum;
import src.utils._BindObjectEnum;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private Vector2 position;
    private Rectangle bounds;
    private BufferedImage image;
    private Animator_controller animator_controller;
    private _BindObjectEnum bindType;

    private Anim_TypeObjectEnum curMove;
    private boolean init;

    public Entity() {
        super();
    }

    public abstract void jump();

    public abstract void right();

    public abstract void left();

    public abstract void resetVelocity();

    public abstract void update(float delta);

    public abstract void dispose();

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public Vector2 getPosition(){
        return position;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void setImage(BufferedImage image) { this.image = image; }
    public BufferedImage getImage(){ return image; }

    public boolean isInit() { return init; }
    protected void setInit(boolean init) { this.init = init; }

    public Anim_TypeObjectEnum getCurMove() { return curMove; }
    public void setCurMove(Anim_TypeObjectEnum curMove) { this.curMove = curMove; }

    public void setAnimator_controller(Animator_controller animator_controller) { this.animator_controller = animator_controller; }
    public Animator_controller getAnimator_controller(){return animator_controller;}

    public void setBindType(_BindObjectEnum bindType) { this.bindType = bindType; }
    public _BindObjectEnum getBindType() { return bindType; }

    public abstract void move(Anim_TypeObjectEnum action);

}
