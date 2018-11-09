package src.game_objects;

import src.control.MyPlayState_Modificate;
import src.math.Rectangle;
import src.math.Vector2;
import src.utils.Anim_TypeObjectEnum;
import src.utils._BindObjectEnum;
import src.utils.objects.Screen_Object;

import java.util.ArrayList;
import java.util.List;

/**
 * а вот тут я уже хочу реализовать screen. который будет знать общий масштаб карты, текущее разрешение,
 * а также возвращать спрайты необходимые к рендеру в текущий момент. Так что чтобы можно было
 * ему передать объект на экране для простого получения списка overlaps тайлов и их обработкой.
 *
 * и так, в нём Инфа о карте, инфа о текущих координатах экрана, так же в нём должны быть и настройки отображений.
 *
 * */
public class Screen_manager {
    private static Screen_manager instance = null;
    private static Screen_Object screen;
    public static int width;
    public static int height;
    private int tileWidth;
    private int tileHeight;
    private int globalWidth;
    private int globalHeight;
    public static String title;
    private boolean isInit = false;

    private List<List<Sprite>> currentScreenMap = new ArrayList<>();
    private List<List<Sprite>> fullScreenMap = new ArrayList<>();

    private Vector2 localMapPosition = new Vector2(0,0);

    private Resources_manager resources_manager = new Resources_manager().getInstance();

    public Screen_manager(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            //if (!resources_manager.isInit()) throw new NullPointerException("Resources_manager not init yet.");

            Screen_Object screen = resources_manager.getScreen();
            width = screen.getWidth();
            height = screen.getHeight();
            title = screen.getTitle();

            instance = this; // Задаем ссылку на экземпляр объекта
            initResources();
        }
    }
    public Screen_manager getInstance(){
        //if (!init) throw new NullPointerException("Screen_manager not load yet.");
        if (instance == null){
            new Screen_manager();
            initResources();
        }
        return instance;
    }
    /** Инициализируем все необходимые для Экрана ресурсы.
     * определяем зависимости
     * */
    private void initResources(){
        screen = resources_manager.getScreen();
        fullScreenMap = resources_manager.getMain_level().getSprites();
        width = screen.getWidth();
        height = screen.getHeight();

        tileWidth = resources_manager.getSpriteByBindandType(_BindObjectEnum.global, Anim_TypeObjectEnum.tile).getWidth();
        tileHeight = resources_manager.getSpriteByBindandType(_BindObjectEnum.global, Anim_TypeObjectEnum.tile).getHeight();
        globalWidth = resources_manager.getMain_level().getLevel().getWidth()*tileWidth;
        globalHeight = resources_manager.getMain_level().getLevel().getHeight()*tileHeight;

        Vector2 deltaPosition = new Vector2(0,0);
        fillCurrentScreenMap(deltaPosition);

        Camera cam = new Camera();
        cam.start();

        isInit = true;
    }

    public void fillCurrentScreenMap(Vector2 deltaPosition){
        Vector2 oldPosition = new Vector2(localMapPosition.x, localMapPosition.y);
        Vector2 newPosition = localMapPosition.add(deltaPosition);
        System.out.print(globalWidth +"-"+ width + "=" + (globalWidth - width));
        System.out.print("  PositScreen:" + newPosition.x + "|" + newPosition.y);

        newPosition.x = newPosition.x > (globalWidth - width) ? globalWidth - width : newPosition.x < 0 ? 0 : newPosition.x;
        newPosition.y = newPosition.y > (globalHeight - height) ? globalHeight - height : newPosition.y < 0 ? 0 : newPosition.y;
        //boolean isXBorder = oldPosition.x == newPosition.x ? true : false;
        //boolean isYBorder = oldPosition.y == newPosition.y ? true : false;

        System.out.print(" -> :" + newPosition.x + "|" + newPosition.y + "; ");

        int globalIX = (int)newPosition.x/tileWidth;
        int globalDeltaX = (int)(oldPosition.x - newPosition.x);
        int globalJY = (int)newPosition.y/tileHeight;
        int globalDeltaY = (int)(oldPosition.y - newPosition.y);

        int countI = (globalWidth - globalIX*tileWidth)/tileWidth;
        int countJ = (globalHeight - globalJY*tileHeight)/tileHeight;

        System.out.print("(" + globalIX + "*" + tileWidth + "+" + globalDeltaX + ")" + countI + ";");
        System.out.println("(" + globalJY + "*" + tileHeight + "+" + globalDeltaY + ")" + countJ);

        for (int j = globalJY; j < countJ; j++){
            List<Sprite> spriteX = fullScreenMap.get(j);
            List<Sprite> temp = new ArrayList<>();
            for (Sprite spriteXY : spriteX) {
                Vector2 spritePos = spriteXY.position;
                spritePos.add(new Vector2(globalDeltaX, globalDeltaY));
                //spritePos.add(new Vector2(localMapPosition.x%tileWidth, localMapPosition.y%tileHeight));
                temp.add(new Platform(spritePos, spriteXY.getImage(), spriteXY.typeObject));
            }
            currentScreenMap.add(temp);
        }

        //localMapPosition = newPosition;
    }

    public boolean isInit() { return isInit; }
    public int getViewCountTileW(){ return width/tileWidth; }
    public int getViewCountTileH(){ return height/tileHeight; }

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public List<List<Sprite>> getCurrentScreenMap() { return currentScreenMap; }
    public List<List<Sprite>> getFullScreenMap() { return fullScreenMap; }

    public java.awt.Rectangle getCameraRect(){
        float delta = 0.2f;
        return new java.awt.Rectangle((int)(MyPlayState_Modificate.WIDTH * delta), (int)(MyPlayState_Modificate.HEIGHT * delta),
                (int)(MyPlayState_Modificate.WIDTH *(1 - 2*delta)), (int) (MyPlayState_Modificate.HEIGHT * (1 - 2*delta)));
    }
}
