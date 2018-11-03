package main;

import org.json.JSONObject;
import org.json.XML;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    public static String jsontoxml(String text){
        try {
            JSONObject json = new JSONObject(text);
            String xml = XML.toString(json);
            return xml;
        } catch (Exception e){
            return "<error>wrong json format</error>";
        }
    }
    public static String xmltojson(String text){
        try {
            Pattern MY_PATTERN = Pattern.compile("(xmlns(:\\w{2,6}|)=\"\")|(xmlns(:\\w{2,6}|)=\".+?\")");
            Matcher m = MY_PATTERN.matcher(text);
            while (m.find()) {
                System.out.println(m.groupCount());
                String s = m.group(0);
                text = text.replace(s, "");
                System.out.println("contain: " + s);
                // s now contains "BAR"
            }

            JSONObject json = XML.toJSONObject(text);
            String json_ = json.toString();
            return json_;
        } catch (Exception e){
            e.printStackTrace();
            return "{\"error\":\"wrong xml format\"}";
        }
    }

}
