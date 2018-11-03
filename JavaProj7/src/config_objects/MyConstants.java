package src.config_objects;

public final class MyConstants {
    private static MyConstants instance = null;
    public static String CONFIG_PATH = "JavaProj7/src/config_objects/levels_config.txt";


    public MyConstants(){
        if (instance == null)
        { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта
        }
    }
    public MyConstants getInstance(){
        if (instance == null){
            new MyConstants();
        }
        return instance;
    }

}
