package src.game_objects;


import src.math.Vector2;
import src.utils.Anim_TypeObjectEnum;
import src.utils.Utils;
import src.utils._BindObjectEnum;

/**
 *  Класс для инициализации всех объектов приложения и возможности вызвать именно их.
 * */
public class Objects_manager {
    private static Objects_manager instance = null;
    private Resources_manager resources_manager = new Resources_manager().getInstance();

    private Player player;

    public Objects_manager(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initResources();
        }
    }
    public Objects_manager getInstance(){
        if (instance == null){
            new Resources_manager();
            initResources();
        }
        return instance;
    }

    private void initResources(){
        // инициализация объектов при необходимости
        while (!resources_manager.isInit()){
            System.out.println("resources_manager is no init yet");
        }
        player = new Player(new Vector2(30, 100), new Vector2(0, 0),
                Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player,
                        Anim_TypeObjectEnum.idle).resetAnimation().getImage()),
                resources_manager.getMap_Animator_controller(_BindObjectEnum.player));
    }

    public Player getPlayer() { return player; }
}
