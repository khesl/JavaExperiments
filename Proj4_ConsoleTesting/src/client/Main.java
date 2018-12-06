package client;

import client.webServiceDep.DictRequestWebService;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import javax.faces.model.SelectItem;

import javax.jws.WebMethod;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;



public class Main implements Comparable{
    TypeObj typeObj;
    int x;
    
    public Main() {
        super();
    }

    public int compareTo(Object o) {
        Main lMain = (Main) o;
        return (this.x == lMain.x ? 0 : this.x > lMain.x ? 1 : -1);
    }


    private enum TypeObj {
        start, close, unknown, dataFailed;
        @SuppressWarnings("compatibility:5626581587423932349")
        private static final long serialVersionUID = 1L;
    }

    class StringLengthComparator implements Comparator{
        public int compare(Object o1, Object o2) {
            return String.valueOf(o1).length() - String.valueOf(o2).length(); 
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Runnable(){
            public void run(){
            
            }
        });
        
       /* int INCREASE = 100000;
        int pureINC = 0;
        int perc = 14;
        double x = 0;
        
        for (int i=1; i<=12; i++){
            x += x*(perc/100.00)/12 + INCREASE;
            pureINC += INCREASE;
            System.out.println("val in " + i + " month " + String.valueOf(x) + ", and pure " + pureINC);
        }*/
        
     /*
        DiplomaProj main = new DiplomaProj();
        main.x = 4;
        DiplomaProj main2 = new DiplomaProj();
        main2.x = 13 >> 1;

        
        System.out.println(main2.x + " " + main2.compareTo(main));*/
    }
    
    public void serviceCall() throws MalformedURLException, IOException {
       // создаем ссылку на wsdl описание
       URL url = new URL("http://host/AppDictService-DictService-context-root/Dictionary_SendServicePort?wsdl");
       // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
       // 1-ый аргумент смотрим в атрибуте targetNamespace
       // 2-ой аргумент смотрим в атрибуте name
       QName qname = new QName("http://manualRequest.com.beans/", "Dictionary_SendService");
       Service service = Service.create(url, qname);
    //        System.out.println("serviceCall Message:" + service.;
       DictRequestWebService dictService = service.getPort(DictRequestWebService.class);
       System.out.println("serviceCall Message:");
       ArrayList<SelectItem> selectItems = new ArrayList<SelectItem>();
       List<SelectItem> outputItems = new ArrayList<SelectItem>();
       selectItems.add(new SelectItem("?", "CODESERVICE"));
       selectItems.add(new SelectItem("<request><spr>" + "CL_GROUP" + "</spr></request>", "DATAIN"));
       
       String str;
       str = stringBuilder(selectItems);
       System.out.println(str);
       str = dictService.callRequest4(str);
        System.out.println(str);
       outputItems = listBuilder(str);
       
       
       System.out.println("outputItems Message request:" + outputItems.size());
       for (SelectItem si : outputItems)
           System.out.println(si.getLabel() + " = " + si.getValue());  
     
    }
    
    public String stringBuilder(List<SelectItem> selectItems){
        String result = "";
        for (SelectItem si : selectItems)
            result += si.getLabel() + ">>>" + si.getValue() + "\n";
        return result;
    }
    
    /** Example:
     * CODESERVICE ?
     * DATAIN <request><spr>NAMES_CITY</spr></request>
     * */
    private List<SelectItem> listBuilder(String request){
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        String[] records= request.split("\n");
        for (String record : records){
            String[] parts = record.split(">>>");
            System.out.println(parts[0] + ", and value "+ parts[1]);
            selectItems.add(new SelectItem(parts[1], parts[0]));
        }
        return selectItems;
    }
        
}
