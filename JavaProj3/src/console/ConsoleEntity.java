package console;

import client.AbstractTypeClass;
import client.MainTestThreadClass;
import client.commandClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import objects.TypeObj;

public class ConsoleEntity implements Runnable{
    public static final ConsoleEntity INSTANCE = new ConsoleEntity();
    private Thread T = new Thread(this, "Сonsole");
    //private UniqueObject localObj;
    private ArrayList<AbstractTypeClass> obj = new ArrayList<AbstractTypeClass>();
    Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private MainTestThreadClass main = new MainTestThreadClass();
    Map<String, Object> classMap = new HashMap<String, Object>();
    TypeLogObj logCommand;
    private int objID = 0;
    private boolean exit = false;
    private Scanner in;
    private consoleCommand command;
    private boolean isCommand = false;
    
    private ConsoleEntity() {
        T = new Thread(this, "Console");
        System.out.println("Console thread created: " + T);
        T.start(); 
    }
    
    public void run() {
        System.out.println("You are in Console Thread! " + Thread.currentThread().getName());
        while (!exit){
            
            if (T.interrupted()) OutcomeInterraction();    
            if (isCommand) OutcomeInterraction();
            lifeCycle();  
        }
    }  
    
    /* структура комманд консоли
     * добавлять сюда необходимые комманды для консоли для дальнейшей обработки
     * первый уровень*/
    public enum consoleCommand {
        start, enter, wait, init, view, help, whoareyou, unknown, exit;
        @SuppressWarnings("compatibility:8925072004066147044")
        private static final long serialVersionUID = 1L; 
    }
    
    /* структура для создания простой системы логирования
     * записываются все коды ошибок, и переопределяется вывод
     * в функции LogWrite() */
    private enum TypeLogObj {
        start, close, unknown, notimplemented, repairmethod, initsuccess, initfailed, dataFailed;
        @SuppressWarnings("compatibility:-5014413075966977321")
        private static final long serialVersionUID = 1L;
    }
    
    
    // первый уровень команд
    private void lifeCycle(){
        Scanner in = new Scanner(System.in);
        System.out.println(T.getName()+ ": write your command:");
        isCommand = false;
        do {
            System.out.print(T.getName()+ "# ");
            String type = String.valueOf(in.nextLine()).toLowerCase();
            setCommand(type);
        } while (!isCommand);
        if (isCommand){
            isCommand = !isCommand;
            switch (command) {
            case start: {
                    System.out.println("command start ->");
                    break;
                }
            case enter: {
                    initConsole(setInitThreadName());
                    break;
                }
            case init: {
                    InitObject();
                    break;
                } 
            case view: {
                    ViewObject();
                    break;
                }
            case help: {
                    help();
                    break;
                } 
            case whoareyou: {
                    System.out.println("command whoareyou ->");
                    System.out.println("  -> " + T.getName() + " ->  ");
                    break;
                    }
            case exit: {
                    System.out.println("exit ->");
                    exit = true;
                    T.interrupt();
                    break;
                }
            default: {
                    System.out.println("Unknown Command, try more!");
                    System.out.println("You can choose one of the next values:");
                    System.out.println(Arrays.toString(commandClass.values()));
                }
            }
        }
    }
    
    /*class ConcreteCreatorHuman extends Creator {
        @Override
        public UniqueObject factoryMethod() { return new Human(); }
    }*/

    
    public void LogWrite(TypeLogObj command, Object object) {
        try {
            switch (command) {
            case start:
                log.log(Level.INFO, "Element: " + object.getClass().getName() + " was Started."); break;
            case close:
                log.log(Level.INFO, "Element: " + object.getClass().getName() + " was closed."); break;
            case dataFailed:
                log.log(Level.WARNING, "Data from: " + object.getClass().getName() + " wrong. Please check the data validity."); break;
            case initsuccess:
                log.log(Level.FINE, "Object: " + object.getClass().getName() + ", was successfully initiated!"); break;  
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
            case notimplemented:
                log.log(Level.INFO, "Sorry, that function not implemented in Programm!"); break;
            case repairmethod:
                log.log(Level.INFO, "Sorry, that function in repair mode and disabled now!"); break;
            case initsuccess:
                log.log(Level.FINE, "Object was successfully initiated!"); break;    
            case initfailed:
                log.log(Level.WARNING, "Object initiation was canceled or failed."); break;
            default:
                log.log(Level.WARNING, "Unknown type command: " + command);
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Unknown command: " + command);
        }
    }

    // функция некой консоли(меню?) с коммандами   
    // второй уровень консоли
    public boolean initConsole(String ThreadName){
        String command = String.valueOf("");
        while (!command.equalsIgnoreCase("exit")){
            System.out.print("Write your command: ");
            in = new Scanner(System.in);
            command = in.nextLine();
            exit = false;
            if (command.equalsIgnoreCase("InitMainForm")){ 
                LogWrite(logCommand.notimplemented);
            }
            if (command.equalsIgnoreCase("init")){    
                InitObject();
                LogWrite(logCommand.initsuccess);
            }
            if (command.equalsIgnoreCase("view")){
                ViewObject();
            }
            if (command.equalsIgnoreCase("kill")){
                KillObj();
            }
            if (command.equalsIgnoreCase("viewLog")){
                System.out.println("shy probnem posmotret Logi");
                LogWrite(logCommand.notimplemented);
            }
            if (command.equalsIgnoreCase("help")){ 
                System.out.println("    list of available command:");
                System.out.println("init - initialize new object;");
                System.out.println("view - viewing initialized object;");
                System.out.println("kill - killing object by Id;");
                
                System.out.println("exit - out from the programm.");                
            }
        }
        return true;        
    }
    
    private String setInitThreadName(){
        Scanner in = new Scanner(System.in);
        System.out.print(" set object type (");
        for (TypeObj type : TypeObj.values()) {
            System.out.print(" " + type.toString() + " ");
        }
        System.out.println("):");
        System.out.print("Console# ");
        String ThreadName = String.valueOf(in.nextLine());
        System.out.println(ThreadName);
        return ThreadName;
    }
    
    private int InitObject(){
        System.out.print("init new object, ");
        main.getInitClass().initObject(setInitThreadName());
        objID = main.getInitClass().getObjID();
        System.out.println("objID new object = " + objID);
        return objID;        
    }
    
    private void ViewObject(){
        Scanner in = new Scanner(System.in);
        if (main.getInitClass().getClassMap().isEmpty()){
            System.out.println("No one Object is created!");
        }else { 
            System.out.print("Write Object Id you want to see (last Id is " + objID +  "): ");
            try {
                int localId = Integer.valueOf(in.nextLine());
                if (localId > objID) {
                    localId = lastIndexObj();
                    System.out.println("your Id too Much, try last of index: " + localId);
                }
                getObj(objID).viewParams();
            } catch (Exception e) {
                log.log(Level.SEVERE, "problem with viewing of object: " + e);
            }
        }
    }
    
    private void KillObj(){
        Scanner in = new Scanner(System.in);
        if (obj.isEmpty()){
            System.out.println("No one Object is created!");
        }else { 
            System.out.print("Write Object Id you want to kill (last Id is " + objID +  "): ");
            try {
                int localId = Integer.valueOf(in.nextLine());
                boolean choose = false;
                if (localId > lastIndexObj()) {
                    localId = obj.size() - 1;
                    System.out.println("your Id too Much, try last of index: " + localId);
                    System.out.print("Do you want to kill last Object? (y/n): ");
                    String chooseVal = String.valueOf(in.nextLine()).toLowerCase();
                    choose = false;
                    if (("y").equals(chooseVal) || ("yes").equals(chooseVal)) choose = true;
                    else if (("n").equals(chooseVal) || ("no").equals(chooseVal)) choose = false;
                }
                if (choose) {
                    System.out.println(objID + " " + localId);
                    killObj(localId);
                    objID = lastIndexObj();
                    System.out.println(objID + " " + localId);
                    System.out.println("norm?");
                } else {
                    System.out.print("Return to main console!");
                }
            } catch (Throwable e) {
                log.log(Level.SEVERE, "problem with killing of object: " + e);
            }
        }
    
    }
    
    private void OutcomeInterraction(){
        lifeCycle();
        if (isCommand){            
            if (commandClass.exit.equals(this.command))
                exit = true;             
        } 
    }
 
    public void help(){
        System.out.println("List of available command: start, enter, wait, init, view, help, whoareyou, unknown, exit;");
        System.out.println("    start - not finish yet;");
        System.out.println("    enter - enter to new console lvl;");
        System.out.println("    wait - send wait command;");
        System.out.println("    init - init new object;");
        System.out.println("    view - view objcet by (id);");
        System.out.println("    whoareyou - write name of Thread;");
        System.out.println("    exit - exit from console;");
    } 
    
    public Thread getTObj(){
        return T;
    }    
    public void setCommand(String str) {
        consoleCommand command;        
        try {
            command = consoleCommand.valueOf(str);
            isCommand = true;
        } catch (Exception e) {
            command = consoleCommand.unknown;
            isCommand = false;
            System.out.println("unknown command. Write 'help' for list of available command.");
        }
        this.command = command;        
    }
    public int getLastIdObj(){
        return objID;
    } 
    private AbstractTypeClass getObj(int objId){
        return main.getInitClass().getObject(objId);
    }
    private boolean killObj(int objID){
        getObj(objID).killObject();
        objID = main.getInitClass().getObjID();
        return true;
    }
    private int lastIndexObj(){
        return obj.size()-1;
    }

}
