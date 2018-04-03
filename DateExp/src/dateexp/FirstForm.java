package dateexp;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DateFormat;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FirstForm extends JFrame{
    private JButton jButton3 = new JButton();
    Incr increment;
    
    private JTextField jTextField2 = new JTextField();
    private JLabel jLabel6 = new JLabel();
    private JComboBox jComboBox1 = new JComboBox();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel1 = new JLabel();
    
    private static final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
    private JTextField jTextField1 = new JTextField();
    private JButton jButton1 = new JButton();

    private enum Incr{
        one_day, two_day, tree_day, four_day, five_day;
        @SuppressWarnings("compatibility:-8444436833617110484")
        private static final long serialVersionUID = 1L;
    }
    
    public FirstForm() {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( ( screenSize.width - frameSize.width ) / 2, ( screenSize.height - frameSize.height ) / 2 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible(true);
        
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout( null );
        this.setSize(new Dimension(300, 150));

        jButton3.setText(" -> текущая дата + 1 день <-");
        jButton3.setBounds(new Rectangle(15, 10, 230, 20));
        jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton3_actionPerformed(e);
                }
            });
        System.out.println(Thread.currentThread().getName());
        jTextField2.setBounds(new Rectangle(15, 40, 135, 20));
        jLabel6.setText("_______");
        jLabel6.setBounds(new Rectangle(155, 70, 130, 15));
        jComboBox1.setBounds(new Rectangle(165, 40, 110, 20));
        jComboBox1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jComboBox1_itemStateChanged(e);
                }
            });
        this.getContentPane().add(jButton1, null);
        this.getContentPane().add(jTextField1, null);
        this.getContentPane().add(jLabel1, null);
        this.getContentPane().add(jLabel7, null);
        this.getContentPane().add(jComboBox1, null);
        this.getContentPane().add(jLabel6, null);
        this.getContentPane().add(jTextField2, null);
        this.getContentPane().add(jButton3, null);
        jComboBox1.setModel(new DefaultComboBoxModel(Incr.values()));
        jLabel7.setText("раб/вых");
        jLabel7.setBounds(new Rectangle(155, 95, 115, 15));
        jLabel1.setText("Полученная дата:");
        jLabel1.setBounds(new Rectangle(15, 70, 115, 15));
        jTextField1.setBounds(new Rectangle(15, 95, 20, 20));
        jButton1.setText("jButton1");
        jButton1.setBounds(new Rectangle(105, 95, 35, 20));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
    }
    

    private void jButton1_actionPerformed(ActionEvent e) {
        if (!clicked) jButton3_actionPerformed(e);
        int incrVal = Integer.parseInt(jTextField1.getText());
        Date Ldate = getNewDate();
        Date endDate = null;
        System.out.println("    StartDate: " + Ldate.toString());
        System.out.println( incrVal + "_day ->");
        Ldate = DateIncrement(getNewDate(), incrVal);
        System.out.println(WeekCheck(Ldate));
        endDate = HolidayCheck(Ldate);
        setNewDate(endDate);
        jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
        System.out.println(WeekCheck(endDate)?"Work_Day":"Day_Off");
    }
 
    private void jComboBox1_itemStateChanged(ItemEvent e) {
        Date Ldate = getNewDate();
        System.out.println("    StartDate: " + Ldate.toString());
        switch ((Incr) e.getItem()) {
        case one_day: {
                System.out.println("one_day ->");
                Ldate = DateIncrement(getNewDate(), 1);
                System.out.println(WeekCheck(Ldate));
                Ldate = HolidayCheck(Ldate);
                setNewDate(Ldate);
                jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
                System.out.println(WeekCheck(Ldate)?"Work_Day":"Day_Off");
                break;
            }
        case two_day: {
                System.out.println("two_day ->");
                Ldate = DateIncrement(getNewDate(), 2);
                System.out.println(WeekCheck(Ldate));
                jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
                Ldate = HolidayCheck(Ldate);
                setNewDate(Ldate);
                System.out.println(WeekCheck(Ldate)?"Work_Day":"Day_Off");
                break;
            }
        case tree_day: {
                System.out.println("tree_day ->");
                Ldate = DateIncrement(getNewDate(), 3);
                System.out.println(WeekCheck(Ldate));
                Ldate = HolidayCheck(Ldate);
                setNewDate(Ldate);
                jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
                System.out.println(WeekCheck(Ldate)?"Work_Day":"Day_Off");
                break;
            }
        case four_day: {
                System.out.println("four_day ->");
                Ldate = DateIncrement(getNewDate(), 4);
                System.out.println(WeekCheck(Ldate));
                Ldate = HolidayCheck(Ldate);
                setNewDate(Ldate);
                jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
                System.out.println(WeekCheck(Ldate)?"Work_Day":"Day_Off");
                break;
            }
        case five_day:
            {
                System.out.println("five_day ->");
                Ldate = DateIncrement(getNewDate(), 5);
                System.out.println(WeekCheck(Ldate));
                Ldate = HolidayCheck(Ldate);
                setNewDate(Ldate);
                jLabel7.setText(WeekCheck(Ldate)?"Рабочий":"Выходной");
                System.out.println(WeekCheck(Ldate)?"Work_Day":"Day_Off");
                break;
            }
        }
    }
    
    private Date DateIncrement(Date date, int incrVal){
        System.out.println("DateIncrement -> " + incrVal);
        if (incrVal > 1){            
            date = DateIncrement(date, incrVal - 1);
            date = DateIncrement(date, 1);
        } else if (incrVal == 1) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            System.out.println("    -OldDate: " + FORMAT.format(cal.getTime()));
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1);
            System.out.println("    -NewDate: " + FORMAT.format(cal.getTime()));
            return cal.getTime();
        } else {
            return date;
        }
        return date;
    }

    private Date getNewDate(){
        Date Ldate = null;
        try {
            Ldate = FORMAT.parse(jTextField2.getText());
        } catch (ParseException e) {
            System.out.print(e.getMessage());
        }
        return Ldate;
    }
    
    private void setNewDate(Date date){
        jLabel6.setText(FORMAT.format(date));
    }

    private Date HolidayCheck(Date date) {
        Date Ldate = date;
        while (!WeekCheck(Ldate)){
            Ldate = DateIncrement(Ldate, 1);
        }        
        return Ldate;
    }
    
    private boolean WeekCheck(Date date){
        System.out.print("WeekCheck ");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        System.out.println("DAY_OF_WEEK " + (cal.get(Calendar.DAY_OF_WEEK)-1 == 0 ? 7: String.valueOf(cal.get(Calendar.DAY_OF_WEEK)-1)));
        return dayOfWeek == 6 || dayOfWeek == 0 ? false : true;
    }

    private boolean clicked = false;
    private void jButton3_actionPerformed(ActionEvent e) {        
        System.out.println("incr");
        if (jTextField2.getText() == "" || jTextField2.getText().isEmpty()){ 
            jTextField2.setText(FORMAT.format(Calendar.getInstance().getTime()));
            clicked = true;
        } else {
            String InputDate = jTextField2.getText();
            Date d = null;
            try {
                d = FORMAT.parse(InputDate);
            } catch (ParseException f) {
                System.out.print(f.getMessage());
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1);
            jTextField2.setText(FORMAT.format(cal.getTime()));
        }
        
    }
}