package src.config_objects;

import java.util.ArrayList;
import java.util.List;

import src.utils.Builder_Objects;
import src.utils.FileReader;
import src.utils.objects.Buildable_Objects;
import src.utils.objects.Level;

/**
 * файл подгрузки компонент level из файла конфига
 * */
public final class Levels_resources {
    private static Levels_resources instance = null;
    private String configFile;
    private static boolean readed = false;

    private static String configPath = MyConstants.CONFIG_PATH;
    private List<Level> listLevels = new ArrayList<Level>();

    public Levels_resources(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
            initLevels();
        }
    }
    public Levels_resources getInstance(){
        if (instance == null)
            initLevels();
        return instance;
    }
    private void initLevels(){
        if (!readed) readConfigFile();
        listLevels = parseConfigLevel_entitys(configFile);
    }

    private void readConfigFile()
    {
        configFile = new FileReader().readFile(configPath);
        readed = true;
    }

    public Level getLevel(int levelId){
        for (Level level : listLevels)
            if (level.getId() == levelId) return level;
        return null;
    }
    public List<Level> getLevels(){
        return (List<Level>)listLevels;
    }

    private List<Level> parseConfigLevel_entitys(String inputString) {
        List<Level> listLevels_entitys = new ArrayList<Level>();
        for (Buildable_Objects objects : Builder_Objects.parseConfigObject_entitys(Level.getParentClassTag(), Level.getClassTag(), inputString))
            listLevels_entitys.add((Level)objects);
        return listLevels_entitys;
    }
}
