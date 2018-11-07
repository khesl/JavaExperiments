package src.game_objects;

import src.control.Move_manager;
import src.control.MyPlayState_Modificate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;

public class Game_statement_manager extends JPanel {
    private MyPlayState_Modificate mpsm = new MyPlayState_Modificate().getInstance();
    private Resources_manager resources_manager = new Resources_manager().getInstance();
    private Objects_manager objects_manager = new Objects_manager().getInstance();
    private Move_manager move_manager;
    private boolean debugMode = MyPlayState_Modificate.debugMode;
    public  float delta = MyPlayState_Modificate.delta;

    private static final long serialVersionUID = 1L;
    private volatile ArrayList<Entity> batches = new ArrayList<Entity>();
    private volatile boolean render = true;
    BufferedImage image;
    Dimension size;
    boolean show;

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

    public JPanel getUIPanel() {
        final JCheckBox clipBox = new JCheckBox("show clip", show);
        clipImage();

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

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isRender() {
        return render;
    }

    public void changeImage(BufferedImage image){
        this.image = image;
    }

    public Game_statement_manager(BufferedImage image) {
        changeImage(image);
        size = new Dimension(image.getWidth(), image.getHeight());
        show = false;

        Thread rR = new Render();
        rR.start();


        batches.add(objects_manager.getPlayer()); // добавляем игрока
        //setBatch(new Rectangle(new Point(0, 0), new Dimension (33, 48)), player);

        move_manager = new Move_manager(getInputMap(WHEN_IN_FOCUSED_WINDOW), getActionMap());
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
}
