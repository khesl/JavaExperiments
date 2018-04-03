package proj1;

import objects.Cat;
import objects.Human;
import objects.UniqueObject;

/**
 * первая попытка реализации консолького объекта. Реализован паттерн создания объектов через
 * Factory builder.
 * 
 * спустя 2 года после того как я это написал смотрится интересно..
 * 
 * 
 * @author VassinAK
 * */
public class MainClass {
    public MainClass() {
        super();
    }    

    public static void main(String[] args) {
        //MainClass mainClass = new MainClass();
        //Cat cat = new Cat("Nafanya", 17, 20);
        //Human man = new Human("Me", "Again", 22, 175);
        //UniqueObject obj = new Human();
        
        action action = new action();        
        action.initAction();
        
        //man.WriteAllParam();
        System.out.println("exit from initAction().");
        
        
        
    }
}


