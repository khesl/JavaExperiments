package src.game_objects;

import src.utils.objects.Level;

import java.util.ArrayList;
import java.util.List;

public class Level_Object {
    private Level level;

    private transient char[][] map;
    private transient List<List<Sprite>> sprites = new ArrayList<>();

    private boolean isInit = false;

    public void initMap(Level level){
        this.level = level;
        String dataMap = level.getMap();
        int height = 0;
        map = new char[level.getHeight()][level.getWidth()];
        while (dataMap.length() >= level.getWidth()) {
            //System.out.println("height:" + height + ", level: " + level.getWidth() + ", dataMap: " + dataMap);
            for (int i = 0; i<level.getWidth(); i++)
                //System.out.print(dataMap.substring(0, level.getWidth()).toCharArray()[i]);
                map[height][i] = dataMap.substring(0, level.getWidth()).toCharArray()[i];
            dataMap = dataMap.substring(level.getWidth(), dataMap.length());
            height ++;
        }

        System.out.println("Level was loaded. " + level.toString());
        isInit = true;
    }

    public void createSpriteMap(){

    }

    public void addW(List<Sprite> sprites){
        this.sprites.add(sprites);
    }
    public void addH(int index, Sprite sprites){
        this.sprites.get(index).add(sprites);
    }

    public Level getLevel() { return level; }

    public boolean isInit() { return isInit; }

    public char[][] getMap() {
        return map;
    }

    public List<List<Sprite>> getSprites(){
        return sprites;
    }
}
