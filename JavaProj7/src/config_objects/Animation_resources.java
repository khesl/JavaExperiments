package src.config_objects;

import src.utils.Builder_Objects;
import src.utils.FileReader;
import src.utils._BindObjectEnum;
import src.utils.objects.Animation_Objects;
import src.utils.objects.Buildable_Objects;

import java.util.ArrayList;
import java.util.List;

public class Animation_resources {
    private static Animation_resources instance = null;
    private String configFile;
    private static boolean readed = false;

    private static String configPath = MyConstants.CONFIG_PATH;
    private List<Animation_Objects> listAnimationObjects = new ArrayList<Animation_Objects>();

    public Animation_resources(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initAnimations();
        }
    }
    public Animation_resources getInstance(){
        if (instance == null)
            initAnimations();
        return instance;
    }
    private void initAnimations(){
        if (!readed) readConfigFile();
        listAnimationObjects = parseConfigAnimation_Objects_entitys(configFile);
    }

    private void readConfigFile()
    {
        configFile = new FileReader().readFile(configPath);
        readed = true;
    }

    public Animation_Objects getAnimation(int animationId){
        for (Animation_Objects animation : listAnimationObjects)
            if (animation.getId() == animationId) return animation;
        return null;
    }
    public List<Animation_Objects> getAnimations(){
        return (List<Animation_Objects>) listAnimationObjects;
    }
    public List<Animation_Objects> getAnimations(_BindObjectEnum binded_object){
        List<Animation_Objects> temp = new ArrayList<Animation_Objects>();
        for (Animation_Objects obj : listAnimationObjects)
            if (obj.getBinded_object() == binded_object)
                temp.add(obj);
        return temp;
    }

    private List<Animation_Objects> parseConfigAnimation_Objects_entitys(String inputString) {
        List<Animation_Objects> listLevels_entitys = new ArrayList<Animation_Objects>();
        for (Buildable_Objects objects : Builder_Objects.parseConfigObject_entitys(Animation_Objects.getParentClassTag(), Animation_Objects.getClassTag(), inputString))
            listLevels_entitys.add((Animation_Objects)objects);
        return listLevels_entitys;
    }
}
