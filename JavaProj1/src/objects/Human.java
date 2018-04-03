package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// объект типа человек
public class Human extends UniqueObject{
    String surname;
    String name;
    int age;
    int width;
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    public void constructText(){
        System.out.println("Object typeOf Human was created!");
    }
        
    public Human() {
        super();
        ForcedConstruct();
        constructText();
    }
    
    public void KillObject(){
        try {
            this.finalize();
        } catch (Throwable e) {
            log.log(Level.WARNING, "Cant Kill Object, " + this.getClass().getName() + " " + e);
        }
        log.log(Level.FINE, "Object was Killed.");
    }
    
    @Override
    protected void ForcedConstruct(){        
        Scanner in = new Scanner(System.in); 
        System.out.print("Write Name of Object: ");
        this.name = String.valueOf(in.nextLine());
        System.out.print("Write Age of Object: ");
        this.age = Integer.valueOf(in.nextLine());
        System.out.print("Write Width of Object: ");
        this.width = Integer.valueOf(in.nextLine());
        System.out.println("Write Surname of Object: ");
        this.surname = String.valueOf(in.nextLine());
    }
    
    public Human(String name, String surname, int age, int width){
        setName(name);
        setSurname(surname);
        setAge(age);
        setWidth(width);
        
        constructText();
    }
    
    @Override
    public void WriteAllParam(){
        System.out.println(this.getClass().getName());
        List<List<Object>> list = getAllParam();
        for (List<Object> list2 : list){
            if (!list2.isEmpty()) {
                System.out.println(list2.get(1) + ": " + list2.get(0));
            }
        }
    }
    
    @Override
    public List<List<Object>> getAllParam() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getName());
        list.get(list.size()-1).add("Name");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getSurname());
        list.get(list.size()-1).add("Surname");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getAge());
        list.get(list.size()-1).add("Age");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getWidth());
        list.get(list.size()-1).add("Width");
        
        return list;
    }

    public void setSurname (String s){
        this.surname = s;
    }
    public String getSurname (){
        return this.surname;
    }
    protected void setName (String s){
        this.name = s;
    }
    public String getName (){
        return this.name;
    }
    protected void setAge (int s){
        this.age = s;
    }
    public int getAge (){
        return this.age;
    }
    protected void setWidth (int s){
        this.width = s;
    }
    public int getWidth (){
        return this.width;
    }

    
}
