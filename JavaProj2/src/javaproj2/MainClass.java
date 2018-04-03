package javaproj2;

import javaproj2.MainFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainClass {
    private JFrame frame = new MainFrame();
    MainFrame mFrame = new MainFrame();
    
    public MainClass() {        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainClass();
        //initCount();
    }
    
    private static void initCount(){
        int i = 0;
        while (i < 100) {
            //mFrame.setJbutton1Text(String.valueOf(i++));
            if (i == 99) i = 0;
        }
    }
}
