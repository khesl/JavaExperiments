package src.game_objects;

import src.config_objects.MyConstants;
import src.utils.Anim_TypeObjectEnum;
import src.utils.objects.Anim_Sprite;
import src.utils.objects.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для реализации модуля анимации. Должен содержаться ENUM типов анимации, контейнер спрайтов,
 * методы поиска необходимого спрайта по ID(?)
 * */

public class Anim_control_module {
    private static Anim_control_module instance = null;
    private int seq_SpriteId = 1;
    private int seq_SpritesId = 1;
    private int seq_Controller_component = 1;
    private List<Anim_Sprite> list_anim = new ArrayList<Anim_Sprite>();

    private static String configPath = MyConstants.CONFIG_PATH;
    private List<Level> listLevels = new ArrayList<Level>();

    public Anim_control_module(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
        }
    }

    public Anim_control_module getInstance(){
        return instance;
    }

    public void setAnimSeq(int idInSeq, Image image, Anim_TypeObjectEnum typeObject){
        //list_anim.add (new Anim_Sprite(getSpriteId(), idInSeq, image, typeObject));
    }

    protected int getSpriteId(){ return ++seq_SpriteId;}
    protected int getSpritesId(){ return ++seq_SpritesId;}
    public int getSeq_Controller_component(){ return ++seq_Controller_component;}
}
