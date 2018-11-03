package src.game_objects;


import src.utils.Anim_TypeObjectEnum;
import src.utils.Utils;
import src.utils._BindObjectEnum;
import src.utils.objects.Anim_Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *  класс для объединения спрайтов из класса public class Anim_sprite
 *  можно сказать класс реализует саму анимацию. содержит последовательность спрайтов и выдаёт следующий спрайт
 * */
public class Animation {
    private int id;
    private Anim_TypeObjectEnum typeObject;
    private int width = 0;  //задаются размеры ассетов, если sprite_file_type = true
    private int height = 0; //задаются размеры ассетов, если sprite_file_type = true
    private int count_w = 1;
    private int count_h = 1;
    private int seq_max_val;
    private _BindObjectEnum binded_object;
    private boolean filled = false;

    private List<Anim_Sprite> listSprites = new ArrayList<Anim_Sprite>();
    private int curSprite = 0;

    public int getCurSprite(){return curSprite; }
    private int getNewSpriteCount(){
        if (curSprite >= seq_max_val) curSprite = 0;
        return curSprite++;
    }
    public Anim_Sprite getNewSprite(){
        //System.out.println("getNewSprite " + curSprite);
        return listSprites.get(getNewSpriteCount());
    }
    public Anim_Sprite resetAnimation(){
        curSprite = 0;
        return listSprites.get(curSprite);
    }

    public Animation(){
        this.id = new Anim_control_module().getInstance().getSpritesId();
    }
    public Animation(Anim_Sprite sprite){
        this.id = new Anim_control_module().getInstance().getSpritesId();
        if (sprite.isSprite_solo_file_type()) {
            this.typeObject = sprite.getTypeObject();
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
            this.seq_max_val = 1;
            this.binded_object = sprite.getBinded_object();
            listSprites.add(sprite);
            this.filled = true;
        } else initSprites(sprite);
    }

    public void initSprites(Anim_Sprite sprite){
        if (isFilled()) return;
        this.typeObject = sprite.getTypeObject();
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.binded_object = sprite.getBinded_object();
        int sprite_count = 0;

        count_w = (sprite.getImage().getWidth(null)+1)/(sprite.getWidth()+1);
        count_h = (sprite.getImage().getHeight(null)+1)/(sprite.getHeight()+1);
        for (int i = 0; i < count_h; i++){
            Anim_Sprite new_tile;
            for (int j = 0; j < count_w; j++){
                try{
                BufferedImage tempI = Utils.toBufferedImage(sprite.getImage());
                Image tempII = tempI.getSubimage(j*(sprite.getWidth() + 1), i*(sprite.getHeight() + 1),sprite.getWidth(), sprite.getHeight());
                new_tile = new Anim_Sprite(getId(), sprite_count++, true, sprite.getWidth(), sprite.getHeight(),
                        sprite.getSeq_max_val(), tempII, sprite.getTypeObject(), sprite.getBinded_object());
                listSprites.add(new_tile);
                } catch (Exception e) {
                    System.out.println("error:"+ i*(sprite.getHeight() + 1)+ ":" + j*(sprite.getWidth() + 1)+ ":" + sprite.getWidth()+ ":" + sprite.getHeight() + "|||" + e.toString());
                }
            }

        }

        this.seq_max_val = sprite_count;
        this.filled = true;
    }

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getCount_w(){ return count_w; }
    public void setCount_w(int count_w){ this.count_w = count_w; }
    public int getCount_h(){ return count_h; }
    public void setCount_h(int count_h){ this.count_h = count_h; }
    public Anim_TypeObjectEnum getTypeObject() { return typeObject; }
    public void setTypeObject(Anim_TypeObjectEnum typeObject) { this.typeObject = typeObject; }
    public _BindObjectEnum getBinded_object() { return binded_object; }
    public void setBinded_object(_BindObjectEnum binded_object) { this.binded_object = binded_object; }
    public int getSeq_max_val() { return seq_max_val; }
    public void setSeq_max_val(int seq_max_val) { this.seq_max_val = seq_max_val; }

    public int length(){ return listSprites.size(); }
    public boolean isFilled() {return filled; }

    public List<Anim_Sprite> getListSprites() {
        return listSprites;
    }

    public String toString(){
        return "id:" + id + ", typeObject:" + typeObject.toString() +
                ", width:" + width +
                ", height:" + height +
                ", seq_max_val:" + seq_max_val +
                ", filled:" + filled +
                ", binded_object:" + binded_object +
                ", listSprites:" + listSprites.size();
    }
}
