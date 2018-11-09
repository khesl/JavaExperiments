package src.game_objects;

import src.config_objects.Animation_resources;
import src.config_objects.Levels_resources;
import src.config_objects.Screen_resources;
import src.config_objects.Sprite_resources;
import src.math.Vector2;
import src.utils.Anim_TypeObjectEnum;
import src.utils.TypeObjectEnum;
import src.utils._BindObjectEnum;
import src.utils.objects.Anim_Sprite;
import src.utils.objects.Level;
import src.utils.objects.Screen_Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Буду аккумулировать тут все типы загружаемых ресурсов, и также предоставлять функции доступа к ним.
 * */
public final class Resources_manager {
    private static Resources_manager instance = null;
    private boolean init = false;

    private transient Screen_Object screen;
    private int defaultScreen = 0;
    private transient ArrayList<Animation> sprite_resources = new ArrayList<Animation>();
    private transient Map<_BindObjectEnum, Animator_controller> map_Animator_controllers = new HashMap<_BindObjectEnum, Animator_controller>();
    private Objects_manager objects_manager;
    private Level_Object main_level;
    private Screen_manager screen_manager;


    public Level_Object getMain_level(){return main_level;}
    public ArrayList<Animation> getSprite_resources() {return sprite_resources;}
    public Animator_controller getMap_Animator_controller(_BindObjectEnum bindObject) {
        return map_Animator_controllers.get(bindObject);
    }

    public Resources_manager(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initResources();
        }
    }
    public Resources_manager getInstance(){
        if (instance == null){
            new Resources_manager();
            initResources();
        }
        return instance;
    }

    private void initResources(){
        /*try */
        {
            initMap(0);
            initMonitor();
            initAssets();
            initSpritesMap(main_level);
            initAnimations();

            objects_manager = new Objects_manager().getInstance();
            screen_manager = new Screen_manager().getInstance();

            System.out.println("Init resources success.");
        } /*catch (IOException e) {
            e.printStackTrace();
        }*/

        init = true;
    }

    private boolean isInitMap = false;
    private void initMap(int mapId){
        Levels_resources levelsResources = new Levels_resources();
        main_level = new Level_Object();
        main_level.initMap(levelsResources.getLevel(mapId));
                System.out.println("Level was loaded." + main_level.getLevel().toString());
        isInitMap = true;

    }
    private void initMonitor(){
        Screen_resources resources = new Screen_resources();
        //if (resources.getScreens().size() > 1) throw new IllegalArgumentException("Found several Screen configs!");
        if (resources.getScreens().size() < defaultScreen) throw new IllegalArgumentException("Not found Screen configs!");
        screen = resources.getScreens().get(defaultScreen);
    }
    private void initAssets() {
        Sprite_resources resources = new Sprite_resources();
        resources.downloadSprite();
        for (Anim_Sprite sprites : resources.getSprites())
            sprite_resources.add(new Animation(sprites));
    }
    private void initSpritesMap(Level_Object level_object){
        if (!level_object.isInit()) throw new NullPointerException("Map is not initialized!");
        char map[][] = level_object.getMap();

        Animation sprites_res = getSpriteByBindandType(_BindObjectEnum.global, Anim_TypeObjectEnum.tile);
        System.out.println("global:tile:" + sprites_res);

        for (int i = 0; i < map.length; i++){
            ArrayList<Sprite> spritesLocal = new ArrayList<Sprite>();
            int platfCount = 5;
            int airCount = 3;

            for (int j = 0; j < map[i].length; j++){
                if (map[i][j] == '#'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(10).get(5) - земля
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*10+5).getImage(), TypeObjectEnum.wall));
                }
                if (map[i][j] == '-'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(5-10) - платформа
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*9 + platfCount++).getImage(), TypeObjectEnum.wall));
                    if (platfCount > 10) platfCount = 5;
                }
                if (map[i][j] == '<'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(4) - начало платформы
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*9+4).getImage(), TypeObjectEnum.wall));
                }
                if (map[i][j] == '>'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(11) - конец платформы
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*9+11).getImage(), TypeObjectEnum.wall));
                }
                if (map[i][j] == 's'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(10).get(0) - небо
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*5 + airCount++).getImage(), TypeObjectEnum.wall));
                    if (airCount > 11) airCount = 3;
                }
                if (map[i][j] == '.'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(2).get(15) - пустота
                    spritesLocal.add(new Platform(new Vector2(x, y), sprites_res.getListSprites().get(sprites_res.getCount_w()*2+14).getImage(), TypeObjectEnum.air));
                }
            }
            level_object.addW(spritesLocal);
        }
    }
    private void initAnimations() {
        Animation_resources resources = new Animation_resources();
        Animator_controller animator_controllers = new Animator_controller();
        for (_BindObjectEnum bindVal : _BindObjectEnum.values()){
            if (resources.getAnimations(bindVal).size() > 0)
                animator_controllers = new Animator_controller(resources.getAnimations(bindVal));
            map_Animator_controllers.put(bindVal, animator_controllers);
        }
    }

    public ArrayList<Animation> getSpritesById(int id){
        ArrayList<Animation> temp = new ArrayList<Animation>();
        for (Animation sprites : sprite_resources){
            if (sprites.getId() == id) temp.add(sprites);
        }
        return temp;
    }
    public ArrayList<Animation> getSpritesByBind(_BindObjectEnum binded_object){
        ArrayList<Animation> temp = new ArrayList<Animation>();
        for (Animation sprites : sprite_resources){
            if (sprites.getBinded_object().equals(binded_object)) temp.add(sprites);
        }
        return temp;
    }
    public Animation getSpriteByType(ArrayList<Animation> sprites, Anim_TypeObjectEnum type){
        for (Animation sprite : sprites){
            if (sprite.getTypeObject().equals(type)) return sprite;
        }
        return null;
    }
    public Animation getSpriteByBindandType(_BindObjectEnum binded_object, Anim_TypeObjectEnum type){
        for (Animation sprite : getSpritesByBind(binded_object)){
            if (sprite.getTypeObject().equals(type)) return sprite;
        }
        return null;
    }

    public Screen_Object getScreen() {
        return screen;
    }

    public boolean isInit() {
        return init;
    }
    public void setInit(boolean init) {
        this.init = init;
    }

    public Objects_manager getObjects_manager() {
        if (isInit()) return objects_manager;
        throw new NullPointerException("Resources_manager not load yet.");
    }
    public Screen_manager getScreen_manager() {
        if (isInit()) return screen_manager;
        throw new NullPointerException("Resources_manager not load yet.");
    }
}
