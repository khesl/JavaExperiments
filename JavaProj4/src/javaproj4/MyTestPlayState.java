package javaproj4;

import src.sprites.Sprite;

import com.mylogic.math.Vector2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.awt.image.RasterFormatException;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import javaproj4.utils.TypeObject;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import src.sprites.Entity;
import src.sprites.Platform;
import src.sprites.Player; // 2D фигурки

/**
 * Рабочий проект 2D платформера на Java, реализовано почти на чистой джаве
 * либы движка только для подтягивания вектора и иногда графики.
 * 
 * @author VassinAK
 * 
 * */
public class MyTestPlayState extends JFrame{    
    private char[][] map = {
    {'#', 's', 's','s', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', '#'}, //0
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //1
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //2
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //3
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //4
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //5
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //6
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //7
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '<', '-', '-', '-', '>', '.', '.', '.', '#'}, //8
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //9
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //10
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //11
    {'#', '.', '.','.', '.', '.', '<', '-', '-', '-', '-', '-', '-', '-', '-', '>', '.', '.', '.', '.', '#'}, //12
    {'#', '.', '.','.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'}, //13
    {'#', '#', '#','#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};//14

    public static final int WIDTH = 340;
    public static final int HEIGHT = 300;
    public static final int WIDTH_FIELD = 340; // типо глобальный X
    public static final int HEIGHT_FIELD = 240; // типо глобальный Y
    public static final String TITLE = "Sky platform";
    public static final boolean debugMode = false;
    public static BufferedImage myImage;
    public static final float delta = 0.5f;
    public static Image visibleImage;
    private static BufferedImage dude;
    private static BufferedImage dude1;
    private static BufferedImage dude2;
    private static BufferedImage dude2_2;
    private static BufferedImage dude3;
    private static BufferedImage dude3_2;
    private static BufferedImage dude4;
    private transient ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    //private Sprite[][] sprites2 = new Sprite[15][22];
    //private ArrayList<ArrayList<Sprite>> sprites2_2 = new ArrayList<ArrayList<Sprite>>(); // поробовал перейти от двумерного массива на листы
    private transient ArrayList<ArrayList<BufferedImage>> assets1 =
        new ArrayList<ArrayList<BufferedImage>>();
    private int moveCount;
    private JFrame jf;
    private MyPanel jp;
    private volatile boolean render = true;
    private TypeObject typeObject;
    
    
    private static Player player = new Player();
    
    //private Platformer p = new Platformer();

    public MyTestPlayState() throws IOException {
        dude = ImageIO.read(new File("src/res/assets/player.png"));
        dude1 = ImageIO.read(new File("src/res/assets/dude1.png"));
        dude2 = ImageIO.read(new File("src/res/assets/dude2.png"));
        dude2_2 = ImageIO.read(new File("src/res/assets/dude2_2.png"));
        dude3 = ImageIO.read(new File("src/res/assets/dude3.png"));
        dude3_2 = ImageIO.read(new File("src/res/assets/dude3_2.png"));
        dude4 = ImageIO.read(new File("src/res/assets/dude4.png"));
        
        //p.create();
        //p.render();
        
        initAssets();
        initSprites();
        
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
        MyTestPlayState t = new MyTestPlayState();
    }
    
    public void initAssets() throws IOException {
        for (int i = 0; i < 11; i++){
            ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
            for (int j = 0;j < 18; j++){
                BufferedImage tempI = ImageIO.read(new File("src/res/assets/64643.png"));
                temp.add(tempI.getSubimage(j*(16 + 1), i*(16 + 1), 16, 16));
            }
            assets1.add(temp);
        }
    }
    public void initSprites(){
        //AccessibleAWTFrame<Sprite> tempSprite = new ArrayList<Sprite>();
        for (int i = 0; i < map.length; i++){
            ArrayList<Sprite> spritesLocal = new ArrayList<Sprite>();
            int platfCount = 5;
            for (int j = 0; j < map[i].length; j++){
                if (map[i][j] == '#'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(10).get(5) - земля
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(10).get(5), TypeObject.wall));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(10).get(5), TypeObject.wall));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(10).get(5), TypeObject.wall);
                }
                if (map[i][j] == '-'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(5-10) - платформа
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(9).get(platfCount), TypeObject.wall));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(9).get(platfCount), TypeObject.wall));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(9).get(platfCount++), TypeObject.wall);
                    if (platfCount > 10) platfCount = 5;
                }
                if (map[i][j] == '<'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(4) - начало платформы
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(9).get(4), TypeObject.wall));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(9).get(4), TypeObject.wall));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(9).get(4), TypeObject.wall);
                }
                if (map[i][j] == '>'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(9).get(11) - конец платформы
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(9).get(11), TypeObject.wall));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(9).get(11), TypeObject.wall));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(9).get(11), TypeObject.wall);
                }
                if (map[i][j] == 's'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(10).get(0) - небо
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(10).get(0), TypeObject.wall));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(10).get(0), TypeObject.wall));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(10).get(0), TypeObject.wall);
                }
                if (map[i][j] == '.'){
                    int x = 16*j;
                    int y = 16*i;
                    //assets1.get(2).get(15) - пустота
                    sprites.add(new Platform(new Vector2(x, y), assets1.get(2).get(14), TypeObject.air));
                    //spritesLocal.add(new Platform(new Vector2(y, x), assets1.get(2).get(14), TypeObject.air));
                    //sprites2[i][j] = new Platform(new Vector2(x, y), assets1.get(2).get(14), TypeObject.air);
                }
            }
            //sprites2_2.add(spritesLocal);
        }
        //SpriteBatch sb = new SpriteBatch();
    }
    

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isRender() {
        return render;
    }

    public class Render extends Thread{
        public Render(){super();}
        
        public void run(){
            System.out.println("-render-");
            while (isRender()) {
                player.update(1f);
                MyTestPlayState.this.repaint();
                
                for (int i=0;i<100;i++) player.update(1f);
                //jp.paintComponent(MyTestPlayState.this.getGraphics());
                try {
                    this.sleep(50);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("-stop render-");
            
            return; 
        }
    }
    
    public enum MoveAction{
        left, right, jump, down, stopAfterL, stopAfterR;
        @SuppressWarnings("compatibility:465374388914757567")
        private static final long serialVersionUID = 1L;
    }
    
    public class MyRectangle extends Rectangle{
        private double x;
        private double y;
        private Entity entity;
        private boolean init = false;
        private BufferedImage image;
        
        public MyRectangle(Rectangle rect, Entity entity){
            this(rect);
            setEntity(entity);
            setInit(true);
        }
        public MyRectangle(Rectangle rect){
            super(rect);
        }
        
        public void update(){
            setX(entity.getBounds().x);
            setY(entity.getBounds().y);
        }
        
        public double getX(){
            return x;
        }
        public double getY(){
            return y;
        }
        public void setX(double x){
            this.x = x;
        }
        public void setY(double y){
            this.y = y;
        }
        public void setLocation(double x, double y){
            this.x = x;
            this.y = y;
        }
        public void setEntity(Entity entity){
            this.entity = entity;
        }

        public void setInit(boolean init) {
            this.init = init;
        }

        public boolean isInit() {
            return init;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public BufferedImage getImage() {
            return image;
        }

        public Entity getEntity() {
            return entity;
        }
    }
 
    public class MyPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int playerSpeed = 20;
        private static final int playerJumpHight = 50;
        private int oldYpos;
        private boolean move = false;
        public volatile MyRectangle rectPlayer;
        private volatile ArrayList<MyRectangle> batches = new ArrayList<MyRectangle>();
        BufferedImage image;
        Dimension size;
        boolean show;
        public JLabel pEnt;
        int curChoice;
        
        private final int[] animRight = {3, 4, 32, 4};
        private final int[] animLeft = {2, 1, 22, 1};
        private volatile int animCount = 0;
        
        protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                //System.out.println("paintComponent");
                /* убрать отсюда нахрен + 2, посмотреть откуда и подравлять картинки
                 * */
                                
                for (Sprite spr: sprites){
                    g2.drawImage(spr.getImage(), (int)spr.getPosition().x , (int)spr.getPosition().y, this);
                }
                for (MyRectangle mr : batches){
                    if (mr.isInit()){
                        //mr.getEntity().update(delta);
                        mr.update();
                    }
                    // рисуем игрока
                    g2.drawImage(mr.getImage(), (int)mr.getEntity().getBounds().x + 2, 
                                                (int)mr.getEntity().getBounds().y, this);
                    /*for (ArrayList<BufferedImage> bal: assets1){
                        int j = 0;
                        for (BufferedImage bi : bal){
                            g2.drawImage(bi, 50+ j*(16 + 1) , 10 + i*(16 + 1), this);
                            //assets1[i][j] = tempI.getSubimage(j*(16 + 1), i*(16 + 1), 16, 16); 
                            j++;
                        }
                        i++;
                    }*/
                }
                
//                g2.drawImage(image, (int)rectPlayer.getX() + 2, (int)rectPlayer.getY(), this);
                if(show)
                {
                    g2.setPaint(Color.red);
                    g2.draw(new Rectangle((int)player.getBounds().x,(int)player.getBounds().y,
                                          (int)player.getBounds().width, (int)player.getBounds().height));
                    for (Sprite spr: sprites){
                        g2.draw(new Rectangle((int)spr.getPosition().x,(int)spr.getPosition().y,
                                          spr.getImage().getWidth(), spr.getImage().getHeight()));
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
                        for (MyRectangle mr : batches)
                        {//MyRectangle mr = batches.get(batches.size() - 1);
                            if (debugMode) System.out.println("Y coord = " + mr.getEntity().getBounds().y);
                            if (mr.isInit()){
                                
                                // перебор анимации
                                if (isLeftAction()){
                                    mr.getEntity().left();
                                    mr.setImage(setNeededImage(animLeft[animCount]));
                                    if (animCount < animRight.length - 1) animCount++; else animCount = 0;
                                }
                                if (isRightAction()){
                                    mr.getEntity().right();
                                    mr.setImage(setNeededImage(animRight[animCount]));
                                    if (animCount < animRight.length - 1) animCount++; else animCount = 0;
                                }
                                //mr.update();
                                mr.getEntity().update(delta); // перерасчет координат
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
                                if (debugMode) System.out.println("new after Update coords " + mr.getEntity().getBounds().x + " " + mr.getEntity().getBounds().y); 
                                for (Sprite spr : sprites){
                                    if (!spr.getTypeObject().isMoveThrough()) // если обьекты проходимы
                                        if (spr.collides(mr.getEntity().getBounds())){
                                            mr.getEntity().addCollides(spr);
                                            if (debugMode) System.out.println("collides true - " + spr.getPosition().x + " " + spr.getPosition().y);
                                            //что будет если он прикасается элеметов
                                        }
                                }
                                mr.getEntity().updateBorders(); // поставить на условие есть ли колизии, может стать быстрее
                                Player player = (Player)mr.getEntity();
                                if (debugMode) System.out.println("isStayOnObject " + player.isStayOnObject());
                            }
                        }
                        try {
                            this.sleep(250);
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
                    int w = rectPlayer.width;
                    int h = rectPlayer.height;
                    int x0 = (getWidth() - size.width)/2;
                    int y0 = (getHeight() - size.height)/2;
                    int x = (int)rectPlayer.x - x0;
                    int y = (int)rectPlayer.y - y0;
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
        
        public Point getRectPoint(){
            return new Point((int)Math.round(rectPlayer.getX()), (int)Math.round(rectPlayer.getY()));
        }
          
        public MyPanel(BufferedImage image) {  
            changeImage(image);
            //actualDude = image;
            size = new Dimension(image.getWidth(), image.getHeight());
            show = false;
            
            Thread rR = new Render();
            rR.start();
            
            batches.add(new MyRectangle(new Rectangle(new Point(0, 0), new Dimension (33, 48)), player));
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
                    System.out.println(" _jump_");
                    move(getPlayerBanch(), MoveAction.jump);
                }
            });
            am.put("onLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" <- left?");
                    if (!isLeftAction()) move(getPlayerBanch(), MoveAction.left);
                }
            });
            am.put("onLeftReleased", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" <- leftReleased");
                    move(getPlayerBanch(), MoveAction.stopAfterL);
                }
            });
            am.put("onRight", new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println(" -> right?");
                    if (!isRightAction()) move(getPlayerBanch(), MoveAction.right);
                }
            });
            am.put("onRightReleased", new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" -> rightReleased");
                    move(getPlayerBanch(), MoveAction.stopAfterR);
                }
            });
            am.put("onUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" /|\\ up?");
                    //setRender(true);
                    move(getPlayerBanch(), MoveAction.jump);
                }
            });
            am.put("onDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" \\|/ down?");
                    //setRender(false);
                    move(getPlayerBanch(), MoveAction.down);
                }
            });
        }
        
        public void setBatch(Rectangle rectPlayer, Entity entity){
            this.rectPlayer = new MyRectangle(rectPlayer, entity);
        }
        
        public MyRectangle getPlayerBanch(){
            for (MyRectangle mr : batches){
                if (mr.getEntity().getClass().equals(Player.class)){
                    return mr;  
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
            animCount = 0;
        }
        
        private void move(MyRectangle entity, MoveAction action) {
            switch (action) {
            case left:
                {
                    System.out.println("left motion");
                    actionReset();
                    setLeftAction(true);
                    player.left();
                    entity.setImage(setNeededImage(2));
                    break;
                }
            case right:
                {
                    System.out.println("right motion");
                    actionReset();
                    setRightAction(true);
                    player.right();
                    entity.setImage(setNeededImage(3));
                    break;
                }
            case jump:
                {
                    System.out.println("jump motion");
                    player.jump();
                    break;
                }
            case down:
                {
                    entity.setImage(setNeededImage(0));
                    if (entity.getY() + playerSpeed <= getSize().height)
                        entity.setLocation(entity.getX(), entity.getY() + playerSpeed);
                    else entity.setLocation(entity.getX(), entity.getY());
                    break;
                }
            case stopAfterL:
                {
                    actionReset();
                    entity.setImage(setNeededImage(1));
                    break;
                }
            case stopAfterR:
                {
                    actionReset();
                    entity.setImage(setNeededImage(4));
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
            System.out.println(rectPlayer.getX());
            //repaint();
        }
        
        private void onDown(){
            changeImage(setNeededImage(0));
            move(rectPlayer, MoveAction.down);
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
