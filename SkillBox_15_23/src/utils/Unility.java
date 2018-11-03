package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Unility {
    private static Unility instance = null;

    public Unility(){
        if (instance == null)
            instance = this;
    }

    /** Возвращает текущее время в String формате.
     * @return String      -   текущее время в формате String
     * */
    public static String getTime(String time_pattern){ return new SimpleDateFormat(time_pattern).format(new Date()); }

    /** Возвращает снэп экрана.
     * @return BufferedImage      -   снимок экрана
     * */
    public static BufferedImage grabScreen() throws AWTException {
        return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())) ;
    }
}
