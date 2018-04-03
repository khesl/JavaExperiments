package speechapi2;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame1 extends JFrame {
    public Frame1() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize( new Dimension(400, 300) );
    }
}
