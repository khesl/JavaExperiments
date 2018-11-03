package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;


public class Main {    
    private final String USER_AGENT = "Mozilla/5.0";

    
    public Main() {
        super();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    

        System.out.println("Testing 1 - Send Http GET request");
        //main.sendGet();
        main.mySend2();
        
        //https://www.google.com/speech-api/v1/recognize
    }
    
    private void mySend() throws IOException {
        //String key = "AIzaSyAqk7vE0vQDR3JItUPgFp6bcPqgJz8h8tI";
        String key = "AIzaSyBfB-K5jOzEUMfgKw0p6HhM0T5J8xNlEuw";
        String url = "http://www.google.com/speech-api/v2/recognize?output=json&lang=ru-ru&key=";
        String url3 = "http://www.google.com/speech-api/v2/recognize";
        
        URL obj = new URL(url+key);
        
        File file = new File("C:\\Users\\vassina\\Desktop\\project\\JavaExperiments\\speechApi\\src\\client\\test1.flac");
        InputStream is = new FileInputStream(file);
        BufferedReader audioFile =
            new BufferedReader(new InputStreamReader(is));
                
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestProperty("Content-Type", "audio/x-flac;rate=16000");//44100
        con.setRequestProperty("output", "json");
        con.setRequestProperty("lang", "ru-ru");
        con.setRequestProperty("key", key);
        con.setRequestProperty("app", audioFile.toString());
    
        int responseCode = con.getResponseCode();
        System.out.println("Content : " + con.toString());
        System.out.println("Content-type : " + con.getContentType().toString());
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
            new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }
    
    
    private void mySend2() throws IOException {
        org.apache.http.client.HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        
        String key = "AIzaSyBfB-K5jOzEUMfgKw0p6HhM0T5J8xNlEuw";
        String url = "http://www.google.com/speech-api/v2/recognize?output=json&lang=ru-ru&key=";
        String url3 = "http://www.google.com/speech-api/v2/recognize";
        
        File file = new File("C:\\Users\\vassina\\Desktop\\project\\JavaExperiments\\speechApi\\src\\client\\test1.flac");
        InputStream is = new FileInputStream(file);
        BufferedReader audioFile =
            new BufferedReader(new InputStreamReader(is));
        
        JSONObject json = new JSONObject();
        json.put("output", "json");
        json.put("lang", "ru-ru");
        json.put("key", key);
        json.put("app", audioFile.toString());

        HttpResponse response;
        try {
            HttpPost request = new HttpPost(url3);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "audio/x-flac;rate=16000");//44100
            request.setEntity(params);
            //System.out.println(request.getParams().getParameter(key).toString());
            
            response = httpClient.execute(request);
            
            //httpClient.execute(request);
        // handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            //httpClient.close();
        }
    }
    
  /*  public void QuickstartSample() throws Exception {
        // Instantiates a client
        SpeechClient speech = SpeechClient.create();

        // The path to the audio file to transcribe
        String fileName = "./resources/audio.raw";

        // Reads the audio file into memory
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        ByteString audioBytes = ByteString.copyFrom(data);

        // Builds the sync recognize request
        RecognitionConfig config = RecognitionConfig.newBuilder()
            .setEncoding(AudioEncoding.LINEAR16)
            .setSampleRateHertz(16000)
            .setLanguageCode("en-US")
            .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder()
            .setContent(audioBytes)
            .build();

        // Performs speech recognition on the audio file
        RecognizeResponse response = speech.recognize(config, audio);
        List<SpeechRecognitionResult> results = response.getResultsList();

        for (SpeechRecognitionResult result: results) {
          List<SpeechRecognitionAlternative> alternatives = result.getAlternativesList();
          for (SpeechRecognitionAlternative alternative: alternatives) {
            System.out.printf("Transcription: %s%n", alternative.getTranscript());
          }
        }
        speech.close();
      }
*/
    
    // HTTP GET request

    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
            new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
    
    /*public static String GoogleSpeechRequest(String flacName, int sampleRate)
         {
           
           WebRequest request = WebRequest.Create("https://www.google.com/speech-api/v1/recognize?xjerr=1&client=chromium&lang=ru-RU");

           request.Method = "POST";
           
           byte[] byteArray = File.ReadAllBytes(flacName);

           // Set the ContentType property of the WebRequest.
           request.ContentType = "audio/x-flac; rate=" + sampleRate; //"16000";        
           request.ContentLength = byteArray.Length;

           // Get the request stream.
           Stream dataStream = request.GetRequestStream();
           // Write the data to the request stream.
           dataStream.Write(byteArray, 0, byteArray.Length);
           
           dataStream.Close();

           // Get the response.
           WebResponse response = request.GetResponse();

           dataStream = response.GetResponseStream();
           // Open the stream using a StreamReader for easy access.
           StreamReader reader = new StreamReader(dataStream);
           // Read the content.
           string responseFromServer = reader.ReadToEnd();

           // Clean up the streams.
           reader.Close();
           dataStream.Close();
           response.Close();

           return responseFromServer;
         }*/
    
}
