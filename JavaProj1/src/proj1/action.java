package proj1;

import forms.MainFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import objects.*;
import objects.Creator;

public class action {
    UniqueObject localObj;
    ArrayList<UniqueObject> obj = new ArrayList<UniqueObject>();
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    //Logger log;
    private int objId = 0;
    
    public action() {
        super();
        //log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    class ConcreteCreatorHuman extends Creator {
        @Override
        public UniqueObject factoryMethod() { return new Human(); }
    }
    class ConcreteCreatorCat extends Creator {
        @Override
        public UniqueObject factoryMethod() { return new Cat(); }
    }
    class ConcreteCreatorZombi extends Creator {
        @Override
        public UniqueObject factoryMethod() { return new Zombi(); }
    }
    class ConcreteCreatorSpider extends Creator {
        @Override
        public UniqueObject factoryMethod() { return new Spider(); }
    }
    
    public void LogWrite(TypeLogObj command, Object object) {
        try {
            switch (command) {
            case start:
                log.log(Level.INFO, "Element: " + object.getClass().getName() + " was Started."); break;
            case close:
                log.log(Level.INFO, "Element: " + object.getClass().getName() + " was closed."); break;
            case dataFailed:
                log.log(Level.WARNING, "Data from: " + object.getClass().getName() + " wrong. Please check the data validity."); break;
            default:
                log.log(Level.WARNING, "Unknown type command: " + command);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Unknown command: " + command);
        }
    }
    public void LogWrite(TypeLogObj command) {
        try {
            switch (command) {
            case start:
                log.log(Level.INFO, "Element was Started."); break;
            case close:
                log.log(Level.INFO, "Element was closed."); break;
            case dataFailed:
                log.log(Level.WARNING, "Data from: wrong. Please check the data validity."); break;
            default:
                log.log(Level.WARNING, "Unknown type command: " + command);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Unknown command: " + command);
        }
    }

    // сюда добавлять все типы объектов, пока не изменится структура    
    // System.out.println(Arrays.toString(TypeObj.values())); 
    //          - метод для просмотра всех элементов
    private enum TypeObj {
        human, cat, zombi, spider, unknown, exit;
        @SuppressWarnings("compatibility:-1715697748613507675")
        private static final long serialVersionUID = 1L;
    }
    
    /* структура для создания простой системы логирования
     * записываются все коды ошибок, и переопределяется вывод
     * в функции LogWrite() */
    private enum TypeLogObj {
        start, close, unknown, dataFailed;
        @SuppressWarnings("compatibility:-1081464239410447183")
        private static final long serialVersionUID = 1L;
    }
 
    // функция некой консоли(меню?) с коммандами   
    public boolean initAction(){
        String command = String.valueOf("");
        while (!command.equalsIgnoreCase("exit")){
            System.out.print("Write your command: ");
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            boolean exit = false;
            if (command.equalsIgnoreCase("InitMainForm")){ 
                System.out.println("Not realized Form system.");
                /*MainFrame frame = new MainFrame(this);  
                try {
                    frame.jbInit();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            if (command.equalsIgnoreCase("init")){    
                boolean suc = false;
                Creator creator = null;
                while (!suc) {
                    System.out.print("Write type of Object: ");
                    String type = String.valueOf(in.nextLine()).toLowerCase();
                    TypeObj typeO = TypeObj.valueOf("unknown");
                    try {
                        typeO = TypeObj.valueOf(type);
                        suc = true;
                    } catch (Exception e) {
                        log.log(Level.INFO, "try to create typeOfObject with type: " + type);
                    }
                    switch (typeO) {
                    case human: creator = new ConcreteCreatorHuman(); break;
                    case cat: creator = new ConcreteCreatorCat(); break;
                    case zombi: creator = new ConcreteCreatorZombi(); break;
                    case spider: creator = new ConcreteCreatorSpider(); break;
                    case exit: exit = true; break;
                    default:
                        {
                            System.out.println("Unknown Type, try more!");
                            System.out.println("You can choose one of the next values:");
                            System.out.println(Arrays.toString(TypeObj.values()));
                            suc = false;                            
                        }
                    }
                }
                if (!exit){
                    localObj = creator.factoryMethod();
                    obj.add(localObj);
                    objId = obj.lastIndexOf(localObj);
                    System.out.println("Object with type '" + getObj(objId).getClass().getName() +
                                       "' and ID = '" + objId + "' was created!");
                }
            }
            if (command.equalsIgnoreCase("view")){
                if (obj.isEmpty()){
                    System.out.println("No one Object is created!");
                }else { 
                    System.out.print("Write Object Id you want to see (last Id is " + objId +  "): ");
                    try {
                        int localId = Integer.valueOf(in.nextLine());
                        if (localId > objId) {
                            localId = lastIndexObj();
                            System.out.println("your Id too Much, try last of index: " + localId);
                        }
                        getObj(localId).WriteAllParam();
                    } catch (Exception e) {
                        log.log(Level.SEVERE, "problem with viewing of object: " + e);
                    }
                }
            }
            if (command.equalsIgnoreCase("kill")){
                if (obj.isEmpty()){
                    System.out.println("No one Object is created!");
                }else { 
                    System.out.print("Write Object Id you want to kill (last Id is " + objId +  "): ");
                    try {
                        int localId = Integer.valueOf(in.nextLine());
                        if (localId > lastIndexObj()) {
                            localId = obj.size() - 1;
                            System.out.println("your Id too Much, try last of index: " + localId);
                            System.out.print("Do you want to kill last Object? (y/n): ");
                            String chooseVal = String.valueOf(in.nextLine()).toLowerCase();
                            boolean choose = false;
                            if (("y").equals(chooseVal) || ("yes").equals(chooseVal)) choose = true;
                            else if (("n").equals(chooseVal) || ("no").equals(chooseVal)) choose = false;
                            if (!choose) {
                                System.out.print("Return to main console!");
                                continue;
                            }
                        }
                        System.out.println(objId + " " + localId);
                        killObj(localId).KillObject();
                        objId = lastIndexObj();
                        System.out.println(objId + " " + localId);
                        System.out.println("norm?");
                    } catch (Throwable e) {
                        log.log(Level.SEVERE, "problem with killing of object: " + e);
                    }
                }
            }
            if (command.equalsIgnoreCase("viewLog")){
                System.out.println("shy probnem posmotret Logi");
            }
            if (command.equalsIgnoreCase("help")){ 
                System.out.println("    list of available command:");
                System.out.println("init - initialize new object;");
                System.out.println("view - viewing initialized object;");
                
                System.out.println("exit - out from the programm.");                
            }
        }
        return true;        
    }
    
    public int getLastIdObj(){
        return objId;
    }    
    private UniqueObject getObj(int objId){
        return obj.get(objId);
    }
    private UniqueObject killObj(int objId){
        return obj.remove(objId);
    }
    private int lastIndexObj(){
        return obj.size()-1;
    }

}
