package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Http {

    public static void main(String[] args){
        Http http = new Http();

        try {
            http.func();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void func() throws ParseException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String key = "AIzaSyBfB-K5jOzEUMfgKw0p6HhM0T5J8xNlEuw";
        String url3 = "https://www.googleapis.com/drive/v3/about?fields=user&key=" + key;

        //import org.json.simple.JSONObject;
        //String json = "{paramsArray: [\"first\", 100],"
             //   + "paramsObj: {one: \"two\", three: \"four\"},"
             //   + "paramsStr: \"some string\"}";

        //JSONParser parser = new JSONParser();

        //Object obj = parser.parse(json);
        JSONObject resultJson = new JSONObject();

        /*resultJson.put("name","foo");
        resultJson.put("num",new Integer(100));
        resultJson.put("is_vip",new Boolean(true));
        resultJson.put("nickname",null);*/

        System.out.print(resultJson.toString());

        HttpResponse response;
        try
        {
            HttpPost request = new HttpPost(url3);
            StringEntity params = new StringEntity(resultJson.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
            // handle response here...

            response = httpClient.execute(request);
        }
        catch (Exception ex)
        {
            // handle exception here
        }
        finally
        {
            //httpClient.close();
        }
// {"name": "foo", "num": 100, "is_vip": true, "nickname: null}
    }
}
