package utils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Вся информация по интеграции API DropBox ->
 * @link https://github.com/dropbox/dropbox-sdk-java
 * */
public class MyDropThread extends Thread {
    private String path;
    private int timeSleep;
    private String client_name;
    private String time_pattern;

    DbxRequestConfig config;
    DbxClientV2 client;
    Unility util = new Unility();

    public MyDropThread(String threadName, String client_name, String token, String time_pattern, int timeSleep){
        super();
        this.setName(threadName);
        this.client_name = client_name;
        this.time_pattern = time_pattern;
        this.timeSleep = timeSleep;

        config = DbxRequestConfig.newBuilder(client_name).build();
        client = new DbxClientV2(config, token);
    }

    public void run(){
        while (!isInterrupted()) {
            try {
                BufferedImage bimage = util.grabScreen(); // init BufferedImage и передаю в неё снимок экрана
                String time = util.getTime(time_pattern); // записываю время создания снимка
                String fileType = "png";
                String fileName = time + "." + fileType;
                ByteArrayOutputStream bais = new ByteArrayOutputStream();
                ImageIO.write(bimage, fileType, bais);

                InputStream in = new ByteArrayInputStream(bais.toByteArray()); // загрузка файлов
                FileMetadata metadata = client.files().uploadBuilder("/" + fileName).uploadAndFinish(in);

                System.out.println(this.getName() + ": snap created to '"
                        + ConsoleColor.setColor(fileName, ConsoleColor.ANSI_RED)
                        + "', resolution '" + bimage.getWidth() + "x"
                        + bimage.getHeight() + "'(w*h), file path '" + "DropBox" + "'");
                this.sleep(timeSleep);
            } catch (IOException e) { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }
            catch (DbxException e) { e.printStackTrace(); }
            catch (AWTException e) { e.printStackTrace(); }
        }
    }

    public void testDrop(){
        FullAccount account = null;
        try {
            account = client.users().getCurrentAccount();
        } catch (DbxException e) {
            e.printStackTrace();
        }
        System.out.println("Test account connection from thread (" + getName() + "): "
                + account.getName().getDisplayName());
    }

    public void setPath(String path){ this.path = path; }
    public String getPath() { return path; }

    public int getTimeSleep() { return timeSleep; }
    public void setTimeSleep(int timeSleep) { this.timeSleep = timeSleep; }
}