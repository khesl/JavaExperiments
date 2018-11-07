package src.control;

import src.game_objects.Resources_manager;
import src.utils.Anim_TypeObjectEnum;
import src.utils.Utils;
import src.utils._BindObjectEnum;
import src.game_objects.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Попытка сделать более формализованно для дальнейших экспериементов
 *
 *
 * @author VassinAK khesl
 *
 * */

public class MyPlayState_Modificate extends JFrame{
    private static MyPlayState_Modificate instance = null;

    public static final int WIDTH = 356;
    public static final int HEIGHT = 316;
    public static final int WIDTH_FIELD = 340; // типо глобальный X
    public static final int HEIGHT_FIELD = 240; // типо глобальный Y
    public static final String TITLE = "Sky platform";
    public static final boolean debugMode = false;
    public static final boolean keyDebugMode = false;
    public static final float delta = 0.5f;
    //private transient ArrayList<ArrayList<Image>> assets1 = new ArrayList<ArrayList<Image>>();
    //private Map<String, int> bind_sprites_table = new // привязку можно сделать потом.

    private JFrame jf;
    private Game_statement_manager jp;

    private Resources_manager resources_manager = new Resources_manager().getInstance();
    private Objects_manager objects_manager = new Objects_manager().getInstance();

    public MyPlayState_Modificate() {
        if (instance == null) { // Экземпляр менеджера был найден
            instance = this; // Задаем ссылку на экземпляр объекта

            // проверка контроллера аниматора для игрока
            System.out.println("player_control: \n" + resources_manager.getMap_Animator_controller(_BindObjectEnum.player).getCurrentNode().toStringAll());


            jf = new JFrame();
            jf.setSize(WIDTH, HEIGHT);
            //jf.setLocation(200, 200);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jp = new Game_statement_manager(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.idle).resetAnimation().getImage()));
            jp.setLayout(new BorderLayout());
            //jLabel1.setIcon(new javax.swing.ImageIcon(visibleImage)); //getClass().getResource("/org/me/myimageapp/newpackage/image.png")));
            //jp.paintComponent(this.getGraphics());
            jp.changeShow();

            //player = new Player();

            jf.getContentPane().add(new JScrollPane(jp));
            jf.getContentPane().add(jp.getUIPanel(), "South");
            //jf.getContentPane().add(jp, "South");

            //jf.add(jLabel1);
            jf.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MyPlayState_Modificate t = new MyPlayState_Modificate();
    }

    public MyPlayState_Modificate getInstance(){
        if (instance == null){
            throw new NullPointerException("Move_manager not initialized yet.");
            //new Move_manager();
            //initMove_manager();
        }
        return instance;
    }

   /* @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(visibleImage, 100, 100, jf);
    }*/

}
