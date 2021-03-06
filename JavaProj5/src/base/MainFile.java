package base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

import java.awt.image.RasterFormatException;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.KeyStroke; // 2D фигурки

public class MainFile extends JFrame{


    public static BufferedImage myImage;
    public static Image visibleImage;
    private static BufferedImage dude;
    private static BufferedImage dude1;
    private static BufferedImage dude2;
    private static BufferedImage dude2_2;
    private static BufferedImage dude3;
    private static BufferedImage dude3_2;
    private static BufferedImage dude4;
    private int moveCount;
    private JFrame jf;
    private MyPanel jp;

    public MainFile() {
        JFrame jf = new JFrame();
        jf.setSize(300, 300);
        //jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*try {
            jp.changeImage(ImageIO.read(new File("src/res/assets/dude.png")));
        } catch (IOException e) {
        };*/
        
        jp = new MyPanel(dude);
        jp.setLayout(new BorderLayout());
        jp.SetRectPlayer(new Rectangle(new Point(0, 0), new Dimension (33, 48)));
        //jLabel1.setIcon(new javax.swing.ImageIcon(visibleImage)); //getClass().getResource("/org/me/myimageapp/newpackage/image.png")));
        //jp.paintComponent(this.getGraphics());
        //jp.changeShow();     
        
        
        jf.getContentPane().add(new JScrollPane(jp));
        jf.getContentPane().add(jp.getUIPanel(), "South");
        //jf.getContentPane().add(jp, "South");
        
        //jf.add(jLabel1);
        jf.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        //visibleImage = (Image)ImageIO.read(new File("src/res/assets/dude.png"));
        dude = ImageIO.read(new File("src/assets/player.png"));
        dude1 = ImageIO.read(new File("src/assets/dude1.png"));
        dude2 = ImageIO.read(new File("src/assets/dude2.png"));
        dude2_2 = ImageIO.read(new File("src/assets/dude2_2.png"));
        dude3 = ImageIO.read(new File("src/assets/dude3.png"));
        dude3_2 = ImageIO.read(new File("src/assets/dude3_2.png"));
        dude4 = ImageIO.read(new File("src/assets/dude4.png"));     
        MainFile t = new MainFile();

    }
    
    public enum MoveAction{
        left, right, jump, down;
        @SuppressWarnings("compatibility:465374388914757567")
        private static final long serialVersionUID = 1L;
    }
    
    public class MyRectangle extends Rectangle{
        private double x;
        private double y;
        
        public MyRectangle(Rectangle rect){
            super(rect);
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
            
    }
 
    public class MyPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int playerSpeed = 20;
        private static final int playerJumpHight = 50;
        private int oldYpos;
        private boolean move = false;
        private volatile MyRectangle rectPlayer;
        BufferedImage image;
        Dimension size;
        boolean show;
        public JLabel pEnt;
        int curChoice;
        
        protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                System.out.println("paintComponent");
                /* убрать отсюда нахрен + 2, посмотреть откуда и подравлять картинки
                 * */
                g2.drawImage(image, (int)rectPlayer.getX() + 2, (int)rectPlayer.getY(), this);
                if(show)
                {
                    g2.setPaint(Color.red);
                    g2.draw(rectPlayer);
                }
            }

        public void SetRectPlayer(int x, int y){
            // keep clip within raster
            int x0 = (getWidth() - size.width) / 2;
            int y0 = (getHeight() - size.height) / 2;
            if (x < x0 || x + rectPlayer.width > x0 + size.width || y < y0 ||
                y + rectPlayer.height > y0 + size.height)
                return;
            rectPlayer.setLocation(x, y);
            repaint();
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
                    onEnter();
                }
            });
            am.put("onSpace", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" _jump_");
                }
            });
            am.put("onLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" <- left?");
                    onLeft();
                }
            });
            am.put("onLeftReleased", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" <- leftReleased");
                    onLeftReleased();
                }
            });
            am.put("onRight", new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println(" -> right?");
                    onRight();
                }
            });
            am.put("onRightReleased", new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    System.out.println(" -> rightReleased");
                    onRightReleased();
                }
            });
            am.put("onUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" /|\\ up?");
                    onJump();
                }
            });
            am.put("onDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(" \\|/ down?");
                    onDown();
                }
            });
        }
        
        public void SetRectPlayer(Rectangle rectPlayer){
            this.rectPlayer = new MyRectangle(rectPlayer);
        }
        
     /*   public Rectangle initPlayer(){
            rectPlayer = new Rectangle(new Point(0, 0), new Dimension (33, 48));
            return rectPlayer;
        }*/
        
        private void move(MoveAction action){
            switch (action) {
            case left:
                {
                    System.out.println("left motion");
                    if (rectPlayer.getX() - playerSpeed > 0) {
                        rectPlayer.setLocation(rectPlayer.getX() - playerSpeed, rectPlayer.getY());
                        System.out.println("here");
                        System.out.println(rectPlayer.getX());
                    } else
                        rectPlayer.setLocation(0, rectPlayer.getY());
                    break;
                }
            case right:
                {
                    System.out.println("right motion");
                    if (rectPlayer.getX() + playerSpeed < (getWidth() - 50)) {
                        rectPlayer.setLocation((rectPlayer.getX() + playerSpeed), rectPlayer.getY());
                        System.out.println("here");
                        System.out.println(rectPlayer.getX());
                    } else {
                        rectPlayer.setLocation(0, rectPlayer.getY());
                    }
                    break;
                }
            case jump:
                {
                    oldYpos = (int)rectPlayer.getY();
                    for (int i = playerJumpHight; i >= 0; i--) {
                        if ((getY() - playerJumpHight/5) >= 0) {
                            rectPlayer.setLocation(rectPlayer.getX(), rectPlayer.getY() - 1);
                        } else
                            rectPlayer.setLocation(rectPlayer.getX(), rectPlayer.getY());
                        /*try {
                            Thread.currentThread().sleep(2);
                        } catch (InterruptedException e) {
                        }*/
                    }
                    /*for (int i = 0; i <= oldYpos; i++) {
                        if (getY() <= oldYpos) {
                            rectPlayer.setLocation(getX(), getY() - 1);
                        } else
                            rectPlayer.setLocation(getX(), getY());
                    }*/
                    break;
                }
            case down:
                {
                    /*for (int i = 0; i <= oldYpos; i++) {
                        if (getY() <= oldYpos) {
                            rectPlayer.setLocation(getX(), getY() - 1);
                        } else
                            rectPlayer.setLocation(getX(), getY());
                    }*/
                    rectPlayer.setLocation(rectPlayer.getX(), rectPlayer.getY() + playerSpeed);
                    break;
                }
            }
            changeMove();
        }

        private void onLeft() {
            moveCount = 0;
            changeMove();
            move(MoveAction.left);
            changeImage(setNeededImage(2));
            repaint();
        }
        private void onLeftReleased(){
            changeImage(setNeededImage(1));
            repaint();
        }
        
        private void onRight(){
            moveCount = 0;  
            changeMove();
            move(MoveAction.right);
            changeImage(setNeededImage(3));
            System.out.println(rectPlayer.getX());
            repaint();
        }         
        private void onRightReleased(){
            changeImage(setNeededImage(4));
            System.out.println(rectPlayer.getX());
            repaint();
        }
        
        private void onDown(){
            changeImage(setNeededImage(0));
            move(MoveAction.down);
            repaint();
        }
        private void onJump(){
            changeMove();
            move(MoveAction.jump);
            if (getCurChoice() == 2)
                changeImage(setNeededImage(22));
            if (getCurChoice() == 3)
                changeImage(setNeededImage(32));
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

