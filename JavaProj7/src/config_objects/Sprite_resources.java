package src.config_objects;

import src.utils.Builder_Objects;
import src.utils.FileReader;
import src.utils.objects.Anim_Sprite;
import src.utils.objects.Buildable_Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * файл подгрузки компонент sprite из файла конфига
 * */
public final class Sprite_resources {
    private static Sprite_resources instance = null;
    private String configFile;
    private static boolean readed = false;

    private static String configPath = MyConstants.CONFIG_PATH;
    private List<Anim_Sprite> listSprites = new ArrayList<Anim_Sprite>();

    public Sprite_resources (){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initSprites();
        }
    }
    public Sprite_resources getInstance(){
        if (instance == null)
            initSprites();
        return instance;
    }
    private void initSprites(){
        if (!readed) readConfigFile();
        listSprites = parseConfigSprites_entitys(configFile);
    }

    private void readConfigFile()
    {
        configFile = new FileReader().readFile(configPath);
        readed = true;
    }

    public Anim_Sprite getSprite(int levelId){
        for (Anim_Sprite sprite : listSprites)
            if (sprite.getId() == levelId) return sprite;
        return null;
    }
    public List<Anim_Sprite> getSprites(){
        return (List<Anim_Sprite>)listSprites;
    }

    public void downloadSprite(){
        for (Anim_Sprite sprite : listSprites){
            ImageIcon ii = new ImageIcon(sprite.getLink());
            Image image = ii.getImage();
            int count_w = (image.getWidth(null)+1)/(sprite.getWidth()+1);
            if (image.getWidth(null) % (count_w*(sprite.getWidth()) + 1*(count_w-1)) != 0)
                throw new IllegalArgumentException("Check sprite resolution! (width)");
            int count_h = (image.getHeight(null)+1)/(sprite.getHeight()+1);
            if (image.getHeight(null) % (count_h*(sprite.getHeight()) + 1*(count_h-1)) != 0)
                throw new IllegalArgumentException("Check sprite resolution! (height)");
            sprite.setImage(ii.getImage());
            System.out.println("Load sprite id(" + sprite.getId() + ") complete.");
        }
    }

    private List<Anim_Sprite> parseConfigSprites_entitys(String inputString) {
        List<Anim_Sprite> listSprite_entitys = new ArrayList<Anim_Sprite>();
        for (Buildable_Objects objects : Builder_Objects.parseConfigObject_entitys(Anim_Sprite.getParentClassTag(), Anim_Sprite.getClassTag(), inputString))
            listSprite_entitys.add((Anim_Sprite)objects);
        return listSprite_entitys;
    }
}
