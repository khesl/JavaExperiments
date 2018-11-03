package com;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;
import utils.ConsoleColor;
import utils.MyDropThread;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Main_22 {
    private static final String TIME_PATTERN = "yyyyMMdd_HHmmss"; // задаём шаблон для записи времени снэпа экрана
    private static final String API_CLIENT_NAME = "dropbox/SkillBoxIntensive_Snaps";
    private static final String ACCESS_TOKEN = "wclR_-eEstAAAAAAAAAAFMjxm67wctgnNJ0GyRDz7rygMui5kwYcNVXeH_EECeFI";
    private int threadCounter = 0;

    public static void main(String[] args) throws IOException {
        Main_22 main = new Main_22();

        for (int i = 0 ;i<512;i++){
            System.out.print((char)i + "|");
            if (i%100 == 0)System.out.println();
        }
        System.out.println();
            System.out.print((int) 'ë');
        //main.issue_22_1(); // вызываю функцию создания снэпа от имени экземпляра Main_22
    }

    public void issue_22_1(){
        List<MyDropThread> list = new ArrayList<MyDropThread>();

        list.add(new MyDropThread(getThreadName(), API_CLIENT_NAME, ACCESS_TOKEN, TIME_PATTERN, 5000));

        list.get(0).testDrop();
        list.get(0).start();
    }

    private String getThreadName(){ return "thread_" + ++threadCounter; }
}