package musicexp;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

public class FirstForm extends JFrame implements Runnable{
    private Thread T = new Thread("FirstFormThread");
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    JTextField jTextField1 = new JTextField();
    private JLabel jLabel5 = new JLabel();
    private JTextField filename = new JTextField(), dir = new JTextField();
    private JPanel jPanel1 = new JPanel();
    private XYLayout xYLayout1 = new XYLayout();


    public void run() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!T.interrupted()) {
            System.out.println(Thread.currentThread().getName() + "   here");
        }
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
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        /*try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    
    public JFrame getFrame(){
        return this;
    }
    
    // можно убрать, когда вызов чисто из main файла
    public static void main(String[] args) {
        FirstForm form = new FirstForm();
        try {
            form.T.join();
            form.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
        
        jButton1.setText("openFile_playMusic");
        jButton1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton1_mouseClicked(e);
                }

                public void mousePressed(MouseEvent e) {
                    jButton1_mousePressed(e);
                }
            });
        
        jButton2.setText("Кнопочка2");
        jButton2.setBounds(new Rectangle(230, 20, 120, 20));
        jButton2.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    jButton2_mouseClicked(e);
                }
            });
        
        jLabel1.setText("значения с DiplomaProj");
        jLabel1.setBounds(new Rectangle(15, 15, 95, 15));
        jLabel2.setBounds(new Rectangle(145, 15, 34, 14));
        jLabel3.setText("значения с формы");
        jLabel3.setBounds(new Rectangle(15, 190, 100, 15));
        jLabel4.setText(jTextField1.getText());
        jLabel4.setBounds(new Rectangle(150, 190, 34, 14));
        jLabel5.setText(Thread.currentThread().getName());
        jLabel5.setBounds(new Rectangle(15, 100, 120, 15));
        System.out.println(Thread.currentThread().getName() + " - Thread");
        jPanel1.setBounds(new Rectangle(215, 190, 175, 80));
        jPanel1.setLayout(xYLayout1);
        jPanel1.add(filename, new XYConstraints(5, 5, 165, 20));
        jPanel1.add(dir, new XYConstraints(5, 30, 165, 20));
        jPanel1.add(jButton1, new XYConstraints(5, 55, 165, 20));
        this.getContentPane().add(jPanel1, null);
        this.getContentPane().add(jLabel5, null);
        this.getContentPane().add(jLabel4, null);
        this.getContentPane().add(jLabel3, null);
        this.getContentPane().add(jLabel2, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jButton2, null);
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
    
    private void openFile(MouseEvent e){

        JFileChooser c = new JFileChooser();
        // Demonstrate "Open" dialog:
        int rVal = c.showOpenDialog(FirstForm.this);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            filename.setText(c.getSelectedFile().getName());
            dir.setText(c.getCurrentDirectory().toString());
        }
        if (rVal == JFileChooser.CANCEL_OPTION) {
            filename.setText("You pressed cancel");
            dir.setText("");
        }
        if (c.getSelectedFile().canRead()){
            Sound sound = new Sound(c.getSelectedFile());
            sound.play();
            
            //Sound.playSound(c.getSelectedFile().getPath()).play();

        }
        
    }


    private void jButton2_mouseClicked(MouseEvent e) {
        System.out.println("clicked2");
        System.out.println("Thread - " + Thread.currentThread().getName());
    }
    private void jButton1_mouseClicked(MouseEvent e) {
        System.out.println("clicked");
        //initCount();
        openFile(e);
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
    
    public Thread getThread(){
        return T;
    }
    
    public int disposeMethod(){
        int exit = this.DISPOSE_ON_CLOSE;
        this.getThread().interrupt();
        return exit;
    }
    

}
