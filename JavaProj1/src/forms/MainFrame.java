package forms;

import java.awt.Dimension;
import javax.swing.JFrame;
import proj1.action;

public class MainFrame extends JFrame {
    private action action;
    
    public MainFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MainFrame(action callClass) {
        this.action = callClass;
    }

    public void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
    }
}
