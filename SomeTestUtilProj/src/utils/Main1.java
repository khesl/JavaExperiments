package utils;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main1 {
    public String info = "AED>>>Дирхам (ОАЭ)\n" + "BEF>>>Бельгийский франк\n";
    
    private String request = "insert into STAGING.acb_spr a (a.spr_name, a.VALUE, a.display, a.parent, a.is_active, a.description)";
    private String spr_name = "FT_MONEY";
    //public String 
    
    public Main1() {
        super();
    }

    public static void main(String[] args) {
        Main1 main1 = new Main1();
        
        // System.out.println(main1.convertUTF("http://www.google.com"));
        //System.out.println(main1.normalizeText("http://www.google.com"));

        System.out.println(fetchUnNormalize(""));
        
        //System.out.println(find("abcefdabc", "bc", 2));
        
        // генерация SQL запросов в БД по info формату данных
        /*List<String[]> str = main1.listBuilder();
        for (String[] s : str){
            //System.out.println(s[0] + "|" + s[1]);
            System.out.println(main1.request(s));
        }*/
        
        //c(); // тесты с датой
        /*
        for (int i =0;i<10;i++){
            int time = 500;
            int complexity = 1;
            System.out.print( ((Math.random()*100)*(complexity+10)/100)/100*500 + " ");
            /*if (Math.random() > 0.5){
                System.out.println("->" + ((Math.random()*10 * 0.6) * ((float)(100 - complexity)/100)));
                time += (int)(500 * (Math.random()*100 * 0.6) * ((100 - 1)/100));
            }
            else{
                System.out.println("<-" + (500 * (Math.random()*10 * 0.4) * ((float)complexity/100)));
                time -= (int)(500 * (Math.random()*100 * 0.4) * (1/100));
            }* /
            System.out.println(time + " ");
        }*/
        
        //getValidIin(10); // ищем валидный иин
        //getValidJurIin(10); // ищем валидный бин
        System.out.println(main1.d("031029551539"));
        // 940722300173 - проверка ИИН 870816350062
        //if (main1.d("610804451158")) System.out.println("valid");
        //else System.out.println("not valid");        
        //if (main1.checkIINValue("971204451158", "Женский", "04.12.1997")) System.out.println("valid");
        //generateIin(971204, "Женский");
        //generateIin(940722, "Мужской");
        //if (main1.checkIINValue("100101330071", "Мужской", "01.01.1910")) System.out.println("valid");
        //else System.out.println("not valid"); 
        
        /*String file = "hfi78796uyg878 78g76571432v8";
        System.out.println(file.replaceAll("[^\\d.]", ""));
        
        Long key = Long.parseLong(file.replaceAll("[^\\d.]", ""));
        System.out.println(key);

        Map<Long, String> files2 = new HashMap<Long, String>();
        files2.put(key, file);
        
        for(Map.Entry m: files2.entrySet()){
            System.out.println(m.getKey() + " - " + m.getValue());
        }*/
        //for (int i = 0; i<=10;i++)
            //System.out.println(main1.getDefaultDocCount());
        
        //checkPatternNumber("PHONE_INT", "+995(98)12345678");
                
        
    }
    private int defaultDocCount = 0;
    
    public void setDefaultDocCount(int defaultDocCount) {
        this.defaultDocCount = defaultDocCount;
    }
    public int getDefaultDocCount() {
        setDefaultDocCount(++defaultDocCount);
        return defaultDocCount;
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
           List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
           Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
               public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                   return (o1.getValue()).compareTo( o2.getValue() );
               }
           });

           Map<K, V> result = new LinkedHashMap<K, V>();
           for (Map.Entry<K, V> entry : list) {
               result.put(entry.getKey(), entry.getValue());
           }
           return result;
       }
    
    public String convertUTF(String s){
        final int[][] ranges = new int[][]{ 
                {  1024,  1279 }, 
                {  1280,  1327 }, 
                { 11744, 11775 }, 
                { 42560, 42655 },
            };
        StringBuilder b = new StringBuilder();

        for( char c : s.toCharArray() ){
            System.out.print((int)c + " ");
            int[] insideRange = null;
            for( int[] range : ranges ){
                if( range[0] <= c && c <= range[1] ){
                    insideRange = range;
                    System.out.println(Integer.toHexString(c));
                    break;
                }
            }

            if( insideRange != null ){
                b.append( "\\u0" ).append( Integer.toHexString(c) );
            }else{
                b.append( c );
            }
        }
        return b.toString();
    }
    
    private static int find (String str, String searchElem){
        boolean isFind = false;
        for (int i = 0; i < str.length() - searchElem.length() + 1; i++){
            if (str.substring(i, i + searchElem.length()).equals(searchElem)) isFind = true;
            if (isFind) return i;
        }        
        return -1;
    }    
    private static int find (String str, String searchElem, int count){
        int count_ = 0;
        int increase = 0;
        int val = -1;
        for (int i = 0; i < count; i++){

            System.out.println("search str: " + str);
            val = find (str, searchElem);
            System.out.println("search val: " + val);
            if (val == -1) return -1;
            count_++;
            str = str.substring(val + searchElem.length());
            increase += searchElem.length();
        }     
        return val + increase;
    }
    
    public static String normalizeText(String value){
        String outValue = "";
        char[] array = value.toCharArray();
        for (char arr : array){        
            outValue += (int)arr;
        }
        return outValue;    
    }
    
    public List<String[]> listBuilder(){
        //List<List<String>> srts = new ArrayList<List<String>>();
        String request = info;
        List<String[]> str = new ArrayList<String[]>();
        String[] records= request.split("\n");
        for (String record : records){
            String[] parts = record.split(">>>");
            //System.out.println(parts[0] + ", and value "+ parts[1]);
            str.add(parts);
        }
        return str;
    }
    public String request(String[] str){
        if (str.length > 1)            
            return request.concat(" values ('" + spr_name + "', '" + str[0] + "', '" + str[1] + "', null, 'true', null);");
        return "";
    }
    
    public static void c(){
        try { 
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse("22.07.1994");
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            String data = String.format("%td%tm%ty", c, c, c);
            //int data = Integer.valueOf(String.format("%tY", c));
            //String data = String.format("%tY", c);
            System.out.println(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void a(Date value){
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        System.out.println(String.valueOf(c.get(Calendar.YEAR)).substring(2) + (Integer)(c.get(Calendar.MONTH) + 1) + c.get(Calendar.DAY_OF_MONTH));
    }
    public static void b(Date value){
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        String data = String.format("%ty%tm%td", c, c, c);
        System.out.println(data);
    }
    
    public static void getValidIin(int size) {
        int v1_2, v3_4, v5_6, v7, v8_9_10_11, v12;
        String iin = "000000000000";
        int count = 0;
        for (v1_2 = 10; v1_2 < 100; v1_2++)
            for (v3_4 = 1; v3_4 < 13; v3_4++)
                for (v5_6 = 1; v5_6 < 29; v5_6++)
                    for (v7 = 3; v7 <= 4; v7++)
                        for (v8_9_10_11 = 3000; v8_9_10_11 < 9999; v8_9_10_11++)
                            for (v12 = 1; v12 <= 9; v12++) {
                                iin = String.valueOf(v1_2) + 
                                        (v3_4 < 10 ? "0" + String.valueOf(v3_4) : v3_4) + 
                                        (v5_6 < 10 ? "0" + String.valueOf(v5_6) : v5_6) + 
                                        String.valueOf(v7) + 
                                        String.valueOf(v8_9_10_11) + 
                                        String.valueOf(v12);
                                if (d(iin)){
                                    count++;
                                    System.out.println(count + "\tiin = '" + iin + "'");
                                    if (count > size) return;
                                }
                            }
    }
    public static void getValidJurIin(int size) {
        int v1_2, v3_4, v5, v6_7, v8_9_10_11, v12;
        String iin = "000000000000";
        int count = 0;
        for (v1_2 = 10; v1_2 < 100; v1_2++)
            for (v3_4 = 1; v3_4 < 13; v3_4++)
                for (v5 = 4; v5 < 6; v5++)
                    for (v6_7 = 0; v6_7 <= 99; v6_7++)
                        for (v8_9_10_11 = 3000; v8_9_10_11 < 9999; v8_9_10_11++)
                            for (v12 = 1; v12 <= 9; v12++) {
                                iin = String.valueOf(v1_2) + 
                                        (v3_4 < 10 ? "0" + String.valueOf(v3_4) : v3_4) + 
                                        String.valueOf(v5) + 
                                        (v6_7 < 10 ? "0" + String.valueOf(v6_7) : v6_7) + 
                                        String.valueOf(v8_9_10_11) + 
                                        String.valueOf(v12);
                                if (d(iin)){
                                    count++;
                                    System.out.println(count + "\tiin = '" + iin + "'");
                                    if (count > size) return;
                                }
                            }
    }
    public static void generateIin(int dateNum, String gender){
        int count = 0;
        int v7 = 0, v8_9_10_11, v12;
        String iin = "000000000000";
        int data = 0;
        if ((int)(dateNum/10000) > 17)  data = 1900 + (int)(dateNum/10000);
        else data = 2000 + (int)(dateNum/10000);
            if (gender == "Мужской") {
                if (data <= 1900)
                    v7 = 1;
                else if (data <= 2000)
                    v7 = 3;
                else if (data <= 2100)
                    v7 = 5;
            }
            if (gender == "Женский") {
                if (data <= 1900)
                    v7 = 2;
                else if (data <= 2000)
                    v7 = 4;
                else if (data <= 2100)
                    v7 = 6;
            }
        for (v8_9_10_11 = 10; v8_9_10_11 < 100; v8_9_10_11++)
            for (v12 = 1; v12 <= 9; v12++) {
                iin = String.valueOf(dateNum) +
                        String.valueOf(v7) + 
                      (v8_9_10_11 < 1000 ? v8_9_10_11 < 100 ? "00" + String.valueOf(v8_9_10_11) : "0" + String.valueOf(v8_9_10_11) : String.valueOf(v8_9_10_11)) + 
                        String.valueOf(v12);
                if (d(iin)){
                    count++;
                    System.out.println(count + "\tiin = '" + iin + "'");
                    //if (count > size) return;
                }
            }
    }
    
    private boolean checkIINValue(String iin, String gender, String dataB){
        // проверка № 1
        if (iin.length() > 12) return false;
        if (iin != "000000000000"){
            char[] arr = iin.toCharArray();
            // проверка № 3
            if ((arr[6] == '7') || (arr[6] == '8') || (arr[6] == '9') || (arr[6] == '0'))
                return false;
            
            if (dataB != null) {
                Date date;
                Calendar c = Calendar.getInstance();
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dataB);
                    c.setTime(date);
                } catch (ParseException e) {
                    System.out.println("Data Parse Problem in 'checkIINValue'");
                }
                // проверка № 2
                {
                    //"YYMMDD000000"
                    String data = String.format("%ty%tm%td", c, c, c);
                    String cond = iin.substring(0, 6);
                    if (!cond.equals(data)){
                        return false;
                    }
                }
                // проверка № 4
                {
                    int data = Integer.valueOf(String.format("%tY", c));
                    boolean reason = false;
                    if (gender == "Мужской") {
                        if (data <= 1900)
                            if (arr[6] == '1') reason = true;
                        if (data <= 2000)
                            if (arr[6] == '3') reason = true;
                        if (data <= 2100)
                            if (arr[6] == '5') reason = true;
                    }
                    if (gender == "Женский") {
                        if (data <= 1900)
                            if (arr[6] == '2') reason = true;
                        if (data <= 2000)
                            if (arr[6] == '4') reason = true;
                        if (data <= 2100)
                            if (arr[6] == '6') reason = true;
                    }
                    if (!reason) return false;
                }
            }
            // проверка № 5
            {
                if (!d(iin)) return false;
            }
        }
        return true;
    }
    // проверка ИИН/БИН на корректность по весам, проверка №5
    public static boolean d(String iin){
        //String iin = "940722300173";
        if (iin.length() > 12) return false;
        {
            int[] intIin = new int[12];
            int i = 0; 
            for (char ch : iin.toCharArray()){
                intIin[i] = Integer.valueOf(String.valueOf(ch));
                i++;
            }
            int summ = 0;
            int summStep2 = 0;
            for (int j = 0;j < 11; j++)
                summ += intIin[j]*(j+1);
            int r = summ%11;
            if (r == 10){
                for (int j = 0;j < 11; j++){
                    int weight = (j+1+2)%11;
                    if (weight == 0) weight = 11;
                    summStep2 += intIin[j]*(weight);
                }
                if (summStep2%11 == intIin[11]) return true;
            }
            if (summ%11 == intIin[11]) return true;
        }
        return false;
    }
    
    public boolean checkJurIINValue(String iin, String dataB){
        // проверка № 1
        if (iin.length() > 12) return false;
        if (iin != "000000000000"){
            char[] arr = iin.toCharArray();
            // проверка № 3
            if ((arr[6] == '7') || (arr[6] == '8') || (arr[6] == '9') || (arr[6] == '0'))
                return false;
                
            if (dataB != null) {
                Date date;
                Calendar c = Calendar.getInstance();
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dataB);
                    c.setTime(date);
                } catch (ParseException e) {
                    System.out.println("Data Parse Problem in 'checkIINValue'");
                }
                // проверка № 2
                {
                    //"YYMMDD000000"
                    String data = String.format("%ty%tm", c, c);
                    String cond = iin.substring(0, 4);
                    if (!cond.equals(data)){
                        return false;
                    }
                }  
                // проверка на резиденство № 3
                
                // проверка № 4
                {
                    if (!d(iin)) return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkPatternNumber(String type, Object object){
        boolean reason = false;
        String message = "";
        
        System.out.println("  uIComponent(ContactType) = " + type);
        //System.out.println("  ContactType = " + dataManipulator.getValueAttribute("ContactType1"));
        if (type.equals("PHONE")) {
            System.out.println("here ->");
            Pattern p = Pattern.compile("^[0-9]{10}"); //^[0-9]{10}  ^\d{10}$
            Matcher m = p.matcher(object.toString());
            if (m.matches()){
                reason = true;
                System.out.println("here -->");
            } else {
                message = "Wrong number, example: '1234567890'";
            }
        } else
        if (type.equals("MAIL")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+[.]{1}[a-z]{2,6}$");
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong E-mail, example: 'test@domain.kz'";
            }
        } else
        if (type.equals("WEB")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^https?+(\\:\\/\\/)?+[a-z0-9._]+[.]{1}[a-z0-9.-]+[.]{1}[a-z]{2,6}$");
            Matcher m = p.matcher(object.toString());
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Web address, example: 'http://www.domain.kz'";
            }
        } else
        if (type.equals("FTP")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^ftp?+(\\:\\/\\/)?+[a-z0-9._%+-]+[.]{1}[a-z0-9.-]+[.]{1}[a-z]{2,6}$");
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Ftp, example: 'ftp://ftp.domain.kz'";
            }
        } else
        if (type.equals("PHONE_HOME")){
            System.out.println("here h->");
            Pattern p = Pattern.compile("^\\([7][12][0-9]{1,3}\\)[0-9]{5,7}$"); // (7[1-2]{1}[0-9]{0,2})
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Tel. number, example: '(7172)123456'";
            }
        } else
        if (type.equals("PHONE_MOBILE")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^\\(7[04567][0-9]{1}\\)[0-9]{7}$");
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Tel. number, example: '(701)1234567'";
            }
        } else
        if (type.equals("PHONE_WORK")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^\\(7[12]\\d{1,3}\\)\\d{5,7}$");
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Tel. number, example: '(727)1234567'";
            }
        } else
        if (type.equals("PHONE_INT")){
            System.out.println("here ->");
            Pattern p = Pattern.compile("^\\+[0-9]{1,4}\\([0-9]{2,5}\\)[0-9]{4,9}$");
            Matcher m = p.matcher(object.toString());  
            if (m.matches()){
                reason = true;
            } else {
                message = "Wrong Tel. number, example: '+995(98)12345678'";
            }
        } else if ((type.equals("SMARTPAY_USERID")) || (type.equals("FAX")) ||
                   (type.equals("TELEX")) || (type.equals("TELETYPE")) ||
                   (type.equals("TELEGRAPH")) || (type.equals("TEKOC")) ||
                   (type.equals("QIWI_WALLETID")) || (type.equals("WALLETONE_USERID"))){
            reason = true;
        }
        if (!reason){
            System.out.println(" reason = " + reason + ", mesage:" + message);
        }
        return reason;
    }
    
    private static String fetchNormalize(String response) {
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
        return response;
    }
    private static String fetchUnNormalize(String response) {
        response = response.replaceAll("<", "&lt;");
        response = response.replaceAll(">", "&gt;");
        return response;
    }
    private static String delMetaSymbol(String string){
        string = string.replaceAll("\n", "");
        string = string.replaceAll(";", "");
        return string;
    }
    private static int searchSymbol(String string, String searchSeq){
        int count = 0;
        while (string.contains(searchSeq)){
            count++;
            string = string.substring(find(string, searchSeq)+1);
            if (string.length() < 0) return count;
        }
        return count;
    }
}
