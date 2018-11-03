package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClearJunctionRest {
    public ClearJunctionRest() {
        super();
    }

    public static void main(String[] args) {
        ClearJunctionRest clearJunctionRest = new ClearJunctionRest();
        try {
            System.out.println("start");
            clearJunctionRest.getResponse("https://maps.googleapis.com/maps/api/elevation/json?locations=43.15,77.085&key=AIzaSyBfB-K5jOzEUMfgKw0p6HhM0T5J8xNlEuw");
        } catch (Exception e) {
        }
    }
    
    /**
     * делаем запрос в Google по переданным данным, 
     * и возвращаем распаренную высоту
     * 
     * @param url запрос
     * @return String
     * @exception Exception
     */
    public String getResponse(String url) throws Exception {
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
        System.out.println(response.toString());
        
        //Read JSON response and print
        JSONParser parser = new JSONParser();
        JSONObject myResponse = (JSONObject) parser.parse(response.toString());
        //JSONObject myResponse = new JSONObject(response.toString());
        JSONArray resp_Ar = (JSONArray)parser.parse(myResponse.get("results").toString());
        JSONObject resp_ = (JSONObject)parser.parse(resp_Ar.get(0).toString());
        return resp_.get("elevation").toString();
    }
}
