package utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;

import javax.jws.WebMethod;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class CodeWebConnectionTest {
    private static final String Url = "http://srv-d-osb.acb.kz:8011/API_ClearJunction/Services/Proxy_Services/ClearJ_IBANReserve";
    private static final String code = "" +
        "{\n" + 
        "\"clientCustomerId\": \"c4b78t34qb678034qpn\",\n" + 
        "\"name\": \"Tangezi Gelashvily\",\n" + 
        "\"email\": \"tangezi.gelashvily@maildomain.com\",\n" + 
        "\"phoneNumber\": \"972541122334\",\n" + 
        "\"document\": [{\n" + 
        "\"type\": \"passport\",\n" + 
        "\"number\": \"AE111111\",\n" + 
        "\"issuedCountryCode\": \"GE\",\n" + 
        "\"issuedBy\": \"Tbilisi\",\n" + 
        "\"issuedDate\": \"2017-06-22\"\n" + 
        "}],\n" + 
        "\"dateOfBirth\": \"2017-02-22\",\n" + 
        "\"placeOfBirth\": \"Tbilisi\",\n" + 
        "\"address\": \"Some address\",\n" + 
        "\"customInfo\": {\n" + 
        "\"firstName\": \"Tangezi\",\n" + 
        "\"lastName\": \"Gelashvily\",\n" + 
        "\"localFirstName\": \"сссссс\",\n" + 
        "\"localLastName\": \"сссссЈсссс\"\n" + 
        "}\n" + 
        "}";
    
    
    public CodeWebConnectionTest() {
        super();
    }
    
    public static void main(String[] args) {
        CodeWebConnectionTest cwct = new CodeWebConnectionTest();

        try {
            cwct.getQuery(Url, code);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }
    
    public String getQuery(String wsURL, String XMLCode) throws MalformedURLException, IOException {
        //Code to make a webservice HTTP request
        String responseString = "";
        String outputString = "";
        System.out.println("code::\n" + XMLCode + "\n end code.");
        //String wsURL = "http://srv-osb.acb.kz:8011/IBSO_Adapter/Services/Proxy_Services/ACB_DICT_UPD_WSDL";
        URL url = new URL(wsURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection)connection;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        String xmlInput = XMLCode;
       
        byte[] buffer = new byte[xmlInput.length()];
        buffer = xmlInput.getBytes();
        bout.write(buffer);
        byte[] b = bout.toByteArray();
        // Set the appropriate HTTP parameters.
        
        httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        
        //httpConn.setRequestProperty("doCompliance", "true");
        
        httpConn.setRequestProperty("Date", a());
        httpConn.setRequestProperty("X-API-KEY", "730ee406-817e-11e7-bb31-be2e44b06b34");
        //httpConn.setRequestProperty("Authorization", "bearer ");        
        {
            String username = "bpm";
            String password = "4Bp_r#gwJ"; // "12345678";//
            if(username != null && password != null) {
                String encodedPassword = username + ":" + password;
                String encoded = Base64.encode(encodedPassword.getBytes());
                httpConn.setRequestProperty("Authorization", "bearer "+encoded);
            }
        }
        
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = httpConn.getOutputStream();
        //Write the content of the request to the outputstream of the HTTP Connection.
        out.write(b);

        out.close();
        //Ready with sending the request.
        //Read the response.
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
        } catch (IOException e) {
            httpConn.getErrorStream();
        }
        System.out.println("Response Message is '" + httpConn.getResponseMessage() + "'");
        
        // read message
       /* BufferedReader in = new BufferedReader(isr);
        //Write the SOAP message response to a String.
        while ((responseString = in.readLine()) != null) {
            outputString = outputString + responseString;
        }*/
        //System.out.println(fetchNormalize(outputString));
        if (outputString.contains("&lt;") || outputString.contains("&gt;"))
            outputString = fetchNormalize(outputString);
        System.out.println("Dictionary request was successful!");
        return outputString;
    }

    public String fetchNormalize(String response){        
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
        return response;    
    }
    
    public static String a(){
        Calendar cal = Calendar.getInstance();        
        Date date = cal.getTime();
        SimpleDateFormat dt1 = new SimpleDateFormat("YYYY-MM-DD'T'hh:mm:ss+06:00");
        String x = dt1.format(date).toString();
        return x;
    }
}
