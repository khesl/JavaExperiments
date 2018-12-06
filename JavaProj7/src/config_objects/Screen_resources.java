package src.config_objects;

import src.utils.Builder_Objects;
import src.utils.FileReader;
import src.utils.objects.Buildable_Objects;
import src.utils.objects.Screen_Object;

import java.util.ArrayList;
import java.util.List;

public class Screen_resources {
    private static Screen_resources instance = null;
    private String configFile;
    private static boolean readed = false;

    private static String configPath = MyConstants.CONFIG_PATH;
    private List<Screen_Object> listscreenObject = new ArrayList<Screen_Object>();

    public Screen_resources(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initScreens();
        }
    }
    public Screen_resources getInstance(){
        if (instance == null)
            initScreens();
        return instance;
    }
    private void initScreens(){
        if (!readed) readConfigFile();
        listscreenObject = parseConfigScreen_Objects_entitys(configFile);
    }

    private void readConfigFile()
    {
        configFile = new FileReader().readFile(configPath);
        readed = true;
    }

    public Screen_Object getScreen(int animationId){
        for (Screen_Object animation : listscreenObject)
            if (animation.getId() == animationId) return animation;
        return null;
    }
    public List<Screen_Object> getScreens(){
        return (List<Screen_Object>) listscreenObject;
    }

    private List<Screen_Object>  parseConfigScreen_Objects_entitys(String inputString) {
        List<Screen_Object> listScreen_entitys = new ArrayList<Screen_Object>();
        for (Buildable_Objects objects : Builder_Objects.parseConfigObject_entitys(Screen_Object.getParentClassTag(), Screen_Object.getClassTag(), inputString))
            listScreen_entitys.add((Screen_Object)objects);
        return listScreen_entitys;
    }
}
