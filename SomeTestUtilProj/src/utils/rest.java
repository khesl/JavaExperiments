package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author andy
 */

public class rest {
    public rest() {
        super();
    }
    
    public String url = "https://maps.googleapis.com/maps/api/elevation";
    public String key = "AIzaSyBfB-K5jOzEUMfgKw0p6HhM0T5J8xNlEuw";
    public double coordX;
    public double coordY;
    
    public static void main(String[] args) throws Exception {
        try {
            rest tf = new rest();
            tf.start(43.15, 77.085, 5.0);
        } catch (IOException ex) {
            Logger.getLogger(rest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private final int stepX = 4;
    private final int stepY = 4;
    Image img;
    
    /**
     * функция построения карты высоты по заданной начальной координате
     * 
     * @param minX координата широты нижнеголевого угла карты
     * @param minY координата долготы нижнеголевого угла карты
     * @param accuracy accuracy точность построения карты
     * @exception IOException
     * @exception Exception
     * 
     */
    public void start(double minX, double minY, double accuracy) throws IOException, Exception{
        accuracy = accuracy/1000; // приведение к КМ
        double incX = getIncX(accuracy);
        double incY = getIncY(minX, accuracy);
        System.out.println("incX:" + incX + " incY:" + incY);
        
        coordX = minX;
        coordY = minY;     
        double[][] matrix = new double[stepX][stepY];
        
        int count = 0;
        
        String request = url + "/json?locations=" + coordX + "," + coordY + "&key=" + key;
        System.out.println(request);
        
        for (int i = 0;i<stepX;i++){
            coordX += incX;
            coordY = minY;
            for (int j = 0;j<stepY;j++){
                count++;
                coordY += getIncY(coordX, accuracy);//incY;
                request = url + "/json?locations=" + coordX + "," + coordY + "&key=" + key;
                matrix[i][j] = Double.valueOf(getElevation(request)); // для double // бой
                System.out.print("[" + coordX + "," + coordY + "]:" + matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("count: " + count);
        
        int[] arr = new int[stepX*stepY];
        int countArr = 0;
        for (int i = 0;i<stepX;i++){
            coordX += incX;
            for (int j = 0;j<stepY;j++){
                arr[countArr] = getRGB(getMiddle(matrix[i][j]));
                System.out.print(arr[countArr] + ", ");
                countArr++;
            }
        }  
        
        BufferedImage image = new BufferedImage(stepX, stepY, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = image.getRaster();
        raster.setDataElements(0,0,stepX, stepY, arr);
        
        ImageIO.write(image, "png", new File("capturedImage.png"));
    
    }
    
    /**
     * делаем запрос в Google по переданным данным, 
     * и возвращаем распаренную высоту
     * 
     * @param url запрос
     * @return String
     * @exception Exception
     */
    public String getElevation(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        StringBuilder response; 
        //int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'GET' request to URL : " + url);
        //System.out.println("Response Code : " + responseCode);
        try (BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        
        //print in String
        //System.out.println(response.toString());
        
        //Read JSON response and print
        JSONParser parser = new JSONParser();
        JSONObject myResponse = (JSONObject) parser.parse(response.toString());
        //JSONObject myResponse = new JSONObject(response.toString());
        JSONArray resp_Ar = (JSONArray)parser.parse(myResponse.get("results").toString());
        JSONObject resp_ = (JSONObject)parser.parse(resp_Ar.get(0).toString());
        return resp_.get("elevation").toString();
    }
    
    /**
     * нормализуем текущее значение высоты из диапазона [minEl..maxEl]
     *                                      в диапазон [0..255]
     * 
     * @param value текущее значение высоты
     * @return int
     */
    public static int getMiddle(double value){
        int maxEl = 3200; // максимальная высота
        int minEl = 1700; // минимальная высота
        int range = maxEl - minEl; // расброс высоты
        int grad = 255; // градация степени
        if (value < minEl) value = minEl;
        
        double val = (value-range)*grad/range;
        return Double.valueOf(val).intValue();
    }
    /**
     * создаём цвет RBG в градации серого
     * 
     *  @param grad передаём уровень градации серого
     *  @return int
     */
    public static int getRGB(int grad){
        int argb = -16777216; // 255 alpha

        argb += ((int) grad & 0xff); // blue
        argb += (((int) grad & 0xff) << 8); // green
        argb += (((int) grad & 0xff) << 16); // red
        return argb;
    }  
    /**
     * высчитываем инкремент для широты
     * 
     * @param accuracy точность определения инкремента
     * @return double
     */
    public static double getIncX(double accuracy){
        return (1/(111.1348555))*accuracy;
    }
    /**
     * высчитываем инкремент для долготы
     * 
     * @param x координата широты
     * @param accuracy точность определения инкремента
     * @return double
     */
    public static double getIncY(double x, double accuracy){
        return (1/(111.3*Math.cos(x/57.2958)))*accuracy;  // minX/57.2958 - градус переводим в радиан
    }
    
}

