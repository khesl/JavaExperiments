package javaproj4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
//import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.awt.image.RasterFormatException;

import java.io.File;
import java.io.IOException;
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

import src.game_objects.Player; // 2D фигурки


public class MyTestPlayState extends JFrame{

    public static final int WIDTH = 340;
    public static final int HEIGHT = 240;
    public static final String TITLE = "Sky platform";
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
    
    private final Player player;

    public MyTestPlayState() throws IOException {
        JFrame jf = new JFrame();
        jf.setSize(WIDTH, HEIGHT);
        //jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*try {
            jp.changeImage(ImageIO.read(new File("src/res/assets/dude.png")));
        } catch (IOException e) {
        };*/
        
        jp = new MyPanel(dude);
        jp.setLayout(new BorderLayout());
        //jp.SetRectPlayer(new Rectangle(new Point(0, 0), new Dimension (33, 48)));
        //jLabel1.setIcon(new javax.swing.ImageIcon(visibleImage)); //getClass().getResource("/org/me/myimageapp/newpackage/image.png")));
        //jp.paintComponent(this.getGraphics());
        jp.changeShow();

        player = new Player();

        jf.getContentPane().add(new JScrollPane(jp));
        jf.getContentPane().add(jp.getUIPanel(), "South");
        //jf.getContentPane().add(jp, "South");
        
        //jf.add(jLabel1);
        jf.setVisible(true);

    }

    public static void main(String[] args) throws IOException {
        //visibleImage = (Image)ImageIO.read(new File("src/res/assets/dude.png"));
        dude = ImageIO.read(new File("src/res/assets/player.png"));
        dude1 = ImageIO.read(new File("src/res/assets/dude1.png"));
        dude2 = ImageIO.read(new File("src/res/assets/dude2.png"));
        dude2_2 = ImageIO.read(new File("src/res/assets/dude2_2.png"));
        dude3 = ImageIO.read(new File("src/res/assets/dude3.png"));
        dude3_2 = ImageIO.read(new File("src/res/assets/dude3_2.png"));
        dude4 = ImageIO.read(new File("src/res/assets/dude4.png"));
        
        MyTestPlayState t = new MyTestPlayState();

    }
    
    public enum MoveAction{
        left, right, jump, down;
        @SuppressWarnings("compatibility:465374388914757567")
        private static final long serialVersionUID = 1L;
    }
   
 
    public class MyPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static final int playerSpeed = 20;
        private static final int playerJumpHight = 50;
        private int oldYpos;
        private boolean move = false;
        //private volatile Rectangle rectPlayer;
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
                g2.drawImage(player.getImage(), (int)player.getBounds().x, (int)player.getBounds().y, this);
                if(show)
                {
                    g2.setPaint(Color.red);
                    g2.draw(new java.awt.Rectangle((int)player.getBounds().x, (int)player.getBounds().y,
                                                   (int)player.getBounds().width, (int)player.getBounds().height));
                }
            }

        public void SetRectPlayer(int x, int y){
            // keep clip within raster
            int x0 = (getWidth() - size.width) / 2;
            int y0 = (getHeight() - size.height) / 2;
            if (x < x0 || x + player.getBounds().width > x0 + size.width || y < y0 ||
                y + player.getBounds().height > y0 + size.height)
                return;
            player.resetVelocity();
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
                    int w = (int)player.getBounds().width;
                    int h = (int)player.getBounds().height;
                    int x0 = (getWidth() - size.width)/2;
                    int y0 = (getHeight() - size.height)/2;
                    int x = (int)player.getBounds().x - x0;
                    int y = (int)player.getBounds().y - y0;
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
        
        public void changeImage(Player entity, BufferedImage image){
            entity.setImage(image);
        }
        
        public Point getRectPoint(){
            return new Point((int)Math.round(player.getBounds().getX()), (int)Math.round(player.getBounds().getY()));
        }
          
        public MyPanel(BufferedImage image) {  
            //changeImage(player, image);
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

        private void move(Player entity, MoveAction action) {
            switch (action) {
            case left:
                {
                    System.out.println("left motion");
                    entity.left();
                    break;
                }
            case right:
                {
                    System.out.println("right motion");
                    entity.right();
                    break;
                }
            case jump:
                {
                    System.out.println("jump motion");
                    entity.jump();
                    break;
                }
            case down:
                {
                    System.out.println("down = non = motion");
                    break;
                }
            }

            player.update(1);

            changeMove();
        }

        private void onLeft() {
            moveCount = 0;
            changeMove();
            move(player, MoveAction.left);
            changeImage(player, setNeededImage(2));
            repaint();
        }
        private void onLeftReleased(){
            changeImage(player, setNeededImage(1));
            repaint();
        }

        private void onRight(){
            moveCount = 0;  
            changeMove();
            move(player, MoveAction.right);
            changeImage(player, setNeededImage(3));
            repaint();
        }         
        private void onRightReleased(){
            changeImage(player, setNeededImage(4));
            repaint();
        }

        private void onDown(){
            changeImage(player, setNeededImage(0));
            move(player, MoveAction.down);
            repaint();
        }
        private void onJump(){
            changeMove();
            move(player, MoveAction.jump);
            if (getCurChoice() == 2)
                changeImage(player, setNeededImage(22));
            if (getCurChoice() == 3)
                changeImage(player, setNeededImage(32));
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
