package src.control;

import src.config_objects.Animation_resources;
import src.config_objects.Sprite_resources;
import src.game_objects.Animator_controller;
import src.game_objects.Resources_manager;
import src.utils.Anim_TypeObjectEnum;
import src.utils.TypeObjectEnum;
import src.utils.Utils;
import src.utils._BindObjectEnum;
import src.utils.objects.Anim_Sprite;
import src.utils.objects.Animation_Objects;
import src.utils.objects.Level;
import src.game_objects.*;
import src.config_objects.Levels_resources;
import src.math.Vector2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

/**
 * Попытка сделать более формализованно для дальнейших экспериементов
 *
 *
 * @author VassinAK khesl
 *
 * */

public class MyPlayState_Modificate extends JFrame{

    public static final int WIDTH = 356;
    public static final int HEIGHT = 316;
    public static final int WIDTH_FIELD = 340; // типо глобальный X
    public static final int HEIGHT_FIELD = 240; // типо глобальный Y
    public static final String TITLE = "Sky platform";
    public static final boolean debugMode = false;
    public static final boolean keyDebugMode = false;
    public static BufferedImage myImage;
    public static final float delta = 0.5f;
    public static Image visibleImage;
    //private transient ArrayList<ArrayList<Image>> assets1 = new ArrayList<ArrayList<Image>>();
    //private Map<String, int> bind_sprites_table = new // привязку можно сделать потом.

    private JFrame jf;
    private MyPanel jp;
    private volatile boolean render = true;

    private static BufferedImage dude;
    private static BufferedImage dude1;
    private static BufferedImage dude2;
    private static BufferedImage dude2_2;
    private static BufferedImage dude3;
    private static BufferedImage dude3_2;
    private static BufferedImage dude4;
    private int moveCount;

    private Resources_manager resources_manager = new Resources_manager().getInstance();

    //private static Player player;

    private static String path_correction = "JavaProj7/";

    public MyPlayState_Modificate() throws IOException {
        dude = ImageIO.read(new File(path_correction + "src/res/assets/player.png"));
        dude1 = ImageIO.read(new File(path_correction + "src/res/assets/dude1.png"));
        dude2 = ImageIO.read(new File(path_correction + "src/res/assets/dude2.png"));
        dude2_2 = ImageIO.read(new File(path_correction + "src/res/assets/dude2_2.png"));
        dude3 = ImageIO.read(new File(path_correction + "src/res/assets/dude3.png"));
        dude3_2 = ImageIO.read(new File(path_correction + "src/res/assets/dude3_2.png"));
        dude4 = ImageIO.read(new File(path_correction + "src/res/assets/dude4.png"));

        //player = new Player(new Vector2(0, 30), new Vector2(0, 0), dude);

        //p.create();
        //p.render();

        System.out.println("player_control: \n" + resources_manager.getMap_Animator_controller(_BindObjectEnum.player).getCurrentNode().toStringAll());

        //testController_comp();

        //game_objects.add(new Platform(new Vector2(50, 50), assets1.get(10).get(5), TypeObjectEnum.wall));
        //System.out.println("size(" + game_objects.size() + ");");
        //System.out.println("x: " + game_objects.get(0).getPosition().x + "; y: " + game_objects.get(0).getPosition().y);


        {
            JFrame jf = new JFrame();
            jf.setSize(WIDTH, HEIGHT);
            //jf.setLocation(200, 200);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Thread rR = new Render();

            //rR.start();


            jp = new MyPanel(dude);
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

    public static void main(String[] args) throws IOException {
        MyPlayState_Modificate t = new MyPlayState_Modificate();
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isRender() {
        return render;
    }

   /* public class Render extends Thread{
        public Render(){super();}

        public void run(){
            System.out.println("-render-");
            while (isRender()) {
                jp.getPlayerBanch().update(1f);
                MyPlayState_Modificate.this.repaint();

                for (int i=0;i<100;i++) jp.getPlayerBanch().update(1f);
                //jp.paintComponent(MyTestPlayState.this.getGraphics());
                try {
                    this.sleep(50);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("-stop render-");

            return;
        }
    }*/

    public enum MoveAction{
        left, right, jump, down, stopAfterL, stopAfterR;
        @SuppressWarnings("compatibility:465374388914757567")
        private static final long serialVersionUID = 1L;
    }

    public class MyPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int playerSpeed = 20;
        private static final int playerJumpHight = 50;
        private int oldYpos;
        private boolean move = false;
        private volatile ArrayList<Entity> batches = new ArrayList<Entity>();
        BufferedImage image;
        Dimension size;
        boolean show;
        public JLabel pEnt;
        int curChoice;

        //private final int[] animRight = {3, 4, 32, 4};
        //private final int[] animLeft = {2, 1, 22, 1};
        //private volatile int animCount = 0;

        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            //System.out.println("paintComponent");
            /* убрать отсюда нахрен + 2, посмотреть откуда и подравлять картинки
             * */

            for (Sprite spr: resources_manager.getSprites()){
                g2.drawImage(spr.getImage(), (int)spr.getPosition().x, (int)spr.getPosition().y, this);
            }
            for (Entity ent : batches){
                if (ent.isInit()){
                    //mr.getEntity().update(delta);
                    //ent.update(); // это я что, догонял борта квадрата при изменении положения entity? лишнее...
                }
                // рисуем игрока
                g2.drawImage(ent.getImage(), (int)ent.getBounds().x + 2, (int)ent.getBounds().y, this);
            }

//                g2.drawImage(image, (int)rectPlayer.getX() + 2, (int)rectPlayer.getY(), this);
            if(show)
            {
                g2.setPaint(Color.red);
                Player player = (Player)getPlayerBanch();
                g2.draw(new Rectangle((int)player.getBounds().x,(int)player.getBounds().y,
                        (int)player.getBounds().width, (int)player.getBounds().height));
                for (Sprite spr: resources_manager.getSprites()){
                    g2.draw(new Rectangle((int)spr.getPosition().x, (int)spr.getPosition().y,
                            spr.getImage().getWidth(null), spr.getImage().getHeight(null)));
                }
            }
        }

        public void setLeftAction(boolean leftAction) {
            this.leftAction = leftAction;
        }

        public boolean isLeftAction() {
            return leftAction;
        }

        public void setRightAction(boolean rightAction) {
            this.rightAction = rightAction;
        }

        public boolean isRightAction() {
            return rightAction;
        }

        public class Render extends Thread{
            public Render(){super();}

            public void run(){
                System.out.println("-render-");
                while (!isInterrupted()) {
                    while (isRender()) {
                        //System.out.println("-repaint-");
                        for (Entity mr : batches)
                        {//MyRectangle mr = batches.get(batches.size() - 1);
                            if (debugMode) System.out.println("Y coord = " + mr.getBounds().y);
                            if (mr.isInit()){

                                // перебор анимации
                                if (isLeftAction()){
                                    mr.left();
                                    mr.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.left).getNewSprite().getImage()));
                                    //System.out.print(getSpriteByType(getSpritesByBind("player"), Anim_TypeObjectEnum.left).getNewSprite().getImage().toString());
                                }
                                if (isRightAction()){
                                    mr.right();
                                    mr.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.right).getNewSprite().getImage()));
                                }
                                //mr.update();
                                mr.update(delta); // перерасчет координат
                                /*{
                                    int i = (int)mr.getEntity().getBounds().x / 16;
                                    int j = (int)mr.getEntity().getBounds().y / 16;
                                    int m = (int)(mr.getEntity().getBounds().x + mr.getEntity().getBounds().width) / 16 + 1;
                                    int n = (int)(mr.getEntity().getBounds().y + mr.getEntity().getBounds().height) / 16 + 1;
                                    // квадраты полей которые физически могут касаться объекта.
                                    System.out.println("(" + i + ", " + j + "), (" + m + ", " + n + ")");
                                    for (int ii = i; ii < m; ii++)
                                        for (int jj = j; jj < n; jj++){
                                            //mr.getEntity().addCollides(sprites2[ii][jj]);
                                            //System.out.println("collides 0.5 true - (" + ii + ", " + jj + ") - " + sprites2[ii][jj].getPosition().x + " " + sprites2[ii][jj].getPosition().y);
                                            if (sprites2_2.get(ii).get(jj).collides(mr.getEntity().getBounds()))
                                                mr.getEntity().addCollides(sprites2_2.get(ii).get(jj));
                                            System.out.println("collides 0.5 true - (" + ii + ", " + jj + ") - " + sprites2_2.get(ii).get(jj).getPosition().x + " " + sprites2_2.get(ii).get(jj).getPosition().y);

                                        } // взаимо заменяемые?? или я просчитываю геометрически, или он сам определяет что задето.
                                        // - я понял, это для оптимизации, проверять только те что вокруг, а не все.
                                }*/
                                if (debugMode) System.out.println("new after Update coords " + mr.getBounds().x + " " + mr.getBounds().y);
                                for (Sprite spr : resources_manager.getSprites()){
                                    if (!spr.getTypeObject().isMoveThrough()) // если обьекты проходимы
                                    {}
                                        /*if (spr.collides(mr.getBounds())){
                                            mr.addCollides(spr);
                                            if (debugMode) System.out.println("collides true - " + spr.getX() + " " + spr.getY());
                                            //что будет если он прикасается элеметов
                                        }*/
                                }
                                //mr.updateBorders(); // поставить на условие есть ли колизии, может стать быстрее
                                Player player = (Player) mr;
                                if (debugMode) System.out.println("isStayOnObject " + player.isStayOnObject());
                            }
                        }
                        try {
                            this.sleep(200); // 200 - норма движение, больше для тестирования.
                        } catch (InterruptedException e) {
                        }
                        repaint();
                    }
                    //System.out.println("-stop render-");
                }
                return;
            }
        }

        public Dimension getPreferredSize(){
            return size;
        }

        private JPanel getUIPanel() {
            final JCheckBox clipBox = new JCheckBox("show clip", show);


            clipBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    show = clipBox.isSelected();
                    repaint();
                    System.out.println(Thread.currentThread().getName());
                }
            });
            JButton clip = new JButton("clip image");
            clip.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    clipImage();
                }
            });
            JPanel panel = new JPanel();
            panel.add(clipBox);
            panel.add(clip);
            return panel;
        }

        private void clipImage(){
            BufferedImage clipped = null;
            try
            {
                Player player = (Player) getPlayerBanch();
                int w = player.getImage().getWidth();
                int h = player.getImage().getHeight();
                int x0 = (getWidth() - size.width)/2;
                int y0 = (getHeight() - size.height)/2;
                int x = (int)player.getPosition().x - x0;
                int y = (int)player.getPosition().y - y0;
                clipped = image.getSubimage(x, y, w, h);
            }
            catch(RasterFormatException rfe)
            {
                System.out.println("raster format error: " + rfe.getMessage());
                return;
            }
            JLabel label = new JLabel(new ImageIcon(clipped));
            JOptionPane.showMessageDialog(this, label, "clipped image",
                    JOptionPane.PLAIN_MESSAGE);
        }

        public void changeShow(){
            show = !show;
            System.out.println(show);
        }
        private void changeMove(){
            move = !move;
        }

        private BufferedImage setNeededImage(int choice){
            curChoice = choice;
            BufferedImage newIcon = null;
            switch (choice) {
                case 0:{
                    newIcon = dude;
                    break;
                }
                case 1:{
                    newIcon = dude1;
                    break;
                }
                case 2:{
                    newIcon = dude2;
                    break;
                }
                case 22:{
                    newIcon = dude2_2;
                    break;
                }
                case 3:{
                    newIcon = dude3;
                    break;
                }
                case 32:{
                    newIcon = dude3_2;
                    break;
                }
                case 4:{
                    newIcon = dude4;
                    break;
                }
                default: newIcon = dude;
            }
            return newIcon;
        }

        public int getCurChoice(){
            return curChoice;
        }

        public boolean isShowSelected(){
            return show;
        }

        public void changeImage(BufferedImage image){
            this.image = image;
        }

        public MyPanel(BufferedImage image) {
            changeImage(image);
            //actualDude = image;
            size = new Dimension(image.getWidth(), image.getHeight());
            show = false;

            Thread rR = new MyPanel.Render();
            rR.start();

            Player player = new Player(new Vector2(30, 100), new Vector2(0, 0), dude);
            batches.add(player);
            getPlayerBanch().setImage(setNeededImage(0));
            //setBatch(new Rectangle(new Point(0, 0), new Dimension (33, 48)), player);

            InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);// (WHEN_FOCUSED);
            ActionMap am = getActionMap();

            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "onEnter");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "onUp");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "onDown");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "onLeft");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "onLeftReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "onRight");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "onRightReleased");
            im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "onSpace");


            am.put("onEnter", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("any pressed?");
                }
            });
            am.put("onSpace", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'_jump_'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" _jump_");
                    move(getPlayerBanch(), MoveAction.jump);
                }
            });
            am.put("onLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'<-'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" <- left?");
                    if (!isLeftAction()) move(getPlayerBanch(), MoveAction.left);
                }
            });
            am.put("onLeftReleased", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'<-*'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" <- leftReleased");
                    move(getPlayerBanch(), MoveAction.stopAfterL);
                }
            });
            am.put("onRight", new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'->'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" -> right?");
                    if (!isRightAction()) move(getPlayerBanch(), MoveAction.right);
                }
            });
            am.put("onRightReleased", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'->*'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" -> rightReleased");
                    move(getPlayerBanch(), MoveAction.stopAfterR);
                }
            });
            am.put("onUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'/|\\'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode)) System.out.println(" /|\\ up?");
                    //setRender(true);
                    move(getPlayerBanch(), MoveAction.jump);
                }
            });
            am.put("onDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keyDebugMode && !debugMode) System.out.print("'\\|/'");
                    if ((debugMode && !keyDebugMode) || (keyDebugMode && debugMode))System.out.println(" \\|/ down?");
                    //setRender(false);
                    move(getPlayerBanch(), MoveAction.down);
                }
            });
        }

        public Entity getPlayerBanch(){
            for (Entity ent : batches){
                if (ent.getClass().equals(Player.class)){
                    return ent;
                }
            }
            System.out.println("not found");
            return null;
        }

        private volatile boolean leftAction = false;
        private volatile boolean rightAction = false;
        public void actionReset(){
            setLeftAction(false);
            setRightAction(false);
            //animCount = 0;
        }

        private void move(Entity entity, MoveAction action) {
            switch (action) {
                case left:
                {
                    if (keyDebugMode) System.out.println("left motion");
                    actionReset();
                    setLeftAction(true);
                    getPlayerBanch().left();
                    entity.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.left).resetAnimation().getImage()));
                    break;
                }
                case right:
                {
                    if (keyDebugMode) System.out.println("right motion");
                    actionReset();
                    setRightAction(true);
                    getPlayerBanch().right();
                    entity.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.right).resetAnimation().getImage()));
                    break;
                }
                case jump:
                {
                    if (keyDebugMode) System.out.println("jump motion");
                    getPlayerBanch().jump();
                    break;
                }
                case down:
                {
                    entity.setImage(setNeededImage(0));
                    if (entity.getPosition().y + playerSpeed <= getSize().height)
                        entity.setPosition(new Vector2(entity.getPosition().x, entity.getPosition().y + playerSpeed));
                    //else entity.setLocation(entity.getX(), entity.getY());
                    break;
                }
                case stopAfterL:
                {
                    actionReset();
                    //entity.setImage(setNeededImage(1));
                    entity.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.left_release).resetAnimation().getImage()));
                    resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.left).resetAnimation();
                    break;
                }
                case stopAfterR:
                {
                    actionReset();
                    //entity.setImage(setNeededImage(4));
                    entity.setImage(Utils.toBufferedImage(resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.right_release).resetAnimation().getImage()));
                    resources_manager.getSpriteByBindandType(_BindObjectEnum.player, Anim_TypeObjectEnum.right).resetAnimation();
                    break;
                }
            }
            changeMove();
        }

        private void onLeftReleased(){
            changeImage(setNeededImage(1));
            repaint();
        }
        private void onRightReleased(){
            changeImage(setNeededImage(4));
            System.out.println(getPlayerBanch().getPosition().x);
            //repaint();
        }

        private void onDown(){
            changeImage(setNeededImage(0));
            move(getPlayerBanch(), MoveAction.down);
            repaint();
        }
    }

    private void onEnter(){
        System.out.println("reset coord.");
        jp.getBounds().setLocation(0, 0);
        moveCount = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(visibleImage, 100, 100, jf);
    }

}
