package javaProj6;

import com.mylogic.math.Vector2;

import sprites.Sprite;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
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

import java.util.Collections;

import java.util.concurrent.CopyOnWriteArrayList;

import javaProj6.utils.TypeEntityObject;

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

import sprites.Arm;
import sprites.Candy;
import sprites.Entity;

// меняем JavaProject4 на другой проект, без карты, он проще, но не менее интересный, поехали
/**
 * Хах это моя игрушка в коде, всё таки она работает, на потоках, с графикой и очками.
 * Делаю тут чтобы понять суть, перед тем как переходить на Unity. К слову, на Unity
 * я её реализовал, см. Halloween Candy 
 * 
 * И даже я не использую тут libGDX! только для вектора
 * 
 * @author VassinAK @since 2017
 * */
public class MyTestPlayState extends JFrame{    

    public static final int WIDTH = 320;
    public static final int HEIGHT = 640;
    public static final int WIDTH_FIELD = 340; // типо глобальный X
    public static final int HEIGHT_FIELD = 640; // типо глобальный Y
    public static final String TITLE = "Candy platform";
    public static final boolean debugMode = false;
    public static BufferedImage myImage;
    public static BufferedImage backGroundImage;
    public static BufferedImage backScoreImage;
    public static final float delta = 0.5f;
    public static Image visibleImage;
    private static BufferedImage armTexture;
    private volatile int score = 0;
    private transient ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private transient ArrayList<Entity> entities = new ArrayList<Entity>();
    //private Sprite[][] sprites2 = new Sprite[15][22];
    //private ArrayList<ArrayList<Sprite>> sprites2_2 = new ArrayList<ArrayList<Sprite>>(); // поробовал перейти от двумерного массива на листы
    private transient ArrayList<ArrayList<BufferedImage>> assets1 = new ArrayList<ArrayList<BufferedImage>>();
    //private transient ArrayList<BufferedImage> assets1 = new ArrayList<BufferedImage>();
    private int moveCount;
    private JFrame jf;
    private MyPanel jp;
    private volatile boolean render = true;

    private static Arm player; //private static Arm player = new Arm();
    private String path_correction = "JavaProj6/";
    
    //private Platformer p = new Platformer();

    public MyTestPlayState() throws IOException {
        //String g_path = "";
        //{ File directory = new File("./"); g_path = directory.getAbsolutePath(); }
        //System.out.println(g_path);
        armTexture = ImageIO.read(new File(path_correction + "src/assets/arm.png"));
        backGroundImage = ImageIO.read(new File(path_correction + "src/assets/halloween_texture(2).jpg"));
        backScoreImage = ImageIO.read(new File(path_correction + "src/assets/score.png"));

        player = new Arm(armTexture);
            
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
                       
            jp = new MyPanel(armTexture);
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
        {
            ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
            for (int i = 0; i < 2; i++){
                for (int j = 0;j < 5; j++){
                    System.out.print("+1 ");
                    BufferedImage tempI = ImageIO.read(new File(path_correction + "src/assets/halloween-candy-4.png"));
                    temp.add(tempI.getSubimage(j*(32 + 1), i*(32 + 1), 32, 32)); // temp.add
                }
            }
            assets1.add(temp);
        }
        {
            ArrayList<BufferedImage> temp = new ArrayList<BufferedImage>();
            for (int j = 0;j < 5; j++){
                System.out.print("+1 ");
                BufferedImage tempI = ImageIO.read(new File(path_correction + "src/assets/halloween-ghost-4.png"));
                temp.add(tempI.getSubimage(j*(32 + 1), 0, 32, 32)); // temp.add
            }
            assets1.add(temp);
        }
    }
    public void initSprites(){
        // функция инициализации карты с её обьектами, тут это не нужно
        
    }
    

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isRender() {
        return render;
    }

    public ArrayList<ArrayList<BufferedImage>> getAssets1() {
        return assets1;
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
        @SuppressWarnings("compatibility:-4221102451267152924")
        private static final long serialVersionUID = 1L;
        private static final int playerSpeed = 20;
        private static final int playerJumpHight = 50;
        private int oldYpos;
        private boolean move = false;
        private volatile MyRectangle rectPlayer;
        private volatile CopyOnWriteArrayList<MyRectangle> batches = new CopyOnWriteArrayList<MyRectangle>();
        private BufferedImage image;
        private Dimension size;
        boolean show;
        private JLabel pEnt;
        int curChoice;
        private final transient Object lock = new Object();
        
        private volatile int animCount = 0;
        
        protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                //System.out.println("paintComponent");
                /* убрать отсюда нахрен + 2, посмотреть откуда и подравлять картинки */
                
                g2.drawImage(backGroundImage, 0, 0, this);   
                
                for (Sprite spr: sprites){
                    g2.drawImage(spr.getImage(), (int)spr.getPosition().x , (int)spr.getPosition().y, this);
                }
                for (MyRectangle mr : getBatches()){
                    if (mr.isInit()){
                        //mr.getEntity().update(delta);
                        mr.update();
                    }
                    // рисуем объекты
                    g2.drawImage(mr.getEntity().getImage(), (int)mr.getEntity().getBounds().x + 2, 
                                                (int)mr.getEntity().getBounds().y, this);
                    /*if (mr.getEntity().getClass().equals(Arm.class)){
                        g2.drawImage(backScoreImage, (int)(WIDTH_FIELD * 0.8) - 10, 20 - 15, this);
                        g2.setPaint(Color.GRAY);
                        g2.drawRect((int)(WIDTH_FIELD * 0.8) - 10, 20 - 15, 80, 20);
                        g2.setPaint(Color.BLACK);
                        g2.drawString(String.valueOf(mr.getEntity().getScore()), (int)(WIDTH_FIELD * 0.8), 20);
                    }*/
                }
                
//                g2.drawImage(image, (int)rectPlayer.getX() + 2, (int)rectPlayer.getY(), this);
                if(show)
                {
                    g2.setPaint(Color.red);
                    //g2.draw(new Rectangle((int)player.getBounds().x,(int)player.getBounds().y,
                        //                  (int)player.getBounds().width, (int)player.getBounds().height));
                        CopyOnWriteArrayList<MyRectangle> batches = getBatches();
                    for (MyRectangle mr : batches){
                        g2.draw(new Rectangle((int)mr.getEntity().getBounds().x, (int)mr.getEntity().getBounds().y,
                                              mr.getBounds().width, mr.getBounds().height));
                    }
                    for (Sprite spr: sprites){
                        g2.draw(new Rectangle((int)spr.getPosition().x,(int)spr.getPosition().y,
                                          spr.getImage().getWidth(), spr.getImage().getHeight()));
                    }
                }
                for (MyRectangle mr : getBatches()){
                    if (mr.getEntity().getClass().equals(Arm.class)){
                        g2.drawImage(backScoreImage, (int)(WIDTH_FIELD * 0.8) - 10, 20 - 15, this);
                        g2.setPaint(Color.GRAY);
                        g2.drawRect((int)(WIDTH_FIELD * 0.8) - 10, 20 - 15, 80, 20);
                        g2.setPaint(Color.BLACK);
                        g2.drawString(String.valueOf(mr.getEntity().getScore()), (int)(WIDTH_FIELD * 0.8), 20);
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

        public CopyOnWriteArrayList<MyTestPlayState.MyRectangle> getBatches() {
            synchronized (lock) {
                return batches;
            }
        }

        public void setBatches(CopyOnWriteArrayList<MyTestPlayState.MyRectangle> batches) {
            this.batches = batches;
        }

        public class Render extends Thread{
            public Render(){super();}
            
            public synchronized void run(){        
                System.out.println("-render-");
                while (!isInterrupted()) { 
                    while (isRender()) {
                        //System.out.println("-repaint-"); 
                        System.out.println("batch.size - " + getBatches().size());
                        CopyOnWriteArrayList<MyRectangle> batches = getBatches();
                        for (MyRectangle mr : getBatches())
                        {//MyRectangle mr = batches.get(batches.size() - 1);
                            if (debugMode) System.out.println(mr.getEntity().getClass());
                            if (debugMode) System.out.println("Y coord = " + mr.getEntity().getBounds().y);
                            if (mr.isInit()){
                                
                                //mr.update();
                                mr.getEntity().update(delta); // перерасчет координат
                                if (debugMode) System.out.println("new after Update coords " + mr.getEntity().getBounds().x + " " + mr.getEntity().getBounds().y); 
                                if (mr.getEntity().getClass().equals(Arm.class)){
                                    for (Entity entity : entities) {
                                        // Меняем всё, в спрайт заполнять текущие конфеты на экране и смотрим, сталкиваются ли они.
                                        if (!entity.equals(mr))
                                            if (entity.collides(mr.getEntity().getBounds())) {
                                                mr.getEntity().addCollides(entity);
                                            }
                                    }
                                } else {
                                    if (mr.getEntity().collides(getPlayerBanch().getEntity().getBounds()) || mr.getEntity().isStayOnObject()){
                                        // удаляем объект который столкнулся с рукой
                                        getPlayerBanch().getEntity().addCollides(mr.getEntity());
                                        getBatches().remove(getBatches().indexOf(mr)); 
                                       // batches.trimToSize();
                                        if (debugMode) System.out.println("remove el, size = " + getBatches().size());
                                    }
                                }
                            }
                        }
                        getPlayerBanch().getEntity().updateScore();
                        setBatches(batches);
                        try {
                            this.sleep(200);
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
                try {
                    // ема что тут происходит
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
            
            getBatches().add(new MyRectangle(new Rectangle(new Point(0, 0), new Dimension (33, 48)), player));
            
            CandyGenerator candyG = new CandyGenerator();
            candyG.start();
            //getPlayerBanch().setImage(setNeededImage(0));
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
                if (mr.getEntity().getClass().equals(Arm.class)){
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
                    //entity.setImage(setNeededImage(2)); // моя анимация
                    break;
                }
            case right:
                {
                    System.out.println("right motion");
                    actionReset();
                    setRightAction(true);
                    player.right();
                    //entity.setImage(setNeededImage(3)); // моя анимация
                    break;
                }
            case jump:
                {
                    System.out.println("jump motion");
                    //player.jump();
                    break;
                }
            case down:
                {
                    //entity.setImage(setNeededImage(0)); // моя анимация
                    if (entity.getY() + playerSpeed <= getSize().height)
                        entity.setLocation(entity.getX(), entity.getY() + playerSpeed);
                    else entity.setLocation(entity.getX(), entity.getY());
                    break;
                }
            case stopAfterL:
                {
                    actionReset();
                    break;
                }
            case stopAfterR:
                {
                    actionReset();
                    break;
                }
            }
            changeMove();
        }
        
        public class CandyGenerator extends Thread{
            private static final int CHANSE_BAD = 25; // % что будет bad конфета.
            private static final int GENERATE_TIME = 750; // время генерации новой конфеты.
            private volatile int complexity = 1; // 'сложность', будет влиять на разброс времени генерации
            private volatile boolean generate = true;
        
            public void run(){
                while (isGenerate()) {
                    int type = (int)(Math.random()*10);
                    TypeEntityObject typeObj = getNewTypeEntityObject(); 
                    BufferedImage image;
                    if (typeObj == TypeEntityObject.candy)
                        image = getAssets1().get(0).get(type);
                    else image = getAssets1().get(1).get(type/2);
                    Vector2 position = new Vector2(getNewXPosition(), HEIGHT_FIELD - 30);
                    com.mylogic.math.Rectangle rect = new com.mylogic.math.Rectangle(position.x, position.y, image.getWidth(), image.getHeight());
                    Candy candy = new Candy();
                    //position, rect, image, getNewTypeEntityObject()
                    
                    candy.setPosition(position.x, position.y);
                    candy.setVelocity(new Vector2(0, 0));
                    candy.setBounds(rect);
                    candy.setImage(image);
                    candy.setTypeObject(getNewTypeEntityObject());
                    if (typeObj == TypeEntityObject.candy) candy.setScore((type + 1)*10);
                    else  candy.setScore(-50);
                    
                    /*
                    if (debugMode) System.out.println((int)rect.x + ", " + (int)rect.y + "; type -" + type);
                    if (debugMode) System.out.println(image.getWidth() + ", " + image.getHeight());
                    
                    if (debugMode) System.out.println(candy.getBounds().x+ ", " + candy.getBounds().y);
                    if (debugMode) System.out.println(candy.getImage().getWidth()+ ", " + candy.getImage().getHeight());
                    if (debugMode) System.out.println("pos " + candy.getPosition().x + ", " + candy.getPosition().y);
                    */
                    
                    Rectangle rect2 = new Rectangle(new Point((int)rect.x, (int)rect.y), new Dimension((int)rect.getWidth(), (int)rect.getHeight()));
                    
                    /*MyRectangle mr = new MyRectangle(rect2, candy);
                    mr.setImage(image);
                    
                    System.out.println("mr - " + mr.getBounds().x+ ", " + mr.getBounds().y);
                    System.out.println("mr2- " + mr.getImage().getWidth()+ ", " + mr.getImage().getHeight());
                    System.out.println("rect " + mr.getEntity().getBounds().x + ", " + mr.getEntity().getBounds().y);
                    */
                    getBatches().add(new MyRectangle(rect2, candy));
                    if (debugMode) System.out.println("one more " + candy.getTypeObject().toString());
                    try {
                        this.sleep(getNextGenerateTime());
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("-stop render-");
                
                return; 
            }
            
            private int getNextGenerateTime(){
                int time = GENERATE_TIME;
                if (Math.random() > 0.5)
                    time += (int)(GENERATE_TIME * ((Math.random()*100)*(complexity+10)/100)/100);
                else
                    time -= (int)(GENERATE_TIME * ((Math.random()*100)*(complexity+10)/100)/100);
                return time;
            }
            private int getNewXPosition(){
                int min = 0;
                return (int)(min + ((WIDTH_FIELD - min)*0.2) + (Math.random()*(WIDTH_FIELD - min)*0.6));
            }
            private TypeEntityObject getNewTypeEntityObject(){
                if ((int)(Math.random()*100) > CHANSE_BAD )                    
                    return TypeEntityObject.candy;
                else 
                    return TypeEntityObject.badCandy;
            }

            public void setComplexity(int complexity) {
                this.complexity = complexity;
            }

            public int getComplexity() {
                return complexity;
            }

            public void setGenerate(boolean generate) {
                this.generate = generate;
            }

            public boolean isGenerate() {
                return generate;
            }
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
