package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// объект типа кот
public class Cat extends UniqueObject{
    int lifeNum;
    String name;
    int age;
    int width;
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void constructText(){
        System.out.println("Object typeOf Cat was created!");
    }

    public Cat() {
        super();
        ForcedConstruct();
        constructText();
    }
    
    public void KillObject(){
        try {
            this.finalize();
        } catch (Throwable e) {
            log.log(Level.WARNING, "Cant Kill Object, "+ this.getClass().getName() + " " + e);
        }
        log.log(Level.FINE, "Object was Killed.");
    }
    
    public Cat(String name, int age, int width, int lifeNum){
        setName(name);
        setAge(age);
        setWidth(width);
        setLifeNum(lifeNum);
        
        constructText();
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
        System.out.println("Write LifeNum of Object: ");
        this.lifeNum = Integer.valueOf(in.nextLine());
    }
    
    public void changeBasicValue(String name, int age, int width){
        if (!name.isEmpty()) this.setName(name);
        if (age != 0) this.setAge(age);
        if (width != 0) this.setWidth(width);
    }
    
    public void WriteBasicParam(){
        if (!name.isEmpty())System.out.println("Name: " + name);
        if (age != 0) System.out.println("Age: " + age);
        if (width != 0) System.out.println("Width: " + width);
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
        list.get(list.size()-1).add(getAge());
        list.get(list.size()-1).add("Age");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getWidth());
        list.get(list.size()-1).add("Width");
        
        list.add(new ArrayList<Object>());
        list.get(list.size()-1).add(getLifeNum());
        list.get(list.size()-1).add("LifeNum");
        
        return list;
    }
    
    public void setLifeNum (int s){
        this.lifeNum = s;
    }
    public int getLifeNum (){
        return this.lifeNum;
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