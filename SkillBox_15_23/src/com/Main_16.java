package com;

import utils.ConsoleColor;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main_16 {
    private static final String TIME_PATTERN = "yyyyMMdd_HHmmss"; // задаём шаблон для записи времени снэпа экрана

    public static void main(String[] args) throws IOException {
        Main_16 main = new Main_16();
        main.issue__15_1(); // вызываю функцию создания снэпа от имени экземпляра Main_16
    }

    public void issue_17_1(){


    }

    public class MyThread extends Thread {



    }
    /** Функция к заданию за 2-й день Вебинара SkillBox */
    public void issue__15_1() throws IOException {
        BufferedImage bimage = grabScreen(); // создаю переменную типа BufferedImage и передаю в неё снимок экрана
        String time = getTime(); // записываю время создания снимка
        System.out.println("snap time '" + time + "'"); // вывод на экран времени создания снимка
        ImageIO.write(bimage, "png", new File(getHomeDir(), time + ".png"));
            // сохраняю файл на диске в домашней директории пользователя
        System.out.println("snap " + ConsoleColor.setColor(getHomeDir() + "\\" + time + ".png", ConsoleColor.ANSI_RED)
                + " will saved, resolution '" + bimage.getWidth() + "x" + bimage.getHeight() + "'(w*h)");
            // вывожу информацию о создании файла
    }

    /** Возвращает текущее время в String формате.
     * @return String      -   текущее время в формате String
     * */
    private String getTime(){ return new SimpleDateFormat(TIME_PATTERN).format(new Date()); }


    /** Возвращает домашнюю директорию текущего пользователя.
     * @return File      -   ссылка на директорию
     * */
    private static File getHomeDir() { return FileSystemView.getFileSystemView().getHomeDirectory(); }

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