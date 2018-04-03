package javaproj4;

import com.mylogic.backends.lwjgl.LwjglApplication;
import com.mylogic.backends.lwjgl.LwjglApplicationConfiguration;

public class OneMoreClass{

            
    public OneMoreClass() {
        super();
    }
    
    public static void main(String[] args){
        //OneMoreClass t = new OneMoreClass();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
         config.width = Platformer.WIDTH;
        config.height = Platformer.HEIGHT;
        config.title = Platformer.TITLE;
        new LwjglApplication(new Platformer(), config);
        

    }
}
