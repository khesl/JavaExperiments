package dateexp;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;


public class Main {

    public Main() {
        super();
    }

    public static void main(String[] args) {
        //FirstForm form = new FirstForm();   // создаёт объект формы и всё что там.   
        
        Date date = Calendar.getInstance().getTime();
        //Calendar c = Calendar.getInstance().getTime();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
        } catch (ParseException e) {
        }
        System.out.println(date.getTime());  
        System.out.println(nextMonth(date));  
    }
    
    
    private static Date nextMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, +1);
        Date nextDate = c.getTime();
        return nextDate;
    }
    
    
    public static XMLGregorianCalendar a(Date value) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        XMLGregorianCalendar x = null;
        try {
            x = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            x.setDay(c.get(Calendar.DAY_OF_MONTH));
            x.setMonth(c.get(Calendar.MONTH) + 1);
            x.setYear(c.get(Calendar.YEAR));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return x;
    }
}
