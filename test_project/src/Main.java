package src;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        issue_1_7();
    }

    /** Глава 1, Задание 7
     * */
    public static void issue_1_7(){
        /*JFrame frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((dim).width, (dim).height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("TestFrame");
        frame.setVisible(true);*/


        JFrame frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800, 600);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("TestFrame");
        //frame.pack();
        //frame.setLocationRelativeTo(null); // через упаковку
        frame.setVisible(true);
    }

    /** Глава 1, Задание 6
     * */
    public static void issue_1_6(){

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(df.format(date));

        System.out.println(new SimpleDateFormat("HH:mm dd.MM.yyyy").format(new Date()));

    }
}
