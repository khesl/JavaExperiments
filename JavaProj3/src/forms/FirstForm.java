package forms;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FirstForm extends JFrame{// implements Runnable{
    //private Thread T = new ;
    private JButton jButton1 = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    private JLabel jLabel5 = new JLabel();
    
    public void run() {
       
    }
    
    public FirstForm() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible(true);
        
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JFrame getFrame(){
        return this;
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
        
        jButton1.setText("Кнопочка");
        jButton1.setBounds(new Rectangle(230, 245, 90, 20));
        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }

                public void mousePressed(MouseEvent e) {
                    jButton1_mousePressed(e);
                }
            });
        
        jLabel1.setText("значения с Main");
        jLabel1.setBounds(new Rectangle(15, 15, 95, 15));
        //jLabel2.setText("jLabel2");
        jLabel2.setBounds(new Rectangle(145, 15, 34, 14));
        jLabel3.setText("значения с формы");
        jLabel3.setBounds(new Rectangle(15, 190, 100, 15));
        jLabel4.setText(jTextField1.getText());
        jLabel4.setBounds(new Rectangle(150, 190, 34, 14));
        jLabel5.setText(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName());
        jLabel5.setBounds(new Rectangle(35, 100, 34, 14));
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jButton1, null);
    }
    
    private String getNamecurrentThread(){
        return Thread.currentThread().getName();
    }
    
    public void initCount(){
        int i = 0;
        while (i < 100) {
            jTextField1.setText(String.valueOf(i++).toString());
            System.out.println("count" + i);
            System.out.println(Thread.currentThread().getName());
            this.repaint();
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 99){ 
                i = 0;
                System.out.println("pause");
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void jButton1_mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        initCount();
    }

    private void jButton1_mousePressed(MouseEvent e) {
        System.out.println("presssed");
    }
    
    public void setJbutton1Text(String str){
        jButton1.setText(str);
    }
    
    public String getJbutton1Text(){
        return jButton1.getText();
    }

}
