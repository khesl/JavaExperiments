package dateexp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import java.awt.image.RasterFormatException;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

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


public class SecondForm extends JFrame {
    private JPanel jPanel1 = new JPanel();
    private static BufferedImage bag_back;
    private static BufferedImage icon_2;

    //private JFrame jf;
    private MyPanel jp;

    public SecondForm() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {        
        bag_back = ImageIO.read(new File("src/assets/bag.png"));
        icon_2 = ImageIO.read(new File("src/assets/icon_2.png"));
        
        SecondForm t = new SecondForm();

    }

    private void jbInit() throws Exception {
        /*this.getContentPane().setLayout( null );
        this.setSize(new Dimension(527, 392));
        jPanel1.setBounds(new Rectangle(5, 5, 330, 270));
        jPanel1.setSize(new Dimension(212, 212));
        this.getContentPane().add(jPanel1, null);*/
        

        JFrame jf = new JFrame();
        jf.setSize(300, 300);
        //jf.setLocation(200, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jp = new MyPanel(bag_back);
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
            
            
        }
        
        public void SetRectPlayer(Rectangle rectPlayer){
            this.rectPlayer = new MyRectangle(rectPlayer);
        }
        
     /*   public Rectangle initPlayer(){
            rectPlayer = new Rectangle(new Point(0, 0), new Dimension (33, 48));
            return rectPlayer;
        }*/
        
        
    } 

    
}
