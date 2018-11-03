package com;

import utils.ConsoleColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Main_17 {
    private static final String TIME_PATTERN = "yyyyMMdd_HHmmss"; // задаём шаблон для записи времени снэпа экрана
    private static final String PATH_TO_SAVE = "S:\\my folder\\doc\\мои файлы\\универ educate\\course\\skillbox_15-23\\day_3\\snaps\\";

    public static void main(String[] args) throws IOException {
        Main_17 main = new Main_17();
        //main.issue_17_1(); // вызываю функцию создания снэпа от имени экземпляра Main_16


    }

    public void issue_17_1(){
        List<MyThread> list = new ArrayList<MyThread>();
        list.add(new MyThread("thead_1", PATH_TO_SAVE, 5000));
        list.add(new MyThread("thead_2", PATH_TO_SAVE, 5000));

        for (MyThread thread : list){
            thread.start();
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public class MyThread extends Thread {
        private String path;
        private int timeSleep;

        public MyThread(String threadName, String path, int timeSleep){
            super();
            this.setName(threadName);
            this.path = path;
            this.timeSleep = timeSleep;
        }

        public void run(){
            while (!isInterrupted()) {
                try {
                    BufferedImage bimage = grabScreen(); // создаю переменную типа BufferedImage и передаю в неё снимок экрана
                    String time = getTime(); // записываю время создания снимка
                    File file = new File(path,time + ".png");
                    ImageIO.write(bimage, "png", file);

                    System.out.println(this.getName() + ": snap created to '" + ConsoleColor.setColor(file.getName(), ConsoleColor.ANSI_RED)
                        + "', resolution '" + bimage.getWidth() + "x" + bimage.getHeight() + "'(w*h), file path '" + file.getPath() + "'");
                    this.sleep(timeSleep);
                } catch (IOException e) { e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }

        public void setPath(String path){ this.path = path; }
        public String getPath() { return path; }

        public int getTimeSleep() { return timeSleep; }
        public void setTimeSleep(int timeSleep) { this.timeSleep = timeSleep; }
    }

    /** Возвращает текущее время в String формате.
     * @return String      -   текущее время в формате String
     * */
    private String getTime(){ return new SimpleDateFormat(TIME_PATTERN).format(new Date()); }

    /** Возвращает снэп экрана.
     * @return BufferedImage      -   снимок экрана
     * */
    private static BufferedImage grabScreen() {
        try {
            return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())) ;
        } catch (SecurityException e) {} catch (AWTException e) {}
        return null;
    }
}